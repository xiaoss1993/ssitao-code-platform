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
 * 三方人员管理 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_iam_cpuser")
public class TbIamCpuser extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String tbIamCpuserId;

    /**
     * 第三方部门_外键id
     */
    private String tbIamCpdeptId;

    /**
     * 用户
     */
    private String cpuserUserName;

    /**
     * 用户id
     */
    private String cpuserUserId;

    /**
     * 最近同步时间
     */
    private String cpuserZjtbsj;

    /**
     * 是否挂接
     */
    private String cpuserSfgjCode;

    /**
     * 挂接本地用户
     */
    private String cpuserBdyh;

    /**
     * 挂接本地用户_id
     */
    private String cpuserBdyhId;

    /**
     * 第三方部门
     */
    private String cpuserCpdept;

    /**
     * 职务
     */
    private String cpuserZw;

    /**
     * 账号
     */
    private String cpuserZh;

    /**
     * 性别
     */
    private String cpuserSexCode;

    /**
     * 手机
     */
    private String cpuserPhone;

    /**
     * 座机
     */
    private String cpuserZj;

    /**
     * 邮箱
     */
    private String cpuserEmail;

    /**
     * 英文名
     */
    private String cpuserYwm;

    /**
     * 身份
     */
    private String cpuserSf;

    /**
     * 可用
     */
    private String cpuserEnable;

    /**
     * 是否加入
     */
    private String cpuserSfjr;

    /**
     * 头像
     */
    private String cpuserTx;

    /**
     * 类型
     */
    private String cpuserType;

    /**
     * 批次号
     */
    private String cpuserPch;

    /**
     * 岗位
     */
    private String cpuserPosition;

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
