package com.ssitao.code.modular.iam.authorization.application.command;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * IAM角色创建命令
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
public class IamRoleCreateCommand {

    /**
     * 角色编码
     */
    @NotBlank(message = "角色编码不能为空")
    @Size(max = 64, message = "角色编码长度不能超过64")
    private String roleCode;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    @Size(max = 128, message = "角色名称长度不能超过128")
    private String roleName;

    /**
     * 角色类型：SYSTEM-系统角色 CUSTOM-自定义角色
     */
    @NotBlank(message = "角色类型不能为空")
    private String roleType;

    /**
     * 数据权限范围：ALL-全部 DEPT-本部门 DEPT_AND_CHILD-本部门及子部门 SELF-本人
     */
    private String dataScope;

    /**
     * 权限组ID
     */
    private Long permGroupId;

    /**
     * 父角色ID
     */
    private Long parentId;

    /**
     * 节点类型：FOLDER-文件夹 ITEM-项目
     */
    private String nodeType;

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
