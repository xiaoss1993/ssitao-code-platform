package org.tweb.mybatis.core.lambda;

import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Lambda 工具类
 * <p>
 * 用于从序列化的 Lambda 表达式中提取方法信息，实现方法引用到列名的转换
 *
 * @author ssitao
 * @since 1.0.0
 */
public class LambdaUtils {

    /**
     * Lambda 序列化缓存
     */
    private static final ConcurrentHashMap<Class<?>, SerializedLambda> LAMBDA_CACHE = new ConcurrentHashMap<>();

    /**
     * 属性名缓存
     */
    private static final ConcurrentHashMap<Class<?>, String> PROPERTY_CACHE = new ConcurrentHashMap<>();

    /**
     * 列名缓存
     */
    private static final ConcurrentHashMap<Class<?>, String> COLUMN_CACHE = new ConcurrentHashMap<>();

    /**
     * 获取数据库列名（下划线命名）
     * <p>
     * 转换规则：
     * <ul>
     *   <li>getUserName -> user_name</li>
     *   <li>isAdmin -> is_admin</li>
     *   <li>createTime -> create_time</li>
     * </ul>
     *
     * @param lambda Lambda 表达式
     * @return 列名
     */
    public static String getColumn(MethodReferenceColumn<?> lambda) {
        Class<?> clazz = lambda.getClass();
        return COLUMN_CACHE.computeIfAbsent(clazz, key -> {
            String property = getProperty(lambda);
            return camelToUnderline(property);
        });
    }

    /**
     * 获取实体属性名（驼峰命名）
     * <p>
     * 转换规则：
     * <ul>
     *   <li>getUserName -> userName</li>
     *   <li>isAdmin -> admin</li>
     *   <li>setName -> name（去掉set前缀）</li>
     * </ul>
     *
     * @param lambda Lambda 表达式
     * @return 属性名
     */
    public static String getProperty(MethodReferenceColumn<?> lambda) {
        Class<?> clazz = lambda.getClass();
        return PROPERTY_CACHE.computeIfAbsent(clazz, key -> {
            SerializedLambda serializedLambda = getSerializedLambda(lambda);
            String methodName = serializedLambda.getImplMethodName();
            return methodNameToProperty(methodName);
        });
    }

    /**
     * 获取序列化的 Lambda
     *
     * @param lambda Lambda 表达式
     * @return 序列化对象
     */
    public static SerializedLambda getSerializedLambda(MethodReferenceColumn<?> lambda) {
        Class<?> clazz = lambda.getClass();
        return LAMBDA_CACHE.computeIfAbsent(clazz, key -> {
            try {
                Method method = clazz.getDeclaredMethod("writeReplace");
                method.setAccessible(true);
                return (SerializedLambda) method.invoke(lambda);
            } catch (Exception e) {
                throw new RuntimeException("无法序列化 Lambda: " + lambda.getClass(), e);
            }
        });
    }

    /**
     * 方法名转属性名
     *
     * @param methodName 方法名
     * @return 属性名
     */
    private static String methodNameToProperty(String methodName) {
        if (methodName == null || methodName.isEmpty()) {
            return "";
        }

        // getXxx -> xxx
        if (methodName.startsWith("get")) {
            return lowerFirst(methodName.substring(3));
        }

        // isXxx -> xxx
        if (methodName.startsWith("is")) {
            return lowerFirst(methodName.substring(2));
        }

        // setXxx -> xxx
        if (methodName.startsWith("set")) {
            return lowerFirst(methodName.substring(3));
        }

        return methodName;
    }

    /**
     * 驼峰转下划线
     *
     * @param camel 驼峰字符串
     * @return 下划线字符串
     */
    private static String camelToUnderline(String camel) {
        if (camel == null || camel.isEmpty()) {
            return "";
        }

        StringBuilder underline = new StringBuilder();
        underline.append(Character.toLowerCase(camel.charAt(0)));

        for (int i = 1; i < camel.length(); i++) {
            char c = camel.charAt(i);
            if (Character.isUpperCase(c)) {
                underline.append('_').append(Character.toLowerCase(c));
            } else {
                underline.append(c);
            }
        }

        return underline.toString();
    }

    /**
     * 首字母小写
     *
     * @param str 字符串
     * @return 首字母小写的字符串
     */
    private static String lowerFirst(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        return Character.toLowerCase(str.charAt(0)) +
                (str.length() > 1 ? str.substring(1) : "");
    }

    /**
     * 清除缓存
     */
    public static void clearCache() {
        LAMBDA_CACHE.clear();
        PROPERTY_CACHE.clear();
        COLUMN_CACHE.clear();
    }

    /**
     * 获取缓存大小
     *
     * @return 缓存大小
     */
    public static int getCacheSize() {
        return LAMBDA_CACHE.size();
    }
}
