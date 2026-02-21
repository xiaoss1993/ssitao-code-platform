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
 * 列打印方案 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_resourcecolumn_print_plan")
public class TbCoreResourcecolumnPrintPlan extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String tbCoreResourcecolumnPrintPlanId;

    /**
     * 打印配置
     */
    private String planPrintConfig;

    /**
     * 功能外键
     */
    private String planFuncinfoId;

    /**
     * saas产品
     */
    private String planSaasPid;

    /**
     * 方案名称
     */
    private String planName;

    /**
     * 方案编码
     */
    private String planCode;

    /**
     * 是否默认方案
     */
    private String planDefault;

    /**
     * 备注
     */
    private String planRemark;

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
     * 租户名称
     */
    private String syTenantName;

    /**
     * 租户编码
     */
    private String syTenantCode;

    /**
     * 租户id
     */
    private String syTenantId;

}
