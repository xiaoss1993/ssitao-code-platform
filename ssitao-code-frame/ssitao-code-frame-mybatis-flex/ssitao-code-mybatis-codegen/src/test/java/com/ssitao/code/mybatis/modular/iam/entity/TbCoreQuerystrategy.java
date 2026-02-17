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
 * 查询策略 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_querystrategy")
public class TbCoreQuerystrategy extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String tbCoreQuerystrategyId;

    /**
     * 功能编码
     */
    private String querystrategyFunccode;

    /**
     * 名称
     */
    private String querystrategyName;

    /**
     * sql
     */
    private String querystrategySql;

    /**
     * 用户id
     */
    private String querystrategyUserid;

    /**
     * 默认
     */
    private String querystrategyDef;

    /**
     * 外键
     */
    private String querystrategyFuncinfoId;

    /**
     * 覆盖功能sql
     */
    private String querystrategyFggnsql;

    /**
     * 方法
     */
    private String querystrategyFn;

    /**
     * 英文名
     */
    private String querystrategyNameEn;

    /**
     * sql备注
     */
    private String querystrategySqlRemarks;

    /**
     * 其他语种
     */
    private String querystrategyQtyz;

    /**
     * saas产品
     */
    private String querystrategySaasPid;

    /**
     * 登记者所在部门id
     */
    private String syCreateorgid;

    /**
     * 登记人id
     */
    private String syCreateuserid;

    /**
     * 租户_id
     */
    private String syTenantId;

    /**
     * 租户名称
     */
    private String syTenantName;

    /**
     * 登记者所在部门
     */
    private String syCreateorgname;

    /**
     * 登记时间
     */
    private String syCreatetime;

    /**
     * 登记人
     */
    private String syCreateusername;

    /**
     * 排序字段
     */
    private Integer syOrderindex;

    /**
     * 数据状态
     */
    private String syStatus;

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

}
