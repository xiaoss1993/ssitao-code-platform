package com.ssitao.code.frame.security.tenant.config;

import com.ssitao.code.frame.security.tenant.utils.TenantUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * 租户基础配置类
 * <p>
 * 始终加载，提供租户相关的工具类和配置，无论租户功能是否启用
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Slf4j
@AutoConfiguration
@EnableConfigurationProperties(TenantProperties.class)
public class TenantBaseConfiguration {

    /**
     * 初始化 TenantUtils，注入 TenantProperties
     */
    @Bean
    public TenantUtilsInitializer tenantUtilsInitializer(TenantProperties properties) {
        log.info("初始化租户工具类，租户功能状态: {}", properties.getEnabled());
        TenantUtils.setTenantProperties(properties);
        return new TenantUtilsInitializer();
    }

    /**
     * 占位符 Bean，用于触发初始化
     */
    public static class TenantUtilsInitializer {
    }

}
