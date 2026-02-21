package com.ssitao.code.modular.iam.authorization.application.command;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * IAM角色分配权限命令
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
public class IamRoleAssignPermissionCommand {

    /**
     * 角色ID
     */
    @NotNull(message = "角色ID不能为空")
    private Long roleId;

    /**
     * 权限ID列表
     */
    @NotNull(message = "权限ID列表不能为空")
    private List<Long> permissionIds;

    /**
     * 租户ID
     */
    private String tenantId;

}
