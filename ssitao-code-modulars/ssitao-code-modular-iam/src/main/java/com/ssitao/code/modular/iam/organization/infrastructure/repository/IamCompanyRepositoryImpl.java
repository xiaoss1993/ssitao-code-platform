package com.ssitao.code.modular.iam.organization.infrastructure.repository;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.organization.dal.dataobject.IamCompanyDO;
import com.ssitao.code.modular.iam.organization.dal.mapper.IamCompanyMapper;
import com.ssitao.code.modular.iam.organization.domain.model.IamCompany;
import com.ssitao.code.modular.iam.organization.domain.repository.IamCompanyRepository;
import com.ssitao.code.modular.iam.organization.infrastructure.converter.IamCompanyConverter;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * IAM公司仓储实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Repository
public class IamCompanyRepositoryImpl implements IamCompanyRepository {

    private static final Integer STATUS_ACTIVE = 1;
    private static final Integer NOT_DELETED = 0;

    @Resource
    private IamCompanyMapper companyMapper;

    @Resource
    private IamCompanyConverter companyConverter;

    @Override
    public String save(IamCompany company) {
        IamCompanyDO companyDO = companyConverter.toDO(company);
        companyDO.setCreateTime(LocalDateTime.now());
        companyDO.setCompanyStatus(STATUS_ACTIVE);
        companyDO.setIsDeleted(NOT_DELETED);
        companyMapper.insert(companyDO);
        return companyDO.getCompanyId();
    }

    @Override
    public void update(IamCompany company) {
        IamCompanyDO companyDO = companyConverter.toDO(company);
        companyDO.setModifyTime(LocalDateTime.now());
        companyMapper.update(companyDO);
    }

    @Override
    public Optional<IamCompany> findById(String id) {
        IamCompanyDO companyDO = companyMapper.selectOneById(id);
        return Optional.ofNullable(companyConverter.toDomain(companyDO));
    }

    @Override
    public Optional<IamCompany> findByCompanyCode(String companyCode, String tenantId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("company_code", companyCode);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("is_deleted", NOT_DELETED);
        IamCompanyDO companyDO = companyMapper.selectOneByQuery(query);
        return Optional.ofNullable(companyConverter.toDomain(companyDO));
    }

    @Override
    public Optional<IamCompany> findByCreditCode(String creditCode, String tenantId) {
        // 数据库表中没有 company_credit_code 字段，此方法暂时返回空
        return Optional.empty();
    }

    @Override
    public boolean existsByCompanyCode(String companyCode, String tenantId, String excludeId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("company_code", companyCode);
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("is_deleted", NOT_DELETED);
        if (excludeId != null) {
            query.ne("company_id", excludeId);
        }
        return companyMapper.selectCountByQuery(query) > 0;
    }

    @Override
    public List<IamCompany> findAll(String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("is_deleted", NOT_DELETED)
             .orderBy("create_time", false);
        List<IamCompanyDO> list = companyMapper.selectListByQuery(query);
        return companyConverter.toDomainList(list);
    }

    @Override
    public List<IamCompany> findByConditions(String companyName, String companyType, String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty() && !"default".equals(tenantId)) {
            query.eq("tenant_id", tenantId);
        }
        query.eq("is_deleted", NOT_DELETED);
        if (companyName != null && !companyName.isEmpty()) {
            query.like("company_name", companyName);
        }
        if (companyType != null && !companyType.isEmpty()) {
            query.eq("company_type", companyType);
        }
        query.orderBy("create_time", false);
        List<IamCompanyDO> list = companyMapper.selectListByQuery(query);
        return companyConverter.toDomainList(list);
    }

    @Override
    public void delete(String id) {
        companyMapper.deleteById(id);
    }
}
