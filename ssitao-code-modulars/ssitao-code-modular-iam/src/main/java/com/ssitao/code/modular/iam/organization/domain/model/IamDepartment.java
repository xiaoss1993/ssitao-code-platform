package com.ssitao.code.modular.iam.organization.domain.model;

import com.ssitao.code.frame.aggregate.domain.AbstractAggregateRoot;
import com.ssitao.code.modular.iam.organization.domain.event.DepartmentChildAddedEvent;
import com.ssitao.code.modular.iam.organization.domain.event.DepartmentCreatedEvent;
import com.ssitao.code.modular.iam.organization.domain.event.DepartmentDeletedEvent;
import com.ssitao.code.modular.iam.organization.domain.event.DepartmentDisabledEvent;
import com.ssitao.code.modular.iam.organization.domain.event.DepartmentEnabledEvent;
import com.ssitao.code.modular.iam.organization.domain.event.DepartmentLeaderUpdatedEvent;
import com.ssitao.code.modular.iam.organization.domain.event.DepartmentUpdatedEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * IAM部门聚合根
 * Organization领域的核心聚合根
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class IamDepartment extends AbstractAggregateRoot {

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
     * 层级
     */
    private Integer layer;

    /**
     * 路径
     */
    private String path;

    /**
     * 负责人ID
     */
    private String leaderId;

    /**
     * 负责人姓名
     */
    private String leaderName;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

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
     * 节点类型：FOLDER-文件夹 ITEM-项目
     */
    private String nodeType;

    /**
     * 节点信息
     */
    private String nodeInfo;

    /**
     * 节点信息类型
     */
    private String nodeInfoType;

    /**
     * 审核标记
     */
    private String audFlag;

    /**
     * 创建组织编码
     */
    private String createOrg;

    /**
     * 创建组织名称
     */
    private String createOrgName;

    /**
     * 启用标记
     */
    private String flag;

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
     * 创建人编码
     */
    private String createUser;

    /**
     * 创建人姓名
     */
    private String createUserName;

    /**
     * 修改人编码
     */
    private String modifyUser;

    /**
     * 修改人姓名
     */
    private String modifyUserName;

    /**
     * 流程实例ID
     */
    private String piid;

    /**
     * 流程定义ID
     */
    private String pdid;

    /**
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 子部门列表
     */
    private List<IamDepartment> children;

    /**
     * 创建部门（聚合根工厂方法）
     *
     * @param deptCode 部门编码
     * @param deptName 部门名称
     * @param tenantId 租户ID
     * @return 部门聚合根
     */
    public static IamDepartment create(String deptCode, String deptName, String tenantId) {
        IamDepartment department = new IamDepartment();
        department.setDeptCode(deptCode);
        department.setDeptName(deptName);
        department.setTenantId(tenantId);
        department.setStatus(true);
        department.setNodeType("ITEM");
        department.setLayer(0);
        department.setFlag("1");
        department.setDeleted(false);
        department.setChildren(new ArrayList<>());
        department.setCreateTime(LocalDateTime.now());
        department.setModifyTime(LocalDateTime.now());

        // 发布部门创建事件
        department.registerEvent(new DepartmentCreatedEvent(
                null, // 暂时没有ID，在Repository保存时会设置
                deptCode,
                deptName,
                tenantId
        ));

        return department;
    }

    /**
     * 添加子部门
     *
     * @param child 子部门
     */
    public void addChild(IamDepartment child) {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        this.children.add(child);
        child.setParentId(this.id);
        child.setLayer(this.layer + 1);

        // 发布子部门添加事件
        this.registerEvent(new DepartmentChildAddedEvent(
                this.id,
                child.getId(),
                this.tenantId
        ));

        this.incrementVersion();
    }

    /**
     * 设置负责人
     *
     * @param leaderId   负责人ID
     * @param leaderName 负责人姓名
     */
    public void setLeader(String leaderId, String leaderName) {
        String oldLeaderId = this.leaderId;
        this.leaderId = leaderId;
        this.leaderName = leaderName;

        // 发布负责人更新事件
        this.registerEvent(new DepartmentLeaderUpdatedEvent(
                this.id,
                oldLeaderId,
                leaderId,
                leaderName,
                this.tenantId
        ));

        this.incrementVersion();
    }

    /**
     * 启用部门
     */
    public void enable() {
        if (!this.status) {
            this.status = true;

            // 发布部门启用事件
            this.registerEvent(new DepartmentEnabledEvent(
                    this.id,
                    this.deptCode,
                    this.deptName,
                    this.tenantId
            ));

            this.incrementVersion();
        }
    }

    /**
     * 禁用部门
     */
    public void disable() {
        if (this.status) {
            this.status = false;

            // 发布部门禁用事件
            this.registerEvent(new DepartmentDisabledEvent(
                    this.id,
                    this.deptCode,
                    this.deptName,
                    this.tenantId
            ));

            this.incrementVersion();
        }
    }

    /**
     * 更新部门信息
     *
     * @param deptName 部门名称
     * @param phone    联系电话
     * @param email    邮箱
     * @param remark   备注
     */
    public void updateInfo(String deptName, String phone, String email, String remark) {
        String oldDeptName = this.deptName;
        this.deptName = deptName;
        this.phone = phone;
        this.email = email;
        this.remark = remark;

        // 发布部门更新事件
        this.registerEvent(new DepartmentUpdatedEvent(
                this.id,
                oldDeptName,
                deptName,
                this.tenantId
        ));

        this.incrementVersion();
    }

    /**
     * 删除部门
     */
    public void delete() {
        this.markAsDeleted();

        // 发布部门删除事件
        this.registerEvent(new DepartmentDeletedEvent(
                this.id,
                this.deptCode,
                this.deptName,
                this.tenantId
        ));

        this.incrementVersion();
    }

    /**
     * 判断是否可用
     *
     * @return true-可用，false-不可用
     */
    public boolean isAvailable() {
        return this.status && !this.deleted;
    }

    /**
     * 构建树形路径
     *
     * @param parentPath 父路径
     */
    public void buildPath(String parentPath) {
        if (parentPath == null || parentPath.isEmpty()) {
            this.path = String.valueOf(this.id);
        } else {
            this.path = parentPath + "/" + this.id;
        }

        if (this.children != null && !this.children.isEmpty()) {
            for (IamDepartment child : this.children) {
                child.buildPath(this.path);
            }
        }
    }

    /**
     * 获取层级深度
     *
     * @return 层级深度
     */
    public int getDepth() {
        return this.layer != null ? this.layer : 0;
    }

}
