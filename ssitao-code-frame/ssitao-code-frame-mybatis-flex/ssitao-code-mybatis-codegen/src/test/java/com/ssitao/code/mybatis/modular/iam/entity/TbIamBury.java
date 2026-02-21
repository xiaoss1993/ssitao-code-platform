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
 * 操作埋点记录 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_iam_bury")
public class TbIamBury extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 操作账号_id
     */
    private String tbIamAccountId;

    /**
     * 主键id
     */
    @Id
    private String tbIamBuryId;

    /**
     * 操作对象_code
     */
    private String buryObjectCode;

    /**
     * 操作对象_name
     */
    private String buryObjectName;

    /**
     * 操作类型_code
     */
    private String buryTypeCode;

    /**
     * 操作类型_name
     */
    private String buryTypeName;

    /**
     * 操作账号_name
     */
    private String buryAccountName;

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
     * 所属机构_id
     */
    private String syOrgId;

    /**
     * 所属公司_id
     */
    private String syCompanyId;

    /**
     * 所属公司_nane
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
     * 租户_id
     */
    private String syTenantId;

    /**
     * 租户名称
     */
    private String syTenantName;

}
