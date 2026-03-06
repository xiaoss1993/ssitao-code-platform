package com.ssitao.code.modular.meta.application.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ssitao.code.common.exception.BusinessException;
import com.ssitao.code.modular.meta.api.dto.MetaEntityDTO;
import com.ssitao.code.modular.meta.application.command.MetaEntityCreateCommand;
import com.ssitao.code.modular.meta.application.command.MetaEntityUpdateCommand;
import com.ssitao.code.modular.meta.application.service.MetaEntityAppService;
import com.ssitao.code.modular.meta.dal.dataobject.MetaEntityDO;
import com.ssitao.code.modular.meta.dal.mapper.MetaEntityMapper;
import com.ssitao.code.modular.meta.infrastructure.converter.MetaEntityConverter;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 元数据实体配置应用服务实现
 *
 * @author ssitao-code
 */
@Service
public class MetaEntityAppServiceImpl implements MetaEntityAppService {

    @Resource
    private MetaEntityMapper metaEntityMapper;

    @Override
    public String create(MetaEntityCreateCommand command, String tenantId) {
        if (checkExists(command.getEntityCode(), tenantId, null)) {
            throw new BusinessException("实体编码已存在");
        }

        MetaEntityDO metaEntityDO = MetaEntityConverter.INSTANCE.toDO(command);
        metaEntityDO.setId(IdUtil.fastSimpleUUID());
        metaEntityDO.setTenantId(tenantId);
        metaEntityDO.setCreateTime(LocalDateTime.now());
        metaEntityDO.setIsDeleted(0);
        metaEntityDO.setVersion(0);

        metaEntityMapper.insert(metaEntityDO);
        return metaEntityDO.getId();
    }

    @Override
    public void update(MetaEntityUpdateCommand command, String tenantId) {
        MetaEntityDO existing = findById(command.getId(), tenantId);
        if (existing == null) {
            throw new BusinessException("实体不存在");
        }

        MetaEntityDO updateEntity = new MetaEntityDO();
        updateEntity.setId(command.getId());
        updateEntity.setEntityCode(command.getEntityCode());
        updateEntity.setEntityName(command.getEntityName());
        updateEntity.setTableName(command.getTableName());
        updateEntity.setEntityType(command.getEntityType());
        updateEntity.setCategory(command.getCategory());
        updateEntity.setDescription(command.getDescription());
        updateEntity.setPackageName(command.getPackageName());
        updateEntity.setModuleName(command.getModuleName());
        updateEntity.setBusinessName(command.getBusinessName());
        updateEntity.setTemplateType(command.getTemplateType());
        updateEntity.setStatus(command.getStatus());
        updateEntity.setModifyTime(LocalDateTime.now());

        metaEntityMapper.update(updateEntity);
    }

    @Override
    public void delete(String id, String tenantId) {
        MetaEntityDO entity = new MetaEntityDO();
        entity.setId(id);
        entity.setIsDeleted(1);
        entity.setModifyTime(LocalDateTime.now());
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaEntityDO::getId, id)
                .eq(MetaEntityDO::getTenantId, tenantId);
        metaEntityMapper.updateByQuery(entity, wrapper);
    }

    @Override
    public MetaEntityDTO getById(String id, String tenantId) {
        MetaEntityDO metaEntityDO = findById(id, tenantId);
        if (metaEntityDO == null) {
            return null;
        }
        return MetaEntityConverter.INSTANCE.toDTO(metaEntityDO);
    }

    @Override
    public List<MetaEntityDTO> list(String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaEntityDO::getTenantId, tenantId)
                .eq(MetaEntityDO::getIsDeleted, 0)
                .orderBy("create_time", true);
        List<MetaEntityDO> entities = metaEntityMapper.selectListByQuery(wrapper);
        return MetaEntityConverter.INSTANCE.toDTOList(entities);
    }

    @Override
    public boolean checkExists(String entityCode, String tenantId, String excludeId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaEntityDO::getEntityCode, entityCode)
                .eq(MetaEntityDO::getTenantId, tenantId)
                .eq(MetaEntityDO::getIsDeleted, 0);
        if (excludeId != null) {
            wrapper.ne(MetaEntityDO::getId, excludeId);
        }
        return metaEntityMapper.selectCountByQuery(wrapper) > 0;
    }

    private MetaEntityDO findById(String id, String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaEntityDO::getId, id)
                .eq(MetaEntityDO::getTenantId, tenantId)
                .eq(MetaEntityDO::getIsDeleted, 0);
        return metaEntityMapper.selectOneByQuery(wrapper);
    }
}
