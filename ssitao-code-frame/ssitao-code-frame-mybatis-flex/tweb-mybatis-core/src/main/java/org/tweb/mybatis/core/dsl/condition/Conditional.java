package org.tweb.mybatis.core.dsl.condition;

import org.tweb.mybatis.core.lambda.MethodReferenceColumn;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 条件接口
 * <p>
 * 提供丰富的条件构建方法，支持链式调用
 * <p>
 * 示例：
 * <pre>
 * query.where("name", "张三")
 *      .and("age", 18)
 *      .nest()
 *          .or("status", 1)
 *          .or("status", 2)
 *      .end()
 *      .list();
 * </pre>
 *
 * @author ssitao
 * @since 1.0.0
 * @param <T> 条件类型
 */
public interface Conditional<T extends Conditional<T>> extends LogicalOperation<T> {

    // ========== 基础比较 ==========

    /**
     * 等于
     * <p>
     * 示例：.eq("name", "张三") -> name = '张三'
     *
     * @param column 列名
     * @param value 条件值
     * @return this
     */
    default T eq(String column, Object value) {
        return and(column, TermType.eq, value);
    }

    /**
     * 等于（Lambda 版本）
     * <p>
     * 示例：.eq(SysUser::getName, "张三") -> user_name = '张三'
     *
     * @param column Lambda 列引用
     * @param value 条件值
     * @return this
     */
    default <E> T eq(MethodReferenceColumn<E> column, Object value) {
        return eq(column.getColumn(), value);
    }

    /**
     * 不等于
     */
    default T ne(String column, Object value) {
        return and(column, TermType.ne, value);
    }

    /**
     * 不等于（Lambda 版本）
     */
    default <E> T ne(MethodReferenceColumn<E> column, Object value) {
        return ne(column.getColumn(), value);
    }

    /**
     * 大于
     */
    default T gt(String column, Object value) {
        return and(column, TermType.gt, value);
    }

    /**
     * 大于（Lambda 版本）
     */
    default <E> T gt(MethodReferenceColumn<E> column, Object value) {
        return gt(column.getColumn(), value);
    }

    /**
     * 大于等于
     */
    default T ge(String column, Object value) {
        return and(column, TermType.ge, value);
    }

    /**
     * 大于等于（Lambda 版本）
     */
    default <E> T ge(MethodReferenceColumn<E> column, Object value) {
        return ge(column.getColumn(), value);
    }

    /**
     * 小于
     */
    default T lt(String column, Object value) {
        return and(column, TermType.lt, value);
    }

    /**
     * 小于（Lambda 版本）
     */
    default <E> T lt(MethodReferenceColumn<E> column, Object value) {
        return lt(column.getColumn(), value);
    }

    /**
     * 小于等于
     */
    default T le(String column, Object value) {
        return and(column, TermType.le, value);
    }

    /**
     * 小于等于（Lambda 版本）
     */
    default <E> T le(MethodReferenceColumn<E> column, Object value) {
        return le(column.getColumn(), value);
    }

    // ========== 模糊查询 ==========

    /**
     * 模糊查询（LIKE %value%）
     * <p>
     * 示例：.like("name", "张") -> name LIKE '%张%'
     *
     * @param column 列名
     * @param value 条件值
     * @return this
     */
    default T like(String column, Object value) {
        return and(column, TermType.like, value);
    }

    /**
     * 模糊查询（Lambda 版本）
     */
    default <E> T like(MethodReferenceColumn<E> column, Object value) {
        return like(column.getColumn(), value);
    }

    /**
     * 左模糊（LIKE %value）
     */
    default T likeLeft(String column, Object value) {
        return and(column, TermType.likeLeft, value);
    }

    /**
     * 右模糊（LIKE value%）
     */
    default T likeRight(String column, Object value) {
        return and(column, TermType.likeRight, value);
    }

    /**
     * 不模糊查询
     */
    default T notLike(String column, Object value) {
        return and(column, TermType.notLike, value);
    }

    // ========== 范围查询 ==========

    /**
     * IN 查询
     * <p>
     * 示例：.in("status", 1, 2, 3) -> status IN (1, 2, 3)
     *
     * @param column 列名
     * @param values 条件值数组
     * @return this
     */
    default T in(String column, Object... values) {
        return and(column, TermType.in, values);
    }

    /**
     * IN 查询（集合）
     *
     * @param column 列名
     * @param values 条件值集合
     * @return this
     */
    default T in(String column, Collection<?> values) {
        return and(column, TermType.in, values.toArray());
    }

    /**
     * NOT IN 查询
     */
    default T notIn(String column, Object... values) {
        return and(column, TermType.notIn, values);
    }

    /**
     * NOT IN 查询（集合）
     */
    default T notIn(String column, Collection<?> values) {
        return and(column, TermType.notIn, values.toArray());
    }

    /**
     * BETWEEN 查询
     * <p>
     * 示例：.between("age", 18, 60) -> age BETWEEN 18 AND 60
     *
     * @param column 列名
     * @param start 起始值
     * @param end 结束值
     * @return this
     */
    default T between(String column, Object start, Object end) {
        return and(column, TermType.between, start, end);
    }

    /**
     * NOT BETWEEN 查询
     */
    default T notBetween(String column, Object start, Object end) {
        return and(column, TermType.notBetween, start, end);
    }

    // ========== 空值判断 ==========

    /**
     * 为空（IS NULL）
     *
     * @param column 列名
     * @return this
     */
    default T isNull(String column) {
        return and(column, TermType.isNull);
    }

    /**
     * 不为空（IS NOT NULL）
     *
     * @param column 列名
     * @return this
     */
    default T isNotNull(String column) {
        return and(column, TermType.isNotNull);
    }

    // ========== 嵌套条件 ==========

    /**
     * 创建 AND 嵌套条件
     * <p>
     * 示例：
     * <pre>
     * .nest()
     *     .or("status", 1)
     *     .or("status", 2)
     * .end()
     * </pre>
     * 生成：WHERE ... AND (status = 1 OR status = 2)
     *
     * @return 嵌套条件对象
     */
    NestConditional<T> nest();

    /**
     * 创建 OR 嵌套条件
     *
     * @return 嵌套条件对象
     */
    NestConditional<T> orNest();

    /**
     * 创建带初始条件的嵌套
     *
     * @param column 列名
     * @param value 条件值
     * @return 嵌套条件对象
     */
    default NestConditional<T> nest(String column, Object value) {
        NestConditional<T> nest = nest();
        nest.where(column, value);
        return nest;
    }

    /**
     * 创建带初始条件的 OR 嵌套
     *
     * @param column 列名
     * @param value 条件值
     * @return 嵌套条件对象
     */
    default NestConditional<T> orNest(String column, Object value) {
        NestConditional<T> nest = orNest();
        nest.where(column, value);
        return nest;
    }

    // ========== 动态 SQL ==========

    /**
     * 原生 SQL 片段
     * <p>
     * 示例：.sql("YEAR(create_time) = 2024")
     *
     * @param SQL SQL 片段
     * @param params 参数
     * @return this
     */
    T sql(String SQL, Object... params);

    // ========== 条件判断 ==========

    /**
     * 条件为真时执行
     * <p>
     * 示例：.when(status != null, q -> q.eq("status", status))
     *
     * @param condition 判断条件
     * @param then 条件为真时执行的逻辑
     * @return this
     */
    default T when(Boolean condition, Consumer<T> then) {
        if (condition) {
            then.accept((T) this);
        }
        return (T) this;
    }

    /**
     * 条件为真时执行（带 Supplier）
     * <p>
     * 示例：.when(() -> isActive(), q -> q.eq("status", 1))
     *
     * @param condition 条件提供者
     * @param then 条件为真时执行的逻辑
     * @return this
     */
    default T when(Supplier<Boolean> condition, Consumer<T> then) {
        if (condition.get()) {
            then.accept((T) this);
        }
        return (T) this;
    }

    /**
     * 值不为空时执行
     * <p>
     * 示例：.whenNotNull(userName, q -> q.like("user_name", userName))
     *
     * @param value 值
     * @param then 值不为空时执行的逻辑
     * @return this
     */
    default T whenNotNull(Object value, Consumer<T> then) {
        if (value != null) {
            then.accept((T) this);
        }
        return (T) this;
    }

    /**
     * 集合不为空时执行
     *
     * @param collection 集合
     * @param then 集合不为空时执行的逻辑
     * @return this
     */
    default T whenNotEmpty(Collection<?> collection, Consumer<T> then) {
        if (collection != null && !collection.isEmpty()) {
            then.accept((T) this);
        }
        return (T) this;
    }

    /**
     * 字符串不为空时执行
     *
     * @param str 字符串
     * @param then 字符串不为空时执行的逻辑
     * @return this
     */
    default T whenNotBlank(String str, Consumer<T> then) {
        if (str != null && !str.trim().isEmpty()) {
            then.accept((T) this);
        }
        return (T) this;
    }

    // ========== 批量条件 ==========

    /**
     * 批量 OR 条件
     * <p>
     * 示例：.eachOr(Arrays.asList("张三", "李四"), "name")
     * 生成：AND (name = '张三' OR name = '李四')
     *
     * @param values 值集合
     * @param column 列名
     * @return this
     */
    default T eachOr(Iterable<?> values, String column) {
        if (values != null) {
            orNest();
            for (Object value : values) {
                or(column, value);
            }
            end();
        }
        return (T) this;
    }

    /**
     * 批量 AND 条件
     *
     * @param values 值集合
     * @param column 列名
     * @return this
     */
    default T eachAnd(Iterable<?> values, String column) {
        if (values != null) {
            nest();
            for (Object value : values) {
                and(column, value);
            }
            end();
        }
        return (T) this;
    }
}
