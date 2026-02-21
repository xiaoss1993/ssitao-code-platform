package com.ssitao.code.frame.security.datapermission.core;

import com.ssitao.code.frame.security.datapermission.annotation.DataPermission;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.MethodClassKey;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 数据权限注解拦截器
 * <p>
 * 拦截带有 @DataPermission 注解的方法，设置数据权限上下文
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Slf4j
public class DataPermissionAnnotationInterceptor implements MethodInterceptor {

    /**
     * 注解缓存
     */
    private final ConcurrentHashMap<MethodClassKey, DataPermission> annotationCache = new ConcurrentHashMap<>();

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            // 获取 @DataPermission 注解
            DataPermission dataPermission = getDataPermissionAnnotation(invocation);
            if (dataPermission != null && dataPermission.enable()) {
                // 设置数据权限上下文
                setDataPermissionContext(dataPermission);
            }

            // 执行方法
            return invocation.proceed();
        } finally {
            // 清除数据权限上下文
            DataPermissionContextHolder.clear();
        }
    }

    /**
     * 获取 @DataPermission 注解
     *
     * @param invocation 方法调用
     * @return 注解对象
     */
    private DataPermission getDataPermissionAnnotation(MethodInvocation invocation) {
        Method method = invocation.getMethod();
        Object target = invocation.getThis();
        Class<?> targetClass = target != null ? AopUtils.getTargetClass(target) : null;

        // 构建缓存 key
        MethodClassKey methodClassKey = new MethodClassKey(method, targetClass);

        // 从缓存获取
        return annotationCache.computeIfAbsent(methodClassKey, k -> {
            // 优先从方法上获取
            DataPermission annotation = AnnotationUtils.findAnnotation(method, DataPermission.class);
            if (annotation == null) {
                // 从类上获取
                annotation = AnnotationUtils.findAnnotation(targetClass, DataPermission.class);
            }
            return annotation;
        });
    }

    /**
     * 设置数据权限上下文
     *
     * @param dataPermission 数据权限注解
     */
    private void setDataPermissionContext(DataPermission dataPermission) {
        DataPermissionContextHolder holder = new DataPermissionContextHolder();

        // 设置包含规则
        if (dataPermission.includeRules().length > 0) {
            holder.setIncludeRules(new HashSet<>(Arrays.asList(dataPermission.includeRules())));
        }

        // 设置排除规则
        if (dataPermission.excludeRules().length > 0) {
            holder.setExcludeRules(new HashSet<>(Arrays.asList(dataPermission.excludeRules())));
        }

        // 设置是否忽略租户权限
        holder.setIgnoreTenant(dataPermission.ignoreTenant());
    }

}
