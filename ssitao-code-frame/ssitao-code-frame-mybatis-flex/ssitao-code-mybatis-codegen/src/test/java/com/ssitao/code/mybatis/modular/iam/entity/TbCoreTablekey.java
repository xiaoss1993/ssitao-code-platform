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
 * 资源_键管理 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_tablekey")
public class TbCoreTablekey extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String tbCoreTablekeyId;

    /**
     * 键编码
     */
    private String tablekeyCode;

    /**
     * 类型
     */
    private String tablekeyType;

    /**
     * 字段编码
     */
    private String tablekeyColumncode;

    /**
     * 关联表
     */
    private String tablekeyLinktable;

    /**
     * 关联字段
     */
    private String tablekeyLinecolumncode;

    /**
     * 关联类型
     */
    private String tablekeyLinetyle;

    /**
     * 是否检测
     */
    private String tablekeyChecked;

    /**
     * 是否强制性约束
     */
    private String tablekeyIsrestraint;

    /**
     * 是否已创建
     */
    private String tablekeyIscreate;

    /**
     * 资源表外键
     */
    private String tablekeyResourcetableId;

    /**
     * 表编码
     */
    private String tablekeyTablecode;

    /**
     * 分类
     */
    private String tablekeyClassify;

    /**
     * 字段名称
     */
    private String tablekeyColumnname;

    /**
     * 产品id
     */
    private String syProductId;

    /**
     * 产品名称
     */
    private String syProductName;

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
     * 流程实例id
     */
    private String syPiid;

    /**
     * 流程定义id
     */
    private String syPdid;

}
