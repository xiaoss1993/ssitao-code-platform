package com.ssitao.code.modular.iam.system.infrastructure.repository;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.dal.dataobject.IamDictTypeDO;
import com.ssitao.code.modular.iam.dal.mapper.IamDictTypeMapper;
import com.ssitao.code.modular.iam.system.domain.model.IamDictType;
import com.ssitao.code.modular.iam.system.domain.repository.IamDictTypeRepository;
import com.ssitao.code.modular.iam.system.infrastructure.converter.IamDictTypeConverter;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Resource
    private IamDictTypeMapper dictTypeMapper;

    @Resource
    private IamDictTypeConverter dictTypeConverter;

    @Override
    public String save(IamDictType dictType) {
        IamDictTypeDO dictTypeDO = dictTypeConverter.toDO(dictType);
        dictTypeDO.setSyCreatetime(LocalDateTime.now().format(DATE_FORMATTER));
        dictTypeMapper.insert(dictTypeDO);
        return dictTypeDO.getTbIamDicttypeId();
    }

    @Override
    public void update(IamDictType dictType) {
        IamDictTypeDO dictTypeDO = dictTypeConverter.toDO(dictType);
        dictTypeDO.setSyModifytime(LocalDateTime.now().format(DATE_FORMATTER));
        dictTypeMapper.update(dictTypeDO);
    }

    @Override
    public void deleteById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("tb_iam_dicttype_id", id);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        dictTypeMapper.deleteByQuery(query);
    }

    @Override
    public Optional<IamDictType> findById(String id, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("tb_iam_dicttype_id", id);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        IamDictTypeDO dictTypeDO = dictTypeMapper.selectOneByQuery(query);
        return Optional.ofNullable(dictTypeConverter.toDomain(dictTypeDO));
    }

    @Override
    public Optional<IamDictType> findByDictTypeCode(String dictTypeCode, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("dicttype_code", dictTypeCode);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        IamDictTypeDO dictTypeDO = dictTypeMapper.selectOneByQuery(query);
        return Optional.ofNullable(dictTypeConverter.toDomain(dictTypeDO));
    }

    @Override
    public List<IamDictType> findAll(String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.orderBy("sy_orderindex", true);
        List<IamDictTypeDO> list = dictTypeMapper.selectListByQuery(query);
        return dictTypeConverter.toDomainList(list);
    }

    @Override
    public boolean existsByDictTypeCode(String dictTypeCode, String tenantId, String excludeId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("dicttype_code", dictTypeCode);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        if (excludeId != null) {
            query.ne("tb_iam_dicttype_id", excludeId);
        }
        return dictTypeMapper.selectCountByQuery(query) > 0;
    }
}
