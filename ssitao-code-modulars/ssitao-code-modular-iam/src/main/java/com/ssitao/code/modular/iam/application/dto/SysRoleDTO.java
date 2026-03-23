package com.ssitao.code.modular.iam.application.dto;

import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * 角色DTO
 */
@Data
public class SysRoleDTO {

    /** 角色ID */
    private Long roleId;

    /** 角色名称 */
    private String roleName;

    /** 角色权限 */
    private String roleKey;

    /** 角色排序 */
    private String roleSort;

    /** 数据范围 */
    private String dataScope;

    /** 角色状态（0正常 1停用） */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    private Date updateTime;

    /** 备注 */
    private String remark;

    /** 菜单组 */
    private Long[] menuIds;

    /** 部门组 */
    private Long[] deptIds;

    /** 角色菜单权限 */
    private Set<String> permissions;
}
