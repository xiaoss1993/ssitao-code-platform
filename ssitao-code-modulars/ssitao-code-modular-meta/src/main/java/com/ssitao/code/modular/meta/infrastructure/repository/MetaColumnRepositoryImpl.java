package com.ssitao.code.modular.meta.infrastructure.repository;

import com.ssitao.code.modular.meta.dal.dataobject.MetaColumnDO;
import com.ssitao.code.modular.meta.dal.mapper.MetaColumnMapper;
import com.ssitao.code.modular.meta.domain.repository.MetaColumnRepository;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 元数据表字段仓储实现
 *
 * @author ssitao-code
 */
@Repository
public class MetaColumnRepositoryImpl implements MetaColumnRepository {

    @Resource
    private MetaColumnMapper metaColumnMapper;

    @Override
    public String save(MetaColumnDO column) {
        column.setCreateTime(LocalDateTime.now());
        column.setIsDeleted(0);
        column.setVersion(0);
        metaColumnMapper.insert(column);
        return column.getColumnId();
    }

    @Override
    public List<String> batchSave(List<MetaColumnDO> columns) {
        return columns.stream()
                .map(this::save)
                .collect(Collectors.toList());
    }

    @Override
    public void update(MetaColumnDO column) {
        column.setModifyTime(LocalDateTime.now());
        metaColumnMapper.update(column);
    }

    @Override
    public void deleteById(String columnId, String tenantId) {
        MetaColumnDO column = new MetaColumnDO();
        column.setColumnId(columnId);
        column.setIsDeleted(1);
        column.setModifyTime(LocalDateTime.now());
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaColumnDO::getColumnId, columnId)
                .eq(MetaColumnDO::getTenantId, tenantId);
        metaColumnMapper.updateByQuery(column, wrapper);
    }

    @Override
    public void deleteByTableId(String tableId, String tenantId) {
        MetaColumnDO column = new MetaColumnDO();
        column.setIsDeleted(1);
        column.setModifyTime(LocalDateTime.now());
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaColumnDO::getTableId, tableId)
                .eq(MetaColumnDO::getTenantId, tenantId);
        metaColumnMapper.updateByQuery(column, wrapper);
    }

    @Override
    public MetaColumnDO findById(String columnId, String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaColumnDO::getColumnId, columnId)
                .eq(MetaColumnDO::getTenantId, tenantId)
                .eq(MetaColumnDO::getIsDeleted, 0);
        return metaColumnMapper.selectOneByQuery(wrapper);
    }

    @Override
    public List<MetaColumnDO> findByTableId(String tableId, String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaColumnDO::getTableId, tableId)
                .eq(MetaColumnDO::getTenantId, tenantId);
        return metaColumnMapper.selectListByQuery(wrapper);
    }

    @Override
    public List<MetaColumnDO> findActiveByTableId(String tableId, String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaColumnDO::getTableId, tableId)
                .eq(MetaColumnDO::getTenantId, tenantId)
                .eq(MetaColumnDO::getIsDeleted, 0)
                .orderByAsc(MetaColumnDO::getColumnSort);
        return metaColumnMapper.selectListByQuery(wrapper);
    }

    @Override
    public boolean existsByColumnName(String tableId, String columnName, String tenantId, String excludeId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaColumnDO::getTableId, tableId)
                .eq(MetaColumnDO::getColumnName, columnName)
                .eq(MetaColumnDO::getTenantId, tenantId)
                .eq(MetaColumnDO::getIsDeleted, 0);
        if (excludeId != null) {
            wrapper.ne(MetaColumnDO::getColumnId, excludeId);
        }
        return metaColumnMapper.selectCountByQuery(wrapper) > 0;
    }
}
