package com.ssitao.code.modular.meta.domain.repository;

import com.ssitao.code.modular.meta.dal.dataobject.MetaColumnDO;

import java.util.List;

/**
 * 元数据表字段仓储接口
 *
 * @author ssitao-code
 */
public interface MetaColumnRepository {

    /**
     * 保存字段
     *
     * @param column 字段数据对象
     * @return 字段ID
     */
    String save(MetaColumnDO column);

    /**
     * 批量保存字段
     *
     * @param columns 字段列表
     * @return 字段ID列表
     */
    List<String> batchSave(List<MetaColumnDO> columns);

    /**
     * 更新字段
     *
     * @param column 字段数据对象
     */
    void update(MetaColumnDO column);

    /**
     * 根据ID删除字段
     *
     * @param columnId 字段ID
     * @param tenantId 租户ID
     */
    void deleteById(String columnId, String tenantId);

    /**
     * 根据表ID删除所有字段
     *
     * @param tableId  表ID
     * @param tenantId 租户ID
     */
    void deleteByTableId(String tableId, String tenantId);

    /**
     * 根据ID查询字段
     *
     * @param columnId 字段ID
     * @param tenantId 租户ID
     * @return 字段数据对象
     */
    MetaColumnDO findById(String columnId, String tenantId);

    /**
     * 根据表ID查询字段列表
     *
     * @param tableId  表ID
     * @param tenantId 租户ID
     * @return 字段列表
     */
    List<MetaColumnDO> findByTableId(String tableId, String tenantId);

    /**
     * 根据表ID查询所有未删除的字段列表
     *
     * @param tableId  表ID
     * @param tenantId 租户ID
     * @return 字段列表
     */
    List<MetaColumnDO> findActiveByTableId(String tableId, String tenantId);

    /**
     * 检查字段名是否存在
     *
     * @param tableId    表ID
     * @param columnName 字段名
     * @param tenantId   租户ID
     * @param excludeId  排除的字段ID
     * @return 是否存在
     */
    boolean existsByColumnName(String tableId, String columnName, String tenantId, String excludeId);
}
