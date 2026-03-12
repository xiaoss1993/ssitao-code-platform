package com.ssitao.code.common.pojo;

import lombok.Data;

/**
 * 分页查询参数
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
public class PageParam {

    /**
     * 当前页码，从1开始
     */
    private Integer pageNum = 1;

    /**
     * 每页大小
     */
    private Integer pageSize = 10;

    /**
     * 排序字段
     */
    private String orderBy;

    /**
     * 排序方向：asc/desc
     */
    private String orderDirection = "asc";

    /**
     * 获取偏移量
     */
    public Integer getOffset() {
        return (pageNum - 1) * pageSize;
    }
}
