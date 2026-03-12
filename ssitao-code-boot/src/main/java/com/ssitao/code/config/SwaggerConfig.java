package com.ssitao.code.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springdoc.core.customizers.ServerBaseUrlCustomizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

/**
 * Knife4j 配置类
 * 基于 SpringDoc OpenAPI 3.0 规范
 *
 * @author ssitao
 */
@Slf4j
@Configuration
public class SwaggerConfig {

    @Value("${server.port:8080}")
    private Integer port;

    @Value("${spring.application.name:ssitao-code-platform}")
    private String applicationName;

    /**
     * 配置 OpenAPI 信息
     *
     * @return OpenAPI 实例
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // 文档基本信息
                .info(new Info()
                        .title("基础权限平台 API 文档")
                        .description("基于 Spring Boot 的基础权限平台接口文档")
                        .version("1.1.1")
                        .contact(new Contact()
                                .name("ssitao")
                                .email("ssitao@example.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                // 外部文档
                .externalDocs(new ExternalDocumentation()
                        .description("基础权限平台 Wiki 文档")
                        .url("https://github.com/ssitao/ssitao-code-platform/wiki"))
                // 安全组件配置
                .components(new Components()
                        .addSecuritySchemes(HttpHeaders.AUTHORIZATION,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("JWT 认证 Token")));
    }

    /**
     * 全局 OpenAPI 自定义配置
     * 主要用于全局添加鉴权参数
     *
     * @return GlobalOpenApiCustomizer 实例
     */
    @Bean
    public GlobalOpenApiCustomizer orderGlobalOpenApiCustomizer() {
        return openApi -> {
            // 全局添加鉴权参数
            if (openApi.getPaths() != null) {
                openApi.getPaths().forEach((s, pathItem) -> {
                    pathItem.readOperations().forEach(operation -> {
                        operation.addSecurityItem(new SecurityRequirement().addList(HttpHeaders.AUTHORIZATION));
                    });
                });
            }
        };
    }

    /**
     * 自定义服务器地址配置
     *
     * @return ServerBaseUrlCustomizer 实例
     */
    @Bean
    public ServerBaseUrlCustomizer serverBaseUrlCustomizer() {
        return url -> "http://localhost:" + port;
    }
}
