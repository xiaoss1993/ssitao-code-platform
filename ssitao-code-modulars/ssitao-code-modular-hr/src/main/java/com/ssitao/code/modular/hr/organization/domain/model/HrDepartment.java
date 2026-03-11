package com.ssitao.code.modular.hr.organization.domain.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 部门领域模型
 *
 * @author ssitao
 */
@Data
public class HrDepartment {

    /**
     * 部门ID
     */
    private String id;

    /**
     * 部门编码
     */
    private String deptCode;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 父部门ID
     */
    private String parentId;

    /**
     * 部门层级
     */
    private Integer deptLevel;

    /**
     * 部门路径
     */
    private String deptPath;

    /**
     * 排序
     */
    private Integer deptSort;

    /**
     * 部门负责人
     */
    private String deptLeader;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态: NORMAL-正常, DISABLED-停用
     */
    private String status;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private String updater;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否删除: 0-否, 1-是
     */
    private Integer deleted;

    /**
     * 子部门列表
     */
    private java.util.List<HrDepartment> children;

    /**
     * 创建部门
     */
    public static HrDepartment create(String deptCode, String deptName, String tenantId) {
        HrDepartment dept = new HrDepartment();
        dept.id = java.util.UUID.randomUUID().toString().replace("-", "");
        dept.deptCode = deptCode;
        dept.deptName = deptName;
        dept.tenantId = tenantId;
        dept.status = "NORMAL";
        dept.deleted = 0;
        dept.deptLevel = 1;
        dept.parentId = "0";
        dept.deptSort = 0;
        dept.createTime = LocalDateTime.now();
        return dept;
    }

    /**
     * 构建树形路径
     */
    public void buildPath(String parentPath) {
        if (parentPath == null || parentPath.isEmpty()) {
            this.deptPath = "/" + this.id;
        } else {
            this.deptPath = parentPath + "/" + this.id;
        }
    }

    /**
     * 启用部门
     */
    public void enable() {
        this.status = "NORMAL";
    }

    /**
     * 禁用部门
     */
    public void disable() {
        this.status = "DISABLED";
    }
}
