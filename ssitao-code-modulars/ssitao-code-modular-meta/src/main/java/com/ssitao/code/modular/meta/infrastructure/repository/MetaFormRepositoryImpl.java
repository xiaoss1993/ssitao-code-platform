package com.ssitao.code.modular.meta.infrastructure.repository;

import com.ssitao.code.modular.meta.dal.dataobject.MetaFormDO;
import com.ssitao.code.modular.meta.dal.dataobject.MetaFormFieldDO;
import com.ssitao.code.modular.meta.dal.mapper.MetaFormMapper;
import com.ssitao.code.modular.meta.dal.mapper.MetaFormFieldMapper;
import com.ssitao.code.modular.meta.domain.repository.MetaFormRepository;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 元数据表单配置仓储实现
 *
 * @author ssitao-code
 */
@Repository
public class MetaFormRepositoryImpl implements MetaFormRepository {

    @Resource
    private MetaFormMapper metaFormMapper;

    @Resource
    private MetaFormFieldMapper metaFormFieldMapper;

    // ==================== MetaForm 操作 ====================

    @Override
    public String saveForm(MetaFormDO form) {
        form.setCreateTime(LocalDateTime.now());
        form.setIsDeleted(0);
        form.setVersion(0);
        metaFormMapper.insert(form);
        return form.getFormId();
    }

    @Override
    public void updateForm(MetaFormDO form) {
        form.setModifyTime(LocalDateTime.now());
        metaFormMapper.update(form);
    }

    @Override
    public void deleteFormById(String formId, String tenantId) {
        MetaFormDO form = new MetaFormDO();
        form.setFormId(formId);
        form.setIsDeleted(1);
        form.setModifyTime(LocalDateTime.now());
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaFormDO::getFormId, formId)
                .eq(MetaFormDO::getTenantId, tenantId);
        metaFormMapper.updateByQuery(form, wrapper);
    }

    @Override
    public MetaFormDO findFormById(String formId, String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaFormDO::getFormId, formId)
                .eq(MetaFormDO::getTenantId, tenantId)
                .eq(MetaFormDO::getIsDeleted, 0);
        return metaFormMapper.selectOneByQuery(wrapper);
    }

    @Override
    public List<MetaFormDO> findFormsByEntityId(String entityId, String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaFormDO::getEntityId, entityId)
                .eq(MetaFormDO::getTenantId, tenantId);
        return metaFormMapper.selectListByQuery(wrapper);
    }

    @Override
    public List<MetaFormDO> findActiveFormsByEntityId(String entityId, String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaFormDO::getEntityId, entityId)
                .eq(MetaFormDO::getTenantId, tenantId)
                .eq(MetaFormDO::getIsDeleted, 0)
                .orderBy("create_time", true);
        return metaFormMapper.selectListByQuery(wrapper);
    }

    @Override
    public boolean existsByFormCode(String entityId, String formCode, String tenantId, String excludeId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaFormDO::getEntityId, entityId)
                .eq(MetaFormDO::getFormCode, formCode)
                .eq(MetaFormDO::getTenantId, tenantId)
                .eq(MetaFormDO::getIsDeleted, 0);
        if (excludeId != null) {
            wrapper.ne(MetaFormDO::getFormId, excludeId);
        }
        return metaFormMapper.selectCountByQuery(wrapper) > 0;
    }

    // ==================== MetaFormField 操作 ====================

    @Override
    public String saveField(MetaFormFieldDO field) {
        field.setCreateTime(LocalDateTime.now());
        field.setIsDeleted(0);
        field.setVersion(0);
        metaFormFieldMapper.insert(field);
        return field.getFieldId();
    }

    @Override
    public List<String> batchSaveFields(List<MetaFormFieldDO> fields) {
        return fields.stream()
                .map(this::saveField)
                .collect(Collectors.toList());
    }

    @Override
    public void updateField(MetaFormFieldDO field) {
        field.setModifyTime(LocalDateTime.now());
        metaFormFieldMapper.update(field);
    }

    @Override
    public void deleteFieldById(String fieldId, String tenantId) {
        MetaFormFieldDO field = new MetaFormFieldDO();
        field.setFieldId(fieldId);
        field.setIsDeleted(1);
        field.setModifyTime(LocalDateTime.now());
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaFormFieldDO::getFieldId, fieldId)
                .eq(MetaFormFieldDO::getTenantId, tenantId);
        metaFormFieldMapper.updateByQuery(field, wrapper);
    }

    @Override
    public void deleteFieldsByFormId(String formId, String tenantId) {
        MetaFormFieldDO field = new MetaFormFieldDO();
        field.setIsDeleted(1);
        field.setModifyTime(LocalDateTime.now());
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaFormFieldDO::getFormId, formId)
                .eq(MetaFormFieldDO::getTenantId, tenantId);
        metaFormFieldMapper.updateByQuery(field, wrapper);
    }

    @Override
    public MetaFormFieldDO findFieldById(String fieldId, String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaFormFieldDO::getFieldId, fieldId)
                .eq(MetaFormFieldDO::getTenantId, tenantId)
                .eq(MetaFormFieldDO::getIsDeleted, 0);
        return metaFormFieldMapper.selectOneByQuery(wrapper);
    }

    @Override
    public List<MetaFormFieldDO> findFieldsByFormId(String formId, String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaFormFieldDO::getFormId, formId)
                .eq(MetaFormFieldDO::getTenantId, tenantId);
        return metaFormFieldMapper.selectListByQuery(wrapper);
    }

    @Override
    public List<MetaFormFieldDO> findActiveFieldsByFormId(String formId, String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaFormFieldDO::getFormId, formId)
                .eq(MetaFormFieldDO::getTenantId, tenantId)
                .eq(MetaFormFieldDO::getIsDeleted, 0)
                .orderBy("sort_order", true);
        return metaFormFieldMapper.selectListByQuery(wrapper);
    }

    @Override
    public boolean existsFieldByCode(String formId, String fieldCode, String tenantId, String excludeId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaFormFieldDO::getFormId, formId)
                .eq(MetaFormFieldDO::getFieldCode, fieldCode)
                .eq(MetaFormFieldDO::getTenantId, tenantId)
                .eq(MetaFormFieldDO::getIsDeleted, 0);
        if (excludeId != null) {
            wrapper.ne(MetaFormFieldDO::getFieldId, excludeId);
        }
        return metaFormFieldMapper.selectCountByQuery(wrapper) > 0;
    }
}
