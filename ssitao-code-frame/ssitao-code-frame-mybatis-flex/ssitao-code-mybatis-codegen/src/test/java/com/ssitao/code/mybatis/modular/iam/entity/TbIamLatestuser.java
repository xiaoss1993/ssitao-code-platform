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
 * 常用人员 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_iam_latestuser")
public class TbIamLatestuser extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账号部门_id
     */
    private String tbIamAccountdeptId;

    /**
     * 主键id
     */
    @Id
    private String tbIamLatestuserId;

    /**
     * 人员主键_id
     */
    private String tbIamUserId;

    /**
     * 部门主键_id
     */
    private String tbIamDeptId;

    /**
     * 常用人员_id
     */
    private String latestuserUserId;

    /**
     * 常用人员部门_id
     */
    private String latestuserDeptId;

    /**
     * 常用人员_name
     */
    private String latestuserUserName;

    /**
     * 常用人员部门_name
     */
    private String latestuserDeptName;

    /**
     * 选择次数
     */
    private Integer latestuserCount;

    /**
     * 常用人员_code
     */
    private String latestuserUserCode;

    /**
     * 常用人员部门_code
     */
    private String latestuserDeptCode;

    /**
     * 关联用户_id
     */
    private String userAssociationId;

    /**
     * 电话
     */
    private String accountPhone;

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
