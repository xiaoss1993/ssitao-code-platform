package com.ssitao.code.modular.meta.domain.repository;

import com.ssitao.code.modular.meta.dal.dataobject.MetaFormDO;
import com.ssitao.code.modular.meta.dal.dataobject.MetaFormFieldDO;

import java.util.List;

/**
 * 元数据表单配置仓储接口
 *
 * @author ssitao-code
 */
public interface MetaFormRepository {

    // ==================== MetaForm 操作 ====================

    /**
     * 保存表单配置
     *
     * @param form 表单数据对象
     * @return 表单ID
     */
    String saveForm(MetaFormDO form);

    /**
     * 更新表单配置
     *
     * @param form 表单数据对象
     */
    void updateForm(MetaFormDO form);

    /**
     * 根据ID删除表单配置
     *
     * @param formId  表单ID
     * @param tenantId 租户ID
     */
    void deleteFormById(String formId, String tenantId);

    /**
     * 根据ID查询表单配置
     *
     * @param formId  表单ID
     * @param tenantId 租户ID
     * @return 表单数据对象
     */
    MetaFormDO findFormById(String formId, String tenantId);

    /**
     * 根据实体ID查询表单配置列表
     *
     * @param entityId 实体ID
     * @param tenantId 租户ID
     * @return 表单列表
     */
    List<MetaFormDO> findFormsByEntityId(String entityId, String tenantId);

    /**
     * 根据实体ID查询所有未删除的表单配置列表
     *
     * @param entityId 实体ID
     * @param tenantId 租户ID
     * @return 表单列表
     */
    List<MetaFormDO> findActiveFormsByEntityId(String entityId, String tenantId);

    /**
     * 检查表单编码是否存在
     *
     * @param entityId  实体ID
     * @param formCode 表单编码
     * @param tenantId 租户ID
     * @param excludeId 排除的表单ID
     * @return 是否存在
     */
    boolean existsByFormCode(String entityId, String formCode, String tenantId, String excludeId);

    // ==================== MetaFormField 操作 ====================

    /**
     * 保存表单字段配置
     *
     * @param field 字段数据对象
     * @return 字段ID
     */
    String saveField(MetaFormFieldDO field);

    /**
     * 批量保存表单字段配置
     *
     * @param fields 字段列表
     * @return 字段ID列表
     */
    List<String> batchSaveFields(List<MetaFormFieldDO> fields);

    /**
     * 更新表单字段配置
     *
     * @param field 字段数据对象
     */
    void updateField(MetaFormFieldDO field);

    /**
     * 根据ID删除表单字段配置
     *
     * @param fieldId  字段ID
     * @param tenantId 租户ID
     */
    void deleteFieldById(String fieldId, String tenantId);

    /**
     * 根据表单ID删除所有字段配置
     *
     * @param formId   表单ID
     * @param tenantId 租户ID
     */
    void deleteFieldsByFormId(String formId, String tenantId);

    /**
     * 根据ID查询表单字段配置
     *
     * @param fieldId  字段ID
     * @param tenantId 租户ID
     * @return 字段数据对象
     */
    MetaFormFieldDO findFieldById(String fieldId, String tenantId);

    /**
     * 根据表单ID查询字段配置列表
     *
     * @param formId   表单ID
     * @param tenantId 租户ID
     * @return 字段列表
     */
    List<MetaFormFieldDO> findFieldsByFormId(String formId, String tenantId);

    /**
     * 根据表单ID查询所有未删除的字段配置列表
     *
     * @param formId   表单ID
     * @param tenantId 租户ID
     * @return 字段列表
     */
    List<MetaFormFieldDO> findActiveFieldsByFormId(String formId, String tenantId);

    /**
     * 检查字段编码是否存在
     *
     * @param formId   表单ID
     * @param fieldCode 字段编码
     * @param tenantId 租户ID
     * @param excludeId 排除的字段ID
     * @return 是否存在
     */
    boolean existsFieldByCode(String formId, String fieldCode, String tenantId, String excludeId);
}
