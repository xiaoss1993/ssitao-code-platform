package com.ssitao.code.modular.meta.application.service.impl;

import com.ssitao.code.common.pojo.PageResult;
import com.ssitao.code.modular.meta.application.service.MetaDictionaryAppService;
import com.ssitao.code.modular.meta.domain.model.MetaDictionary;
import com.ssitao.code.modular.meta.domain.repository.MetaDictionaryRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 字典类型应用服务实现
 *
 * @author ssitao-code
 */
@Service
public class MetaDictionaryAppServiceImpl implements MetaDictionaryAppService {

    @Resource
    private MetaDictionaryRepository dictionaryRepository;

    @Override
    public MetaDictionary getDictionaryById(String id) {
        return dictionaryRepository.findById(id).orElse(null);
    }

    @Override
    public List<MetaDictionary> listDictionaries(String tenantId) {
        return dictionaryRepository.findAll(tenantId);
    }

    @Override
    public PageResult<MetaDictionary> listDictionariesPage(Integer page, Integer size, String tenantId, String sort, String order) {
        List<MetaDictionary> dictionaries = dictionaryRepository.findAll(tenantId);

        if (dictionaries == null || dictionaries.isEmpty()) {
            return PageResult.of(new ArrayList<>(), 0);
        }

        // 排序
        if (sort != null && !sort.isEmpty()) {
            String finalSort = sort;
            String finalOrder = order != null ? order.toLowerCase() : "asc";
            dictionaries.sort((a, b) -> {
                int cmp = 0;
                switch (finalSort) {
                    case "dictTypeName":
                    case "dict_type_name":
                        cmp = (a.getDictTypeName() != null ? a.getDictTypeName() : "").compareTo(
                              b.getDictTypeName() != null ? b.getDictTypeName() : "");
                        break;
                    case "createTime":
                    case "create_time":
                        cmp = (a.getCreateTime() != null ? a.getCreateTime().toString() : "").compareTo(
                              b.getCreateTime() != null ? b.getCreateTime().toString() : "");
                        break;
                    default:
                        cmp = (a.getDictTypeName() != null ? a.getDictTypeName() : "").compareTo(
                              b.getDictTypeName() != null ? b.getDictTypeName() : "");
                }
                return "desc".equals(finalOrder) ? -cmp : cmp;
            });
        }

        // 记录总数
        int total = dictionaries.size();

        // 分页
        int pageNum = page != null && page > 0 ? page : 1;
        int pageSize = size != null && size > 0 ? size : 10;
        int start = (pageNum - 1) * pageSize;
        int end = Math.min(start + pageSize, dictionaries.size());
        if (start >= dictionaries.size()) {
            return PageResult.of(new ArrayList<>(), total);
        }

        List<MetaDictionary> pagedList = dictionaries.subList(start, end);
        return PageResult.of(pagedList, total);
    }

    @Override
    public MetaDictionary getDictionaryByCode(String dictTypeCode, String tenantId) {
        return dictionaryRepository.findByDictTypeCode(dictTypeCode, tenantId).orElse(null);
    }

    @Override
    public String createDictionary(MetaDictionary dictionary) {
        // 设置创建时间
        dictionary.setCreateTime(LocalDateTime.now());
        dictionary.setUpdateTime(LocalDateTime.now());
        if (dictionary.getDeleted() == null) {
            dictionary.setDeleted(0);
        }
        if (dictionary.getStatus() == null) {
            dictionary.setStatus(1);
        }
        if (dictionary.getSortOrder() == null) {
            dictionary.setSortOrder(0);
        }

        return dictionaryRepository.save(dictionary);
    }

    @Override
    public void updateDictionary(MetaDictionary dictionary) {
        dictionary.setUpdateTime(LocalDateTime.now());
        dictionaryRepository.update(dictionary);
    }

    @Override
    public void deleteDictionary(String id) {
        dictionaryRepository.delete(id);
    }

    @Override
    public void batchDeleteDictionaries(List<String> ids) {
        if (ids != null && !ids.isEmpty()) {
            dictionaryRepository.batchDelete(ids);
        }
    }
}
