package org.tweb.mybatis.core.dsl.condition;

/**
 * 嵌套条件接口
 * <p>
 * 支持复杂的嵌套条件组合，可以多层嵌套
 * <p>
 * 示例：
 * <pre>
 * query.nest()
 *         .or("status", 1)
 *         .nest()
 *             .and("type", "A")
 *             .or("type", "B")
 *         .end()
 *     .end()
 * </pre>
 * 生成：WHERE ... AND (status = 1 OR (type = 'A' OR type = 'B'))
 *
 * @author ssitao
 * @since 1.0.0
 * @param <T> 父条件类型
 */
public interface NestConditional<T> extends Conditional<NestConditional<T>> {

    /**
     * 结束嵌套，返回父条件
     * <p>
     * 调用此方法表示当前嵌套条件结束，返回到上一层
     *
     * @return 父条件对象
     */
    T end();

    /**
     * 嵌套嵌套（支持多层嵌套）
     * <p>
     * 可以在嵌套条件中继续创建嵌套
     *
     * @return 新的嵌套条件对象
     */
    @Override
    NestConditional<NestConditional<T>> nest();

    /**
     * OR 嵌套
     *
     * @return 新的嵌套条件对象
     */
    @Override
    NestConditional<NestConditional<T>> orNest();
}
