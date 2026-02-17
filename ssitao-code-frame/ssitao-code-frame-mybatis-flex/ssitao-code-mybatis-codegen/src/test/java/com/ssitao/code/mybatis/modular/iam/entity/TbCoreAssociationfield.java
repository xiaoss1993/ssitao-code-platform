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
 * 主子功能关联字段 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_associationfield")
public class TbCoreAssociationfield extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String tbCoreAssociationfieldId;

    /**
     * 主字段名称
     */
    private String associationfieldPrifieldname;

    /**
     * 主字段编码
     */
    private String associationfieldPrifieldcode;

    /**
     * 子字段名称
     */
    private String associationfieldChifieldname;

    /**
     * 子字段编码
     */
    private String associationfieldChifieldcode;

    /**
     * where条件
     */
    private String associationfieldWherecon;

    /**
     * 关联关系
     */
    private String associationfieldAssociation;

    /**
     * 传值
     */
    private String associationfieldTransmit;

    /**
     * 说明
     */
    private String associationfieldRemark;

    /**
     * 主功能主键
     */
    private String associationfieldFid;

    /**
     * 子功能主键
     */
    private String associationfieldPid;

    /**
     * 是否硬编码
     */
    private String associationfieldHard;

    /**
     * 级联更新
     */
    private String associationfieldCascadeupdate;

    /**
     * 删除子单
     */
    private String associationfieldDelchild;

    /**
     * 系统模式
     */
    private String associationfieldSysmode;

    /**
     * 外键
     */
    private String associationfieldFuncrelatId;

    /**
     * 自定义sql
     */
    private String associationfieldSql;

    /**
     * saas产品
     */
    private String associationfieldSaasPid;

    /**
     * 登记者所在部门
     */
    private String syCreateorgname;

    /**
     * 登记时间
     */
    private String syCreatetime;

    /**
     * 登记人
     */
    private String syCreateusername;

    /**
     * 是否启用本条数据
     */
    private String syFlag;

    /**
     * 修改人部门
     */
    private String syModifyorgname;

    /**
     * 修改时间
     */
    private String syModifytime;

    /**
     * 修改人
     */
    private String syModifyusername;

    /**
     * 数据状态
     */
    private String syStatus;

    /**
     * 排序字段
     */
    private Integer syOrderindex;

    /**
     * 拼音简写
     */
    private String syPyjz;

    /**
     * 拼音全称
     */
    private String syPyqc;

    /**
     * 表单上传虚字段
     */
    private String syFormuploadfiles;

    /**
     * 登记者所在部门id
     */
    private String syCreateorgid;

    /**
     * 登记人id
     */
    private String syCreateuserid;

    /**
     * 修改人部门id
     */
    private String syModifyorgid;

    /**
     * 修改人id
     */
    private String syModifyuserid;

    /**
     * 租户_id
     */
    private String syTenantId;

    /**
     * 租户名称
     */
    private String syTenantName;

}
