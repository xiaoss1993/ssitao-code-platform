package com.ssitao.code.modular.meta.application.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ssitao.code.common.exception.BusinessException;
import com.ssitao.code.modular.meta.api.dto.MetaColumnDTO;
import com.ssitao.code.modular.meta.application.command.MetaColumnBatchCreateCommand;
import com.ssitao.code.modular.meta.application.command.MetaColumnCreateCommand;
import com.ssitao.code.modular.meta.application.command.MetaColumnUpdateCommand;
import com.ssitao.code.modular.meta.application.service.MetaColumnAppService;
import com.ssitao.code.modular.meta.dal.dataobject.MetaColumnDO;
import com.ssitao.code.modular.meta.domain.repository.MetaColumnRepository;
import com.ssitao.code.modular.meta.infrastructure.converter.MetaColumnConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 元数据字段应用服务实现
 *
 * @author ssitao-code
 */
@Service
public class MetaColumnAppServiceImpl implements MetaColumnAppService {

    @Resource
    private MetaColumnRepository metaColumnRepository;

    @Override
    public String create(MetaColumnCreateCommand command, String tenantId) {
        if (checkExists(command.getTableId(), command.getColumnName(), tenantId, null)) {
            throw new BusinessException("字段名称已存在");
        }

        MetaColumnDO metaColumnDO = MetaColumnConverter.INSTANCE.toDO(command);
        metaColumnDO.setColumnId(IdUtil.fastSimpleUUID());
        metaColumnDO.setTenantId(tenantId);

        return metaColumnRepository.save(metaColumnDO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<String> batchCreate(MetaColumnBatchCreateCommand command, String tenantId) {
        List<MetaColumnDO> columns = command.getColumns().stream()
                .map(c -> {
                    MetaColumnDO column = MetaColumnConverter.INSTANCE.toDO(c);
                    column.setColumnId(IdUtil.fastSimpleUUID());
                    column.setTableId(command.getTableId());
                    column.setTenantId(tenantId);
                    return column;
                })
                .collect(Collectors.toList());

        for (MetaColumnDO column : columns) {
            if (checkExists(column.getTableId(), column.getColumnName(), tenantId, null)) {
                throw new BusinessException("字段名称已存在: " + column.getColumnName());
            }
        }

        return metaColumnRepository.batchSave(columns);
    }

    @Override
    public void update(MetaColumnUpdateCommand command, String tenantId) {
        MetaColumnDO existing = metaColumnRepository.findById(command.getColumnId(), tenantId);
        if (existing == null) {
            throw new BusinessException("字段不存在");
        }

        if (command.getColumnName() != null &&
                !command.getColumnName().equals(existing.getColumnName())) {
            if (checkExists(existing.getTableId(), command.getColumnName(), tenantId, command.getColumnId())) {
                throw new BusinessException("字段名称已存在");
            }
        }

        MetaColumnDO updateColumn = new MetaColumnDO();
        updateColumn.setColumnId(command.getColumnId());
        updateColumn.setColumnName(command.getColumnName());
        updateColumn.setColumnDesc(command.getColumnDesc());
        updateColumn.setColumnType(command.getColumnType());
        updateColumn.setJavaType(command.getJavaType());
        updateColumn.setJavaField(command.getJavaField());
        updateColumn.setIsPk(command.getIsPk());
        updateColumn.setIsIncrement(command.getIsIncrement());
        updateColumn.setIsRequired(command.getIsRequired());
        updateColumn.setIsQuery(command.getIsQuery());
        updateColumn.setQueryType(command.getQueryType());
        updateColumn.setIsDisplay(command.getIsDisplay());
        updateColumn.setIsList(command.getIsList());
        updateColumn.setIsForm(command.getIsForm());
        updateColumn.setFormType(command.getFormType());
        updateColumn.setDictType(command.getDictType());
        updateColumn.setDefaultValue(command.getDefaultValue());
        updateColumn.setColumnSort(command.getColumnSort());
        updateColumn.setRemark(command.getRemark());

        metaColumnRepository.update(updateColumn);
    }

    @Override
    public void delete(String columnId, String tenantId) {
        metaColumnRepository.deleteById(columnId, tenantId);
    }

    @Override
    public MetaColumnDTO getById(String columnId, String tenantId) {
        MetaColumnDO metaColumnDO = metaColumnRepository.findById(columnId, tenantId);
        if (metaColumnDO == null) {
            return null;
        }
        return MetaColumnConverter.INSTANCE.toDTO(metaColumnDO);
    }

    @Override
    public List<MetaColumnDTO> listByTableId(String tableId, String tenantId) {
        List<MetaColumnDO> columns = metaColumnRepository.findActiveByTableId(tableId, tenantId);
        return MetaColumnConverter.INSTANCE.toDTOList(columns);
    }

    @Override
    public boolean checkExists(String tableId, String columnName, String tenantId, String excludeId) {
        return metaColumnRepository.existsByColumnName(tableId, columnName, tenantId, excludeId);
    }
}
