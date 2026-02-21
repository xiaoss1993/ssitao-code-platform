package com.ssitao.code.modular.iam.organization.application.command;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * IAM岗位创建命令
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
public class IamPostCreateCommand {

    /**
     * 岗位编码
     */
    @NotBlank(message = "岗位编码不能为空")
    @Size(max = 64, message = "岗位编码长度不能超过64")
    private String postCode;

    /**
     * 岗位名称
     */
    @NotBlank(message = "岗位名称不能为空")
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
     * 租户ID
     */
    private String tenantId;

    /**
     * 备注
     */
    @Size(max = 512, message = "备注长度不能超过512")
    private String remark;

}
