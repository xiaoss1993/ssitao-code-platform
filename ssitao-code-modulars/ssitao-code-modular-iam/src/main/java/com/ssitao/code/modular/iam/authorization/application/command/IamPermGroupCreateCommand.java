package com.ssitao.code.modular.iam.authorization.application.command;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * IAM权限组创建命令
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
public class IamPermGroupCreateCommand {

    /**
     * 权限组编码
     */
    @NotBlank(message = "权限组编码不能为空")
    @Size(max = 64, message = "权限组编码长度不能超过64")
    private String groupCode;

    /**
     * 权限组名称
     */
    @NotBlank(message = "权限组名称不能为空")
    @Size(max = 128, message = "权限组名称长度不能超过128")
    private String groupName;

    /**
     * 权限组描述
     */
    @Size(max = 512, message = "权限组描述长度不能超过512")
    private String description;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 备注
     */
    @Size(max = 512, message = "备注长度不能超过512")
    private String remark;

    /**
     * 权限ID列表
     */
    private Set<Long> permissionIds;

}
