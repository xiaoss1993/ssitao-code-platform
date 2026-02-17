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
 * 钉钉应用管理 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_iam_dingtalk_app")
public class TbIamDingtalkApp extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String tbIamDingtalkAppId;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 应用秘钥
     */
    private String appSecret;

    /**
     * 应用key
     */
    private String appKey;

    /**
     * pc入口地址
     */
    private String appPcIndex;

    /**
     * h5入口地址
     */
    private String appH5Index;

    /**
     * pc登录地址
     */
    private String appPcLogin;

    /**
     * h5登录地址
     */
    private String appH5Login;

    /**
     * 应用描述
     */
    private String appRemark;

    /**
     * pc入口参数
     */
    private String appPcParams;

    /**
     * h5入口参数
     */
    private String appH5Params;

    /**
     * 菜单_id
     */
    private String appMenuId;

    /**
     * 菜单名称
     */
    private String appMenuName;

    /**
     * 移动端菜单_id
     */
    private String appAppmenuId;

    /**
     * 移动端菜单_name
     */
    private String appAppmenuName;

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
