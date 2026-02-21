package com.ssitao.code.modular.iam.system.infrastructure.repository;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.dal.dataobject.IamDictDataDO;
import com.ssitao.code.modular.iam.dal.mapper.IamDictDataMapper;
import com.ssitao.code.modular.iam.system.domain.model.IamDictData;
import com.ssitao.code.modular.iam.system.domain.repository.IamDictDataRepository;
import com.ssitao.code.modular.iam.system.infrastructure.converter.IamDictDataConverter;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Resource
    private IamDictDataMapper dictDataMapper;

    @Resource
    private IamDictDataConverter dictDataConverter;

    @Override
    public String save(IamDictData dictData) {
        IamDictDataDO dictDataDO = dictDataConverter.toDO(dictData);
        dictDataDO.setSyCreatetime(LocalDateTime.now().format(DATE_FORMATTER));
        dictDataMapper.insert(dictDataDO);
        return dictDataDO.getTbIamDictdataId();
    }

    @Override
    public List<String> saveBatch(List<IamDictData> dictDataList) {
        List<String> ids = new ArrayList<>();
        if (dictDataList == null || dictDataList.isEmpty()) {
            return ids;
        }
        String now = LocalDateTime.now().format(DATE_FORMATTER);
        for (IamDictData dictData : dictDataList) {
            IamDictDataDO dictDataDO = dictDataConverter.toDO(dictData);
            dictDataDO.setSyCreatetime(now);
            dictDataMapper.insert(dictDataDO);
            ids.add(dictDataDO.getTbIamDictdataId());
        }
        return ids;
    }

    @Override
    public void update(IamDictData dictData) {
        IamDictDataDO dictDataDO = dictDataConverter.toDO(dictData);
        dictDataDO.setSyModifytime(LocalDateTime.now().format(DATE_FORMATTER));
        dictDataMapper.update(dictDataDO);
    }

    @Override
    public void deleteById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("tb_iam_dictdata_id", id);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        dictDataMapper.deleteByQuery(query);
    }

    @Override
    public void deleteByDictTypeId(String dictTypeId, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("tb_iam_dicttype_id", dictTypeId);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        dictDataMapper.deleteByQuery(query);
    }

    @Override
    public Optional<IamDictData> findById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("tb_iam_dictdata_id", id);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        IamDictDataDO dictDataDO = dictDataMapper.selectOneByQuery(query);
        return Optional.ofNullable(dictDataConverter.toDomain(dictDataDO));
    }

    @Override
    public List<IamDictData> findByDictTypeId(String dictTypeId, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("tb_iam_dicttype_id", dictTypeId);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.orderBy("sy_orderindex", true);
        List<IamDictDataDO> list = dictDataMapper.selectListByQuery(query);
        return dictDataConverter.toDomainList(list);
    }

    @Override
    public List<IamDictData> findByDictTypeCode(String dictTypeCode, String tenantId) {
        // 需要关联字典类型表查询，这里先简单实现
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.orderBy("sy_orderindex", true);
        List<IamDictDataDO> list = dictDataMapper.selectListByQuery(query);
        return dictDataConverter.toDomainList(list);
    }

    @Override
    public Optional<IamDictData> findDefaultByDictTypeCode(String dictTypeCode, String tenantId) {
        // 查找默认的字典数据
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.orderBy("sy_orderindex", true)
                .limit(1);
        IamDictDataDO dictDataDO = dictDataMapper.selectOneByQuery(query);
        return Optional.ofNullable(dictDataConverter.toDomain(dictDataDO));
    }

    @Override
    public List<IamDictData> findAll(String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.orderBy("sy_orderindex", true);
        List<IamDictDataDO> list = dictDataMapper.selectListByQuery(query);
        return dictDataConverter.toDomainList(list);
    }
}
