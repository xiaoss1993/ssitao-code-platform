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
 * 新登录日志 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_iam_loginlog")
public class TbIamLoginlog extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String tbIamLoginlogId;

    /**
     * 账号_name
     */
    private String loginlogAccountName;

    /**
     * 设备标识
     */
    private String loginlogDevice;

    /**
     * 账号_code
     */
    private String loginlogAccountCode;

    /**
     * 登录方式_code
     */
    private String loginlogTypeCode;

    /**
     * 登录方式_name
     */
    private String loginlogTypeName;

    /**
     * 操作类型_code
     */
    private String loginlogOptypeCode;

    /**
     * 操作类型_name
     */
    private String loginlogOptypeName;

    /**
     * 备注信息
     */
    private String loginlogBzxx;

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
     * 数据状态
     */
    private String syStatus;

    /**
     * 排序字段
     */
    private Integer syOrderindex;

    /**
     * 账号_id
     */
    private String syAccountId;

    /**
     * 所属公司_id
     */
    private String syCompanyId;

    /**
     * 所属公司_name
     */
    private String syCompanyName;

    /**
     * 所属集团公司_id
     */
    private String syGroupCompanyId;

    /**
     * 所属集团公司_name
     */
    private String syGroupCompanyName;

    /**
     * 所属机构_id
     */
    private String syOrgId;

    /**
     * 所属机构_name
     */
    private String syOrgName;

    /**
     * 租户_id
     */
    private String syTenantId;

    /**
     * 租户名称
     */
    private String syTenantName;

}
