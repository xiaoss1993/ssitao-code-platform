package com.ssitao.code.modular.meta.infrastructure.persistence;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.ssitao.code.modular.meta.application.service.DynamicEntityService;
import com.ssitao.code.modular.meta.domain.model.MetaEntity;
import com.ssitao.code.modular.meta.domain.model.MetaField;
import javassist.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 动态实体服务实现类
 * 根据 MetaEntity 和 MetaField 动态生成实体类
 *
 * @author ssitao-code
 */
@Slf4j
@Service
public class DynamicEntityServiceImpl implements DynamicEntityService {

    private final JdbcTemplate jdbcTemplate;

    /**
     * 缓存动态生成的实体类
     */
    private final Map<String, Class<?>> entityClassCache = new ConcurrentHashMap<>();

    /**
     * 缓存动态生成的Mapper类
     */
    private final Map<String, Class<?>> mapperClassCache = new ConcurrentHashMap<>();

    /**
     * 缓存表名与实体编码的映射
     */
    private final Map<String, String> tableNameToEntityCode = new ConcurrentHashMap<>();

    public DynamicEntityServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Class<?> createDynamicEntityClass(MetaEntity entity, List<MetaField> fields) {
        String entityCode = entity.getEntityCode();

        // 检查是否已存在
        if (entityClassCache.containsKey(entityCode)) {
            return entityClassCache.get(entityCode);
        }

        try {
            // 使用 Javassist 创建动态类
            ClassPool pool = ClassPool.getDefault();

            // 创建类
            String className = getPackageName(entity) + ".entity." + toPascalCase(entityCode);
            CtClass ctClass = pool.makeClass(className);

            // 注意：Lombok注解在运行时动态生成的类上不起作用，因为Lombok是编译时注解处理器
            // 动态生成的类需要手动实现getter/setter等方法

            // 添加字段
            if (CollUtil.isNotEmpty(fields)) {
                for (MetaField field : fields) {
                    addField(pool, ctClass, field);
                }
            }

            // 编译并加载类
            Class<?> clazz = ctClass.toClass();
            entityClassCache.put(entityCode, clazz);

            // 缓存表名映射
            if (StrUtil.isNotBlank(entity.getTableName())) {
                tableNameToEntityCode.put(entity.getTableName(), entityCode);
            }

            log.info("动态创建实体类[{}]成功", className);
            return clazz;

        } catch (CannotCompileException | NotFoundException e) {
            log.error("动态创建实体类[{}]失败", entityCode, e);
            throw new RuntimeException("动态创建实体类失败: " + e.getMessage(), e);
        }
    }

    @Override
    public Class<?> createDynamicMapperClass(MetaEntity entity, List<MetaField> fields) {
        String entityCode = entity.getEntityCode();

        // 检查是否已存在
        if (mapperClassCache.containsKey(entityCode)) {
            return mapperClassCache.get(entityCode);
        }

        try {
            // 使用 Javassist 创建动态 Mapper 接口
            ClassPool pool = ClassPool.getDefault();

            String mapperName = getPackageName(entity) + ".mapper." + toPascalCase(entityCode) + "Mapper";
            CtClass ctMapper = pool.makeInterface(mapperName);

            // 获取实体类
            Class<?> entityClass = createDynamicEntityClass(entity, fields);

            // 添加基本的 CRUD 方法
            addMapperMethods(pool, ctMapper, entityClass);

            // 编译并加载类
            Class<?> mapperClass = ctMapper.toClass();
            mapperClassCache.put(entityCode, mapperClass);

            log.info("动态创建Mapper接口[{}]成功", mapperName);
            return mapperClass;

        } catch (CannotCompileException | NotFoundException e) {
            log.error("动态创建Mapper接口[{}]失败", entityCode, e);
            throw new RuntimeException("动态创建Mapper接口失败: " + e.getMessage(), e);
        }
    }

    @Override
    public void registerDynamicEntity(String entityCode, String tableName, List<MetaField> fields) {
        // 创建实体类
        MetaEntity entity = MetaEntity.builder()
                .entityCode(entityCode)
                .tableName(tableName)
                .build();

        createDynamicEntityClass(entity, fields);

        // 创建Mapper类
        createDynamicMapperClass(entity, fields);

        log.info("注册动态实体[{}]成功", entityCode);
    }

    @Override
    public Class<?> getDynamicEntityClass(String entityCode) {
        return entityClassCache.get(entityCode);
    }

    @Override
    public Class<?> getDynamicMapperClass(String entityCode) {
        return mapperClassCache.get(entityCode);
    }

    @Override
    public void removeDynamicEntity(String entityCode) {
        entityClassCache.remove(entityCode);
        mapperClassCache.remove(entityCode);

        // 移除表名映射
        tableNameToEntityCode.entrySet().removeIf(entry -> entityCode.equals(entry.getValue()));

        log.info("移除动态实体[{}]成功", entityCode);
    }

    @Override
    public List<Map<String, Object>> executeDynamicSql(String entityCode, String sql, Object... params) {
        try {
            if (params != null && params.length > 0) {
                return jdbcTemplate.queryForList(sql, params);
            } else {
                return jdbcTemplate.queryForList(sql);
            }
        } catch (Exception e) {
            log.error("执行动态SQL失败: {}", sql, e);
            throw new RuntimeException("执行动态SQL失败: " + e.getMessage(), e);
        }
    }

    @Override
    public Object getDynamicEntityInstance(String entityCode) {
        Class<?> clazz = entityClassCache.get(entityCode);
        if (clazz == null) {
            throw new RuntimeException("动态实体类[" + entityCode + "]不存在");
        }
        return ReflectUtil.newInstance(clazz);
    }

    /**
     * 添加字段到动态类
     */
    private void addField(ClassPool pool, CtClass ctClass, MetaField field) throws NotFoundException, CannotCompileException {
        String fieldName = field.getFieldCode();
        String javaType = field.getJavaType();

        // 获取字段类型
        CtClass fieldType = getCtClass(pool, javaType);

        // 创建字段
        CtField ctField = new CtField(fieldType, fieldName, ctClass);

        // 设置访问修饰符
        ctField.setModifiers(Modifier.PRIVATE);

        ctClass.addField(ctField);

        // 添加 getter 方法
        addGetterMethod(ctClass, fieldName, fieldType);

        // 添加 setter 方法
        addSetterMethod(ctClass, fieldName, fieldType);
    }

    /**
     * 添加 getter 方法
     */
    private void addGetterMethod(CtClass ctClass, String fieldName, CtClass fieldType) throws CannotCompileException {
        String capitalizedName = capitalize(fieldName);
        String getterName = "get" + capitalizedName;

        // 布尔类型的 getter 可能是 isXxx
        if (fieldType.getName().equals("boolean") || fieldType.getName().equals("java.lang.Boolean")) {
            getterName = "is" + capitalizedName;
        }

        String getterCode = "public " + fieldType.getName() + " " + getterName + "() { return " + fieldName + "; }";
        ctClass.addMethod(CtMethod.make(getterCode, ctClass));
    }

    /**
     * 添加 setter 方法
     */
    private void addSetterMethod(CtClass ctClass, String fieldName, CtClass fieldType) throws CannotCompileException {
        String capitalizedName = capitalize(fieldName);
        String setterName = "set" + capitalizedName;

        String setterCode = "public void " + setterName + "(" + fieldType.getName() + " " + fieldName + ") { this." + fieldName + " = " + fieldName + "; }";
        ctClass.addMethod(CtMethod.make(setterCode, ctClass));
    }

    /**
     * 首字母大写
     */
    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 添加 Mapper 方法
     */
    private void addMapperMethods(ClassPool pool, CtClass ctMapper, Class<?> entityClass) throws NotFoundException, CannotCompileException {
        String entityName = entityClass.getSimpleName();

        // 添加 insert 方法
        String insertMethod = "int insert(" + entityName + " entity);";
        ctMapper.addMethod(CtMethod.make(insertMethod, ctMapper));

        // 添加 update 方法
        String updateMethod = "int update(" + entityName + " entity);";
        ctMapper.addMethod(CtMethod.make(updateMethod, ctMapper));

        // 添加 deleteById 方法
        String deleteByIdMethod = "int deleteById(Object id);";
        ctMapper.addMethod(CtMethod.make(deleteByIdMethod, ctMapper));

        // 添加 selectById 方法
        String selectByIdMethod = entityName + " selectById(Object id);";
        ctMapper.addMethod(CtMethod.make(selectByIdMethod, ctMapper));

        // 添加 selectAll 方法
        String selectAllMethod = "java.util.List<" + entityName + "> selectAll();";
        ctMapper.addMethod(CtMethod.make(selectAllMethod, ctMapper));

        // 添加 selectList 方法（带条件）
        String selectListMethod = "java.util.List<" + entityName + "> selectList(java.util.Map<String, Object> params);";
        ctMapper.addMethod(CtMethod.make(selectListMethod, ctMapper));
    }

    /**
     * 获取 CtClass
     */
    private CtClass getCtClass(ClassPool pool, String javaType) throws NotFoundException {
        if (javaType == null) {
            return pool.get("java.lang.String");
        }

        switch (javaType) {
            case "String":
                return pool.get("java.lang.String");
            case "Integer":
            case "int":
                return pool.get("java.lang.Integer");
            case "Long":
            case "long":
                return pool.get("java.lang.Long");
            case "Double":
            case "double":
                return pool.get("java.lang.Double");
            case "Float":
            case "float":
                return pool.get("java.lang.Float");
            case "Boolean":
            case "boolean":
                return pool.get("java.lang.Boolean");
            case "BigDecimal":
                return pool.get("java.math.BigDecimal");
            case "LocalDate":
                return pool.get("java.time.LocalDate");
            case "LocalDateTime":
                return pool.get("java.time.LocalDateTime");
            case "LocalTime":
                return pool.get("java.time.LocalTime");
            case "Date":
                return pool.get("java.util.Date");
            case "List":
                return pool.get("java.util.List");
            case "Map":
                return pool.get("java.util.Map");
            default:
                return pool.get("java.lang.Object");
        }
    }

    /**
     * 获取包名
     */
    private String getPackageName(MetaEntity entity) {
        if (StrUtil.isNotBlank(entity.getPackageName())) {
            return entity.getPackageName();
        }
        return "com.ssitao.code.dynamic";
    }

    /**
     * 驼峰命名转换（首字母大写）
     */
    private String toPascalCase(String str) {
        if (StrUtil.isBlank(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        boolean capitalizeNext = true;
        for (char c : str.toCharArray()) {
            if (c == '_' || c == '-') {
                capitalizeNext = true;
            } else if (capitalizeNext) {
                sb.append(Character.toUpperCase(c));
                capitalizeNext = false;
            } else {
                sb.append(Character.toLowerCase(c));
            }
        }
        return sb.toString();
    }

}
