package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 数据字典项 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbCoreDictionaryitemTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 数据字典项
     */
    public static final TbCoreDictionaryitemTableDef TB_CORE_DICTIONARYITEM = new TbCoreDictionaryitemTableDef();

    /**
     * 是否启用本条数据
     */
    public final QueryColumn SY_FLAG = new QueryColumn(this, "sy_flag");

    /**
     * 树形路径
     */
    public final QueryColumn SY_PATH = new QueryColumn(this, "sy_path");

    /**
     * 拼音简写
     */
    public final QueryColumn SY_PYJZ = new QueryColumn(this, "sy_pyjz");

    /**
     * 拼音全称
     */
    public final QueryColumn SY_PYQC = new QueryColumn(this, "sy_pyqc");

    /**
     * 层次
     */
    public final QueryColumn SY_LAYER = new QueryColumn(this, "sy_layer");

    /**
     * 父节点id
     */
    public final QueryColumn SY_PARENT = new QueryColumn(this, "sy_parent");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 审核标记
     */
    public final QueryColumn SY_AUDFLAG = new QueryColumn(this, "sy_audflag");

    /**
     * 节点类型
     */
    public final QueryColumn SY_NODETYPE = new QueryColumn(this, "sy_nodetype");

    /**
     * 租户_id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * 登记时间
     */
    public final QueryColumn SY_CREATETIME = new QueryColumn(this, "sy_createtime");

    /**
     * 修改时间
     */
    public final QueryColumn SY_MODIFYTIME = new QueryColumn(this, "sy_modifytime");

    /**
     * 排序字段
     */
    public final QueryColumn SY_ORDERINDEX = new QueryColumn(this, "sy_orderindex");

    /**
     * 父节点路径
     */
    public final QueryColumn SY_PARENTPATH = new QueryColumn(this, "sy_parentpath");

    /**
     * 租户名称
     */
    public final QueryColumn SY_TENANT_NAME = new QueryColumn(this, "sy_tenant_name");

    /**
     * 登记者所在部门id
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 修改人部门id
     */
    public final QueryColumn SY_MODIFYORGID = new QueryColumn(this, "sy_modifyorgid");

    /**
     * 登记人id
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 修改人id
     */
    public final QueryColumn SY_MODIFYUSERID = new QueryColumn(this, "sy_modifyuserid");

    /**
     * 登记者所在部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 修改人部门
     */
    public final QueryColumn SY_MODIFYORGNAME = new QueryColumn(this, "sy_modifyorgname");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 修改人
     */
    public final QueryColumn SY_MODIFYUSERNAME = new QueryColumn(this, "sy_modifyusername");

    /**
     * 树形排序字段
     */
    public final QueryColumn SY_TREEORDERINDEX = new QueryColumn(this, "sy_treeorderindex");

    /**
     * 图标
     */
    public final QueryColumn DICTIONARYITEM_ICON = new QueryColumn(this, "dictionaryitem_icon");

    /**
     * saas产品
     */
    public final QueryColumn DICTIONARYITEM_SAAS_PID = new QueryColumn(this, "dictionaryitem_saas_pid");

    /**
     * 分类
     */
    public final QueryColumn DICTIONARYITEM_CLASSIFY = new QueryColumn(this, "dictionaryitem_classify");

    /**
     * 节点编码
     */
    public final QueryColumn DICTIONARYITEM_ITEMCODE = new QueryColumn(this, "dictionaryitem_itemcode");

    /**
     * 节点名称
     */
    public final QueryColumn DICTIONARYITEM_ITEMNAME = new QueryColumn(this, "dictionaryitem_itemname");

    /**
     * 节点信息
     */
    public final QueryColumn DICTIONARYITEM_NODEINFO = new QueryColumn(this, "dictionaryitem_nodeinfo");

    
    public final QueryColumn TB_CORE_DICTIONARYITEM_ID = new QueryColumn(this, "tb_core_dictionaryitem_id");

    /**
     * 字体颜色
     */
    public final QueryColumn DICTIONARYITEM_FONTCOLOR = new QueryColumn(this, "dictionaryitem_fontcolor");

    /**
     * 图标颜色
     */
    public final QueryColumn DICTIONARYITEM_ICONCOLOR = new QueryColumn(this, "dictionaryitem_iconcolor");

    /**
     * 英文
     */
    public final QueryColumn DICTIONARYITEM_ITEMNAME_EN = new QueryColumn(this, "dictionaryitem_itemname_en");

    /**
     * 字典外键
     */
    public final QueryColumn DICTIONARYITEM_DICTIONARY_ID = new QueryColumn(this, "dictionaryitem_dictionary_id");

    /**
     * 节点信息类型
     */
    public final QueryColumn DICTIONARYITEM_NODEINFOTYPE = new QueryColumn(this, "dictionaryitem_nodeinfotype");

    /**
     * 单元格颜色
     */
    public final QueryColumn DICTIONARYITEM_BACKGROUNDCOLOR = new QueryColumn(this, "dictionaryitem_backgroundcolor");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_DICTIONARYITEM_ID, DICTIONARYITEM_NODEINFO, DICTIONARYITEM_NODEINFOTYPE, DICTIONARYITEM_ITEMNAME, DICTIONARYITEM_ITEMCODE, DICTIONARYITEM_DICTIONARY_ID, DICTIONARYITEM_BACKGROUNDCOLOR, DICTIONARYITEM_FONTCOLOR, DICTIONARYITEM_CLASSIFY, DICTIONARYITEM_ITEMNAME_EN, DICTIONARYITEM_ICON, DICTIONARYITEM_ICONCOLOR, DICTIONARYITEM_SAAS_PID, SY_PATH, SY_LAYER, SY_PARENT, SY_NODETYPE, SY_PARENTPATH, SY_TREEORDERINDEX, SY_CREATEORGID, SY_CREATEUSERID, SY_MODIFYORGID, SY_MODIFYUSERID, SY_TENANT_ID, SY_TENANT_NAME, SY_AUDFLAG, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERNAME, SY_FLAG, SY_MODIFYORGNAME, SY_MODIFYTIME, SY_MODIFYUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_PYJZ, SY_PYQC};

    public TbCoreDictionaryitemTableDef() {
        super("", "tb_core_dictionaryitem");
    }

    private TbCoreDictionaryitemTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreDictionaryitemTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreDictionaryitemTableDef("", "tb_core_dictionaryitem", alias));
    }

}
