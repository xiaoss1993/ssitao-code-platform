package com.ssitao.code.modular.hr.organization.application.command;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 创建部门命令
 *
 * @author ssitao
 */
@Data
public class HrDepartmentCreateCommand {

    /**
     * 部门编码
     */
    @NotBlank(message = "部门编码不能为空")
    private String deptCode;

    /**
     * 部门名称
     */
    @NotBlank(message = "部门名称不能为空")
    private String deptName;

    /**
     * 父部门ID
     */
    private String parentId;

    /**
     * 部门负责人
     */
    private String deptLeader;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 排序
     */
    private Integer deptSort;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 创建人
     */
    private String creator;
}
