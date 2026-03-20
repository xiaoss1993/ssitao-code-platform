package com.ssitao.code.modular.iam.application.command;

import lombok.Data;

import javax.validation.constraints.*;

/**
 * 更新角色命令
 */
@Data
public class UpdateRoleCommand {

    /** 角色ID */
    @NotNull(message = "角色ID不能为空")
    private Long roleId;

    /** 角色名称 */
    @NotBlank(message = "角色名称不能为空")
    @Size(min = 0, max = 30, message = "角色名称长度不能超过30个字符")
    private String roleName;

    /** 角色权限 */
    @NotBlank(message = "权限字符不能为空")
    @Size(min = 0, max = 100, message = "权限字符长度不能超过100个字符")
    private String roleKey;

    /** 角色排序 */
    @NotBlank(message = "显示顺序不能为空")
    private String roleSort;

    /** 数据范围 */
    private String dataScope;

    /** 角色状态（0正常 1停用） */
    private String status;

    /** 菜单组 */
    private Long[] menuIds;

    /** 部门组 */
    private Long[] deptIds;

    /** 备注 */
    private String remark;
}
