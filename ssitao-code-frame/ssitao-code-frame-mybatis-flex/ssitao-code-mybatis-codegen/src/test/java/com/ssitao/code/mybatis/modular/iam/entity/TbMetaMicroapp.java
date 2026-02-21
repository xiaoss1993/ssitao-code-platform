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
 * 微应用管理 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_meta_microapp")
public class TbMetaMicroapp extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String tbMetaMicroappId;

    /**
     * 应用编码
     */
    private String microappCode;

    /**
     * 应用名称
     */
    private String microappName;

    /**
     * 应用入口
     */
    private String microappEntry;

    /**
     * 应用激活路由
     */
    private String microappActiveroute;

    /**
     * 激活方式
     */
    private String microappActivetype;

    /**
     * 备注
     */
    private String microappRemark;

    /**
     * 平台应用
     */
    private String microappPtyy;

    /**
     * 激活菜单类型_name
     */
    private String microappMenutypeName;

    /**
     * 激活菜单类型
     */
    private String microappMenutypeCode;

    /**
     * 所属产品_id
     */
    private String syProductId;

    /**
     * 所属产品_code
     */
    private String syProductCode;

    /**
     * 所属产品_name
     */
    private String syProductName;

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
     * 修改人部门主键
     */
    private String syModifyorgid;

    /**
     * 修改人部门
     */
    private String syModifyorgname;

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
