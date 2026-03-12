package com.ssitao.code.modular.iam.system.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 租户数据对象
 * 对应表：iam_tenant
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("iam_tenant")
public class IamTenantDO {

    /**
     * 租户ID
     */
    @Id(keyType = KeyType.None)
    private String id;

    /**
     * 租户编码
     */
    private String tenantCode;

    /**
     * 租户名称
     */
    private String tenantName;

    /**
     * 租户类型：TRIAL-试用 STANDARD-标准 ENTERPRISE-企业
     */
    private String tenantType;

    /**
     * 联系人
     */
    private String contact;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 联系邮箱
     */
    private String contactEmail;

    /**
     * 公司地址
     */
    private String address;

    /**
     * 租户状态：NORMAL-正常 EXPIRED-过期 DISABLED-禁用
     */
    private String tenantStatus;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    /**
     * 用户数量限制
     */
    private Integer userLimit;

    /**
     * 当前用户数量
     */
    private Integer currentUserCount;

    /**
     * 存储空间限制(MB)
     */
    private Long storageLimit;

    /**
     * 已使用存储空间(MB)
     */
    private Long usedStorage;

    /**
     * Logo URL
     */
    private String logoUrl;

    /**
     * 网站域名
     */
    private String domain;

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
     * 是否删除: 0-否, 1-是
     */
    private Integer deleted;

}
