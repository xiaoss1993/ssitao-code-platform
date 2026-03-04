package com.ssitao.code.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import com.ssitao.code.frame.satoken.core.SecurityUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;

/**
 * Sa-Token 配置类
 * 配置路由拦截规则
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，使用 SaRouter 进行灵活的路由权限校验
        registry.addInterceptor(new SaInterceptor(handle -> {
            // 1. 首先检查是否登录
            StpUtil.checkLogin();

            // 2. 获取请求信息
            HttpServletRequest request = handle.getRequest();
            String method = request.getMethod();
            String path = request.getRequestURI();

            // 3. 根据请求路径进行权限校验
            // GET 请求只做登录校验（查询操作），非 GET 请求需要权限校验（增删改操作）
            if (!"GET".equalsIgnoreCase(method)) {
                // 提取权限码：从 /api/user/add -> user:add
                String permission = extractPermission(path, method);
                if (permission != null) {
                    // 检查是否为超级管理员，如果是则跳过权限校验
                    if (!SecurityUtil.isSuperAdmin()) {
                        SecurityUtil.checkPermission(permission);
                    }
                }
            }
        }))
        .addPathPatterns("/**")
        .excludePathPatterns(
                // 登录相关
                "/login",
                "/login/**",
                "/api/auth/login",
                "/api/auth/check",
                "/api/auth/captcha",
                "/api/auth/logout",

                // 静态资源
                "/assets/**",
                "/static/**",
                "/css/**",
                "/js/**",
                "/img/**",
                "/fonts/**",
                "/images/**",
                "/favicon.ico",

                // API文档
                "/swagger-ui.html",
                "/swagger-ui/**",
                "/v3/api-docs/**",
                "/doc.html",
                "/webjars/**",

                // 演示接口
                "/demo/**",

                // 错误页面
                "/error"
        );
    }

    /**
     * 从请求路径提取权限码
     * 例如：/api/user/add -> user:add, /api/user/delete/1 -> user:delete
     */
    private String extractPermission(String path, String method) {
        // 移除前缀 /api
        if (path.startsWith("/api/")) {
            path = path.substring(5);
        }

        // 移除路径变量，例如 /user/delete/1 -> /user/delete
        path = path.replaceAll("/\\d+", "");
        path = path.replaceAll("/[a-f0-9-]{36}", ""); // 移除 UUID

        // 转换为小写
        path = path.toLowerCase();

        // 根据 HTTP 方法映射权限
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

        // 组合权限码：user:add, user:edit, user:delete, user:view
        String[] parts = path.split("/");
        if (parts.length >= 2) {
            return parts[0] + ":" + methodPermission;
        }

        return null;
    }
}
