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
 * sql模板库 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_qjsql")
public class TbCoreQjsql extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String tbCoreQjsqlId;

    /**
     * 模板功能描述
     */
    private String qjsqlMbgnms;

    /**
     * 模板编码
     */
    private String qjsqlMbbm;

    /**
     * 模板内容
     */
    private String qjsqlMbnr;

    /**
     * 启用
     */
    private String qjsqlQyCode;

    /**
     *  所属服务
     */
    private String qjsqlProjectName;

    /**
     *  所属服务_id
     */
    private String qjsqlProjectId;

    /**
     * 模板编写规范
     */
    private String qjsqlMbbxgf;

    /**
     * 执行策略_name
     */
    private String qjsqlSqljbzxclName;

    /**
     * 执行策略
     */
    private String qjsqlSqljbzxclCode;

    /**
     * 所属服务_code
     */
    private String qjsqlProjectCode;

    /**
     * 所属组
     */
    private String qjsqlSszName;

    /**
     * 所属模块_name
     */
    private String qjsqlSsmkName;

    /**
     * 所属模块
     */
    private String qjsqlSsmkCode;

    /**
     * 所属模块_id
     */
    private String qjsqlSsmkId;

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
