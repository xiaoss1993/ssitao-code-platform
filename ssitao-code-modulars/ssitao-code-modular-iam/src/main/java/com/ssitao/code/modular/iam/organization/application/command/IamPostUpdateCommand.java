package com.ssitao.code.modular.iam.organization.application.command;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * IAM岗位更新命令
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
public class IamPostUpdateCommand {

    /**
     * 岗位ID
     */
    @NotNull(message = "岗位ID不能为空")
    private Long id;

    /**
     * 岗位名称
     */
    @Size(max = 128, message = "岗位名称长度不能超过128")
    private String postName;

    /**
     * 岗位等级
     */
    private Integer postLevel;

    /**
     * 岗位类别
     */
    @Size(max = 64, message = "岗位类别长度不能超过64")
    private String postCategory;

    /**
     * 部门ID
     */
    private Long deptId;

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
