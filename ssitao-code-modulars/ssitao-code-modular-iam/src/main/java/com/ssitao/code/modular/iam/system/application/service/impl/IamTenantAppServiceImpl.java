package com.ssitao.code.modular.iam.system.application.service.impl;

import com.ssitao.code.modular.iam.system.application.command.IamTenantCreateCommand;
import com.ssitao.code.modular.iam.system.application.command.IamTenantUpdateCommand;
import com.ssitao.code.modular.iam.system.application.service.IamTenantAppService;
import com.ssitao.code.modular.iam.system.api.dto.IamTenantDTO;
import com.ssitao.code.modular.iam.system.domain.model.IamTenant;
import com.ssitao.code.modular.iam.system.domain.repository.IamTenantRepository;
import com.ssitao.code.modular.iam.system.infrastructure.converter.IamTenantConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * IAM租户应用服务实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Service
public class IamTenantAppServiceImpl implements IamTenantAppService {

    @Resource
    private IamTenantRepository tenantRepository;

    @Resource
    private IamTenantConverter tenantConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createTenant(IamTenantCreateCommand command) {
        // 检查租户编码是否已存在
        if (tenantRepository.existsByTenantCode(command.getTenantCode(), null)) {
            throw new IllegalArgumentException("租户编码已存在: " + command.getTenantCode());
        }

        // 检查域名是否已存在
        if (command.getDomain() != null && tenantRepository.existsByDomain(command.getDomain(), null)) {
            throw new IllegalArgumentException("域名已被使用: " + command.getDomain());
        }

        // 创建租户聚合根
        IamTenant tenant = IamTenant.create(
                command.getTenantCode(),
                command.getTenantName(),
                command.getTenantType()
        );

        // 设置ID
        tenant.setId(UUID.randomUUID().toString().replace("-", ""));

        // 设置其他属性
        tenant.setContact(command.getContact());
        tenant.setContactPhone(command.getContactPhone());
        tenant.setContactEmail(command.getContactEmail());
        tenant.setAddress(command.getAddress());
        tenant.setExpireTime(command.getExpireTime());
        if (command.getUserLimit() != null) {
            tenant.setUserLimit(command.getUserLimit());
        }
        if (command.getStorageLimit() != null) {
            tenant.setStorageLimit(command.getStorageLimit());
        }
        tenant.setDomain(command.getDomain());
        tenant.setRemark(command.getRemark());

        String id = tenantRepository.save(tenant);
        return id != null ? Long.valueOf(id) : null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTenant(IamTenantUpdateCommand command) {
        IamTenant tenant = tenantRepository.findById(command.getId().toString())
                .orElseThrow(() -> new IllegalArgumentException("租户不存在: " + command.getId()));

        // 检查域名是否已被其他租户使用
        if (command.getDomain() != null && tenantRepository.existsByDomain(command.getDomain(), command.getId().toString())) {
            throw new IllegalArgumentException("域名已被其他租户使用: " + command.getDomain());
        }

        // 更新属性
        if (command.getTenantName() != null) {
            tenant.setTenantName(command.getTenantName());
        }
        tenant.setContact(command.getContact());
        tenant.setContactPhone(command.getContactPhone());
        tenant.setContactEmail(command.getContactEmail());
        tenant.setAddress(command.getAddress());
        tenant.setExpireTime(command.getExpireTime());
        if (command.getUserLimit() != null) {
            tenant.setUserLimit(command.getUserLimit());
        }
        if (command.getStorageLimit() != null) {
            tenant.setStorageLimit(command.getStorageLimit());
        }
        tenant.setDomain(command.getDomain());
        tenant.setRemark(command.getRemark());
        tenant.setUpdateTime(LocalDateTime.now());

        tenantRepository.update(tenant);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTenant(Long id) {
        tenantRepository.deleteById(id.toString());
    }

    @Override
    public IamTenantDTO getTenantById(Long id) {
        IamTenant tenant = tenantRepository.findById(id.toString())
                .orElseThrow(() -> new IllegalArgumentException("租户不存在: " + id));
        return convertToDTO(tenant);
    }

    @Override
    public IamTenantDTO getTenantByCode(String tenantCode) {
        IamTenant tenant = tenantRepository.findByTenantCode(tenantCode)
                .orElseThrow(() -> new IllegalArgumentException("租户不存在: " + tenantCode));
        return convertToDTO(tenant);
    }

    @Override
    public IamTenantDTO getTenantByDomain(String domain) {
        IamTenant tenant = tenantRepository.findByDomain(domain)
                .orElseThrow(() -> new IllegalArgumentException("租户不存在: " + domain));
        return convertToDTO(tenant);
    }

    @Override
    public List<IamTenantDTO> listTenants() {
        List<IamTenant> tenants = tenantRepository.findAll();
        return tenants.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enableTenant(Long id) {
        IamTenant tenant = tenantRepository.findById(id.toString())
                .orElseThrow(() -> new IllegalArgumentException("租户不存在: " + id));
        tenant.enable();
        tenantRepository.update(tenant);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disableTenant(Long id) {
        IamTenant tenant = tenantRepository.findById(id.toString())
                .orElseThrow(() -> new IllegalArgumentException("租户不存在: " + id));
        tenant.disable();
        tenantRepository.update(tenant);
    }

    /**
     * 转换租户为DTO
     */
    private IamTenantDTO convertToDTO(IamTenant tenant) {
        IamTenantDTO dto = new IamTenantDTO();
        dto.setId(tenant.getId());
        dto.setTenantCode(tenant.getTenantCode());
        dto.setTenantName(tenant.getTenantName());
        dto.setTenantType(tenant.getTenantType());
        dto.setContact(tenant.getContact());
        dto.setContactPhone(tenant.getContactPhone());
        dto.setContactEmail(tenant.getContactEmail());
        dto.setAddress(tenant.getAddress());
        dto.setTenantStatus(tenant.getTenantStatus());
        dto.setExpireTime(tenant.getExpireTime());
        dto.setExpired(tenant.isExpired());
        dto.setUserLimit(tenant.getUserLimit());
        dto.setCurrentUserCount(tenant.getCurrentUserCount());
        dto.setStorageLimit(tenant.getStorageLimit());
        dto.setUsedStorage(tenant.getUsedStorage());
        dto.setLogoUrl(tenant.getLogoUrl());
        dto.setDomain(tenant.getDomain());
        dto.setRemark(tenant.getRemark());
        dto.setCreateTime(tenant.getCreateTime());
        dto.setUpdateTime(tenant.getUpdateTime());
        dto.setCreator(tenant.getCreator());
        dto.setUpdater(tenant.getUpdater());

        // 计算使用百分比
        if (tenant.getUserLimit() != null && tenant.getCurrentUserCount() != null) {
            dto.setUserUsagePercent((int) (tenant.getCurrentUserCount() * 100 / tenant.getUserLimit()));
        }
        if (tenant.getStorageLimit() != null && tenant.getUsedStorage() != null) {
            dto.setStorageUsagePercent((int) (tenant.getUsedStorage() * 100 / tenant.getStorageLimit()));
        }

        // 设置状态文本
        dto.setTenantStatusText(getTenantStatusText(tenant.getTenantStatus()));
        dto.setTenantTypeText(getTenantTypeText(tenant.getTenantType()));

        return dto;
    }

    /**
     * 获取租户状态文本
     */
    private String getTenantStatusText(String status) {
        if (status == null) {
            return "";
        }
        switch (status) {
            case "NORMAL":
                return "正常";
            case "EXPIRED":
                return "过期";
            case "DISABLED":
                return "禁用";
            default:
                return status;
        }
    }

    /**
     * 获取租户类型文本
     */
    private String getTenantTypeText(String type) {
        if (type == null) {
            return "";
        }
        switch (type) {
            case "TRIAL":
                return "试用";
            case "STANDARD":
                return "标准";
            case "ENTERPRISE":
                return "企业";
            default:
                return type;
        }
    }
}
