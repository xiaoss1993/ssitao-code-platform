package com.ssitao.code.modular.iam.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Column;
import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * IAM字典数据数据对象
 * 对应数据库表：tb_core_dictionaryitem
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Table(value = "tb_core_dictionaryitem")
public class IamDictDataDO {

    /**
     * 主键id
     */
    @Id(keyType = KeyType.None)
    @Column(value = "tb_core_dictionaryitem_id")
    private String tbCoreDictionaryitemId;

    /**
     * 字典外键
     */
    @Column(value = "dictionaryitem_dictionary_id")
    private String dictionaryitemDictionaryId;

    /**
     * 节点名称
     */
    @Column(value = "dictionaryitem_itemname")
    private String dictionaryitemItemname;

    /**
     * 节点编码
     */
    @Column(value = "dictionaryitem_itemcode")
    private String dictionaryitemItemcode;

    /**
     * 节点信息
     */
    @Column(value = "dictionaryitem_nodeinfo")
    private String dictionaryitemNodeinfo;

    /**
     * 节点信息类型
     */
    @Column(value = "dictionaryitem_nodeinfotype")
    private String dictionaryitemNodeinfotype;

    /**
     * 单元格颜色
     */
    @Column(value = "dictionaryitem_backgroundcolor")
    private String dictionaryitemBackgroundcolor;

    /**
     * 字体颜色
     */
    @Column(value = "dictionaryitem_fontcolor")
    private String dictionaryitemFontcolor;

    /**
     * 分类
     */
    @Column(value = "dictionaryitem_classify")
    private String dictionaryitemClassify;

    /**
     * 图标
     */
    @Column(value = "dictionaryitem_icon")
    private String dictionaryitemIcon;

    /**
     * 图标颜色
     */
    @Column(value = "dictionaryitem_iconcolor")
    private String dictionaryitemIconcolor;

    /**
     * 树形路径
     */
    @Column(value = "sy_path")
    private String syPath;

    /**
     * 层次
     */
    @Column(value = "sy_layer")
    private Integer syLayer;

    /**
     * 父节点id
     */
    @Column(value = "sy_parent")
    private String syParent;

    /**
     * 节点类型
     */
    @Column(value = "sy_nodetype")
    private String syNodetype;

    /**
     * 父节点路径
     */
    @Column(value = "sy_parentpath")
    private String syParentpath;

    /**
     * 树形排序字段
     */
    @Column(value = "sy_treeorderindex")
    private String syTreeorderindex;

    /**
     * 创建时间
     */
    @Column(value = "sy_createtime")
    private String syCreatetime;

    /**
     * 创建人ID
     */
    @Column(value = "sy_createuserid")
    private String syCreateuserid;

    /**
     * 创建人名称
     */
    @Column(value = "sy_createusername")
    private String syCreateusername;

    /**
     * 修改时间
     */
    @Column(value = "sy_modifytime")
    private String syModifytime;

    /**
     * 修改人ID
     */
    @Column(value = "sy_modifyuserid")
    private String syModifyuserid;

    /**
     * 数据状态
     */
    @Column(value = "sy_status")
    private String syStatus;

    /**
     * 是否启用本条数据
     */
    @Column(value = "sy_flag")
    private String syFlag;

    /**
     * 租户ID
     */
    @Column(value = "sy_tenant_id")
    private String syTenantId;

    /**
     * 租户名称
     */
    @Column(value = "sy_tenant_name")
    private String syTenantName;

}
