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
 * 账号部门角色 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_iam_accountrole")
public class TbIamAccountrole extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String tbIamAccountroleId;

    /**
     * 角色_id
     */
    private String accountroleRoleId;

    /**
     * 账号_id
     */
    private String accountroleAccountId;

    /**
     * 角色_name
     */
    private String accountroleRoleName;

    /**
     * 所属部门_id
     */
    private String accountroleDeptId;

    /**
     * 所属部门_name
     */
    private String accountroleDeptName;

    /**
     * 是否主部门_code
     */
    private String accountroleMainCode;

    /**
     * 登记时间
     */
    private String syCreatetime;

    /**
     * 数据状态
     */
    private String syStatus;

    /**
     * 排序字段
     */
    private String syOrderindex;

    /**
     * 租户_id
     */
    private String syTenantId;

    /**
     * 租户名称
     */
    private String syTenantName;

    /**
     * 登记人
     */
    private String syCreateusername;

}
