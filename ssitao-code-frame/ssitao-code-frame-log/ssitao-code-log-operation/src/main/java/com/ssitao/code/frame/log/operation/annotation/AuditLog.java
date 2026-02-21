package com.ssitao.code.frame.log.operation.annotation;

import java.lang.annotation.*;

/**
 * 操作日志注解
 * <p>
 * 用于标注需要记录操作日志的方法
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuditLog {

    /**
     * 操作模块
     */
    String module() default "";

    /**
     * 操作名称
     */
    String name() default "";

    /**
     * 操作类型
     */
    OperateType type() default OperateType.OTHER;

    /**
     * 是否记录请求参数
     */
    boolean logArgs() default true;

    /**
     * 是否记录返回结果
     */
    boolean logResult() default false;

    /**
     * 是否记录异常
     */
    boolean logError() default true;

    /**
     * 操作类型枚举
     */
    enum OperateType {
        /**
         * 创建
         */
        CREATE,
        /**
         * 更新
         */
        UPDATE,
        /**
         * 删除
         */
        DELETE,
        /**
         * 查询
         */
        QUERY,
        /**
         * 导出
         */
        EXPORT,
        /**
         * 导入
         */
        IMPORT,
        /**
         * 其他
         */
        OTHER
    }

}
