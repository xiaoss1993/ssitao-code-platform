package com.ssitao.code.config;

import com.ssitao.code.resolver.MyRequestArgumentResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Web MVC 配置
 * 包含参数解析器和静态资源映射
 */
@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * 配置参数解析器
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new MyRequestArgumentResolver());
    }

    /**
     * 配置静态资源映射
     * 映射 Knife4j 和 SpringDoc 的 webjars 静态资源
     * 禁用缓存以确保获取最新版本
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 映射 webjars 静态资源（Knife4j 和 SpringDoc 的前端资源）
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/")
                .setCachePeriod(0)  // 禁用缓存
                .resourceChain(false);  // 禁用资源链

        // 映射 Knife4j 主页面
        registry.addResourceHandler("/doc.html")
                .addResourceLocations("classpath:/META-INF/resources/")
                .setCachePeriod(0);  // 禁用缓存

        // 映射 swagger-ui 资源
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/swagger-ui/")
                .setCachePeriod(0);  // 禁用缓存

        log.info("静态资源映射配置完成: /webjars/**, /doc.html, /swagger-ui/**");
    }

}
