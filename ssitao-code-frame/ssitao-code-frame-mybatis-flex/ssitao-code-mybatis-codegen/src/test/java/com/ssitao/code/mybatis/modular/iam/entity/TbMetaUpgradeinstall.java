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
 * 升级包安装管理 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_meta_upgradeinstall")
public class TbMetaUpgradeinstall extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String tbMetaUpgradeinstallId;

    /**
     * 升级包主键
     */
    private String tbMetaUpgradepackageId;

    /**
     * 包名
     */
    private String upgradeinstallName;

    /**
     * 版本
     */
    private String upgradeinstallVersion;

    /**
     * 压缩包
     */
    private String upgradeinstallPackageFile;

    /**
     * 升级说明
     */
    private String upgradeinstallRemark;

    /**
     * 升级时间
     */
    private String upgradeinstallInstallTime;

    /**
     * 是否平台_code
     */
    private String upgradeinstallPlatformCode;

    /**
     * 是否平台_name
     */
    private String upgradeinstallPlatformName;

    /**
     * 打包时间
     */
    private String upgradeinstallPackageTime;

    /**
     * 安装状态_code
     */
    private String upgradeinstallInstallCode;

    /**
     * 安装状态_name
     */
    private String upgradeinstallInstallName;

    /**
     * 文件key
     */
    private String upgradeinstallFileKey;

    /**
     * 产品code
     */
    private String syProductCode;

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
