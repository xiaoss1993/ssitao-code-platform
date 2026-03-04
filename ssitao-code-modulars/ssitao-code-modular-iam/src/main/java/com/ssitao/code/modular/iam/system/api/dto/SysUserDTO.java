package com.ssitao.code.modular.iam.system.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 系统用户DTO
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysUserDTO {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 姓名
     */
    private String name;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String picUrl;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 用户性别(0=女,1=男)
     */
    private Integer gender;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 职位ID
     */
    private Long positionId;

    /**
     * 职位名称
     */
    private String positionName;

    /**
     * 角色ID列表
     */
    private List<Long> roleIdList;

    /**
     * 角色名称列表
     */
    private List<String> roleNameList;

    /**
     * 皮肤主题
     */
    private String skin;

    /**
     * 主题模式
     */
    private String theme;

    /**
     * 用户状态(0=禁用,1=正常)
     */
    private Integer status;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 最后登录IP
     */
    private String lastLoginIp;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建者姓名
     */
    private String createUserName;

    /**
     * 更新者姓名
     */
    private String updateUserName;

}
