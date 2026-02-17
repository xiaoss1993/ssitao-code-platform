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
 * 列方案 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_resourcecolumn_plan")
public class TbCoreResourcecolumnPlan extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String tbCoreResourcecolumnPlanId;

    /**
     * 名称
     */
    private String planName;

    /**
     * 功能外键
     */
    private String planFuncinfoId;

    /**
     * 方案编码
     */
    private String planCode;

    /**
     * 类型
     */
    private String planType;

    /**
     * 是否默认方案
     */
    private String planDefault;

    /**
     * 备注
     */
    private String planRemark;

    /**
     * 是否系统方案
     */
    private String planSy;

    /**
     * 父id
     */
    private String planParentId;

    /**
     * 提示类型
     */
    private String planTipType;

    /**
     * 租户id
     */
    private String syTenantId;

    /**
     * 租户编码
     */
    private String syTenantCode;

    /**
     * 租户名称
     */
    private String syTenantName;

    /**
     * saas产品
     */
    private String planSaasPid;

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

}
