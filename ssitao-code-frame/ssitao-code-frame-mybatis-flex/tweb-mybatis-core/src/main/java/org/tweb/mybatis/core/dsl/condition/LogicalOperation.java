package org.tweb.mybatis.core.dsl.condition;

/**
 * 逻辑操作接口
 * <p>
 * 定义 AND 和 OR 逻辑操作，支持逻辑条件的组合
 *
 * @author ssitao
 * @since 1.0.0
 * @param <T> 条件类型
 */
public interface LogicalOperation<T extends LogicalOperation<T>> {

    /**
     * 切换到 AND 模式
     * <p>
     * 后续条件将使用 AND 连接
     *
     * @return this
     */
    T and();

    /**
     * 切换到 OR 模式
     * <p>
     * 后续条件将使用 OR 连接
     *
     * @return this
     */
    T or();

    /**
     * 添加 AND 条件
     *
     * @param column 列名
     * @param termType 条件类型
     * @param value 条件值
     * @return this
     */
    default T and(String column, TermType termType, Object value) {
        and().accept(column, termType, value);
        return (T) this;
    }

    /**
     * 添加 AND 条件（支持多参数）
     *
     * @param column 列名
     * @param termType 条件类型
     * @param values 条件值数组
     * @return this
     */
    default T and(String column, TermType termType, Object... values) {
        and().accept(column, termType, values);
        return (T) this;
    }

    /**
     * 添加 OR 条件
     *
     * @param column 列名
     * @param termType 条件类型
     * @param value 条件值
     * @return this
     */
    default T or(String column, TermType termType, Object value) {
        or().accept(column, termType, value);
        return (T) this;
    }

    /**
     * 添加 OR 条件（支持多参数）
     *
     * @param column 列名
     * @param termType 条件类型
     * @param values 条件值数组
     * @return this
     */
    default T or(String column, TermType termType, Object... values) {
        or().accept(column, termType, values);
        return (T) this;
    }

    /**
     * 接受条件
     * <p>
     * 根据当前逻辑模式（AND/OR）添加条件
     *
     * @param column 列名
     * @param termType 条件类型
     * @param values 条件值
     * @return this
     */
    T accept(String column, TermType termType, Object... values);
}
