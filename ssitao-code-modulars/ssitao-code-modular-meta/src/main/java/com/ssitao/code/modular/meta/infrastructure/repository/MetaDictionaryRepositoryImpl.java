package com.ssitao.code.modular.meta.infrastructure.repository;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.frame.mybatisflex.core.BaseMapper;
import com.ssitao.code.modular.meta.dal.dataobject.MetaDictionaryDO;
import com.ssitao.code.modular.meta.dal.mapper.MetaDictionaryMapper;
import com.ssitao.code.modular.meta.domain.model.MetaDictionary;
import com.ssitao.code.modular.meta.domain.repository.MetaDictionaryRepository;
import com.ssitao.code.modular.meta.infrastructure.converter.MetaDictionaryConverter;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 字典类型仓储实现
 *
 * @author ssitao-code
 */
@Repository
public class MetaDictionaryRepositoryImpl implements MetaDictionaryRepository {

    @Resource
    private MetaDictionaryMapper dictionaryMapper;

    private static final Integer NOT_DELETED = 0;

    @Override
    public Optional<MetaDictionary> findById(String id) {
        QueryWrapper wrapper = QueryWrapper.create()
                .eq(MetaDictionaryDO::getDictId, id)
                .eq(MetaDictionaryDO::getIsDeleted, NOT_DELETED);
        MetaDictionaryDO dictDO = dictionaryMapper.selectOneByQuery(wrapper);
        return Optional.ofNullable(dictDO)
                .map(MetaDictionaryConverter.INSTANCE::toDomain);
    }

    @Override
    public List<MetaDictionary> findAll(String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("is_deleted", NOT_DELETED)
             .orderBy("dict_sort", true);
        List<MetaDictionaryDO> list = dictionaryMapper.selectListByQuery(query);
        return MetaDictionaryConverter.INSTANCE.toDomainList(list);
    }

    @Override
    public Optional<MetaDictionary> findByDictTypeCode(String dictTypeCode, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("dict_code", dictTypeCode)
                .eq("is_deleted", NOT_DELETED);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        MetaDictionaryDO dictDO = dictionaryMapper.selectOneByQuery(query);
        return Optional.ofNullable(dictDO)
                .map(MetaDictionaryConverter.INSTANCE::toDomain);
    }

    @Override
    public String save(MetaDictionary dictionary) {
        MetaDictionaryDO dictDO = MetaDictionaryConverter.INSTANCE.toDO(dictionary);
        // 生成ID如果未提供
        if (dictDO.getDictId() == null || dictDO.getDictId().isEmpty()) {
            dictDO.setDictId(java.util.UUID.randomUUID().toString().replace("-", ""));
        }
        dictionaryMapper.insert(dictDO);
        return dictDO.getDictId();
    }

    @Override
    public void update(MetaDictionary dictionary) {
        MetaDictionaryDO dictDO = MetaDictionaryConverter.INSTANCE.toDO(dictionary);
        dictionaryMapper.update(dictDO);
    }

    @Override
    public void delete(String id) {
        QueryWrapper query = QueryWrapper.create()
                .eq("dict_id", id);
        dictionaryMapper.deleteByQuery(query);
    }

    @Override
    public void batchDelete(List<String> ids) {
        if (ids != null && !ids.isEmpty()) {
            QueryWrapper query = QueryWrapper.create()
                    .in("dict_id", ids);
            dictionaryMapper.deleteByQuery(query);
        }
    }

    @Override
    public boolean existsByDictTypeCode(String dictTypeCode, String tenantId, String excludeId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("dict_code", dictTypeCode)
                .eq("is_deleted", NOT_DELETED);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        if (excludeId != null && !excludeId.isEmpty()) {
            query.ne("dict_id", excludeId);
        }
        return dictionaryMapper.selectCountByQuery(query) > 0;
    }
}
