package com.ssitao.code.modular.iam.controller.vo.post;

import lombok.Data;

import java.io.Serializable;

/**
 * 岗位列表查询请求 VO
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
public class PostListReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 岗位编码（模糊查询）
     */
    private String code;

    /**
     * 岗位名称（模糊查询）
     */
    private String name;

    /**
     * 状态：1-启用 0-禁用
     */
    private Integer status;

}
