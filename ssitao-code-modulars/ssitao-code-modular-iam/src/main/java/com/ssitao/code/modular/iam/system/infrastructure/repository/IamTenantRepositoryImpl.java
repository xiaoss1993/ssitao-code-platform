package com.ssitao.code.modular.iam.system.infrastructure.repository;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.dal.dataobject.IamTenantDO;
import com.ssitao.code.modular.iam.dal.mapper.IamTenantMapper;
import com.ssitao.code.modular.iam.system.domain.model.IamTenant;
import com.ssitao.code.modular.iam.system.domain.repository.IamTenantRepository;
import com.ssitao.code.modular.iam.system.infrastructure.converter.IamTenantConverter;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

/**
 * IAM租户仓储实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Repository
public class IamTenantRepositoryImpl implements IamTenantRepository {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Resource
    private IamTenantMapper tenantMapper;

    @Resource
    private IamTenantConverter tenantConverter;

    @Override
    public String save(IamTenant tenant) {
        IamTenantDO tenantDO = tenantConverter.toDO(tenant);
        tenantDO.setSyCreatetime(LocalDateTime.now().format(DATE_FORMATTER));
        tenantDO.setSyStatus("0");
        tenantMapper.insert(tenantDO);
        return tenantDO.getTbIamTenantId();
    }

    @Override
    public void update(IamTenant tenant) {
        IamTenantDO tenantDO = tenantConverter.toDO(tenant);
        tenantDO.setSyModifytime(LocalDateTime.now().format(DATE_FORMATTER));
        tenantMapper.update(tenantDO);
    }

    @Override
    public void deleteById(String id) {
        tenantMapper.deleteById(id);
    }

    @Override
    public Optional<IamTenant> findById(String id) {
        IamTenantDO tenantDO = tenantMapper.selectOneById(id);
        return Optional.ofNullable(tenantConverter.toDomain(tenantDO));
    }

    @Override
    public Optional<IamTenant> findByTenantCode(String tenantCode) {
        QueryWrapper query = QueryWrapper.create()
                .where("tenant_code", tenantCode)
                .and("sy_status", "0");
        IamTenantDO tenantDO = tenantMapper.selectOneByQuery(query);
        return Optional.ofNullable(tenantConverter.toDomain(tenantDO));
    }

    @Override
    public Optional<IamTenant> findByDomain(String domain) {
        // 需要额外的域名字段支持，这里返回空
        return Optional.empty();
    }

    @Override
    public List<IamTenant> findAll() {
        QueryWrapper query = QueryWrapper.create()
                .where("sy_status", "0")
                .orderBy("sy_createtime", false);
        List<IamTenantDO> list = tenantMapper.selectListByQuery(query);
        return tenantConverter.toDomainList(list);
    }

    @Override
    public List<IamTenant> findByStatus(String tenantStatus) {
        QueryWrapper query = QueryWrapper.create()
                .where("sy_status", "0");
        if ("NORMAL".equals(tenantStatus)) {
            query.and("status", true);
        } else {
            query.and("status", false);
        }
        query.orderBy("sy_createtime", false);
        List<IamTenantDO> list = tenantMapper.selectListByQuery(query);
        return tenantConverter.toDomainList(list);
    }

    @Override
    public List<IamTenant> findExpired() {
        QueryWrapper query = QueryWrapper.create()
                .where("sy_status", "0")
                .and("status", false)
                .orderBy("sy_createtime", false);
        List<IamTenantDO> list = tenantMapper.selectListByQuery(query);
        return tenantConverter.toDomainList(list);
    }

    @Override
    public boolean existsByTenantCode(String tenantCode, String excludeId) {
        QueryWrapper query = QueryWrapper.create()
                .where("tenant_code", tenantCode)
                .and("sy_status", "0");
        if (excludeId != null) {
            query.and("tb_iam_tenant_id", "!=", excludeId);
        }
        return tenantMapper.selectCountByQuery(query) > 0;
    }

    @Override
    public boolean existsByDomain(String domain, String excludeId) {
        // 需要额外的域名字段支持
        return false;
    }
}
