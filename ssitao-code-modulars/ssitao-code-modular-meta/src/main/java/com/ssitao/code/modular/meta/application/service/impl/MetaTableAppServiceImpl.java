package com.ssitao.code.modular.meta.application.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ssitao.code.common.exception.BusinessException;
import com.ssitao.code.modular.meta.api.dto.MetaTableDTO;
import com.ssitao.code.modular.meta.application.command.MetaTableCreateCommand;
import com.ssitao.code.modular.meta.application.command.MetaTableUpdateCommand;
import com.ssitao.code.modular.meta.application.service.MetaTableAppService;
import com.ssitao.code.modular.meta.dal.dataobject.MetaTableDO;
import com.ssitao.code.modular.meta.dal.mapper.MetaTableMapper;
import com.ssitao.code.modular.meta.infrastructure.converter.MetaTableConverter;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 元数据表配置应用服务实现
 *
 * @author ssitao-code
 */
@Service
public class MetaTableAppServiceImpl implements MetaTableAppService {

    @Resource
    private MetaTableMapper metaTableMapper;

    @Override
    public String create(MetaTableCreateCommand command, String tenantId) {
        if (checkExists(command.getTableName(), tenantId, null)) {
            throw new BusinessException("表名称已存在");
        }

        MetaTableDO metaTableDO = MetaTableConverter.INSTANCE.toDO(command);
        metaTableDO.setId(IdUtil.fastSimpleUUID());
        metaTableDO.setTenantId(tenantId);
        metaTableDO.setEnabled(1);
        metaTableDO.setGenerated(0);
        metaTableDO.setCreateTime(LocalDateTime.now());
        metaTableDO.setIsDeleted(0);
        metaTableDO.setVersion(0);

        metaTableMapper.insert(metaTableDO);
        return metaTableDO.getId();
    }

    @Override
    public void update(MetaTableUpdateCommand command, String tenantId) {
        MetaTableDO existing = findById(command.getId(), tenantId);
        if (existing == null) {
            throw new BusinessException("表不存在");
        }

        MetaTableDO updateTable = new MetaTableDO();
        updateTable.setId(command.getId());
        updateTable.setTableName(command.getTableName());
        updateTable.setTableDesc(command.getTableDesc());
        updateTable.setTableType(command.getTableType());
        updateTable.setPackageName(command.getPackageName());
        updateTable.setModuleName(command.getModuleName());
        updateTable.setClassName(command.getClassName());
        updateTable.setClassDesc(command.getClassDesc());
        updateTable.setEntityName(command.getEntityName());
        updateTable.setAuthor(command.getAuthor());
        updateTable.setEnabled(command.getEnabled());
        updateTable.setGenPath(command.getGenPath());
        updateTable.setRemark(command.getRemark());
        updateTable.setModifyTime(LocalDateTime.now());

        metaTableMapper.update(updateTable);
    }

    @Override
    public void delete(String id, String tenantId) {
        MetaTableDO table = new MetaTableDO();
        table.setId(id);
        table.setIsDeleted(1);
        table.setModifyTime(LocalDateTime.now());
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaTableDO::getId, id)
                .eq(MetaTableDO::getTenantId, tenantId);
        metaTableMapper.updateByQuery(table, wrapper);
    }

    @Override
    public MetaTableDTO getById(String id, String tenantId) {
        MetaTableDO metaTableDO = findById(id, tenantId);
        if (metaTableDO == null) {
            return null;
        }
        return MetaTableConverter.INSTANCE.toDTO(metaTableDO);
    }

    @Override
    public List<MetaTableDTO> list(String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaTableDO::getTenantId, tenantId)
                .eq(MetaTableDO::getIsDeleted, 0)
                .orderBy("create_time", true);
        List<MetaTableDO> tables = metaTableMapper.selectListByQuery(wrapper);
        return MetaTableConverter.INSTANCE.toDTOList(tables);
    }

    @Override
    public boolean checkExists(String tableName, String tenantId, String excludeId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaTableDO::getTableName, tableName)
                .eq(MetaTableDO::getTenantId, tenantId)
                .eq(MetaTableDO::getIsDeleted, 0);
        if (excludeId != null) {
            wrapper.ne(MetaTableDO::getId, excludeId);
        }
        return metaTableMapper.selectCountByQuery(wrapper) > 0;
    }

    private MetaTableDO findById(String id, String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaTableDO::getId, id)
                .eq(MetaTableDO::getTenantId, tenantId)
                .eq(MetaTableDO::getIsDeleted, 0);
        return metaTableMapper.selectOneByQuery(wrapper);
    }
}
