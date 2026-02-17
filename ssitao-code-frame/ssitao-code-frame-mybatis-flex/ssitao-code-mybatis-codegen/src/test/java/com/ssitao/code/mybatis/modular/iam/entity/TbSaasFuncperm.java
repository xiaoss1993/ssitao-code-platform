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
 * 租户数据权限 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_saas_funcperm")
public class TbSaasFuncperm extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String tbSaasFuncpermId;

    /**
     * 功能id
     */
    private String tbCoreFuncinfoId;

    /**
     * 功能名称
     */
    private String funcpermFuncname;

    /**
     * 功能编码
     */
    private String funcpermFunccode;

    /**
     * 使用关系
     */
    private String funcinfoUsecjglnames;

    /**
     * 使用关系id
     */
    private String funcinfoUsecjglids;

    /**
     * 可见人员
     */
    private String funcinfoSeeusernames;

    /**
     * 人员id
     */
    private String funcinfoSeeuserids;

    /**
     * 可见部门
     */
    private String funcinfoSeedeptnames;

    /**
     * 部门id
     */
    private String funcinfoSeedeptids;

    /**
     * 可见角色
     */
    private String funcinfoSeerolenames;

    /**
     * 角色id
     */
    private String funcinfoSeeroleids;

    /**
     * 可见岗位
     */
    private String funcinfoSeesentrynames;

    /**
     * 岗位id
     */
    private String funcinfoSeesentryids;

    /**
     * 权限类型
     */
    private String funcinfoPermconfig;

    /**
     * 权限过滤条件
     */
    private String funcinfoPermsql;

    /**
     * 权限过滤条件脚本
     */
    private String funcinfoPermjs;

    /**
     * 控制人员字段
     */
    private String funcinfoUserfield;

    /**
     * 控制部门字段
     */
    private String funcinfoDeptfield;

    /**
     * 部门关系id
     */
    private String funcinfoDeptcjids;

    /**
     * 部门关系
     */
    private String funcinfoDeptcjnames;

    /**
     * 机构名称
     */
    private String funcpermOrgName;

    /**
     * 机构id
     */
    private String funcpermOrgId;

    /**
     * 人员id
     */
    private String funcpermUserId;

    /**
     * 角色名称
     */
    private String funcpermRoleName;

    /**
     * 角色id
     */
    private String funcpermRoleId;

    /**
     * 人员名称
     */
    private String funcpermUserName;

    /**
     * 部门id
     */
    private String funcpermDeptId;

    /**
     * 部门名称
     */
    private String funcpermDeptName;

    /**
     * 租户id
     */
    private String syZhid;

    /**
     * 租户名称
     */
    private String syZhmc;

}
