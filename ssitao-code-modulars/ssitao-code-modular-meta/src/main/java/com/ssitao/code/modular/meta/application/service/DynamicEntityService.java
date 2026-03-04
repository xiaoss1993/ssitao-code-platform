package com.ssitao.code.modular.meta.application.service;

import com.ssitao.code.modular.meta.domain.model.MetaEntity;
import com.ssitao.code.modular.meta.domain.model.MetaField;

import java.util.List;
import java.util.Map;

/**
 * 动态实体服务接口
 * 根据 MetaEntity 和 MetaField 动态生成实体类
 *
 * @author ssitao-code
 */
public interface DynamicEntityService {

    /**
     * 创建动态实体类
     * 根据实体定义和字段定义动态生成Java实体类
     *
     * @param entity 实体定义
     * @param fields 字段定义列表
     * @return 生成的实体类字节码或类信息
     */
    Class<?> createDynamicEntityClass(MetaEntity entity, List<MetaField> fields);

    /**
     * 创建动态Mapper接口
     * 根据实体定义创建对应的MyBatis Mapper接口
     *
     * @param entity 实体定义
     * @param fields 字段定义列表
     * @return 生成的Mapper接口类
     */
    Class<?> createDynamicMapperClass(MetaEntity entity, List<MetaField> fields);

    /**
     * 注册动态实体到MyBatis
     * 将动态生成的实体类和Mapper注册到MyBatis上下文中
     *
     * @param entityCode 实体编码
     * @param tableName  表名
     * @param fields     字段定义列表
     */
    void registerDynamicEntity(String entityCode, String tableName, List<MetaField> fields);

    /**
     * 获取动态实体类
     *
     * @param entityCode 实体编码
     * @return 实体类
     */
    Class<?> getDynamicEntityClass(String entityCode);

    /**
     * 获取动态Mapper
     *
     * @param entityCode 实体编码
     * @return Mapper接口
     */
    Class<?> getDynamicMapperClass(String entityCode);

    /**
     * 移除动态实体
     *
     * @param entityCode 实体编码
     */
    void removeDynamicEntity(String entityCode);

    /**
     * 执行动态SQL
     *
     * @param entityCode 实体编码
     * @param sql        SQL语句
     * @param params     参数
     * @return 查询结果
     */
    List<Map<String, Object>> executeDynamicSql(String entityCode, String sql, Object... params);

    /**
     * 获取动态实体实例
     *
     * @param entityCode 实体编码
     * @return 实体实例
     */
    Object getDynamicEntityInstance(String entityCode);

}
