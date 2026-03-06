package com.ssitao.code.modular.meta.application.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ssitao.code.common.exception.BusinessException;
import com.ssitao.code.modular.meta.api.dto.MetaFormDTO;
import com.ssitao.code.modular.meta.api.dto.MetaFormFieldDTO;
import com.ssitao.code.modular.meta.application.command.MetaFormCreateCommand;
import com.ssitao.code.modular.meta.application.command.MetaFormUpdateCommand;
import com.ssitao.code.modular.meta.application.service.MetaFormAppService;
import com.ssitao.code.modular.meta.dal.dataobject.MetaFormDO;
import com.ssitao.code.modular.meta.dal.dataobject.MetaFormFieldDO;
import com.ssitao.code.modular.meta.domain.repository.MetaFormRepository;
import com.ssitao.code.modular.meta.infrastructure.converter.MetaFormConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 元数据表单配置应用服务实现
 *
 * @author ssitao-code
 */
@Service
public class MetaFormAppServiceImpl implements MetaFormAppService {

    @Resource
    private MetaFormRepository metaFormRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String create(MetaFormCreateCommand command, String tenantId) {
        if (checkExists(command.getEntityId(), command.getFormCode(), tenantId, null)) {
            throw new BusinessException("表单编码已存在");
        }

        // 保存表单配置
        MetaFormDO metaFormDO = MetaFormConverter.INSTANCE.toDO(command);
        metaFormDO.setFormId(IdUtil.fastSimpleUUID());
        metaFormDO.setTenantId(tenantId);
        String formId = metaFormRepository.saveForm(metaFormDO);

        // 保存字段配置
        if (command.getFields() != null && !command.getFields().isEmpty()) {
            List<MetaFormFieldDO> fields = command.getFields().stream()
                    .map(f -> {
                        MetaFormFieldDO field = MetaFormConverter.INSTANCE.toFieldDO(f);
                        field.setFieldId(IdUtil.fastSimpleUUID());
                        field.setFormId(formId);
                        field.setTenantId(tenantId);
                        return field;
                    })
                    .collect(Collectors.toList());
            metaFormRepository.batchSaveFields(fields);
        }

        return formId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(MetaFormUpdateCommand command, String tenantId) {
        MetaFormDO existing = metaFormRepository.findFormById(command.getFormId(), tenantId);
        if (existing == null) {
            throw new BusinessException("表单配置不存在");
        }

        if (command.getFormCode() != null &&
                !command.getFormCode().equals(existing.getFormCode())) {
            if (checkExists(existing.getEntityId(), command.getFormCode(), tenantId, command.getFormId())) {
                throw new BusinessException("表单编码已存在");
            }
        }

        // 更新表单配置
        MetaFormDO updateForm = new MetaFormDO();
        updateForm.setFormId(command.getFormId());
        updateForm.setEntityId(command.getEntityId());
        updateForm.setFormCode(command.getFormCode());
        updateForm.setFormName(command.getFormName());
        updateForm.setFormType(command.getFormType());
        updateForm.setLayout(command.getLayout());
        updateForm.setWidth(command.getWidth());
        updateForm.setLabelPosition(command.getLabelPosition());
        updateForm.setLabelWidth(command.getLabelWidth());
        updateForm.setShowButtons(command.getShowButtons());
        updateForm.setShowResetButton(command.getShowResetButton());
        updateForm.setShowCancelButton(command.getShowCancelButton());
        updateForm.setSubmitButtonText(command.getSubmitButtonText());
        updateForm.setResetButtonText(command.getResetButtonText());
        updateForm.setCancelButtonText(command.getCancelButtonText());
        updateForm.setSubmitAction(command.getSubmitAction());
        updateForm.setRedirectPath(command.getRedirectPath());
        updateForm.setConfig(command.getConfig());
        updateForm.setRemark(command.getRemark());
        updateForm.setStatus(command.getStatus());

        metaFormRepository.updateForm(updateForm);

        // 如果有字段配置，更新字段
        if (command.getFields() != null) {
            // 删除原有字段
            metaFormRepository.deleteFieldsByFormId(command.getFormId(), tenantId);

            // 保存新字段
            List<MetaFormFieldDO> fields = command.getFields().stream()
                    .map(f -> {
                        MetaFormFieldDO field = MetaFormConverter.INSTANCE.toFieldDO(f);
                        field.setFieldId(IdUtil.fastSimpleUUID());
                        field.setFormId(command.getFormId());
                        field.setTenantId(tenantId);
                        return field;
                    })
                    .collect(Collectors.toList());
            metaFormRepository.batchSaveFields(fields);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String formId, String tenantId) {
        // 删除字段配置
        metaFormRepository.deleteFieldsByFormId(formId, tenantId);
        // 删除表单配置
        metaFormRepository.deleteFormById(formId, tenantId);
    }

    @Override
    public MetaFormDTO getById(String formId, String tenantId) {
        MetaFormDO metaFormDO = metaFormRepository.findFormById(formId, tenantId);
        if (metaFormDO == null) {
            return null;
        }

        MetaFormDTO dto = MetaFormConverter.INSTANCE.toDTO(metaFormDO);

        // 查询字段配置
        List<MetaFormFieldDO> fields = metaFormRepository.findActiveFieldsByFormId(formId, tenantId);
        if (fields != null && !fields.isEmpty()) {
            dto.setFields(MetaFormConverter.INSTANCE.toFieldDTOList(fields));
        }

        return dto;
    }

    @Override
    public List<MetaFormDTO> listByEntityId(String entityId, String tenantId) {
        List<MetaFormDO> forms = metaFormRepository.findActiveFormsByEntityId(entityId, tenantId);
        return MetaFormConverter.INSTANCE.toDTOList(forms);
    }

    @Override
    public boolean checkExists(String entityId, String formCode, String tenantId, String excludeId) {
        return metaFormRepository.existsByFormCode(entityId, formCode, tenantId, excludeId);
    }

    // ==================== 表单字段操作 ====================

    @Override
    public String createField(String formId, MetaFormFieldDTO command, String tenantId) {
        if (metaFormRepository.existsFieldByCode(formId, command.getFieldCode(), tenantId, null)) {
            throw new BusinessException("字段编码已存在");
        }

        MetaFormFieldDO field = new MetaFormFieldDO();
        field.setFieldId(IdUtil.fastSimpleUUID());
        field.setFormId(formId);
        field.setFieldCode(command.getFieldCode());
        field.setFieldName(command.getFieldName());
        field.setFieldType(command.getFieldType());
        field.setControlType(command.getControlType());
        field.setControlWidth(command.getControlWidth());
        field.setPlaceholder(command.getPlaceholder());
        field.setDefaultValue(command.getDefaultValue());
        field.setRequired(command.getRequired());
        field.setEditable(command.getEditable());
        field.setVisible(command.getVisible());
        field.setCopyable(command.getCopyable());
        field.setRules(command.getRules());
        field.setDictTypeCode(command.getDictTypeCode());
        field.setDictData(command.getDictData());
        field.setRemoteUrl(command.getRemoteUrl());
        field.setLabelField(command.getLabelField());
        field.setValueField(command.getValueField());
        field.setGroupName(command.getGroupName());
        field.setSortOrder(command.getSortOrder());
        field.setConfig(command.getConfig());
        field.setTenantId(tenantId);

        return metaFormRepository.saveField(field);
    }

    @Override
    public void updateField(MetaFormFieldDTO command, String tenantId) {
        MetaFormFieldDO existing = metaFormRepository.findFieldById(command.getFieldId(), tenantId);
        if (existing == null) {
            throw new BusinessException("字段不存在");
        }

        if (command.getFieldCode() != null &&
                !command.getFieldCode().equals(existing.getFieldCode())) {
            if (metaFormRepository.existsFieldByCode(existing.getFormId(), command.getFieldCode(), tenantId, command.getFieldId())) {
                throw new BusinessException("字段编码已存在");
            }
        }

        MetaFormFieldDO updateField = new MetaFormFieldDO();
        updateField.setFieldId(command.getFieldId());
        updateField.setFieldCode(command.getFieldCode());
        updateField.setFieldName(command.getFieldName());
        updateField.setFieldType(command.getFieldType());
        updateField.setControlType(command.getControlType());
        updateField.setControlWidth(command.getControlWidth());
        updateField.setPlaceholder(command.getPlaceholder());
        updateField.setDefaultValue(command.getDefaultValue());
        updateField.setRequired(command.getRequired());
        updateField.setEditable(command.getEditable());
        updateField.setVisible(command.getVisible());
        updateField.setCopyable(command.getCopyable());
        updateField.setRules(command.getRules());
        updateField.setDictTypeCode(command.getDictTypeCode());
        updateField.setDictData(command.getDictData());
        updateField.setRemoteUrl(command.getRemoteUrl());
        updateField.setLabelField(command.getLabelField());
        updateField.setValueField(command.getValueField());
        updateField.setGroupName(command.getGroupName());
        updateField.setSortOrder(command.getSortOrder());
        updateField.setConfig(command.getConfig());

        metaFormRepository.updateField(updateField);
    }

    @Override
    public void deleteField(String fieldId, String tenantId) {
        metaFormRepository.deleteFieldById(fieldId, tenantId);
    }

    @Override
    public MetaFormFieldDTO getFieldById(String fieldId, String tenantId) {
        MetaFormFieldDO field = metaFormRepository.findFieldById(fieldId, tenantId);
        if (field == null) {
            return null;
        }
        return MetaFormConverter.INSTANCE.toFieldDTO(field);
    }

    @Override
    public List<MetaFormFieldDTO> listFieldsByFormId(String formId, String tenantId) {
        List<MetaFormFieldDO> fields = metaFormRepository.findActiveFieldsByFormId(formId, tenantId);
        return MetaFormConverter.INSTANCE.toFieldDTOList(fields);
    }
}
