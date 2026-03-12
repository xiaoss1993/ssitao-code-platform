package com.ssitao.code.modular.meta.infrastructure.repository;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.meta.dal.dataobject.MetaDictionaryItemDO;
import com.ssitao.code.modular.meta.dal.mapper.MetaDictionaryItemMapper;
import com.ssitao.code.modular.meta.domain.model.MetaDictionaryItem;
import com.ssitao.code.modular.meta.domain.repository.MetaDictionaryItemRepository;
import com.ssitao.code.modular.meta.infrastructure.converter.MetaDictionaryItemConverter;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * 字典数据仓储实现
 *
 * @author ssitao-code
 */
@Repository
public class MetaDictionaryItemRepositoryImpl implements MetaDictionaryItemRepository {

    @Resource
    private MetaDictionaryItemMapper dictionaryItemMapper;

    private static final Integer NOT_DELETED = 0;

    @Override
    public Optional<MetaDictionaryItem> findById(String id) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaDictionaryItemDO::getItemId, id)
                .eq(MetaDictionaryItemDO::getIsDeleted, NOT_DELETED);
        MetaDictionaryItemDO itemDO = dictionaryItemMapper.selectOneByQuery(wrapper);
        return Optional.ofNullable(itemDO)
                .map(MetaDictionaryItemConverter.INSTANCE::toDomain);
    }

    @Override
    public List<MetaDictionaryItem> findAll(String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("is_deleted", NOT_DELETED)
             .orderBy("item_sort", true);
        List<MetaDictionaryItemDO> list = dictionaryItemMapper.selectListByQuery(query);
        return MetaDictionaryItemConverter.INSTANCE.toDomainList(list);
    }

    @Override
    public List<MetaDictionaryItem> findByDictTypeId(String dictId, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("dict_id", dictId)
                .eq("is_deleted", NOT_DELETED)
                .orderBy("item_sort", true);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        List<MetaDictionaryItemDO> list = dictionaryItemMapper.selectListByQuery(query);
        return MetaDictionaryItemConverter.INSTANCE.toDomainList(list);
    }

    @Override
    public List<MetaDictionaryItem> findByDictTypeCode(String dictCode, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("dict_id", dictCode)
                .eq("is_deleted", NOT_DELETED)
                .orderBy("item_sort", true);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        List<MetaDictionaryItemDO> list = dictionaryItemMapper.selectListByQuery(query);
        return MetaDictionaryItemConverter.INSTANCE.toDomainList(list);
    }

    @Override
    public List<MetaDictionaryItem> findByDictTypeCodeAndStatus(String dictCode, Integer status, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("dict_id", dictCode)
                .eq("item_status", status)
                .eq("is_deleted", NOT_DELETED)
                .orderBy("item_sort", true);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        List<MetaDictionaryItemDO> list = dictionaryItemMapper.selectListByQuery(query);
        return MetaDictionaryItemConverter.INSTANCE.toDomainList(list);
    }

    @Override
    public String save(MetaDictionaryItem item) {
        MetaDictionaryItemDO itemDO = MetaDictionaryItemConverter.INSTANCE.toDO(item);
        // 生成ID如果未提供
        if (itemDO.getItemId() == null || itemDO.getItemId().isEmpty()) {
            itemDO.setItemId(java.util.UUID.randomUUID().toString().replace("-", ""));
        }
        dictionaryItemMapper.insert(itemDO);
        return itemDO.getItemId();
    }

    @Override
    public void update(MetaDictionaryItem item) {
        MetaDictionaryItemDO itemDO = MetaDictionaryItemConverter.INSTANCE.toDO(item);
        dictionaryItemMapper.update(itemDO);
    }

    @Override
    public void delete(String id) {
        QueryWrapper query = QueryWrapper.create()
                .eq("item_id", id);
        dictionaryItemMapper.deleteByQuery(query);
    }

    @Override
    public void batchDelete(List<String> ids) {
        if (ids != null && !ids.isEmpty()) {
            QueryWrapper query = QueryWrapper.create()
                    .in("item_id", ids);
            dictionaryItemMapper.deleteByQuery(query);
        }
    }

    @Override
    public void deleteByDictTypeId(String dictId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("dict_id", dictId);
        dictionaryItemMapper.deleteByQuery(query);
    }
}
