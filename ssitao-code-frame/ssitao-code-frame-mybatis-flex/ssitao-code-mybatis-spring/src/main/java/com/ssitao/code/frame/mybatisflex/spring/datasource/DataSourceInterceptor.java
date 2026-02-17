

package com.ssitao.code.frame.mybatisflex.spring.datasource;

import com.ssitao.code.frame.mybatisflex.annotation.UseDataSource;
import com.ssitao.code.frame.mybatisflex.core.datasource.DataSourceKey;
import com.ssitao.code.frame.mybatisflex.core.util.StringUtil;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.MethodClassKey;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 多数据源切换拦截器
 * <p>
 * 拦截带有 @UseDataSource 注解的方法调用，在方法执行前切换到指定的数据源。
 * 支持在类、接口和方法上标注 @UseDataSource 注解，优先级为：方法 > 类 > 接口。
 * 使用缓存机制提升数据源key的解析性能。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class DataSourceInterceptor implements MethodInterceptor {

    /**
     * 缓存方法对应的数据源
     * <p>
     * 使用 MethodClassKey 作为缓存键，避免重复解析注解
     */
    private final Map<Object, String> dsCache = new ConcurrentHashMap<>();

    /**
     * 拦截方法调用，执行数据源切换
     *
     * @param invocation 方法调用对象
     * @return 方法执行结果
     * @throws Throwable 执行异常
     */
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String dsKey = getDataSourceKey(invocation.getThis(), invocation.getMethod(), invocation.getArguments());
        if (StringUtil.noText(dsKey)) {
            return invocation.proceed();
        }
        try {
            DataSourceKey.use(dsKey);
            return invocation.proceed();
        } finally {
            DataSourceKey.clear();
        }
    }

    /**
     * 获取数据源key
     * <p>
     * 首先从缓存中获取，如果缓存中没有则解析注解并缓存
     *
     * @param target 目标对象
     * @param method 执行的方法
     * @param arguments 方法参数
     * @return 数据源key
     */
    private String getDataSourceKey(Object target, Method method, Object[] arguments) {
        Object cacheKey = new MethodClassKey(method, target.getClass());
        String dsKey = this.dsCache.get(cacheKey);
        if (dsKey == null) {
            dsKey = determineDataSourceKey(method, target.getClass());
            // 对数据源取值进行动态取值处理
            if (StringUtil.hasText(dsKey)) {
                dsKey = DataSourceKey.processDataSourceKey(dsKey, target, method, arguments);
            }
            this.dsCache.put(cacheKey, dsKey);
        }
        return dsKey;
    }

    /**
     * 确定数据源key
     * <p>
     * 按照优先级从方法、类、接口上查找 @UseDataSource 注解
     *
     * @param method 方法
     * @param targetClass 目标类
     * @return 数据源key
     */
    private String determineDataSourceKey(Method method, Class<?> targetClass) {
        // 方法上定义有 UseDataSource 注解
        UseDataSource annotation = method.getAnnotation(UseDataSource.class);
        if (annotation != null) {
            return annotation.value();
        }
        // 类上定义有 UseDataSource 注解
        annotation = targetClass.getAnnotation(UseDataSource.class);
        if (annotation != null) {
            return annotation.value();
        }
        // 接口上定义有 UseDataSource 注解
        Class<?>[] interfaces = targetClass.getInterfaces();
        for (Class<?> anInterface : interfaces) {
            annotation = anInterface.getAnnotation(UseDataSource.class);
            if (annotation != null) {
                return annotation.value();
            }
        }
        // 哪里都没有 UseDataSource 注解
        return "";
    }

}
