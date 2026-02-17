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
 * 移动端api 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_jeappapi")
public class TbCoreJeappapi extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String tbCoreJeappapiId;

    /**
     * 所属
     */
    private String jeapiFor;

    /**
     * 示例
     */
    private String jeapiExample;

    /**
     * 类型
     */
    private String jeapiType;

    /**
     * 版本
     */
    private String uppackageBb;

    /**
     * 说明
     */
    private String jeapiRemark;

    /**
     * 图标
     */
    private String jeapiIconcls;

    /**
     * 名称
     */
    private String jeapiText;

    /**
     * 编码
     */
    private String jeapiCode;

    /**
     * 父节点id
     */
    private String syParent;

    /**
     * 节点类型
     */
    private String syNodetype;

    /**
     * 父节点路径
     */
    private String syParentpath;

    /**
     * 层次
     */
    private Integer syLayer;

    /**
     * 树形路径
     */
    private String syPath;

    /**
     * 是否启用
     */
    private String syDisabled;

    /**
     * 树形排序字段
     */
    private String syTreeorderindex;

    /**
     * 登记者所在部门主键
     */
    private String syCreateorgid;

    /**
     * 登记者所在部门
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
