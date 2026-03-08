package com.ssitao.code.modular.meta.application.service.impl;

import cn.hutool.core.util.IdUtil;
import com.ssitao.code.common.exception.BusinessException;
import com.ssitao.code.modular.meta.api.dto.MetaListDTO;
import com.ssitao.code.modular.meta.api.dto.MetaListColumnDTO;
import com.ssitao.code.modular.meta.api.dto.MetaListButtonDTO;
import com.ssitao.code.modular.meta.api.dto.MetaListQueryDTO;
import com.ssitao.code.modular.meta.application.command.MetaListCreateCommand;
import com.ssitao.code.modular.meta.application.command.MetaListUpdateCommand;
import com.ssitao.code.modular.meta.application.service.MetaListAppService;
import com.ssitao.code.modular.meta.dal.dataobject.MetaListDO;
import com.ssitao.code.modular.meta.dal.dataobject.MetaListColumnDO;
import com.ssitao.code.modular.meta.dal.dataobject.MetaListButtonDO;
import com.ssitao.code.modular.meta.dal.dataobject.MetaListQueryDO;
import com.ssitao.code.modular.meta.domain.repository.MetaListRepository;
import com.ssitao.code.modular.meta.infrastructure.converter.MetaListConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 元数据列表配置应用服务实现
 *
 * @author ssitao-code
 */
@Service
public class MetaListAppServiceImpl implements MetaListAppService {

    @Resource
    private MetaListRepository metaListRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String create(MetaListCreateCommand command, String tenantId) {
        if (checkExists(command.getEntityId(), command.getListCode(), tenantId, null)) {
            throw new BusinessException("列表编码已存在");
        }

        // 保存列表配置
        MetaListDO metaListDO = MetaListConverter.INSTANCE.toDO(command);
        metaListDO.setListId(IdUtil.fastSimpleUUID());
        metaListDO.setTenantId(tenantId);
        String listId = metaListRepository.saveList(metaListDO);

        // 保存列配置
        if (command.getColumns() != null && !command.getColumns().isEmpty()) {
            List<MetaListColumnDO> columns = command.getColumns().stream()
                    .map(c -> {
                        MetaListColumnDO column = MetaListConverter.INSTANCE.toColumnDO(c);
                        column.setColumnId(IdUtil.fastSimpleUUID());
                        column.setListId(listId);
                        column.setTenantId(tenantId);
                        return column;
                    })
                    .collect(Collectors.toList());
            metaListRepository.batchSaveColumns(columns);
        }

        // 保存按钮配置
        if (command.getButtons() != null && !command.getButtons().isEmpty()) {
            List<MetaListButtonDO> buttons = command.getButtons().stream()
                    .map(b -> {
                        MetaListButtonDO button = MetaListConverter.INSTANCE.toButtonDO(b);
                        button.setButtonId(IdUtil.fastSimpleUUID());
                        button.setListId(listId);
                        button.setTenantId(tenantId);
                        return button;
                    })
                    .collect(Collectors.toList());
            metaListRepository.batchSaveButtons(buttons);
        }

        // 保存查询条件配置
        if (command.getQueries() != null && !command.getQueries().isEmpty()) {
            List<MetaListQueryDO> queries = command.getQueries().stream()
                    .map(q -> {
                        MetaListQueryDO query = MetaListConverter.INSTANCE.toQueryDO(q);
                        query.setQueryId(IdUtil.fastSimpleUUID());
                        query.setListId(listId);
                        query.setTenantId(tenantId);
                        return query;
                    })
                    .collect(Collectors.toList());
            metaListRepository.batchSaveQueries(queries);
        }

        return listId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(MetaListUpdateCommand command, String tenantId) {
        MetaListDO existing = metaListRepository.findListById(command.getListId(), tenantId);
        if (existing == null) {
            throw new BusinessException("列表配置不存在");
        }

        if (command.getListCode() != null &&
                !command.getListCode().equals(existing.getListCode())) {
            if (checkExists(existing.getEntityId(), command.getListCode(), tenantId, command.getListId())) {
                throw new BusinessException("列表编码已存在");
            }
        }

        // 更新列表配置
        MetaListDO updateList = new MetaListDO();
        updateList.setListId(command.getListId());
        updateList.setEntityId(command.getEntityId());
        updateList.setListCode(command.getListCode());
        updateList.setListName(command.getListName());
        updateList.setListType(command.getListType());
        updateList.setRowStyle(command.getRowStyle());
        updateList.setStripe(command.getStripe());
        updateList.setBorder(command.getBorder());
        updateList.setShowHeader(command.getShowHeader());
        updateList.setHighlightCurrentRow(command.getHighlightCurrentRow());
        updateList.setSortMode(command.getSortMode());
        updateList.setDefaultSortField(command.getDefaultSortField());
        updateList.setDefaultSortOrder(command.getDefaultSortOrder());
        updateList.setDefaultPageSize(command.getDefaultPageSize());
        updateList.setPageSizeOptions(command.getPageSizeOptions());
        updateList.setShowPagination(command.getShowPagination());
        updateList.setShowRefresh(command.getShowRefresh());
        updateList.setShowColumnSetting(command.getShowColumnSetting());
        updateList.setShowFullscreen(command.getShowFullscreen());
        updateList.setShowSearchBar(command.getShowSearchBar());
        updateList.setSearchBarPosition(command.getSearchBarPosition());
        updateList.setShowAdvancedSearch(command.getShowAdvancedSearch());
        updateList.setShowToolbar(command.getShowToolbar());
        updateList.setShowAddButton(command.getShowAddButton());
        updateList.setShowBatchAddButton(command.getShowBatchAddButton());
        updateList.setShowEditButton(command.getShowEditButton());
        updateList.setShowDeleteButton(command.getShowDeleteButton());
        updateList.setShowExportButton(command.getShowExportButton());
        updateList.setShowImportButton(command.getShowImportButton());
        updateList.setConfig(command.getConfig());
        updateList.setRemark(command.getRemark());
        updateList.setStatus(command.getStatus());

        metaListRepository.updateList(updateList);

        // 如果有列配置，更新列
        if (command.getColumns() != null) {
            metaListRepository.deleteColumnsByListId(command.getListId(), tenantId);
            List<MetaListColumnDO> columns = command.getColumns().stream()
                    .map(c -> {
                        MetaListColumnDO column = MetaListConverter.INSTANCE.toColumnDO(c);
                        column.setColumnId(IdUtil.fastSimpleUUID());
                        column.setListId(command.getListId());
                        column.setTenantId(tenantId);
                        return column;
                    })
                    .collect(Collectors.toList());
            metaListRepository.batchSaveColumns(columns);
        }

        // 如果有按钮配置，更新按钮
        if (command.getButtons() != null) {
            metaListRepository.deleteButtonsByListId(command.getListId(), tenantId);
            List<MetaListButtonDO> buttons = command.getButtons().stream()
                    .map(b -> {
                        MetaListButtonDO button = MetaListConverter.INSTANCE.toButtonDO(b);
                        button.setButtonId(IdUtil.fastSimpleUUID());
                        button.setListId(command.getListId());
                        button.setTenantId(tenantId);
                        return button;
                    })
                    .collect(Collectors.toList());
            metaListRepository.batchSaveButtons(buttons);
        }

        // 如果有查询条件配置，更新查询条件
        if (command.getQueries() != null) {
            metaListRepository.deleteQueriesByListId(command.getListId(), tenantId);
            List<MetaListQueryDO> queries = command.getQueries().stream()
                    .map(q -> {
                        MetaListQueryDO query = MetaListConverter.INSTANCE.toQueryDO(q);
                        query.setQueryId(IdUtil.fastSimpleUUID());
                        query.setListId(command.getListId());
                        query.setTenantId(tenantId);
                        return query;
                    })
                    .collect(Collectors.toList());
            metaListRepository.batchSaveQueries(queries);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String listId, String tenantId) {
        // 删除列配置
        metaListRepository.deleteColumnsByListId(listId, tenantId);
        // 删除按钮配置
        metaListRepository.deleteButtonsByListId(listId, tenantId);
        // 删除查询条件配置
        metaListRepository.deleteQueriesByListId(listId, tenantId);
        // 删除列表配置
        metaListRepository.deleteListById(listId, tenantId);
    }

    @Override
    public MetaListDTO getById(String listId, String tenantId) {
        MetaListDO metaListDO = metaListRepository.findListById(listId, tenantId);
        if (metaListDO == null) {
            return null;
        }

        MetaListDTO dto = MetaListConverter.INSTANCE.toDTO(metaListDO);

        // 查询列配置
        List<MetaListColumnDO> columns = metaListRepository.findActiveColumnsByListId(listId, tenantId);
        if (columns != null && !columns.isEmpty()) {
            dto.setColumns(MetaListConverter.INSTANCE.toColumnDTOList(columns));
        }

        // 查询按钮配置
        List<MetaListButtonDO> buttons = metaListRepository.findActiveButtonsByListId(listId, tenantId);
        if (buttons != null && !buttons.isEmpty()) {
            dto.setButtons(MetaListConverter.INSTANCE.toButtonDTOList(buttons));
        }

        // 查询查询条件配置
        List<MetaListQueryDO> queries = metaListRepository.findActiveQueriesByListId(listId, tenantId);
        if (queries != null && !queries.isEmpty()) {
            dto.setQueries(MetaListConverter.INSTANCE.toQueryDTOList(queries));
        }

        return dto;
    }

    @Override
    public List<MetaListDTO> listByEntityId(String entityId, String tenantId) {
        List<MetaListDO> lists = metaListRepository.findActiveListsByEntityId(entityId, tenantId);
        return MetaListConverter.INSTANCE.toDTOList(lists);
    }

    @Override
    public List<MetaListDTO> page(String keyword, int page, int limit, String sort, String order, String tenantId) {
        List<MetaListDO> lists = metaListRepository.page(keyword, page, limit, sort, order, tenantId);
        return MetaListConverter.INSTANCE.toDTOList(lists);
    }

    @Override
    public boolean checkExists(String entityId, String listCode, String tenantId, String excludeId) {
        return metaListRepository.existsByListCode(entityId, listCode, tenantId, excludeId);
    }

    @Override
    public List<MetaListColumnDTO> listColumnsByListId(String listId, String tenantId) {
        List<MetaListColumnDO> columns = metaListRepository.findActiveColumnsByListId(listId, tenantId);
        if (columns == null || columns.isEmpty()) {
            return java.util.Collections.emptyList();
        }
        return MetaListConverter.INSTANCE.toColumnDTOList(columns);
    }
}
