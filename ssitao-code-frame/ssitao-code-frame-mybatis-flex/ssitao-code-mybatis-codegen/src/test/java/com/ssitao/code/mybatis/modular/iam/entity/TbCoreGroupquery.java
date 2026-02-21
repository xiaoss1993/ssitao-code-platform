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
 * 高级查询配置 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_groupquery")
public class TbCoreGroupquery extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String tbCoreGroupqueryId;

    /**
     * 编码
     */
    private String groupqueryBm;

    /**
     * 名称
     */
    private String groupqueryMc;

    /**
     * 默认查询类型
     */
    private String groupqueryCxlx;

    /**
     * 所占列数
     */
    private String groupquerySzls;

    /**
     * 宽度
     */
    private String groupqueryKd;

    /**
     * 标签宽度
     */
    private String groupqueryBqkd;

    /**
     * 标签隐藏
     */
    private String groupqueryBqyc;

    /**
     * 排序类型
     */
    private String groupqueryPxlx;

    /**
     * 排序
     */
    private String groupqueryPx;

    /**
     * 常用查询
     */
    private String groupqueryCycx;

    /**
     * 功能id
     */
    private String groupqueryGnid;

    /**
     * 英文名称
     */
    private String groupqueryYwmc;

    /**
     * 允许排序
     */
    private String groupqueryAllowSort;

    /**
     * 可选可编辑
     */
    private String groupqueryChoiceandedit;

    /**
     * 字段类型
     */
    private String groupqueryFieldType;

    /**
     * 展示查询类型
     */
    private String groupqueryShowQueryType;

    /**
     * saas产品
     */
    private String groupquerySaasPid;

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
