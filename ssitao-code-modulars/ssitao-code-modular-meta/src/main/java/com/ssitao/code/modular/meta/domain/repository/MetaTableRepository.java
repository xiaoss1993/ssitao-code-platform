package com.ssitao.code.modular.meta.domain.repository;

import com.ssitao.code.modular.meta.domain.model.MetaEntity;
import com.ssitao.code.modular.meta.domain.model.MetaField;

import java.util.List;
import java.util.Optional;

/**
 * 表实体仓储接口
 * 负责 MetaEntity 和 MetaField 的持久化操作
 *
 * @author ssitao-code
 */
public interface MetaTableRepository {

    // ==================== MetaEntity 操作 ====================

    /**
     * 保存实体定义
     *
     * @param entity 实体定义聚合根
     * @return 实体ID
     */
    String saveEntity(MetaEntity entity);

    /**
     * 更新实体定义
     *
     * @param entity 实体定义聚合根
     */
    void updateEntity(MetaEntity entity);

    /**
     * 根据ID删除实体定义
     *
     * @param id       实体ID
     * @param tenantId 租户ID
     */
    void deleteEntityById(String id, String tenantId);

    /**
     * 根据ID查找实体定义
     *
     * @param id       实体ID
     * @param tenantId 租户ID
     * @return 实体定义聚合根
     */
    Optional<MetaEntity> findEntityById(String id, String tenantId);

    /**
     * 根据实体编码查找实体定义
     *
     * @param entityCode 实体编码
     * @param tenantId   租户ID
     * @return 实体定义聚合根
     */
    Optional<MetaEntity> findEntityByCode(String entityCode, String tenantId);

    /**
     * 根据表名查找实体定义
     *
     * @param tableName 表名
     * @param tenantId  租户ID
     * @return 实体定义聚合根
     */
    Optional<MetaEntity> findEntityByTableName(String tableName, String tenantId);

    /**
     * 查询所有实体定义
     *
     * @param tenantId 租户ID
     * @return 实体定义列表
     */
    List<MetaEntity> findAllEntities(String tenantId);

    /**
     * 检查实体编码是否存在
     *
     * @param entityCode 实体编码
     * @param tenantId   租户ID
     * @param excludeId  排除的实体ID
     * @return true-存在，false-不存在
     */
    boolean existsEntityByCode(String entityCode, String tenantId, String excludeId);

    /**
     * 检查表名是否存在
     *
     * @param tableName 表名
     * @param tenantId  租户ID
     * @param excludeId 排除的实体ID
     * @return true-存在，false-不存在
     */
    boolean existsEntityByTableName(String tableName, String tenantId, String excludeId);

    // ==================== MetaField 操作 ====================

    /**
     * 保存字段定义
     *
     * @param field 字段定义
     * @return 字段ID
     */
    String saveField(MetaField field);

    /**
     * 批量保存字段定义
     *
     * @param fields 字段定义列表
     * @return 字段ID列表
     */
    List<String> batchSaveFields(List<MetaField> fields);

    /**
     * 更新字段定义
     *
     * @param field 字段定义
     */
    void updateField(MetaField field);

    /**
     * 根据ID删除字段定义
     *
     * @param id       字段ID
     * @param tenantId 租户ID
     */
    void deleteFieldById(String id, String tenantId);

    /**
     * 根据实体ID删除所有字段定义
     *
     * @param entityId 实体ID
     * @param tenantId 租户ID
     */
    void deleteFieldsByEntityId(String entityId, String tenantId);

    /**
     * 根据ID查找字段定义
     *
     * @param id       字段ID
     * @param tenantId 租户ID
     * @return 字段定义
     */
    Optional<MetaField> findFieldById(String id, String tenantId);

    /**
     * 根据实体ID查找所有字段定义
     *
     * @param entityId 实体ID
     * @param tenantId 租户ID
     * @return 字段定义列表
     */
    List<MetaField> findFieldsByEntityId(String entityId, String tenantId);

    /**
     * 根据实体编码查找所有字段定义
     *
     * @param entityCode 实体编码
     * @param tenantId   租户ID
     * @return 字段定义列表
     */
    List<MetaField> findFieldsByEntityCode(String entityCode, String tenantId);

    /**
     * 检查字段编码是否存在
     *
     * @param entityId  实体ID
     * @param fieldCode 字段编码
     * @param tenantId  租户ID
     * @param excludeId 排除的字段ID
     * @return true-存在，false-不存在
     */
    boolean existsFieldByCode(String entityId, String fieldCode, String tenantId, String excludeId);

}
