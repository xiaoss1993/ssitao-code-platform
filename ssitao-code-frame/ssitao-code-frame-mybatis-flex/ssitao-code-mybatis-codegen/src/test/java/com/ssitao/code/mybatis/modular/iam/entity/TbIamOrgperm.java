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
 * 机构权限关联 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_iam_orgperm")
public class TbIamOrgperm extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限主键
     */
    private String tbIamPermId;

    /**
     * 机构主键
     */
    private String tbIamOrgId;

    /**
     * 是否排他_code
     */
    private String orgpermExcludeCode;

    /**
     * 是否排他_name
     */
    private String orgpermExcludeName;

    /**
     * 授权方式_code
     */
    private String orgpermTypeCode;

    /**
     * 授权方式_name
     */
    private String orgpermTypeName;

    /**
     * 授权类型_code
     */
    private String orgpermGranttypeCode;

    /**
     * 授权类型_name
     */
    private String orgpermGranttypeName;

    /**
     * 租户_id
     */
    private String syTenantId;

    /**
     * 租户_name
     */
    private String syTenantName;

    /**
     * 主键id
     */
    @Id
    private String tbIamOrgpermId;

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
