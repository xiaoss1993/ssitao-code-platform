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
 * 产品应用服务器管理 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_product_server")
public class TbProductServer extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String tbProductServerId;

    /**
     * 服务器名
     */
    private String serverName;

    /**
     * 服务器ip
     */
    private String serverIp;

    /**
     * 内存
     */
    private String serverMemory;

    /**
     * 实例占用内存
     */
    private String serverInstanceMemory;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 架构服务器主键
     */
    private String frameworkServerId;

    /**
     * 所属产品id
     */
    private String syProductId;

    /**
     * 所属产品名称
     */
    private String syProductName;

    /**
     * 所属产品code
     */
    private String syProductCode;

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
