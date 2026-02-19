package com.ssitao.code.modular.iam.controller.vo.dept;

import lombok.Data;

import java.io.Serializable;

/**
 * 部门列表查询请求 VO
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
public class DeptListReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 部门名称（模糊查询）
     */
    private String name;

    /**
     * 部门编码（模糊查询）
     */
    private String code;

    /**
     * 状态：1-启用 0-禁用
     */
    private Integer status;

    /**
     * 父部门ID
     */
    private Long parentId;

}
