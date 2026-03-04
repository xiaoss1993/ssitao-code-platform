package com.ssitao.code.modular.iam.system.infrastructure.repository;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.modular.iam.system.dal.dataobject.IamTenantDO;
import com.ssitao.code.modular.iam.system.dal.mapper.IamTenantMapper;
import com.ssitao.code.modular.iam.system.domain.model.IamTenant;
import com.ssitao.code.modular.iam.system.domain.repository.IamTenantRepository;
import com.ssitao.code.modular.iam.system.infrastructure.converter.IamTenantConverter;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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

    private static final String STATUS_NORMAL = "NORMAL";
    private static final Integer NOT_DELETED = 0;

    @Resource
    private IamTenantMapper tenantMapper;

    @Resource
    private IamTenantConverter tenantConverter;

    @Override
    public String save(IamTenant tenant) {
        IamTenantDO tenantDO = tenantConverter.toDO(tenant);
        tenantDO.setCreateTime(LocalDateTime.now());
        tenantDO.setTenantStatus(STATUS_NORMAL);
        tenantDO.setDeleted(NOT_DELETED);
        tenantMapper.insert(tenantDO);
        return tenantDO.getId();
    }

    @Override
    public void update(IamTenant tenant) {
        IamTenantDO tenantDO = tenantConverter.toDO(tenant);
        tenantDO.setUpdateTime(LocalDateTime.now());
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
                .eq("tenant_code", tenantCode)
                .eq("deleted", NOT_DELETED);
        IamTenantDO tenantDO = tenantMapper.selectOneByQuery(query);
        return Optional.ofNullable(tenantConverter.toDomain(tenantDO));
    }

    @Override
    public Optional<IamTenant> findByDomain(String domain) {
        QueryWrapper query = QueryWrapper.create()
                .eq("domain", domain)
                .eq("deleted", NOT_DELETED);
        IamTenantDO tenantDO = tenantMapper.selectOneByQuery(query);
        return Optional.ofNullable(tenantConverter.toDomain(tenantDO));
    }

    @Override
    public List<IamTenant> findAll() {
        QueryWrapper query = QueryWrapper.create()
                .eq("deleted", NOT_DELETED)
                .orderBy("create_time", false);
        List<IamTenantDO> list = tenantMapper.selectListByQuery(query);
        return tenantConverter.toDomainList(list);
    }

    @Override
    public List<IamTenant> findByStatus(String tenantStatus) {
        QueryWrapper query = QueryWrapper.create()
                .eq("tenant_status", tenantStatus)
                .eq("deleted", NOT_DELETED)
                .orderBy("create_time", false);
        List<IamTenantDO> list = tenantMapper.selectListByQuery(query);
        return tenantConverter.toDomainList(list);
    }

    @Override
    public List<IamTenant> findExpired() {
        QueryWrapper query = QueryWrapper.create()
                .eq("tenant_status", "EXPIRED")
                .eq("deleted", NOT_DELETED)
                .orderBy("create_time", false);
        List<IamTenantDO> list = tenantMapper.selectListByQuery(query);
        return tenantConverter.toDomainList(list);
    }

    @Override
    public boolean existsByTenantCode(String tenantCode, String excludeId) {
        QueryWrapper query = QueryWrapper.create()
                .eq("tenant_code", tenantCode)
                .eq("deleted", NOT_DELETED);
        if (excludeId != null) {
            query.ne("id", excludeId);
        }
        return tenantMapper.selectCountByQuery(query) > 0;
    }

    @Override
    public boolean existsByDomain(String domain, String excludeId) {
        if (domain == null || domain.isEmpty()) {
            return false;
        }
        QueryWrapper query = QueryWrapper.create()
                .eq("domain", domain)
                .eq("deleted", NOT_DELETED);
        if (excludeId != null) {
            query.ne("id", excludeId);
        }
        return tenantMapper.selectCountByQuery(query) > 0;
    }
}
