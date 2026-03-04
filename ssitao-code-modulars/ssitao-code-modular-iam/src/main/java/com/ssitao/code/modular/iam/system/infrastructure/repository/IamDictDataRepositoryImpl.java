package com.ssitao.code.modular.iam.system.infrastructure.repository;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.system.dal.dataobject.IamDictDataDO;
import com.ssitao.code.modular.iam.system.dal.dataobject.IamDictTypeDO;
import com.ssitao.code.modular.iam.system.dal.mapper.IamDictDataMapper;
import com.ssitao.code.modular.iam.system.dal.mapper.IamDictTypeMapper;
import com.ssitao.code.modular.iam.system.domain.model.IamDictData;
import com.ssitao.code.modular.iam.system.domain.repository.IamDictDataRepository;
import com.ssitao.code.modular.iam.system.infrastructure.converter.IamDictDataConverter;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * IAM字典数据仓储实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Repository
public class IamDictDataRepositoryImpl implements IamDictDataRepository {

    private static final Integer STATUS_ACTIVE = 1;
    private static final Integer NOT_DELETED = 0;

    @Resource
    private IamDictDataMapper dictDataMapper;

    @Resource
    private IamDictTypeMapper dictTypeMapper;

    @Resource
    private IamDictDataConverter dictDataConverter;

    @Override
    public String save(IamDictData dictData) {
        IamDictDataDO dictDataDO = dictDataConverter.toDO(dictData);
        dictDataDO.setCreateTime(LocalDateTime.now());
        dictDataDO.setStatus(STATUS_ACTIVE);
        dictDataDO.setDeleted(NOT_DELETED);
        dictDataMapper.insert(dictDataDO);
        return dictDataDO.getId();
    }

    @Override
    public List<String> saveBatch(List<IamDictData> dictDataList) {
        List<String> ids = new ArrayList<>();
        if (dictDataList == null || dictDataList.isEmpty()) {
            return ids;
        }
        LocalDateTime now = LocalDateTime.now();
        for (IamDictData dictData : dictDataList) {
            IamDictDataDO dictDataDO = dictDataConverter.toDO(dictData);
            dictDataDO.setCreateTime(now);
            dictDataDO.setStatus(STATUS_ACTIVE);
            dictDataDO.setDeleted(NOT_DELETED);
            dictDataMapper.insert(dictDataDO);
            ids.add(dictDataDO.getId());
        }
        return ids;
    }

    @Override
    public void update(IamDictData dictData) {
        IamDictDataDO dictDataDO = dictDataConverter.toDO(dictData);
        dictDataDO.setUpdateTime(LocalDateTime.now());
        dictDataMapper.update(dictDataDO);
    }

    @Override
    public void deleteById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("id", id);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        dictDataMapper.deleteByQuery(query);
    }

    @Override
    public void deleteByDictTypeId(String dictTypeId, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("dict_type_id", dictTypeId);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        dictDataMapper.deleteByQuery(query);
    }

    @Override
    public Optional<IamDictData> findById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("id", id);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("deleted", NOT_DELETED);
        IamDictDataDO dictDataDO = dictDataMapper.selectOneByQuery(query);
        return Optional.ofNullable(dictDataConverter.toDomain(dictDataDO));
    }

    @Override
    public List<IamDictData> findByDictTypeId(String dictTypeId, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("dict_type_id", dictTypeId);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("deleted", NOT_DELETED)
             .orderBy("sort_order", true);
        List<IamDictDataDO> list = dictDataMapper.selectListByQuery(query);
        return dictDataConverter.toDomainList(list);
    }

    @Override
    public List<IamDictData> findByDictTypeCode(String dictTypeCode, String tenantId) {
        // 首先根据字典类型编码查询字典类型ID
        QueryWrapper typeQuery = QueryWrapper.create()
                .eq("dict_type_code", dictTypeCode);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            typeQuery.eq("tenant_id", tenantId);
        }
        typeQuery.eq("deleted", NOT_DELETED);
        IamDictTypeDO dictTypeDO = dictTypeMapper.selectOneByQuery(typeQuery);
        if (dictTypeDO == null) {
            return new ArrayList<>();
        }

        // 然后根据字典类型ID查询字典项
        return findByDictTypeId(dictTypeDO.getId(), tenantId);
    }

    @Override
    public Optional<IamDictData> findDefaultByDictTypeCode(String dictTypeCode, String tenantId) {
        // 首先根据字典类型编码查询字典类型ID
        QueryWrapper typeQuery = QueryWrapper.create()
                .eq("dict_type_code", dictTypeCode);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            typeQuery.eq("tenant_id", tenantId);
        }
        typeQuery.eq("deleted", NOT_DELETED);
        IamDictTypeDO dictTypeDO = dictTypeMapper.selectOneByQuery(typeQuery);
        if (dictTypeDO == null) {
            return Optional.empty();
        }

        // 查找默认的字典数据
        QueryWrapper query = QueryWrapper.create()
                .eq("dict_type_id", dictTypeDO.getId())
                .eq("is_default", 1);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("deleted", NOT_DELETED)
             .orderBy("sort_order", true)
             .limit(1);
        IamDictDataDO dictDataDO = dictDataMapper.selectOneByQuery(query);
        return Optional.ofNullable(dictDataConverter.toDomain(dictDataDO));
    }

    @Override
    public List<IamDictData> findAll(String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("deleted", NOT_DELETED)
             .orderBy("sort_order", true);
        List<IamDictDataDO> list = dictDataMapper.selectListByQuery(query);
        return dictDataConverter.toDomainList(list);
    }
}
