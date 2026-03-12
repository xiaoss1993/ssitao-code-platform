package com.ssitao.code.config;

import cn.dev33.satoken.stp.StpUtil;
import com.ssitao.code.frame.satoken.core.SecurityUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Sa-Token 配置类
 * 改为基于注解的权限验证方式
 * 只有添加了@RequiresLogin或@RequiresPermissions注解的接口才进行验证
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，改为基于注解验证
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                // 检查是否是HandlerMethod（Controller方法）
                if (!(handler instanceof org.springframework.web.method.HandlerMethod)) {
                    return true; // 非Controller方法直接放行
                }

                org.springframework.web.method.HandlerMethod handlerMethod = (org.springframework.web.method.HandlerMethod) handler;
                Method method = handlerMethod.getMethod();

                // 检查方法或类上是否有需要登录的注解
                boolean requiresLogin = isAnnotationPresent(method, handlerMethod.getBeanType(),
                        "cn.dev33.satoken.annotation.SaLoginCheck",
                        "org.springframework.security.annotation.AuthenticationPrincipal");

                // 检查方法或类上是否有权限注解
                boolean requiresPermission = isAnnotationPresent(method, handlerMethod.getBeanType(),
                        "org.springframework.security.annotation.PreAuthorize",
                        "cn.dev33.satoken.annotation.SaCheckPermission",
                        "org.springframework.security.annotation.Secured");

                // 只有添加了注解的接口才进行验证
                if (requiresLogin || requiresPermission) {
                    // 先检查登录
                    StpUtil.checkLogin();

                    // 如果需要权限检查
                    if (requiresPermission && !SecurityUtil.isSuperAdmin()) {
                        // 提取权限码进行验证
                        String permission = extractPermissionFromAnnotations(method);
                        if (permission != null) {
                            SecurityUtil.checkPermission(permission);
                        }
                    }
                }

                return true;
            }

            /**
             * 检查是否存在指定注解
             */
            @SuppressWarnings("unchecked")
            private boolean isAnnotationPresent(Method method, Class<?> beanClass, String... annotationNames) {
                // 检查方法上的注解
                for (String annotationName : annotationNames) {
                    try {
                        Class<?> annotationClass = Class.forName(annotationName);
                        if (AnnotatedElementUtils.hasAnnotation(method, (Class<java.lang.annotation.Annotation>) annotationClass)) {
                            return true;
                        }
                    } catch (ClassNotFoundException ignored) {
                        // 注解类不存在，跳过
                    }
                }

                // 检查类上的注解
                for (String annotationName : annotationNames) {
                    try {
                        Class<?> annotationClass = Class.forName(annotationName);
                        if (AnnotatedElementUtils.hasAnnotation(beanClass, (Class<java.lang.annotation.Annotation>) annotationClass)) {
                            return true;
                        }
                    } catch (ClassNotFoundException ignored) {
                        // 注解类不存在，跳过
                    }
                }

                return false;
            }

            /**
             * 从注解中提取权限码
             */
            @SuppressWarnings("unchecked")
            private String extractPermissionFromAnnotations(Method method) {
                // 尝试从 @PreAuthorize 注解提取
                try {
                    Class<?> preAuthorizeClass = Class.forName("org.springframework.security.annotation.PreAuthorize");
                    java.lang.annotation.Annotation annotation = AnnotatedElementUtils.getMergedAnnotation(method, (Class<java.lang.annotation.Annotation>) preAuthorizeClass);
                    if (annotation != null) {
                        // 获取 value 属性
                        Method valueMethod = preAuthorizeClass.getMethod("value");
                        String value = (String) valueMethod.invoke(annotation);
                        if (value != null && !value.isEmpty()) {
                            return value;
                        }
                    }
                } catch (Exception ignored) {
                }

                // 尝试从 @SaCheckPermission 注解提取
                try {
                    Class<?> saCheckPermissionClass = Class.forName("cn.dev33.satoken.annotation.SaCheckPermission");
                    java.lang.annotation.Annotation annotation = AnnotatedElementUtils.getMergedAnnotation(method, (Class<java.lang.annotation.Annotation>) saCheckPermissionClass);
                    if (annotation != null) {
                        Method valueMethod = saCheckPermissionClass.getMethod("value");
                        String[] values = (String[]) valueMethod.invoke(annotation);
                        if (values != null && values.length > 0) {
                            return values[0];
                        }
                    }
                } catch (Exception ignored) {
                }

                // 无法从注解提取，返回null表示只验证登录
                return null;
            }
        })
        .addPathPatterns("/**")
        .excludePathPatterns(
                // 登录相关
                "/login",
                "/login/**",
                "/api/auth/login",
                "/api/auth/check",
                "/api/auth/captcha",
                "/api/auth/logout",
                "/api/test-data/**",

                // 页面路由
                "/",
                "/index",
                "/dashboard",
                "/console",

                // 静态资源
                "/assets/**",
                "/static/**",
                "/css/**",
                "/js/**",
                "/img/**",
                "/fonts/**",
                "/images/**",
                "/favicon.ico",

                // API文档 - Knife4j/Swagger
                "/swagger-ui.html",
                "/swagger-ui/**",
                "/swagger-ui.css",
                "/swagger-ui.js",
                "/swagger-ui-standalone-preset.js",
                "/swagger-ui-standalone-preset.css",
                "/swagger-resources/**",
                "/swagger-resources",
                "/v3/api-docs/**",
                "/v3/api-docs",
                "/v3/openapi.json",
                "/doc.html",
                "/doc.css",
                "/doc.js",
                "/webjars/**",
                "/knife4j/**",
                "/swagger/**",

                // 演示接口
                "/demo/**",

                // 错误页面
                "/error"
        );
    }

    /**
     * 从请求路径提取权限码（备用方案）
     */
    private String extractPermission(String path, String method) {
        if (path.startsWith("/api/")) {
            path = path.substring(5);
        }

        path = path.replaceAll("/\\d+", "");
        path = path.replaceAll("/[a-f0-9-]{36}", "");
        path = path.toLowerCase();

        String methodPermission;
        switch (method.toUpperCase()) {
            case "POST":
                methodPermission = "add";
                break;
            case "PUT":
            case "PATCH":
                methodPermission = "edit";
                break;
            case "DELETE":
                methodPermission = "delete";
                break;
            case "GET":
                methodPermission = "view";
                break;
            default:
                methodPermission = "view";
        }

        String[] parts = path.split("/");
        if (parts.length >= 2) {
            return parts[0] + ":" + methodPermission;
        }

        return null;
    }
}
