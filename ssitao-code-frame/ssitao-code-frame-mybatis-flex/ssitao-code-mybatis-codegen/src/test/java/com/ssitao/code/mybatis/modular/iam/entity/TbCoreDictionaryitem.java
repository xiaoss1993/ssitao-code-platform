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
 * 数据字典项 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_dictionaryitem")
public class TbCoreDictionaryitem extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String tbCoreDictionaryitemId;

    /**
     * 节点信息
     */
    private String dictionaryitemNodeinfo;

    /**
     * 节点信息类型
     */
    private String dictionaryitemNodeinfotype;

    /**
     * 节点名称
     */
    private String dictionaryitemItemname;

    /**
     * 节点编码
     */
    private String dictionaryitemItemcode;

    /**
     * 字典外键
     */
    private String dictionaryitemDictionaryId;

    /**
     * 单元格颜色
     */
    private String dictionaryitemBackgroundcolor;

    /**
     * 字体颜色
     */
    private String dictionaryitemFontcolor;

    /**
     * 分类
     */
    private String dictionaryitemClassify;

    /**
     * 英文
     */
    private String dictionaryitemItemnameEn;

    /**
     * 图标
     */
    private String dictionaryitemIcon;

    /**
     * 图标颜色
     */
    private String dictionaryitemIconcolor;

    /**
     * saas产品
     */
    private String dictionaryitemSaasPid;

    /**
     * 树形路径
     */
    private String syPath;

    /**
     * 层次
     */
    private Integer syLayer;

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
     * 树形排序字段
     */
    private String syTreeorderindex;

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

    /**
     * 审核标记
     */
    private String syAudflag;

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

}
