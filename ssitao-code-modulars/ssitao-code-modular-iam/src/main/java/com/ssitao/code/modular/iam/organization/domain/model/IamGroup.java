package com.ssitao.code.modular.iam.organization.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * IAM集团聚合根
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IamGroup {

    /**
     * 集团ID
     */
    private String id;

    /**
     * 集团编码
     */
    private String groupCode;

    /**
     * 集团名称
     */
    private String groupName;

    /**
     * 负责人
     */
    private String leader;

    /**
     * 电话
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
     * 状态
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
     * 创建集团
     *
     * @param groupCode 集团编码
     * @param groupName 集团名称
     * @param tenantId  租户ID
     * @return 集团聚合根
     */
    public static IamGroup create(String groupCode, String groupName, String tenantId) {
        IamGroup group = new IamGroup();
        group.setGroupCode(groupCode);
        group.setGroupName(groupName);
        group.setTenantId(tenantId);
        group.setStatus(true);
        group.setDeleted(false);
        group.setCreateTime(LocalDateTime.now());
        return group;
    }
}
