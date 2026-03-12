package com.ssitao.code.modular.hr.organization.application.command;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 更新部门命令
 *
 * @author ssitao
 */
@Data
public class HrDepartmentUpdateCommand {

    /**
     * 部门ID
     */
    @NotNull(message = "部门ID不能为空")
    private String id;

    /**
     * 部门编码
     */
    private String deptCode;

    /**
     * 部门名称
     */
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
     * 更新人
     */
    private String updater;
}
