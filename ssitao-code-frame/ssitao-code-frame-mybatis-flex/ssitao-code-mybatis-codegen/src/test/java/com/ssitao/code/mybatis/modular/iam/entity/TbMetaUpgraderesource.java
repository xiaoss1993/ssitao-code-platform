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
 * 升级包资源管理 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_meta_upgraderesource")
public class TbMetaUpgraderesource extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String tbMetaUpgraderesourceId;

    /**
     * 升级包主键_id
     */
    private String tbMetaUpgradepackageId;

    /**
     * 资源类型_name
     */
    private String upgraderesourceTypeName;

    /**
     * 资源类型_code
     */
    private String upgraderesourceTypeCode;

    /**
     * 内容_name
     */
    private String upgraderesourceContentName;

    /**
     * 内容_code
     */
    private String upgraderesourceContentCode;

    /**
     * 备注信息
     */
    private String upgraderesourceRemark;

    /**
     * 内容_value
     */
    private String upgraderesourceContentValue;

    /**
     * 资源编码
     */
    private String upgraderesourceCode;

    /**
     * 内容编码
     */
    private String upgraderesourceContentBm;

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

}
