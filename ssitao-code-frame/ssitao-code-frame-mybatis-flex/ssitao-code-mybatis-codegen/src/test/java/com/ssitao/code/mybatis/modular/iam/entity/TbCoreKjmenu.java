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
 * 快捷菜单 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_kjmenu")
public class TbCoreKjmenu extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String tbCoreKjmenuId;

    /**
     * 菜单id
     */
    private String kjmenuCdid;

    /**
     * 用户ud
     */
    private String kjmenuYhud;

    /**
     * 菜单类型
     */
    private String kjmenuType;

    /**
     * appid
     */
    private String kjmenuAppid;

    /**
     * 登记部门主键
     */
    private String syCreateorgid;

    /**
     * 登记部门编码
     */
    private String syCreateorg;

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
     * 登记人编码
     */
    private String syCreateuser;

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
     * 所属产品_id
     */
    private String syProductId;

    /**
     * 所属产品_name
     */
    private String syProductName;

    /**
     * 产品编码
     */
    private String syProductCode;

    /**
     * 租户_id
     */
    private String syTenantId;

    /**
     * 租户名称
     */
    private String syTenantName;

}
