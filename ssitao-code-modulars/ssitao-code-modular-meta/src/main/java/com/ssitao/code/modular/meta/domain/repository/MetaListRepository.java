package com.ssitao.code.modular.meta.domain.repository;

import com.ssitao.code.modular.meta.dal.dataobject.MetaListDO;
import com.ssitao.code.modular.meta.dal.dataobject.MetaListColumnDO;
import com.ssitao.code.modular.meta.dal.dataobject.MetaListButtonDO;
import com.ssitao.code.modular.meta.dal.dataobject.MetaListQueryDO;

import java.util.List;

/**
 * 元数据列表配置仓储接口
 *
 * @author ssitao-code
 */
public interface MetaListRepository {

    // ==================== MetaList 操作 ====================

    /**
     * 保存列表配置
     */
    String saveList(MetaListDO list);

    /**
     * 更新列表配置
     */
    void updateList(MetaListDO list);

    /**
     * 根据ID删除列表配置
     */
    void deleteListById(String listId, String tenantId);

    /**
     * 根据ID查询列表配置
     */
    MetaListDO findListById(String listId, String tenantId);

    /**
     * 根据实体ID查询列表配置列表
     */
    List<MetaListDO> findListsByEntityId(String entityId, String tenantId);

    /**
     * 根据实体ID查询所有未删除的列表配置列表
     */
    List<MetaListDO> findActiveListsByEntityId(String entityId, String tenantId);

    /**
     * 分页查询列表配置
     */
    List<MetaListDO> page(String keyword, int page, int limit, String sort, String order, String tenantId);

    /**
     * 检查列表编码是否存在
     */
    boolean existsByListCode(String entityId, String listCode, String tenantId, String excludeId);

    // ==================== MetaListColumn 操作 ====================

    /**
     * 保存列表列配置
     */
    String saveColumn(MetaListColumnDO column);

    /**
     * 批量保存列表列配置
     */
    List<String> batchSaveColumns(List<MetaListColumnDO> columns);

    /**
     * 更新列表列配置
     */
    void updateColumn(MetaListColumnDO column);

    /**
     * 根据ID删除列表列配置
     */
    void deleteColumnById(String columnId, String tenantId);

    /**
     * 根据列表ID删除所有列配置
     */
    void deleteColumnsByListId(String listId, String tenantId);

    /**
     * 根据ID查询列表列配置
     */
    MetaListColumnDO findColumnById(String columnId, String tenantId);

    /**
     * 根据列表ID查询列配置列表
     */
    List<MetaListColumnDO> findColumnsByListId(String listId, String tenantId);

    /**
     * 根据列表ID查询所有未删除的列配置列表
     */
    List<MetaListColumnDO> findActiveColumnsByListId(String listId, String tenantId);

    // ==================== MetaListButton 操作 ====================

    /**
     * 保存列表按钮配置
     */
    String saveButton(MetaListButtonDO button);

    /**
     * 批量保存列表按钮配置
     */
    List<String> batchSaveButtons(List<MetaListButtonDO> buttons);

    /**
     * 更新列表按钮配置
     */
    void updateButton(MetaListButtonDO button);

    /**
     * 根据ID删除列表按钮配置
     */
    void deleteButtonById(String buttonId, String tenantId);

    /**
     * 根据列表ID删除所有按钮配置
     */
    void deleteButtonsByListId(String listId, String tenantId);

    /**
     * 根据ID查询列表按钮配置
     */
    MetaListButtonDO findButtonById(String buttonId, String tenantId);

    /**
     * 根据列表ID查询按钮配置列表
     */
    List<MetaListButtonDO> findButtonsByListId(String listId, String tenantId);

    /**
     * 根据列表ID查询所有未删除的按钮配置列表
     */
    List<MetaListButtonDO> findActiveButtonsByListId(String listId, String tenantId);

    // ==================== MetaListQuery 操作 ====================

    /**
     * 保存列表查询条件配置
     */
    String saveQuery(MetaListQueryDO query);

    /**
     * 批量保存列表查询条件配置
     */
    List<String> batchSaveQueries(List<MetaListQueryDO> queries);

    /**
     * 更新列表查询条件配置
     */
    void updateQuery(MetaListQueryDO query);

    /**
     * 根据ID删除列表查询条件配置
     */
    void deleteQueryById(String queryId, String tenantId);

    /**
     * 根据列表ID删除所有查询条件配置
     */
    void deleteQueriesByListId(String listId, String tenantId);

    /**
     * 根据ID查询列表查询条件配置
     */
    MetaListQueryDO findQueryById(String queryId, String tenantId);

    /**
     * 根据列表ID查询查询条件配置列表
     */
    List<MetaListQueryDO> findQueriesByListId(String listId, String tenantId);

    /**
     * 根据列表ID查询所有未删除的查询条件配置列表
     */
    List<MetaListQueryDO> findActiveQueriesByListId(String listId, String tenantId);
}
