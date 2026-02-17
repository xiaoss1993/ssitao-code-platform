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
 * 角色权限关联 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_iam_roleperm")
public class TbIamRoleperm extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String tbIamRolepermId;

    /**
     * 角色主键
     */
    private String tbIamRoleId;

    /**
     * 权限主键
     */
    private String tbIamPermId;

    /**
     * 是否排他_code
     */
    private String rolepermExcludeCode;

    /**
     * 是否排他_name
     */
    private String rolepermExcludeName;

    /**
     * 授权方式_code
     */
    private String rolepermTypeCode;

    /**
     * 授权方式_name
     */
    private String rolepermTypeName;

    /**
     * 授权类型_code
     */
    private String rolepermGranttypeCode;

    /**
     * 授权类型_name
     */
    private String rolepermGranttypeName;

    /**
     * 是否不选中
     */
    private String rolepermNotChecked;

    /**
     * 租户_id
     */
    private String syTenantId;

    /**
     * 租户_name
     */
    private String syTenantName;

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
