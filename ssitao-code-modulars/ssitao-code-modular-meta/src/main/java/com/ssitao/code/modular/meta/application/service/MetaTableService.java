package com.ssitao.code.modular.meta.application.service;

import com.ssitao.code.modular.meta.domain.model.MetaEntity;
import com.ssitao.code.modular.meta.domain.model.MetaField;

import java.util.List;

/**
 * 表服务接口
 * 负责动态表的创建、修改、删除和字段同步
 *
 * @author ssitao-code
 */
public interface MetaTableService {

    /**
     * 创建表
     * 根据实体定义和字段定义创建数据库表
     *
     * @param entity 实体定义
     * @param fields 字段定义列表
     * @return 表名
     */
    String createTable(MetaEntity entity, List<MetaField> fields);

    /**
     * 修改表
     * 根据新增的字段和修改的字段更新数据库表结构
     *
     * @param entity         实体定义
     * @param newFields      新增的字段列表
     * @param modifiedFields 修改的字段列表
     */
    void alterTable(MetaEntity entity, List<MetaField> newFields, List<MetaField> modifiedFields);

    /**
     * 删除表
     * 删除数据库中的表
     *
     * @param tableName 表名
     */
    void dropTable(String tableName);

    /**
     * 同步字段
     * 根据实体定义同步数据库表字段
     *
     * @param entityId 实体ID
     */
    void syncFields(String entityId);

    /**
     * 检查表是否存在
     *
     * @param tableName 表名
     * @return true-存在，false-不存在
     */
    boolean checkTableExists(String tableName);

    /**
     * 获取表的DDL语句
     *
     * @param entity 实体定义
     * @param fields 字段定义列表
     * @return DDL语句
     */
    String getTableDDL(MetaEntity entity, List<MetaField> fields);

}
