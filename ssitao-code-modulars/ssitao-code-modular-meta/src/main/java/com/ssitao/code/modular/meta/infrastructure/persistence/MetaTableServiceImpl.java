package com.ssitao.code.modular.meta.infrastructure.persistence;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.ssitao.code.modular.meta.application.service.MetaTableService;
import com.ssitao.code.modular.meta.domain.model.MetaEntity;
import com.ssitao.code.modular.meta.domain.model.MetaField;
import com.ssitao.code.modular.meta.domain.repository.MetaTableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 表服务实现类
 * 负责动态表的创建、修改、删除和字段同步
 *
 * @author ssitao-code
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MetaTableServiceImpl implements MetaTableService {

    private final MetaTableRepository metaTableRepository;
    private final JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createTable(MetaEntity entity, List<MetaField> fields) {
        String tableName = entity.getTableName();

        // 检查表是否已存在
        if (checkTableExists(tableName)) {
            throw new RuntimeException("表[" + tableName + "]已存在");
        }

        // 生成建表DDL
        String ddl = generateCreateTableDDL(entity, fields);
        log.info("创建表[{}]的DDL: {}", tableName, ddl);

        // 执行建表语句
        jdbcTemplate.execute(ddl);

        log.info("表[{}]创建成功", tableName);
        return tableName;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void alterTable(MetaEntity entity, List<MetaField> newFields, List<MetaField> modifiedFields) {
        String tableName = entity.getTableName();

        // 检查表是否存在
        if (!checkTableExists(tableName)) {
            throw new RuntimeException("表[" + tableName + "]不存在");
        }

        // 添加新字段
        if (CollUtil.isNotEmpty(newFields)) {
            for (MetaField field : newFields) {
                String alterDDL = generateAddColumnDDL(field);
                log.info("表[{}]添加字段[{}]的DDL: {}", tableName, field.getFieldCode(), alterDDL);
                jdbcTemplate.execute(alterDDL);
            }
        }

        // 修改已有字段
        if (CollUtil.isNotEmpty(modifiedFields)) {
            for (MetaField field : modifiedFields) {
                String alterDDL = generateModifyColumnDDL(field);
                log.info("表[{}]修改字段[{}]的DDL: {}", tableName, field.getFieldCode(), alterDDL);
                jdbcTemplate.execute(alterDDL);
            }
        }

        log.info("表[{}]修改成功", tableName);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void dropTable(String tableName) {
        // 检查表是否存在
        if (!checkTableExists(tableName)) {
            log.warn("表[{}]不存在，无需删除", tableName);
            return;
        }

        String ddl = "DROP TABLE IF EXISTS `" + tableName + "`";
        log.info("删除表[{}]的DDL: {}", tableName, ddl);

        jdbcTemplate.execute(ddl);

        log.info("表[{}]删除成功", tableName);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void syncFields(String entityId) {
        // 获取实体定义
        Optional<MetaEntity> entityOpt = metaTableRepository.findEntityById(entityId, null);
        if (!entityOpt.isPresent()) {
            throw new RuntimeException("实体[" + entityId + "]不存在");
        }

        MetaEntity entity = entityOpt.get();

        // 获取字段定义
        List<MetaField> fields = metaTableRepository.findFieldsByEntityId(entityId, entity.getTenantId());

        // 检查表是否存在
        if (!checkTableExists(entity.getTableName())) {
            // 表不存在，创建表
            createTable(entity, fields);
            return;
        }

        // 获取数据库中现有的字段
        List<String> existingColumns = getExistingColumns(entity.getTableName());

        // 分离新增字段和修改字段
        List<MetaField> newFields = fields.stream()
                .filter(f -> !existingColumns.contains(f.getFieldCode()))
                .collect(Collectors.toList());

        List<MetaField> modifiedFields = fields.stream()
                .filter(f -> existingColumns.contains(f.getFieldCode()))
                .collect(Collectors.toList());

        // 执行表结构修改
        alterTable(entity, newFields, modifiedFields);
    }

    @Override
    public boolean checkTableExists(String tableName) {
        String sql = "SELECT COUNT(*) FROM information_schema.TABLES WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, tableName);
        return count != null && count > 0;
    }

    @Override
    public String getTableDDL(MetaEntity entity, List<MetaField> fields) {
        return generateCreateTableDDL(entity, fields);
    }

    /**
     * 生成建表DDL
     */
    private String generateCreateTableDDL(MetaEntity entity, List<MetaField> fields) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE `").append(entity.getTableName()).append("` (\n");

        // 生成字段定义
        for (int i = 0; i < fields.size(); i++) {
            MetaField field = fields.get(i);
            sb.append("  `").append(field.getFieldCode()).append("` ");
            sb.append(getDbTypeDefinition(field));

            // 是否必填
            if (Boolean.TRUE.equals(field.getRequired())) {
                sb.append(" NOT NULL");
            } else {
                sb.append(" NULL");
            }

            // 默认值
            if (StrUtil.isNotBlank(field.getDefaultValue())) {
                sb.append(" DEFAULT ").append(field.getDefaultValue());
            }

            // 注释
            if (StrUtil.isNotBlank(field.getFieldName())) {
                sb.append(" COMMENT '").append(field.getFieldName()).append("'");
            }

            if (i < fields.size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }

        sb.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4");

        // 表注释
        if (StrUtil.isNotBlank(entity.getEntityName())) {
            sb.append(" COMMENT='").append(entity.getEntityName()).append("'");
        }

        return sb.toString();
    }

    /**
     * 生成添加字段DDL
     */
    private String generateAddColumnDDL(MetaField field) {
        StringBuilder sb = new StringBuilder();
        sb.append("ALTER TABLE ADD COLUMN `").append(field.getFieldCode()).append("` ");
        sb.append(getDbTypeDefinition(field));

        if (Boolean.TRUE.equals(field.getRequired())) {
            sb.append(" NOT NULL");
        } else {
            sb.append(" NULL");
        }

        if (StrUtil.isNotBlank(field.getDefaultValue())) {
            sb.append(" DEFAULT ").append(field.getDefaultValue());
        }

        if (StrUtil.isNotBlank(field.getFieldName())) {
            sb.append(" COMMENT '").append(field.getFieldName()).append("'");
        }

        return sb.toString();
    }

    /**
     * 生成修改字段DDL
     */
    private String generateModifyColumnDDL(MetaField field) {
        StringBuilder sb = new StringBuilder();
        sb.append("ALTER TABLE MODIFY COLUMN `").append(field.getFieldCode()).append("` ");
        sb.append(getDbTypeDefinition(field));

        if (Boolean.TRUE.equals(field.getRequired())) {
            sb.append(" NOT NULL");
        } else {
            sb.append(" NULL");
        }

        if (StrUtil.isNotBlank(field.getDefaultValue())) {
            sb.append(" DEFAULT ").append(field.getDefaultValue());
        }

        if (StrUtil.isNotBlank(field.getFieldName())) {
            sb.append(" COMMENT '").append(field.getFieldName()).append("'");
        }

        return sb.toString();
    }

    /**
     * 获取数据库类型定义
     */
    private String getDbTypeDefinition(MetaField field) {
        String dbType = field.getDbType();
        Integer length = field.getLength();
        Integer decimalLength = field.getDecimalLength();

        if (StrUtil.isBlank(dbType)) {
            // 根据Java类型推断数据库类型
            dbType = inferDbType(field.getJavaType());
        }

        switch (dbType.toLowerCase()) {
            case "varchar":
                return "varchar(" + (length != null && length > 0 ? length : 255) + ")";
            case "char":
                return "char(" + (length != null && length > 0 ? length : 1) + ")";
            case "decimal":
            case "numeric":
                return "decimal(" + (length != null && length > 0 ? length : 18)
                        + "," + (decimalLength != null && decimalLength > 0 ? decimalLength : 2) + ")";
            case "text":
                return "text";
            case "longtext":
                return "longtext";
            case "int":
            case "integer":
                return "int(11)";
            case "bigint":
                return "bigint(20)";
            case "smallint":
                return "smallint(6)";
            case "tinyint":
                return "tinyint(4)";
            case "float":
                return "float";
            case "double":
                return "double";
            case "date":
                return "date";
            case "datetime":
                return "datetime";
            case "timestamp":
                return "timestamp";
            case "time":
                return "time";
            case "boolean":
            case "bool":
                return "tinyint(1)";
            case "json":
                return "json";
            default:
                return "varchar(255)";
        }
    }

    /**
     * 根据Java类型推断数据库类型
     */
    private String inferDbType(String javaType) {
        if (javaType == null) {
            return "varchar";
        }
        switch (javaType) {
            case "String":
                return "varchar";
            case "Integer":
            case "int":
                return "int";
            case "Long":
            case "long":
                return "bigint";
            case "BigDecimal":
                return "decimal";
            case "LocalDate":
                return "date";
            case "LocalDateTime":
                return "datetime";
            case "LocalTime":
                return "time";
            case "Boolean":
            case "boolean":
                return "tinyint";
            case "Double":
            case "double":
                return "double";
            case "Float":
            case "float":
                return "float";
            default:
                return "varchar";
        }
    }

    /**
     * 获取表中现有的字段列表
     */
    private List<String> getExistingColumns(String tableName) {
        String sql = "SELECT COLUMN_NAME FROM information_schema.COLUMNS WHERE TABLE_SCHEMA = DATABASE() AND TABLE_NAME = ?";
        return jdbcTemplate.queryForList(sql, String.class, tableName);
    }

}
