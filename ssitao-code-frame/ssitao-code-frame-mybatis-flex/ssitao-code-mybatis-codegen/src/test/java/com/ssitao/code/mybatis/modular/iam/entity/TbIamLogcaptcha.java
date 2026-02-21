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
 * 登录验证码 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_iam_logcaptcha")
public class TbIamLogcaptcha extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账号_id
     */
    private String tbIamAccountId;

    /**
     * 主键id
     */
    @Id
    private String tbIamLogcaptchaId;

    /**
     * 验证码
     */
    private String logcaptchaNum;

    /**
     * 实效时间
     */
    private String logcaptchaExpiretime;

    /**
     * 手机号
     */
    private String logcaptchaPhone;

    /**
     * 账号_name
     */
    private String logcaptchaAccountName;

    /**
     * 登录设备
     */
    private String logcaptchaDevice;

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

}
