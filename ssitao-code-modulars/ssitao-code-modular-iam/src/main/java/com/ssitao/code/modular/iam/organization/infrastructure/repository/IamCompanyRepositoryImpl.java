package com.ssitao.code.modular.iam.organization.infrastructure.repository;

import com.ssitao.code.frame.mybatisflex.core.paginate.Page;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.dal.dataobject.IamCompanyDO;
import com.ssitao.code.modular.iam.dal.mapper.IamCompanyMapper;
import com.ssitao.code.modular.iam.organization.domain.model.IamCompany;
import com.ssitao.code.modular.iam.organization.domain.repository.IamCompanyRepository;
import com.ssitao.code.modular.iam.organization.infrastructure.converter.IamCompanyConverter;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

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

    @Resource
    private IamCompanyMapper companyMapper;

    @Resource
    private IamCompanyConverter companyConverter;

    @Override
    public String save(IamCompany company) {
        IamCompanyDO companyDO = companyConverter.toDO(company);
        companyDO.setSyCreatetime(LocalDateTime.now().toString());
        companyMapper.insert(companyDO);
        return companyDO.getTbIamCompanyId();
    }

    @Override
    public void update(IamCompany company) {
        IamCompanyDO companyDO = companyConverter.toDO(company);
        companyDO.setSyModifytime(LocalDateTime.now().toString());
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
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        IamCompanyDO companyDO = companyMapper.selectOneByQuery(query);
        return Optional.ofNullable(companyConverter.toDomain(companyDO));
    }

    @Override
    public Optional<IamCompany> findByCreditCode(String creditCode, String tenantId) {
        // 数据库表中没有 company_credit_code 字段，此方法暂时返回空
        // 如果需要使用信用代码查询，需要在数据库表中添加该字段
        return Optional.empty();
    }

    @Override
    public boolean existsByCompanyCode(String companyCode, String tenantId, String excludeId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("company_code", companyCode);
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        if (excludeId != null) {
            query.ne("tb_iam_company_id", excludeId);
        }
        return companyMapper.selectCountByQuery(query) > 0;
    }

    @Override
    public List<IamCompany> findAll(String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1")
                .orderBy("sy_createtime", false);
        List<IamCompanyDO> list = companyMapper.selectListByQuery(query);
        return companyConverter.toDomainList(list);
    }

    @Override
    public List<IamCompany> findByConditions(String companyName, String companyType, String tenantId) {
        QueryWrapper query = QueryWrapper.create();
        if (tenantId != null && !tenantId.isEmpty()) {
            query.eq("sy_tenant_id", tenantId);
        }
        query.eq("sy_status", "1");
        if (companyName != null && !companyName.isEmpty()) {
            query.like("company_name", companyName);
        }
        // 数据库表中没有 company_type_code 字段，暂时忽略 companyType 参数
        // if (companyType != null && !companyType.isEmpty()) {
        //     query.eq("company_type_code", companyType);
        // }
        query.orderBy("sy_createtime", false);
        List<IamCompanyDO> list = companyMapper.selectListByQuery(query);
        return companyConverter.toDomainList(list);
    }

    @Override
    public void delete(String id) {
        companyMapper.deleteById(id);
    }
}
