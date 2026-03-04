package com.ssitao.code.modular.iam.authorization.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * IAM角色DTO
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IamRoleDTO {

    /**
     * 角色ID
     */
    private String id;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    // ==================== 前端兼容字段 ====================

    /**
     * 角色名称（前端兼容，用于树形控件显示）
     */
    private String label;

    /**
     * 别名（前端兼容）
     */
    private String alias;

    /**
     * 排序（前端兼容）
     */
    private Integer sort;

    /**
     * 状态（前端兼容，0/1整数）
     */
    private Integer statusInt;

    /**
     * 创建时间（前端兼容，字符串格式）
     */
    private String date;

    // ==================== 原有字段 ====================

    /**
     * 角色类型：SYSTEM-系统角色 CUSTOM-自定义角色
     */
    private String roleType;

    /**
     * 数据权限范围：ALL-全部 DEPT-本部门 DEPT_AND_CHILD-本部门及子部门 SELF-本人
     */
    private String dataScope;

    /**
     * 图标样式
     */
    private String iconCls;

    /**
     * 权限组ID
     */
    private String permGroupId;

    /**
     * 权限组名称
     */
    private String permGroupName;

    /**
     * 父角色ID
     */
    private String parentId;

    /**
     * 层级
     */
    private Integer layer;

    /**
     * 路径
     */
    private String path;

    /**
     * 节点类型：FOLDER-文件夹 ITEM-项目
     */
    private String nodeType;

    /**
     * 状态：1-启用 0-禁用
     */
    private Boolean status;

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
    private String remark;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 更新人
     */
    private String updater;

    /**
     * 权限ID列表
     */
    private List<String> permissionIds;

    /**
     * 子角色列表
     */
    private List<IamRoleDTO> children;

}
