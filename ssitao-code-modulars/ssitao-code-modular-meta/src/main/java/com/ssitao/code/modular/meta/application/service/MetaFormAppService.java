package com.ssitao.code.modular.meta.application.service;

import com.ssitao.code.modular.meta.api.dto.MetaFormDTO;
import com.ssitao.code.modular.meta.api.dto.MetaFormFieldDTO;
import com.ssitao.code.modular.meta.application.command.MetaFormCreateCommand;
import com.ssitao.code.modular.meta.application.command.MetaFormUpdateCommand;

import java.util.List;

/**
 * 元数据表单配置应用服务接口
 *
 * @author ssitao-code
 */
public interface MetaFormAppService {

    // ==================== 表单操作 ====================

    /**
     * 创建表单配置
     *
     * @param command  创建命令
     * @param tenantId 租户ID
     * @return 表单ID
     */
    String create(MetaFormCreateCommand command, String tenantId);

    /**
     * 更新表单配置
     *
     * @param command  更新命令
     * @param tenantId 租户ID
     */
    void update(MetaFormUpdateCommand command, String tenantId);

    /**
     * 删除表单配置
     *
     * @param formId   表单ID
     * @param tenantId 租户ID
     */
    void delete(String formId, String tenantId);

    /**
     * 根据ID获取表单配置
     *
     * @param formId   表单ID
     * @param tenantId 租户ID
     * @return 表单DTO
     */
    MetaFormDTO getById(String formId, String tenantId);

    /**
     * 根据实体ID获取表单配置列表
     *
     * @param entityId 实体ID
     * @param tenantId 租户ID
     * @return 表单DTO列表
     */
    List<MetaFormDTO> listByEntityId(String entityId, String tenantId);

    /**
     * 分页获取表单配置
     */
    List<MetaFormDTO> page(String keyword, int page, int limit, String sort, String order, String tenantId);

    /**
     * 检查表单编码是否存在
     *
     * @param entityId  实体ID
     * @param formCode  表单编码
     * @param tenantId 租户ID
     * @param excludeId 排除的表单ID
     * @return 是否存在
     */
    boolean checkExists(String entityId, String formCode, String tenantId, String excludeId);

    // ==================== 表单字段操作 ====================

    /**
     * 创建表单字段
     *
     * @param formId    表单ID
     * @param command   字段创建命令
     * @param tenantId  租户ID
     * @return 字段ID
     */
    String createField(String formId, MetaFormFieldDTO command, String tenantId);

    /**
     * 更新表单字段
     *
     * @param command   字段更新命令
     * @param tenantId  租户ID
     */
    void updateField(MetaFormFieldDTO command, String tenantId);

    /**
     * 删除表单字段
     *
     * @param fieldId  字段ID
     * @param tenantId 租户ID
     */
    void deleteField(String fieldId, String tenantId);

    /**
     * 根据ID获取表单字段
     *
     * @param fieldId  字段ID
     * @param tenantId 租户ID
     * @return 字段DTO
     */
    MetaFormFieldDTO getFieldById(String fieldId, String tenantId);

    /**
     * 根据表单ID获取字段列表
     *
     * @param formId   表单ID
     * @param tenantId 租户ID
     * @return 字段DTO列表
     */
    List<MetaFormFieldDTO> listFieldsByFormId(String formId, String tenantId);
}
