package com.ssitao.code.modular.iam.system.infrastructure.repository;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.system.dal.dataobject.IamDictTypeDO;
import com.ssitao.code.modular.iam.system.dal.mapper.IamDictTypeMapper;
import com.ssitao.code.modular.iam.system.domain.model.IamDictType;
import com.ssitao.code.modular.iam.system.domain.repository.IamDictTypeRepository;
import com.ssitao.code.modular.iam.system.infrastructure.converter.IamDictTypeConverter;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * IAM字典类型仓储实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Repository
public class IamDictTypeRepositoryImpl implements IamDictTypeRepository {

    private static final Integer STATUS_ACTIVE = 1;
    private static final Integer NOT_DELETED = 0;

    @Resource
    private IamDictTypeMapper dictTypeMapper;

    @Resource
    private IamDictTypeConverter dictTypeConverter;

    @Override
    public String save(IamDictType dictType) {
        IamDictTypeDO dictTypeDO = dictTypeConverter.toDO(dictType);
        dictTypeDO.setCreateTime(LocalDateTime.now());
        dictTypeDO.setStatus(STATUS_ACTIVE);
        dictTypeDO.setDeleted(NOT_DELETED);
        dictTypeMapper.insert(dictTypeDO);
        return dictTypeDO.getId();
    }

    @Override
    public void update(IamDictType dictType) {
        IamDictTypeDO dictTypeDO = dictTypeConverter.toDO(dictType);
        dictTypeDO.setUpdateTime(LocalDateTime.now());
        dictTypeMapper.update(dictTypeDO);
    }

    @Override
    public void deleteById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("id", id);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        dictTypeMapper.deleteByQuery(query);
    }

    @Override
    public Optional<IamDictType> findById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("id", id);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("deleted", NOT_DELETED);
        IamDictTypeDO dictTypeDO = dictTypeMapper.selectOneByQuery(query);
        return Optional.ofNullable(dictTypeConverter.toDomain(dictTypeDO));
    }

    @Override
    public Optional<IamDictType> findByDictTypeCode(String dictTypeCode, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("dict_type_code", dictTypeCode);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("deleted", NOT_DELETED);
        IamDictTypeDO dictTypeDO = dictTypeMapper.selectOneByQuery(query);
        return Optional.ofNullable(dictTypeConverter.toDomain(dictTypeDO));
    }

    @Override
    public List<IamDictType> findAll(String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("deleted", NOT_DELETED)
             .orderBy("sort_order", true);
        List<IamDictTypeDO> list = dictTypeMapper.selectListByQuery(query);
        return dictTypeConverter.toDomainList(list);
    }

    @Override
    public boolean existsByDictTypeCode(String dictTypeCode, String tenantId, String excludeId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("dict_type_code", dictTypeCode);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("deleted", NOT_DELETED);
        if (excludeId != null) {
            query.ne("id", excludeId);
        }
        return dictTypeMapper.selectCountByQuery(query) > 0;
    }
}
