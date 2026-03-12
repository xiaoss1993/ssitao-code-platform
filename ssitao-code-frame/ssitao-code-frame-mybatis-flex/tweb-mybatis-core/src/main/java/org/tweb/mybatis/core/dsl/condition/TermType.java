package org.tweb.mybatis.core.dsl.condition;

/**
 * 条件类型枚举
 * <p>
 * 定义了 ORM 查询中支持的所有条件操作类型
 *
 * @author ssitao
 * @since 1.0.0
 */
public enum TermType {

    // ========== 比较运算 ==========
    /**
     * 等于 (=)
     */
    eq("="),

    /**
     * 不等于 (!= 或 <>)
     */
    ne("!="),

    /**
     * 大于 (>)
     */
    gt(">"),

    /**
     * 大于等于 (>=)
     */
    ge(">="),

    /**
     * 小于 (<)
     */
    lt("<"),

    /**
     * 小于等于 (<=)
     */
    le("<="),

    // ========== 模糊查询 ==========
    /**
     * 模糊匹配 (LIKE)
     */
    like("LIKE"),

    /**
     * 左模糊 (LIKE %value)
     */
    likeLeft("LIKE"),

    /**
     * 右模糊 (LIKE value%)
     */
    likeRight("LIKE"),

    /**
     * 不模糊匹配 (NOT LIKE)
     */
    notLike("NOT LIKE"),

    // ========== 范围查询 ==========
    /**
     * IN 查询
     */
    in("IN"),

    /**
     * NOT IN 查询
     */
    notIn("NOT IN"),

    /**
     * BETWEEN 查询
     */
    between("BETWEEN"),

    /**
     * NOT BETWEEN 查询
     */
    notBetween("NOT BETWEEN"),

    // ========== 空值判断 ==========
    /**
     * 为空 (IS NULL)
     */
    isNull("IS NULL"),

    /**
     * 不为空 (IS NOT NULL)
     */
    isNotNull("IS NOT NULL"),

    // ========== 集合运算 ==========
    /**
     * 任意匹配 (SOME = ANY)
     */
    any("SOME = ANY"),

    /**
     * 全部匹配 (ALL = ALL)
     */
    all("ALL = ALL"),

    // ========== 动态 SQL ==========
    /**
     * 自定义 SQL 片段
     */
    sql("CUSTOM SQL");

    private final String operator;

    TermType(String operator) {
        this.operator = operator;
    }

    /**
     * 获取 SQL 操作符
     *
     * @return SQL 操作符
     */
    public String getOperator() {
        return operator;
    }

    /**
     * 是否需要参数值
     *
     * @return true 表示需要参数，false 表示不需要参数（如 IS NULL）
     */
    public boolean requireParam() {
        return this != isNull && this != isNotNull;
    }

    /**
     * 是否支持多个参数
     *
     * @return true 表示支持多个参数（如 IN、BETWEEN）
     */
    public boolean supportMultiParam() {
        return this == in || this == notIn ||
               this == between || this == notBetween ||
               this == any || this == all;
    }

    /**
     * 是否为模糊查询
     *
     * @return true 表示是模糊查询
     */
    public boolean isLike() {
        return this == like || this == likeLeft || this == likeRight || this == notLike;
    }

    /**
     * 是否为范围查询
     *
     * @return true 表示是范围查询
     */
    public boolean isRange() {
        return this == in || this == notIn || this == between || this == notBetween;
    }

    /**
     * 是否为比较运算
     *
     * @return true 表示是比较运算
     */
    public boolean isCompare() {
        return this == eq || this == ne || this == gt ||
               this == ge || this == lt || this == le;
    }
}
