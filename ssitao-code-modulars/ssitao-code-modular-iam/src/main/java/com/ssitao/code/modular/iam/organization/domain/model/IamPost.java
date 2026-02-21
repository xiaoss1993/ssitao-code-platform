package com.ssitao.code.modular.iam.organization.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * IAM岗位聚合根
 * Organization领域的岗位管理
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IamPost {

    /**
     * 岗位ID
     */
    private String id;

    /**
     * 岗位编码
     */
    private String postCode;

    /**
     * 岗位名称
     */
    private String postName;

    /**
     * 岗位等级
     */
    private Integer postLevel;

    /**
     * 岗位类别
     */
    private String postCategory;

    /**
     * 部门ID
     */
    private String deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 排序
     */
    private Integer sortOrder;

    /**
     * 状态：1-启用 0-禁用
     */
    private Boolean status;

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
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 更新人
     */
    private String updater;

    /**
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 创建岗位
     *
     * @param postCode 岗位编码
     * @param postName 岗位名称
     * @param tenantId 租户ID
     * @return 岗位聚合根
     */
    public static IamPost create(String postCode, String postName, String tenantId) {
        IamPost post = new IamPost();
        post.setPostCode(postCode);
        post.setPostName(postName);
        post.setTenantId(tenantId);
        post.setStatus(true);
        post.setDeleted(false);
        post.setCreateTime(LocalDateTime.now());
        return post;
    }

    /**
     * 设置所属部门
     *
     * @param deptId   部门ID
     * @param deptName 部门名称
     */
    public void setDepartment(String deptId, String deptName) {
        this.deptId = deptId;
        this.deptName = deptName;
    }

    /**
     * 启用岗位
     */
    public void enable() {
        this.status = true;
    }

    /**
     * 禁用岗位
     */
    public void disable() {
        this.status = false;
    }

    /**
     * 判断是否可用
     *
     * @return true-可用，false-不可用
     */
    public boolean isAvailable() {
        return this.status && !this.deleted;
    }

}
