package com.ssitao.code.modular.iam.organization.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 公司数据对象
 * 对应表：iam_company
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("iam_company")
public class IamCompanyDO {

    /**
     * 公司ID
     */
    @Id(keyType = KeyType.Auto)
    private String companyId;

    /**
     * 公司编码
     */
    private String companyCode;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 公司简称
     */
    private String companyShortName;

    /**
     * 公司类型
     */
    private String companyType;

    /**
     * 公司级别
     */
    private Integer companyLevel;

    /**
     * 父公司ID
     */
    private String companyParentId;

    /**
     * 公司地址
     */
    private String companyAddress;

    /**
     * 公司电话
     */
    private String companyPhone;

    /**
     * 公司邮箱
     */
    private String companyMail;

    /**
     * 公司网站
     */
    private String companyWebsite;

    /**
     * 公司LOGO
     */
    private String companyLogo;

    /**
     * 法人代表
     */
    private String companyLegalRep;

    /**
     * 工商注册号
     */
    private String companyRegistrationNo;

    /**
     * 税号
     */
    private String companyTaxNo;

    /**
     * 公司状态: 0-停用, 1-启用
     */
    private Integer companyStatus;

    /**
     * 公司描述
     */
    private String companyDesc;

    /**
     * 排序号
     */
    private Integer companySort;

    /**
     * 树形路径
     */
    private String companyTreePath;

    /**
     * 树形层级
     */
    private Integer companyTreeLevel;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人ID
     */
    private String createUserId;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 修改人ID
     */
    private String modifyUserId;

    /**
     * 是否删除: 0-否, 1-是
     */
    private Integer isDeleted;

    /**
     * 版本号
     */
    private Integer version;

}
