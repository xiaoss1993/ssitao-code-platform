package org.tweb.mybatis.core.lambda;

import java.io.Serializable;
import java.util.function.Function;

/**
 * 方法引用列接口
 * <p>
 * 用于 Lambda 表达式引用实体字段，自动转换为数据库列名
 * <p>
 * 示例：
 * <pre>
 * // getUserName -> user_name
 * query.where(SysUser::getUserName, "张三")
 *
 * // isAdmin -> is_admin
 * query.where(SysUser::isAdmin, true)
 * </pre>
 *
 * @author ssitao
 * @since 1.0.0
 * @param <T> 实体类型
 */
@FunctionalInterface
public interface MethodReferenceColumn<T> extends Function<T, Object>, Serializable {

    /**
     * 获取数据库列名（下划线命名）
     * <p>
     * 例如：getUserName -> user_name
     *
     * @return 列名
     */
    default String getColumn() {
        return LambdaUtils.getColumn(this);
    }

    /**
     * 获取实体属性名（驼峰命名）
     * <p>
     * 例如：getUserName -> userName
     *
     * @return 属性名
     */
    default String getProperty() {
        return LambdaUtils.getProperty(this);
    }
}
