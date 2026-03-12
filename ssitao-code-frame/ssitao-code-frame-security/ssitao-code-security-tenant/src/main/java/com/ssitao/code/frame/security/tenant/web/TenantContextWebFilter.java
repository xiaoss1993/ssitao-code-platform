package com.ssitao.code.frame.security.tenant.web;

import com.ssitao.code.frame.security.tenant.config.TenantProperties;
import com.ssitao.code.frame.security.tenant.core.TenantContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 租户上下文 Web 过滤器
 * <p>
 * 从请求头或请求参数中获取租户ID，并设置到 TenantContextHolder
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Slf4j
public class TenantContextWebFilter extends OncePerRequestFilter {

    private final TenantProperties properties;

    public TenantContextWebFilter(TenantProperties properties) {
        this.properties = properties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // 从请求头获取租户ID
            String tenantId = request.getHeader(properties.getHeaderName());
            if (tenantId == null || tenantId.isEmpty()) {
                // 从请求参数获取租户ID
                tenantId = request.getParameter(properties.getParamName());
            }
            if (tenantId == null || tenantId.isEmpty()) {
                // 使用默认租户ID
                tenantId = properties.getDefaultTenantId();
            }

            // 设置租户上下文
            TenantContextHolder.setTenantId(tenantId);

            filterChain.doFilter(request, response);
        } finally {
            // 清除租户上下文
            TenantContextHolder.clear();
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String uri = request.getRequestURI();
        // 检查是否在忽略列表中
        List<String> ignoreUrls = properties.getIgnoreUrls();
        if (ignoreUrls != null) {
            for (String ignoreUrl : ignoreUrls) {
                if (uri.contains(ignoreUrl)) {
                    return true;
                }
            }
        }
        return false;
    }

}
