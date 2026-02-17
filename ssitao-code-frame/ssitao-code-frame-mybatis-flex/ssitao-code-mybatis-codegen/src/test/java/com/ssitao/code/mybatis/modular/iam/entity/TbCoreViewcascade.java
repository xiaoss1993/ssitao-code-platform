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
 * 资源_级联关系 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_viewcascade")
public class TbCoreViewcascade extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String tbCoreViewcascadeId;

    /**
     * 资源表主键_id
     */
    private String tbCoreResourcetableId;

    /**
     * 源表编码
     */
    private String viewcascadeYbbm;

    /**
     * 源表字段
     */
    private String viewcascadeYbzd;

    /**
     * 目标表编码
     */
    private String viewcascadeMbbbm;

    /**
     * 关联关系_code
     */
    private String viewcascadeGlgxCode;

    /**
     * 关联关系_name
     */
    private String viewcascadeGlgxName;

    /**
     * 目标表字段
     */
    private String viewcascadeMbbzd;

    /**
     * 源表标识
     */
    private String viewcascadeYbbs;

    /**
     * 源表id
     */
    private String viewcascadeYbid;

    /**
     * 源表表名称
     */
    private String viewcascadeYbbmc;

    /**
     * 子表表名称
     */
    private String viewcascadeZbbmc;

    /**
     * 子表标识
     */
    private String viewcascadeZbbz;

    /**
     * 子表id
     */
    private String viewcascadeZbid;

    /**
     * 是否显示线
     */
    private String viewcascadeSfxsx;

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
