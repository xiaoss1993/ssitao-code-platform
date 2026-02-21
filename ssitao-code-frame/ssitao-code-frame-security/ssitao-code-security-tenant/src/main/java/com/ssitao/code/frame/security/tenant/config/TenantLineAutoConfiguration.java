package com.ssitao.code.frame.security.tenant.config;

import com.mybatis.flex.core.FlexGlobalConfig;
import com.mybatis.flex.core.table.TableInfoFactory;
import com.ssitao.code.frame.security.tenant.core.SsitaoTenantLine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * 多租户自动配置类
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Slf4j
@AutoConfiguration
@EnableConfigurationProperties(TenantProperties.class)
@ConditionalOnProperty(prefix = "ssitao.tenant", name = "enabled", havingValue = "true", matchIfMissing = false)
public class TenantLineAutoConfiguration {

    /**
     * 配置 MyBatis-Flex 租户拦截器
     */
    @Bean
    public SsitaoTenantLine ssitaoTenantLine(TenantProperties properties) {
        log.info("启用多租户功能，忽略表: {}", properties.getIgnoreTables());
        SsitaoTenantLine tenantLine = new SsitaoTenantLine();
        // 注册到 MyBatis-Flex 全局配置
        FlexGlobalConfig globalConfig = FlexGlobalConfig.getDefaultConfig();
        globalConfig.setTenantLine(tenantLine);
        return tenantLine;
    }

}
