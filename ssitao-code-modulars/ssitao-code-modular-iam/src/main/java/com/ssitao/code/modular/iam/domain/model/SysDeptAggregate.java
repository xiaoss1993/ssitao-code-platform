package com.ssitao.code.modular.iam.domain.model;

import com.ssitao.code.frame.aggregate.entity.AbstractAggregateRoot;
import com.ssitao.code.modular.iam.domain.event.DeptCreatedEvent;
import com.ssitao.code.modular.iam.domain.event.DeptMovedEvent;
import lombok.Getter;

import java.util.Date;

/**
 * 部门聚合根
 */
@Getter
public class SysDeptAggregate extends AbstractAggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    /** 版本号（用于乐观锁） */
    private long version = 1;

    /** 部门ID */
    private Long deptId;

    /** 父部门ID */
    private Long parentId;

    /** 祖级列表 */
    private String ancestors;

    /** 部门名称 */
    private String deptName;

    /** 显示顺序 */
    private Integer orderNum;

    /** 负责人 */
    private String leader;

    /** 联系电话 */
    private String phone;

    /** 邮箱 */
    private String email;

    /** 部门状态:0正常,1停用 */
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

    public SysDeptAggregate() {
    }

    public SysDeptAggregate(Long deptId) {
        this.deptId = deptId;
    }

    @Override
    public Long getId() {
        return deptId;
    }

    @Override
    public void setId(Long id) {
        this.deptId = id;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public Date getLastUpdateTime() {
        return updateTime;
    }

    /**
     * 创建部门
     */
    public void createDept(Long deptId, String deptName, Long parentId, Integer orderNum,
                          String leader, String phone, String email, String status, String createBy) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.parentId = parentId;
        this.orderNum = orderNum;
        this.leader = leader;
        this.phone = phone;
        this.email = email;
        this.status = status != null ? status : "0";
        this.delFlag = "0";
        this.createBy = createBy;
        this.createTime = new Date();
        this.ancestors = calculateAncestors(parentId);

        apply(new DeptCreatedEvent(deptId, deptName, parentId, createBy, createTime));
    }

    /**
     * 更新部门信息
     */
    public void updateDept(String deptName, Integer orderNum, String leader,
                          String phone, String email, String status, String updateBy) {
        this.deptName = deptName;
        this.orderNum = orderNum;
        this.leader = leader;
        this.phone = phone;
        this.email = email;
        this.status = status;
        this.updateBy = updateBy;
        this.updateTime = new Date();
    }

    /**
     * 移动部门
     */
    public void moveDept(Long newParentId, String ancestors, String updateBy) {
        Long oldParentId = this.parentId;
        this.parentId = newParentId;
        this.ancestors = ancestors;
        this.updateBy = updateBy;
        this.updateTime = new Date();

        apply(new DeptMovedEvent(deptId, deptName, oldParentId, newParentId, ancestors, updateBy, updateTime));
    }

    /**
     * 修改部门状态
     */
    public void changeStatus(String status, String updateBy) {
        this.status = status;
        this.updateBy = updateBy;
        this.updateTime = new Date();
    }

    /**
     * 是否为正常状态
     */
    public boolean isNormal() {
        return "0".equals(this.status);
    }

    /**
     * 是否已删除
     */
    public boolean isDeleted() {
        return "2".equals(this.delFlag);
    }

    /**
     * 是否为根部门
     */
    public boolean isRoot() {
        return this.parentId != null && this.parentId == 0L;
    }

    /**
     * 标记删除
     */
    public void markDeleted(String updateBy) {
        this.delFlag = "2";
        this.updateBy = updateBy;
        this.updateTime = new Date();
    }

    /**
     * 计算祖级列表
     */
    private String calculateAncestors(Long parentId) {
        if (parentId == null || parentId == 0L) {
            return "0";
        }
        return "0," + parentId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
