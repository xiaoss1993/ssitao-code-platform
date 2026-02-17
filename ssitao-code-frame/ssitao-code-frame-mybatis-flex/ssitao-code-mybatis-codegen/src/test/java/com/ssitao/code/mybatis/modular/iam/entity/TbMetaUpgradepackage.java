package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import com.ssitao.code.frame.mybatisflex.codegen.test.BaseEntity;
import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * 产品升级包管理 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_meta_upgradepackage")
public class TbMetaUpgradepackage extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String tbMetaUpgradepackageId;

    /**
     * 包名
     */
    private String upgradepackageName;

    /**
     * 版本
     */
    private String upgradepackageVersion;

    /**
     * 已打包_code
     */
    private String upgradepackagePackageCode;

    /**
     * 已打包_name
     */
    private String upgradepackagePackageName;

    /**
     * 压缩包
     */
    private String upgradepackagePackageFile;

    /**
     * 打包时间
     */
    private String upgradepackagePackageTime;

    /**
     * 升级包类型_code
     */
    private String upgradepackagePlatformCode;

    /**
     * 升级包类型_name
     */
    private String upgradepackagePlatformName;

    /**
     * 升级说明
     */
    private String upgradepackageRemark;

    /**
     * 打包人_id
     */
    private String upgradepackagePackageuserId;

    /**
     * 打包人_name
     */
    private String upgradepackagePackageuserName;

    /**
     * 登记部门主键
     */
    private String syCreateorgid;

    /**
     * 登记部门
     */
    private String syCreateorgname;

    /**
     * 登记时间
     */
    private String syCreatetime;

    /**
     * 登记人主键
     */
    private String syCreateuserid;

    /**
     * 登记人
     */
    private String syCreateusername;

    /**
     * 数据状态
     */
    private String syStatus;

    /**
     * 排序字段
     */
    private Integer syOrderindex;

    /**
     * 所属公司id
     */
    private String syCompanyId;

    /**
     * 所属公司名称
     */
    private String syCompanyName;

    /**
     * 所属集团公司id
     */
    private String syGroupCompanyId;

    /**
     * 所属集团公司名称
     */
    private String syGroupCompanyName;

    /**
     * 所属机构id
     */
    private String syOrgId;

    /**
     * 租户id
     */
    private String syTenantId;

    /**
     * 租户名称
     */
    private String syTenantName;

    /**
     * 产品名称
     */
    private String syProductName;

    /**
     * 产品主键
     */
    private String syProductId;

}
