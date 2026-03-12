package com.ssitao.code.modular.iam.authorization.application.command;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * IAM权限组更新命令
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
public class IamPermGroupUpdateCommand {

    /**
     * 权限组ID
     */
    @NotNull(message = "权限组ID不能为空")
    private Long id;

    /**
     * 权限组名称
     */
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
     * 备注
     */
    @Size(max = 512, message = "备注长度不能超过512")
    private String remark;

    /**
     * 租户ID
     */
    private String tenantId;

}
