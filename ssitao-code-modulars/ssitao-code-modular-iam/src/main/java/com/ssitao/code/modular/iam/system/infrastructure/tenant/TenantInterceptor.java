package com.ssitao.code.modular.iam.system.infrastructure.tenant;

import com.ssitao.code.modular.iam.system.domain.context.TenantContext;
import com.ssitao.code.modular.iam.system.domain.model.IamTenant;
import com.ssitao.code.modular.iam.system.domain.repository.IamTenantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class TenantInterceptor implements HandlerInterceptor {

    private final IamTenantRepository tenantRepository;

    public TenantInterceptor(IamTenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 1. 从请求头获取租户ID (支持 Tenant-Id 和 tenantId 两种请求头)
        String tenantId = request.getHeader("Tenant-Id");
        if (tenantId == null || tenantId.isEmpty()) {
            tenantId = request.getHeader("tenantId");
        }

        // 2. 如果没有，从域名获取
        if (tenantId == null || tenantId.isEmpty()) {
            String host = request.getHeader("Host");
            if (host != null && host.contains(".")) {
                String domain = host.split(":")[0];
                String[] parts = domain.split("\\.");
                if (parts.length >= 2) {
                    String subDomain = parts[0];
                    tenantId = tenantRepository.findByDomain(subDomain)
                        .map(IamTenant::getId).orElse(null);
                }
            }
        }

        // 3. 设置租户上下文
        if (tenantId != null && !tenantId.isEmpty()) {
            TenantContext.setTenantId(tenantId);
            tenantRepository.findById(tenantId).ifPresent(tenant ->
                TenantContext.setTenantCode(tenant.getTenantCode()));
            log.debug("设置租户上下文: {}", tenantId);
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        TenantContext.clear();
    }
}
