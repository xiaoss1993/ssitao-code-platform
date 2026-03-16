package com.ssitao.code.common.entity;

/**
 * ID生成器接口
 *
 * @since 3.0
 */
@FunctionalInterface
public interface IDGenerator<PK> {
    /**
     * 生成ID
     *
     * @return 生成的ID
     */
    PK generate();
}
