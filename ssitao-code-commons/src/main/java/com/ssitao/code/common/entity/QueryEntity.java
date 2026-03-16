package com.ssitao.code.common.entity;

import java.util.StringJoiner;

/**
 *
 * @since 3.0
 */
public interface QueryEntity extends Entity {
    /**
     * 转为http查询参数
     * @return
     */
    default String toHttpQueryParamString() {
        return "";
    }
}
