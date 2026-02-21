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
 * 产品资源申请明细 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("product_resource_application_details")
public class ProductResourceApplicationDetails extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String id;

    /**
     * 作用域
     */
    private String scope;

    /**
     * 服务器类型
     */
    private String serverType;

    /**
     * 服务器数量
     */
    private String serverNumber;

    /**
     * cpu核心数
     */
    private String cpuCores;

    /**
     * 内存
     */
    private String memory;

    /**
     * 硬盘
     */
    private String hardDisk;

    /**
     * 硬盘类型
     */
    private String hardDiskType;

    /**
     * 操作系统
     */
    private String system;

    /**
     * 产品资源申请id
     */
    private String productResourceApplicationId;

    /**
     * 作用域_name
     */
    private String scopeName;

    /**
     * 服务器类型_name
     */
    private String serverTypeName;

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

}
