package com.ssitao.code.modular.meta.application.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ssitao.code.common.exception.BusinessException;
import com.ssitao.code.modular.meta.api.dto.MetaEntityDTO;
import com.ssitao.code.modular.meta.application.command.MetaEntityCreateCommand;
import com.ssitao.code.modular.meta.application.command.MetaEntityUpdateCommand;
import com.ssitao.code.modular.meta.application.service.MetaEntityAppService;
import com.ssitao.code.modular.meta.domain.model.MetaEntity;
import com.ssitao.code.modular.meta.domain.repository.MetaEntityRepository;
import com.ssitao.code.modular.meta.infrastructure.converter.MetaEntityConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 元数据实体配置应用服务实现
 *
 * @author ssitao-code
 */
@Service
public class MetaEntityAppServiceImpl implements MetaEntityAppService {

    @Resource
    private MetaEntityRepository metaEntityRepository;

    @Override
    @Transactional
    public String create(MetaEntityCreateCommand command, String tenantId) {
        if (checkExists(command.getEntityCode(), tenantId, null)) {
            throw new BusinessException("实体编码已存在");
        }

        MetaEntity entity = MetaEntityConverter.INSTANCE.toDomain(command);
        entity.setId(IdUtil.fastSimpleUUID());
        entity.setTenantId(tenantId);
        entity.setCreateTime(LocalDateTime.now());
        entity.setDeleted(false);

        return metaEntityRepository.save(entity);
    }

    @Override
    @Transactional
    public void update(MetaEntityUpdateCommand command, String tenantId) {
        MetaEntity existing = metaEntityRepository.findById(command.getId(), tenantId)
                .orElseThrow(() -> new BusinessException("实体不存在"));

        existing.setEntityCode(command.getEntityCode());
        existing.setEntityName(command.getEntityName());
        existing.setTableName(command.getTableName());
        existing.setEntityType(command.getEntityType());
        existing.setCategory(command.getCategory());
        existing.setDescription(command.getDescription());
        existing.setPackageName(command.getPackageName());
        existing.setModuleName(command.getModuleName());
        existing.setBusinessName(command.getBusinessName());
        existing.setTemplateType(command.getTemplateType());
        existing.setStatus(command.getStatus());
        existing.setUpdateTime(LocalDateTime.now());

        metaEntityRepository.update(existing);
    }

    @Override
    @Transactional
    public void delete(String id, String tenantId) {
        metaEntityRepository.delete(id, tenantId);
    }

    @Override
    public MetaEntityDTO getById(String id, String tenantId) {
        return metaEntityRepository.findById(id, tenantId)
                .map(entity -> {
                    // 转换为 DO 再转 DTO，因为 DTO 字段与 DO 一致
                    return MetaEntityConverter.INSTANCE.toDTO(MetaEntityConverter.INSTANCE.toDO(entity));
                })
                .orElse(null);
    }

    @Override
    public List<MetaEntityDTO> list(String tenantId) {
        return metaEntityRepository.findAll(tenantId).stream()
                .map(entity -> MetaEntityConverter.INSTANCE.toDTO(MetaEntityConverter.INSTANCE.toDO(entity)))
                .collect(Collectors.toList());
    }

    @Override
    public boolean checkExists(String entityCode, String tenantId, String excludeId) {
        return metaEntityRepository.existsByCode(entityCode, tenantId, excludeId);
    }
}
