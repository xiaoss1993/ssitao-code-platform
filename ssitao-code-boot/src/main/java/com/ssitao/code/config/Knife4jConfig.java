package com.ssitao.code.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Knife4j 配置类
 *
 * 参考：https://github.com/xiaoymin/knife4j
 */
@Slf4j
@Configuration
public class Knife4jConfig {

    /**
     * 自定义 OpenAPI 配置
     * 用于设置 API 文档的基本信息
     * 注意：不手动配置 GroupedOpenApi，使用 SpringDoc 自动配置
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("基础权限平台 API")
                        .version("1.1.1")
                        .description("基于 Spring Boot + MyBatis-Flex + Knife4j 的基础权限平台")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}
