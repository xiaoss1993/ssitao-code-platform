package com.ssitao.code.modular.iam.authorization.application.command;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * IAM角色更新命令
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
public class IamRoleUpdateCommand {

    /**
     * 角色ID
     */
    @NotNull(message = "角色ID不能为空")
    private Long id;

    /**
     * 角色名称
     */
    @Size(max = 128, message = "角色名称长度不能超过128")
    private String roleName;

    /**
     * 数据权限范围
     */
    private String dataScope;

    /**
     * 权限组ID
     */
    private Long permGroupId;

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
