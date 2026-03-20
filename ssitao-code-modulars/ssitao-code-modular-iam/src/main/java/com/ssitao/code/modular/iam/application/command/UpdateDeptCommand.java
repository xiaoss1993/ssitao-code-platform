package com.ssitao.code.modular.iam.application.command;

import lombok.Data;

import javax.validation.constraints.*;

/**
 * 更新部门命令
 */
@Data
public class UpdateDeptCommand {

    /** 部门ID */
    @NotNull(message = "部门ID不能为空")
    private Long deptId;

    /** 父部门ID */
    @NotNull(message = "父部门ID不能为空")
    private Long parentId;

    /** 部门名称 */
    @NotBlank(message = "部门名称不能为空")
    @Size(min = 0, max = 30, message = "部门名称长度不能超过30个字符")
    private String deptName;

    /** 显示顺序 */
    @NotNull(message = "显示顺序不能为空")
    private Integer orderNum;

    /** 负责人 */
    private String leader;

    /** 联系电话 */
    @Size(min = 0, max = 11, message = "联系电话长度不能超过11个字符")
    private String phone;

    /** 邮箱 */
    @Email(message = "邮箱格式不正确")
    @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
    private String email;

    /** 部门状态 */
    private String status;

    /** 备注 */
    private String remark;
}
