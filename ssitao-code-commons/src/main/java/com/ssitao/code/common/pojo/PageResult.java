package com.ssitao.code.common.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页结果
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据列表
     */
    private List<T> rows;

    /**
     * 总数
     */
    private Integer total;

    public PageResult() {
    }

    public PageResult(List<T> rows, Integer total) {
        this.rows = rows;
        this.total = total;
    }

    public static <T> PageResult<T> of(List<T> rows, Integer total) {
        return new PageResult<>(rows, total);
    }
}
