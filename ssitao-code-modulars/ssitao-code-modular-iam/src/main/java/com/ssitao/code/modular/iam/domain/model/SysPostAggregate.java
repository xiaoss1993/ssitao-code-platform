package com.ssitao.code.modular.iam.domain.model;

import com.ssitao.code.frame.aggregate.entity.AbstractAggregateRoot;
import lombok.Getter;

import java.util.Date;

/**
 * 岗位聚合根
 */
@Getter
public class SysPostAggregate extends AbstractAggregateRoot<Long> {

    private static final long serialVersionUID = 1L;

    /** 版本号（用于乐观锁） */
    private long version = 1;

    /** 岗位ID */
    private Long postId;

    /** 岗位编码 */
    private String postCode;

    /** 岗位名称 */
    private String postName;

    /** 岗位排序 */
    private String postSort;

    /** 状态（0正常 1停用） */
    private String status;

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

    public SysPostAggregate() {
    }

    public SysPostAggregate(Long postId) {
        this.postId = postId;
    }

    @Override
    public Long getId() {
        return postId;
    }

    @Override
    public void setId(Long id) {
        this.postId = id;
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
     * 创建岗位
     */
    public void createPost(String postCode, String postName, String postSort,
                          String status, String createBy) {
        this.postCode = postCode;
        this.postName = postName;
        this.postSort = postSort;
        this.status = status != null ? status : "0";
        this.createBy = createBy;
        this.createTime = new Date();
    }

    /**
     * 更新岗位信息
     */
    public void updatePost(String postCode, String postName, String postSort,
                          String status, String updateBy) {
        this.postCode = postCode;
        this.postName = postName;
        this.postSort = postSort;
        this.status = status;
        this.updateBy = updateBy;
        this.updateTime = new Date();
    }

    /**
     * 修改岗位状态
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

    // Setters for aggregate operations
    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public void setPostSort(String postSort) {
        this.postSort = postSort;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateBy() {
        return createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public String getRemark() {
        return remark;
    }
}
