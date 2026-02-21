package com.ssitao.code.frame.security.tenant.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * 多租户配置属性
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
@ConfigurationProperties(prefix = "ssitao.tenant")
public class TenantProperties {

    /**
     * 是否启用多租户
     */
    private Boolean enabled = false;

    /**
     * 租户ID 请求头名称
     */
    private String headerName = "Tenant-Id";

    /**
     * 租户ID 请求参数名称
     */
    private String paramName = "tenantId";

    /**
     * 忽略租户隔离的表
     */
    private List<String> ignoreTables = new ArrayList<>();

    /**
     * 忽略租户隔离的 URL
     */
    private List<String> ignoreUrls = new ArrayList<>();

    /**
     * 默认租户ID
     */
    private String defaultTenantId = "0";

}
