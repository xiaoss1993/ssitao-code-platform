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
 * 三方组织管理 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_iam_cpdept")
public class TbIamCpdept extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String tbIamCpdeptId;

    /**
     * 节点名称
     */
    private String cpdeptText;

    /**
     * 节点编码
     */
    private String cpdeptCode;

    /**
     * 部门名称
     */
    private String cpdeptName;

    /**
     * 挂接部门_name
     */
    private String cpdeptGjbmName;

    /**
     * 挂接部门_id
     */
    private String cpdeptGjbmId;

    /**
     * 最近同步时间
     */
    private String cpdeptZjtbsj;

    /**
     * 是否挂接
     */
    private String cpdeptSfgjCode;

    /**
     * 第三方排序字段
     */
    private String cpdeptWxpx;

    /**
     * 第三方父部门id
     */
    private String cpdeptParentid;

    /**
     * 第三方部门id
     */
    private String cpdeptDeptid;

    /**
     * 类型
     */
    private String cpdeptType;

    /**
     * 批次号
     */
    private String cpdeptPch;

    /**
     * 部门全路径
     */
    private String cpdeptNamepath;

    /**
     * 父节点id
     */
    private String syParent;

    /**
     * 节点类型
     */
    private String syNodetype;

    /**
     * 层次
     */
    private Integer syLayer;

    /**
     * 树形路径
     */
    private String syPath;

    /**
     * 是否启用
     */
    private String syDisabled;

    /**
     * 树形排序字段
     */
    private String syTreeorderindex;

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
