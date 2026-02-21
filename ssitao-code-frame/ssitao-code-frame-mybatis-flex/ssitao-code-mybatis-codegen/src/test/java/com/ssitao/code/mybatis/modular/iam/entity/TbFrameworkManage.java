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
 * 架构管理（暂时没用） 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_framework_manage")
public class TbFrameworkManage extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String tbFrameworkManageId;

    /**
     * 服务器名称
     */
    private String serverName;

    /**
     * 服务器ip
     */
    private String serverIp;

    /**
     * 服务器端口
     */
    private String serverPort;

    /**
     * 标准内存
     */
    private String memory;

    /**
     * 推荐实例数
     */
    private String recommendedInstancesNumber;

    /**
     * 已建实例数
     */
    private String createInstancesNumber;

    /**
     * 剩余实例数
     */
    private String surplusInstancesNumber;

    /**
     * 作用域
     */
    private String scope;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 状态
     */
    private String state;

    /**
     * 上架人
     */
    private String onlineUser;

    /**
     * 上架时间
     */
    private String onlineTime;

    /**
     * 审核标记
     */
    private String syAudflag;

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
     * 流程实例id
     */
    private String syPiid;

    /**
     * 流程定义id
     */
    private String syPdid;

    /**
     * 集团公司名称
     */
    private String syJtgsmc;

    /**
     * 集团公司id
     */
    private String syJtgsid;

}
