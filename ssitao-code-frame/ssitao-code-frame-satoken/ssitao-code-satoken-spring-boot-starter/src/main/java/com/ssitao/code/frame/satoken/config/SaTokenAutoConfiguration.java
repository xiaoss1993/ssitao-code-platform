package com.ssitao.code.frame.satoken.config;

import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.stp.StpLogic;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * Sa-Token 自动配置类
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@AutoConfiguration
@EnableConfigurationProperties(SaTokenProperties.class)
public class SaTokenAutoConfiguration {

    /**
     * 配置 Sa-Token 使用 JWT 模式
     */
    @Bean
    @ConditionalOnProperty(prefix = "sa-token", name = "jwt-enabled", havingValue = "true", matchIfMissing = true)
    public StpLogic stpLogicJwt() {
        return new StpLogicJwtForSimple();
    }

}
