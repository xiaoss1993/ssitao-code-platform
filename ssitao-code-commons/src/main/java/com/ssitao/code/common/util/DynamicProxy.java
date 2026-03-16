package com.ssitao.code.common.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态代理创建工具类
 *
 * @since 3.0
 */
public class DynamicProxy {

    private final Class<?> interfaceClass;
    private final Map<String, String> methods = new HashMap<>();

    private DynamicProxy(Class<?> interfaceClass) {
        this.interfaceClass = interfaceClass;
    }

    /**
     * 创建代理构建器
     *
     * @param interfaceClass 接口类
     * @param <T>           接口类型
     * @return 代理构建器
     */
    public static <T> DynamicProxy create(Class<T> interfaceClass) {
        return new DynamicProxy(interfaceClass);
    }

    /**
     * 添加方法实现
     *
     * @param methodCode 方法代码
     * @return 当前代理构建器
     */
    public DynamicProxy addMethod(String methodCode) {
        // 简单解析方法签名
        int start = methodCode.indexOf("public ");
        int end = methodCode.indexOf("(", start);
        if (start >= 0 && end > start) {
            String signature = methodCode.substring(start + 7, end).trim();
            methods.put(signature, methodCode);
        }
        return this;
    }

    /**
     * 创建代理实例
     *
     * @param loader      类加载器
     * @param <T>         接口类型
     * @return 代理实例
     */
    @SuppressWarnings("unchecked")
    public <T> T newInstance(ClassLoader... loader) {
        ClassLoader classLoader = loader.length > 0 ? loader[0] : interfaceClass.getClassLoader();

        return (T) java.lang.reflect.Proxy.newProxyInstance(classLoader, new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String methodKey = method.getName();

                // 尝试找到匹配的方法实现
                for (Map.Entry<String, String> entry : methods.entrySet()) {
                    if (entry.getKey().startsWith(methodKey + "(")) {
                        // 找到方法实现，使用默认返回值
                        return getDefaultReturnValue(method.getReturnType());
                    }
                }

                // 如果没有找到实现，抛出异常
                throw new UnsupportedOperationException("Method " + method.getName() + " not implemented");
            }
        });
    }

    private Object getDefaultReturnValue(Class<?> returnType) {
        if (returnType == void.class) {
            return null;
        } else if (returnType == boolean.class) {
            return false;
        } else if (returnType == char.class) {
            return '\0';
        } else if (returnType == byte.class) {
            return (byte) 0;
        } else if (returnType == short.class) {
            return (short) 0;
        } else if (returnType == int.class) {
            return 0;
        } else if (returnType == long.class) {
            return 0L;
        } else if (returnType == float.class) {
            return 0.0f;
        } else if (returnType == double.class) {
            return 0.0d;
        } else {
            return null;
        }
    }
}
