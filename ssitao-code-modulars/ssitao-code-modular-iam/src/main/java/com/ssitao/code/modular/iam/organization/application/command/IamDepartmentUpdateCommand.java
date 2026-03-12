package com.ssitao.code.modular.iam.organization.application.command;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * IAM部门更新命令
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
public class IamDepartmentUpdateCommand {

    /**
     * 部门ID
     */
    @NotNull(message = "部门ID不能为空")
    private Long id;

    /**
     * 部门名称
     */
    @Size(max = 128, message = "部门名称长度不能超过128")
    private String deptName;

    /**
     * 负责人ID
     */
    private Long leaderId;

    /**
     * 负责人姓名
     */
    @Size(max = 128, message = "负责人姓名长度不能超过128")
    private String leaderName;

    /**
     * 联系电话
     */
    @Size(max = 32, message = "联系电话长度不能超过32")
    private String phone;

    /**
     * 邮箱
     */
    @Size(max = 128, message = "邮箱长度不能超过128")
    private String email;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 备注
     */
    @Size(max = 512, message = "备注长度不能超过512")
    private String remark;

    /**
     * 租户ID
     */
    private String tenantId;

}
