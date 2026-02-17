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
 * 全局脚本库 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_commjs")
public class TbCoreCommjs extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String tbCoreCommjsId;

    /**
     * 脚本
     */
    private String commjsCode;

    /**
     * 功能描述
     */
    private String commjsGnms;

    /**
     * 方法体
     */
    private String commjsFft;

    /**
     * 启用_name
     */
    private String commjsQyName;

    /**
     * 启用
     */
    private String commjsQyCode;

    /**
     * 返回值
     */
    private String commjsFhz;

    /**
     * 个人脚本库分类_name
     */
    private String commjsTypesName;

    /**
     * 个人脚本库分类
     */
    private String commjsTypesCode;

    /**
     * 初始化执行
     */
    private String commjsCshzx;

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
     * 名称名称
     */
    private String syNamrMame;

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

}
