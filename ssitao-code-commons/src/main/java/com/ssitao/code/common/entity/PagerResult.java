package com.ssitao.code.common.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "分页结果")
@Getter
@Setter
@ToString
public class PagerResult<E> implements Entity {
    private static final long serialVersionUID = -6171751136953308027L;

    public static <E> PagerResult<E> empty() {
        return new PagerResult<>(0, new ArrayList<>());
    }

    public static <E> PagerResult<E> of(int total, List<E> list) {
        return new PagerResult<>(total, list);
    }


    @Schema(description = "当前页码")
    private int pageIndex;

    @Schema(description = "每页数据数量")
    private int pageSize;

    @Schema(description = "数据总数量")
    private int total;

    @Schema(description = "查询结果")
    private List<E> data;

    public PagerResult() {
    }

    public PagerResult(int total, List<E> data) {
        this.total = total;
        this.data = data;
    }

}
