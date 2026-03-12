package com.ssitao.code.modular.meta.application.service.impl;

import com.ssitao.code.common.pojo.PageResult;
import com.ssitao.code.modular.meta.application.service.MetaDictionaryItemAppService;
import com.ssitao.code.modular.meta.domain.model.MetaDictionaryItem;
import com.ssitao.code.modular.meta.domain.repository.MetaDictionaryItemRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 字典数据应用服务实现
 *
 * @author ssitao-code
 */
@Service
public class MetaDictionaryItemAppServiceImpl implements MetaDictionaryItemAppService {

    @Resource
    private MetaDictionaryItemRepository dictionaryItemRepository;

    @Override
    public MetaDictionaryItem getDictionaryItemById(String id) {
        return dictionaryItemRepository.findById(id).orElse(null);
    }

    @Override
    public List<MetaDictionaryItem> listDictionaryItems(String tenantId) {
        return dictionaryItemRepository.findAll(tenantId);
    }

    @Override
    public List<MetaDictionaryItem> listDictionaryItemsByTypeId(String dictTypeId, String tenantId) {
        return dictionaryItemRepository.findByDictTypeId(dictTypeId, tenantId);
    }

    @Override
    public List<MetaDictionaryItem> listDictionaryItemsByTypeCode(String dictTypeCode, String tenantId) {
        return dictionaryItemRepository.findByDictTypeCode(dictTypeCode, tenantId);
    }

    @Override
    public List<MetaDictionaryItem> listEnabledDictionaryItemsByTypeCode(String dictTypeCode, String tenantId) {
        return dictionaryItemRepository.findByDictTypeCodeAndStatus(dictTypeCode, 1, tenantId);
    }

    @Override
    public PageResult<MetaDictionaryItem> listDictionaryItemsPage(Integer page, Integer size, String dictId, String tenantId, String sort, String order) {
        List<MetaDictionaryItem> items;
        if (dictId != null && !dictId.isEmpty()) {
            items = dictionaryItemRepository.findByDictTypeId(dictId, tenantId);
        } else {
            items = dictionaryItemRepository.findAll(tenantId);
        }

        if (items == null || items.isEmpty()) {
            return PageResult.of(new ArrayList<>(), 0);
        }

        // 排序
        if (sort != null && !sort.isEmpty()) {
            String finalSort = sort;
            String finalOrder = order != null ? order.toLowerCase() : "asc";
            items.sort((a, b) -> {
                int cmp = 0;
                switch (finalSort) {
                    case "dictDataLabel":
                    case "dict_data_label":
                        cmp = (a.getDictDataLabel() != null ? a.getDictDataLabel() : "").compareTo(
                              b.getDictDataLabel() != null ? b.getDictDataLabel() : "");
                        break;
                    case "createTime":
                    case "create_time":
                        cmp = (a.getCreateTime() != null ? a.getCreateTime().toString() : "").compareTo(
                              b.getCreateTime() != null ? b.getCreateTime().toString() : "");
                        break;
                    default:
                        cmp = (a.getSortOrder() != null ? a.getSortOrder() : Integer.valueOf(0)).compareTo(
                              b.getSortOrder() != null ? b.getSortOrder() : Integer.valueOf(0));
                }
                return "desc".equals(finalOrder) ? -cmp : cmp;
            });
        }

        // 记录总数
        int total = items.size();

        // 分页
        int pageNum = page != null && page > 0 ? page : 1;
        int pageSize = size != null && size > 0 ? size : 10;
        int start = (pageNum - 1) * pageSize;
        int end = Math.min(start + pageSize, items.size());
        if (start >= items.size()) {
            return PageResult.of(new ArrayList<>(), total);
        }

        List<MetaDictionaryItem> pagedList = items.subList(start, end);
        return PageResult.of(pagedList, total);
    }

    @Override
    public String createDictionaryItem(MetaDictionaryItem item) {
        // 设置创建时间
        item.setCreateTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        if (item.getDeleted() == null) {
            item.setDeleted(0);
        }
        if (item.getStatus() == null) {
            item.setStatus(1);
        }
        if (item.getSortOrder() == null) {
            item.setSortOrder(0);
        }
        if (item.getIsDefault() == null) {
            item.setIsDefault(0);
        }
        if (item.getLayer() == null) {
            item.setLayer(1);
        }

        return dictionaryItemRepository.save(item);
    }

    @Override
    public void updateDictionaryItem(MetaDictionaryItem item) {
        item.setUpdateTime(LocalDateTime.now());
        dictionaryItemRepository.update(item);
    }

    @Override
    public void deleteDictionaryItem(String id) {
        dictionaryItemRepository.delete(id);
    }

    @Override
    public void batchDeleteDictionaryItems(List<String> ids) {
        if (ids != null && !ids.isEmpty()) {
            dictionaryItemRepository.batchDelete(ids);
        }
    }

    @Override
    public void deleteDictionaryItemsByTypeId(String dictTypeId) {
        dictionaryItemRepository.deleteByDictTypeId(dictTypeId);
    }
}
