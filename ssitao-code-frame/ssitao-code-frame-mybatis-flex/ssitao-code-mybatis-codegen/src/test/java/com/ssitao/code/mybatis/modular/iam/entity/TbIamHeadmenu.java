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
 * 顶部菜单 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_iam_headmenu")
public class TbIamHeadmenu extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String tbIamHeadmenuId;

    /**
     * 菜单名称
     */
    private String headmenuName;

    /**
     * 菜单编码
     */
    private String headmenuCode;

    /**
     * 类型_code
     */
    private String headmenuTypeCode;

    /**
     * 类型_name
     */
    private String headmenuTypeName;

    /**
     * 图标
     */
    private String headmenuIcon;

    /**
     * 关联门户_id
     */
    private String headmenuPartolId;

    /**
     * 关联门户_name
     */
    private String headmenuPartolName;

    /**
     * 系统logo
     */
    private String headmenuSystemLogo;

    /**
     * 备注信息
     */
    private String headmenuRemark;

    /**
     * 是否公用_code
     */
    private String headmenuPublicCode;

    /**
     * 是否公用_name
     */
    private String headmenuPublicName;

    /**
     * 可见角色_id
     */
    private String headmenuSeeroleId;

    /**
     * 可见角色_name
     */
    private String headmenuSeeroleName;

    /**
     * 可见部门_id
     */
    private String headmenuSeedeptId;

    /**
     * 可见部门_name
     */
    private String headmenuSeedeptName;

    /**
     * 可见机构_id
     */
    private String headmenuSeeorgId;

    /**
     * 可见机构_name
     */
    private String headmenuSeeorgName;

    /**
     * 可见人_id
     */
    private String headmenuSeeuserId;

    /**
     * 可见人_name
     */
    private String headmenuSeeuserName;

    /**
     * 配置信息
     */
    private String headmenuConfiginfo;

    /**
     * 功能展示方式_code
     */
    private String headmenuDispalyCode;

    /**
     * 功能展示方式_name
     */
    private String headmenuDispalyName;

    /**
     * 初始菜单_id
     */
    private String headmenuInitmenuId;

    /**
     * 初始菜单_name
     */
    private String headmenuInitmenuName;

    /**
     * 修改人部门主键
     */
    private String syModifyorgid;

    /**
     * 修改人部门
     */
    private String syModifyorgname;

    /**
     * 修改人主键
     */
    private String syModifyuserid;

    /**
     * 修改人
     */
    private String syModifyusername;

    /**
     * 修改时间
     */
    private String syModifytime;

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
     * 所属产品_id
     */
    private String syProductId;

    /**
     * 所属产品_name
     */
    private String syProductName;

}
