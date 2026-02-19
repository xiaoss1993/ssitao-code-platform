package com.ssitao.code.modular.iam.controller.vo.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户列表查询请求 VO
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
public class UserListReqVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名（模糊查询）
     */
    private String username;

    /**
     * 昵称（模糊查询）
     */
    private String nickname;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 状态：1-启用 0-禁用
     */
    private Integer status;

    /**
     * 部门ID
     */
    private Long deptId;

}
