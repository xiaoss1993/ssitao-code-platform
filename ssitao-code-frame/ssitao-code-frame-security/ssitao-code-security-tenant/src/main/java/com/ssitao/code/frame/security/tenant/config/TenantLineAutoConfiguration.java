package com.ssitao.code.frame.security.tenant.config;

import com.ssitao.code.frame.mybatisflex.core.FlexGlobalConfig;
import com.ssitao.code.frame.mybatisflex.core.tenant.TenantLine;
import com.ssitao.code.frame.security.tenant.core.SsitaoTenantLine;
import com.ssitao.code.frame.security.tenant.web.TenantContextWebFilter;
import com.ssitao.code.frame.security.tenant.web.TenantIdMethodArgumentResolver;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

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
public class TenantLineAutoConfiguration implements WebMvcConfigurer {

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    /**
     * 配置 MyBatis-Flex 租户拦截器
     */
    @Bean
    public TenantLine tenantLine(TenantProperties properties) {
        log.info("启用多租户功能，忽略表: {}", properties.getIgnoreTables());
        SsitaoTenantLine tenantLine = new SsitaoTenantLine();
        // 注册到 MyBatis-Flex 全局配置
        FlexGlobalConfig globalConfig = FlexGlobalConfig.getDefaultConfig();
        globalConfig.setTenantLine(tenantLine);
        return tenantLine;
    }

    /**
     * 配置租户上下文 Web 过滤器
     */
    @Bean
    @ConditionalOnWebApplication
    public FilterRegistrationBean<TenantContextWebFilter> tenantContextWebFilter(TenantProperties properties) {
        FilterRegistrationBean<TenantContextWebFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TenantContextWebFilter(properties));
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        log.info("注册租户上下文 Web 过滤器，请求头: {}", properties.getHeaderName());
        return registrationBean;
    }

    /**
     * 注册租户ID方法参数解析器
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new TenantIdMethodArgumentResolver());
    }

}
