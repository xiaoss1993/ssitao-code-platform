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
 * 数据流转策略 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_dataflow")
public class TbCoreDataflow extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String tbCoreDataflowId;

    /**
     * 启用条件
     */
    private String dataflowQytj;

    /**
     * 动作
     */
    private String dataflowDz;

    /**
     * 操作
     */
    private String dataflowCz;

    /**
     * sql
     */
    private String dataflowSql;

    /**
     * 回调
     */
    private String dataflowHd;

    /**
     * 操作方式
     */
    private String dataflowCzfs;

    /**
     * 功能id
     */
    private String dataflowFuncinfoId;

    /**
     * saas产品
     */
    private String dataflowSaasPid;

    /**
     * 备注
     */
    private String dataflowBz;

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
     * 租户_id
     */
    private String syTenantId;

    /**
     * 租户名称
     */
    private String syTenantName;

}
