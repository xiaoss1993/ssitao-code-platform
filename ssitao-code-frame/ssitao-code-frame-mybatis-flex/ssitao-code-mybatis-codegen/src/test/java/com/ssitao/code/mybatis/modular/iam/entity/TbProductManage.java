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
 * 产品管理 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_product_manage")
public class TbProductManage extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String tbProductManageId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 编码
     */
    private String productCode;

    /**
     * 图标
     */
    private String productIcon;

    /**
     * 图标背景色
     */
    private String productIconBackground;

    /**
     * 图标颜色
     */
    private String productIconColor;

    /**
     * 版本
     */
    private String productVersion;

    /**
     * 产品描述
     */
    private String productDescribe;

    /**
     * 开发人员
     */
    private String productDeveloper;

    /**
     * 产品分类
     */
    private String productClassification;

    /**
     * 适用行业
     */
    private String productApplyIndustry;

    /**
     * 服务目录
     */
    private String productServiceList;

    /**
     * 类型
     */
    private String productType;

    /**
     * 启用
     */
    private String productEnableState;

    /**
     * 开放
     */
    private String productOpenState;

    /**
     * 开发人员id
     */
    private String productDeveloperId;

    /**
     * 开放业务授权
     */
    private String manageKfywsqCode;

    /**
     * 开放业务授权_name
     */
    private String manageKfywsqName;

    /**
     * 开放业务授权_id
     */
    private String manageKfywsqId;

    /**
     * 数据状态
     */
    private String syStatus;

    /**
     * 排序字段
     */
    private Integer syOrderindex;

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
