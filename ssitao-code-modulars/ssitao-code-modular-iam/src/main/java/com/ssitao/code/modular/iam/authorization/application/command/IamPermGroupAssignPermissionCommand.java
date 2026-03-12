package com.ssitao.code.modular.iam.authorization.application.command;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * IAM权限组分配权限命令
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
public class IamPermGroupAssignPermissionCommand {

    /**
     * 权限组ID
     */
    @NotNull(message = "权限组ID不能为空")
    private Long groupId;

    /**
     * 权限ID集合
     */
    @NotNull(message = "权限ID集合不能为空")
    private Set<Long> permissionIds;

    /**
     * 租户ID
     */
    private String tenantId;

}
