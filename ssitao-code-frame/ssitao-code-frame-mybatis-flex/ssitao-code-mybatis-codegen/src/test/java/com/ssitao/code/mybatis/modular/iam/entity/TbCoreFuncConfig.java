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
 * 功能业务配置 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_func_config")
public class TbCoreFuncConfig extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 功能主键
     */
    private String tbCoreFuncinfoId;

    /**
     * 主键id
     */
    @Id
    private String tbCoreFuncConfigId;

    /**
     * 功能code
     */
    private String configFunccode;

    /**
     * 业务分类
     */
    private String configType;

    /**
     * 配置信息
     */
    private String configData;

    /**
     * 事件脚本
     */
    private String configScript;

    /**
     * 功能表名
     */
    private String configTablename;

    /**
     * 备注
     */
    private String configBz;

    /**
     * saas产品
     */
    private String configSaasPid;

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
