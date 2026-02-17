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
 * 数据权限配置 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_funcperm_config")
public class TbCoreFuncpermConfig extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String tbCoreFuncpermConfigId;

    /**
     * 外键
     */
    private String configFuncpermId;

    /**
     * 数据类型
     */
    private String configType;

    /**
     * 配置信息
     */
    private String configInfo;

    /**
     * 数据id
     */
    private String configDataId;

    /**
     * 权限类型
     */
    private String configPermType;

    /**
     * 控制范围
     */
    private String configControlScope;

    /**
     * 选择控制
     */
    private String configSelectScope;

    /**
     * saas产品
     */
    private String configSaasPid;

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
