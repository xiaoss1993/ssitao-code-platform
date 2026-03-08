package com.ssitao.code.modular.meta.infrastructure.repository;

import com.ssitao.code.modular.meta.dal.dataobject.MetaListDO;
import com.ssitao.code.modular.meta.dal.dataobject.MetaListColumnDO;
import com.ssitao.code.modular.meta.dal.dataobject.MetaListButtonDO;
import com.ssitao.code.modular.meta.dal.dataobject.MetaListQueryDO;
import com.ssitao.code.modular.meta.dal.mapper.MetaListMapper;
import com.ssitao.code.modular.meta.dal.mapper.MetaListColumnMapper;
import com.ssitao.code.modular.meta.dal.mapper.MetaListButtonMapper;
import com.ssitao.code.modular.meta.dal.mapper.MetaListQueryMapper;
import com.ssitao.code.modular.meta.domain.repository.MetaListRepository;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 元数据列表配置仓储实现
 *
 * @author ssitao-code
 */
@Repository
public class MetaListRepositoryImpl implements MetaListRepository {

    @Resource
    private MetaListMapper metaListMapper;

    @Resource
    private MetaListColumnMapper metaListColumnMapper;

    @Resource
    private MetaListButtonMapper metaListButtonMapper;

    @Resource
    private MetaListQueryMapper metaListQueryMapper;

    // ==================== MetaList 操作 ====================

    @Override
    public String saveList(MetaListDO list) {
        list.setCreateTime(LocalDateTime.now());
        list.setIsDeleted(0);
        list.setVersion(0);
        metaListMapper.insert(list);
        return list.getListId();
    }

    @Override
    public void updateList(MetaListDO list) {
        list.setModifyTime(LocalDateTime.now());
        metaListMapper.update(list);
    }

    @Override
    public void deleteListById(String listId, String tenantId) {
        MetaListDO list = new MetaListDO();
        list.setListId(listId);
        list.setIsDeleted(1);
        list.setModifyTime(LocalDateTime.now());
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaListDO::getListId, listId)
                .eq(MetaListDO::getTenantId, tenantId);
        metaListMapper.updateByQuery(list, wrapper);
    }

    @Override
    public MetaListDO findListById(String listId, String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaListDO::getListId, listId)
                .eq(MetaListDO::getTenantId, tenantId)
                .eq(MetaListDO::getIsDeleted, 0);
        return metaListMapper.selectOneByQuery(wrapper);
    }

    @Override
    public List<MetaListDO> findListsByEntityId(String entityId, String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaListDO::getEntityId, entityId)
                .eq(MetaListDO::getTenantId, tenantId);
        return metaListMapper.selectListByQuery(wrapper);
    }

    @Override
    public List<MetaListDO> findActiveListsByEntityId(String entityId, String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaListDO::getEntityId, entityId)
                .eq(MetaListDO::getTenantId, tenantId)
                .eq(MetaListDO::getIsDeleted, 0)
                .orderBy("create_time", true);
        return metaListMapper.selectListByQuery(wrapper);
    }

    @Override
    public List<MetaListDO> page(String keyword, int page, int limit, String sort, String order, String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq("tenant_id", tenantId)
                .eq("is_deleted", 0);

        // 处理排序
        String sortColumn = sort != null && !sort.isEmpty() ? sort : "create_time";
        boolean ascending = order == null || !"desc".equalsIgnoreCase(order);
        wrapper.orderBy(sortColumn, ascending);

        // 处理分页
        wrapper.offset((page - 1) * limit).limit(limit);

        return metaListMapper.selectListByQuery(wrapper);
    }

    @Override
    public boolean existsByListCode(String entityId, String listCode, String tenantId, String excludeId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaListDO::getEntityId, entityId)
                .eq(MetaListDO::getListCode, listCode)
                .eq(MetaListDO::getTenantId, tenantId)
                .eq(MetaListDO::getIsDeleted, 0);
        if (excludeId != null) {
            wrapper.ne(MetaListDO::getListId, excludeId);
        }
        return metaListMapper.selectCountByQuery(wrapper) > 0;
    }

    // ==================== MetaListColumn 操作 ====================

    @Override
    public String saveColumn(MetaListColumnDO column) {
        column.setCreateTime(LocalDateTime.now());
        column.setIsDeleted(0);
        column.setVersion(0);
        metaListColumnMapper.insert(column);
        return column.getColumnId();
    }

    @Override
    public List<String> batchSaveColumns(List<MetaListColumnDO> columns) {
        return columns.stream()
                .map(this::saveColumn)
                .collect(Collectors.toList());
    }

    @Override
    public void updateColumn(MetaListColumnDO column) {
        column.setModifyTime(LocalDateTime.now());
        metaListColumnMapper.update(column);
    }

    @Override
    public void deleteColumnById(String columnId, String tenantId) {
        MetaListColumnDO column = new MetaListColumnDO();
        column.setColumnId(columnId);
        column.setIsDeleted(1);
        column.setModifyTime(LocalDateTime.now());
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaListColumnDO::getColumnId, columnId)
                .eq(MetaListColumnDO::getTenantId, tenantId);
        metaListColumnMapper.updateByQuery(column, wrapper);
    }

    @Override
    public void deleteColumnsByListId(String listId, String tenantId) {
        MetaListColumnDO column = new MetaListColumnDO();
        column.setIsDeleted(1);
        column.setModifyTime(LocalDateTime.now());
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaListColumnDO::getListId, listId)
                .eq(MetaListColumnDO::getTenantId, tenantId);
        metaListColumnMapper.updateByQuery(column, wrapper);
    }

    @Override
    public MetaListColumnDO findColumnById(String columnId, String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaListColumnDO::getColumnId, columnId)
                .eq(MetaListColumnDO::getTenantId, tenantId)
                .eq(MetaListColumnDO::getIsDeleted, 0);
        return metaListColumnMapper.selectOneByQuery(wrapper);
    }

    @Override
    public List<MetaListColumnDO> findColumnsByListId(String listId, String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaListColumnDO::getListId, listId)
                .eq(MetaListColumnDO::getTenantId, tenantId);
        return metaListColumnMapper.selectListByQuery(wrapper);
    }

    @Override
    public List<MetaListColumnDO> findActiveColumnsByListId(String listId, String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaListColumnDO::getListId, listId)
                .eq(MetaListColumnDO::getTenantId, tenantId)
                .eq(MetaListColumnDO::getIsDeleted, 0)
                .orderBy("sort_order", true);
        return metaListColumnMapper.selectListByQuery(wrapper);
    }

    // ==================== MetaListButton 操作 ====================

    @Override
    public String saveButton(MetaListButtonDO button) {
        button.setCreateTime(LocalDateTime.now());
        button.setIsDeleted(0);
        button.setVersion(0);
        metaListButtonMapper.insert(button);
        return button.getButtonId();
    }

    @Override
    public List<String> batchSaveButtons(List<MetaListButtonDO> buttons) {
        return buttons.stream()
                .map(this::saveButton)
                .collect(Collectors.toList());
    }

    @Override
    public void updateButton(MetaListButtonDO button) {
        button.setModifyTime(LocalDateTime.now());
        metaListButtonMapper.update(button);
    }

    @Override
    public void deleteButtonById(String buttonId, String tenantId) {
        MetaListButtonDO button = new MetaListButtonDO();
        button.setButtonId(buttonId);
        button.setIsDeleted(1);
        button.setModifyTime(LocalDateTime.now());
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaListButtonDO::getButtonId, buttonId)
                .eq(MetaListButtonDO::getTenantId, tenantId);
        metaListButtonMapper.updateByQuery(button, wrapper);
    }

    @Override
    public void deleteButtonsByListId(String listId, String tenantId) {
        MetaListButtonDO button = new MetaListButtonDO();
        button.setIsDeleted(1);
        button.setModifyTime(LocalDateTime.now());
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaListButtonDO::getListId, listId)
                .eq(MetaListButtonDO::getTenantId, tenantId);
        metaListButtonMapper.updateByQuery(button, wrapper);
    }

    @Override
    public MetaListButtonDO findButtonById(String buttonId, String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaListButtonDO::getButtonId, buttonId)
                .eq(MetaListButtonDO::getTenantId, tenantId)
                .eq(MetaListButtonDO::getIsDeleted, 0);
        return metaListButtonMapper.selectOneByQuery(wrapper);
    }

    @Override
    public List<MetaListButtonDO> findButtonsByListId(String listId, String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaListButtonDO::getListId, listId)
                .eq(MetaListButtonDO::getTenantId, tenantId);
        return metaListButtonMapper.selectListByQuery(wrapper);
    }

    @Override
    public List<MetaListButtonDO> findActiveButtonsByListId(String listId, String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaListButtonDO::getListId, listId)
                .eq(MetaListButtonDO::getTenantId, tenantId)
                .eq(MetaListButtonDO::getIsDeleted, 0)
                .orderBy("sort_order", true);
        return metaListButtonMapper.selectListByQuery(wrapper);
    }

    // ==================== MetaListQuery 操作 ====================

    @Override
    public String saveQuery(MetaListQueryDO query) {
        query.setCreateTime(LocalDateTime.now());
        query.setIsDeleted(0);
        query.setVersion(0);
        metaListQueryMapper.insert(query);
        return query.getQueryId();
    }

    @Override
    public List<String> batchSaveQueries(List<MetaListQueryDO> queries) {
        return queries.stream()
                .map(this::saveQuery)
                .collect(Collectors.toList());
    }

    @Override
    public void updateQuery(MetaListQueryDO query) {
        query.setModifyTime(LocalDateTime.now());
        metaListQueryMapper.update(query);
    }

    @Override
    public void deleteQueryById(String queryId, String tenantId) {
        MetaListQueryDO query = new MetaListQueryDO();
        query.setQueryId(queryId);
        query.setIsDeleted(1);
        query.setModifyTime(LocalDateTime.now());
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaListQueryDO::getQueryId, queryId)
                .eq(MetaListQueryDO::getTenantId, tenantId);
        metaListQueryMapper.updateByQuery(query, wrapper);
    }

    @Override
    public void deleteQueriesByListId(String listId, String tenantId) {
        MetaListQueryDO query = new MetaListQueryDO();
        query.setIsDeleted(1);
        query.setModifyTime(LocalDateTime.now());
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaListQueryDO::getListId, listId)
                .eq(MetaListQueryDO::getTenantId, tenantId);
        metaListQueryMapper.updateByQuery(query, wrapper);
    }

    @Override
    public MetaListQueryDO findQueryById(String queryId, String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaListQueryDO::getQueryId, queryId)
                .eq(MetaListQueryDO::getTenantId, tenantId)
                .eq(MetaListQueryDO::getIsDeleted, 0);
        return metaListQueryMapper.selectOneByQuery(wrapper);
    }

    @Override
    public List<MetaListQueryDO> findQueriesByListId(String listId, String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaListQueryDO::getListId, listId)
                .eq(MetaListQueryDO::getTenantId, tenantId);
        return metaListQueryMapper.selectListByQuery(wrapper);
    }

    @Override
    public List<MetaListQueryDO> findActiveQueriesByListId(String listId, String tenantId) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaListQueryDO::getListId, listId)
                .eq(MetaListQueryDO::getTenantId, tenantId)
                .eq(MetaListQueryDO::getIsDeleted, 0)
                .orderBy("sort_order", true);
        return metaListQueryMapper.selectListByQuery(wrapper);
    }
}
