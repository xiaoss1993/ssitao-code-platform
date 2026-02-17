package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 数据字典 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbCoreDictionaryTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 数据字典
     */
    public static final TbCoreDictionaryTableDef TB_CORE_DICTIONARY = new TbCoreDictionaryTableDef();

    /**
     * 是否启用本条数据
     */
    public final QueryColumn SY_FLAG = new QueryColumn(this, "sy_flag");

    /**
     * 拼音简写
     */
    public final QueryColumn SY_PYJZ = new QueryColumn(this, "sy_pyjz");

    /**
     * 拼音全称
     */
    public final QueryColumn SY_PYQC = new QueryColumn(this, "sy_pyqc");

    /**
     * je系统
     */
    public final QueryColumn SY_JESYS = new QueryColumn(this, "sy_jesys");

    /**
     * je核心
     */
    public final QueryColumn SY_JECORE = new QueryColumn(this, "sy_jecore");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 所属产品id
     */
    public final QueryColumn SY_PRODUCT_ID = new QueryColumn(this, "sy_product_id");

    /**
     * 编码
     */
    public final QueryColumn DICTIONARY_BM = new QueryColumn(this, "dictionary_bm");

    /**
     * 名称
     */
    public final QueryColumn DICTIONARY_MC = new QueryColumn(this, "dictionary_mc");

    /**
     * 主键
     */
    public final QueryColumn DICTIONARY_ZJ = new QueryColumn(this, "dictionary_zj");

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
     * 分隔符
     */
    public final QueryColumn DICTIONARY_FGF = new QueryColumn(this, "dictionary_fgf");

    /**
     * 父节点
     */
    public final QueryColumn DICTIONARY_FJD = new QueryColumn(this, "dictionary_fjd");

    /**
     * sql
     */
    public final QueryColumn DICTIONARY_SQL = new QueryColumn(this, "dictionary_sql");

    /**
     * 登记者所在部门id
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 修改人部门id
     */
    public final QueryColumn SY_MODIFYORGID = new QueryColumn(this, "sy_modifyorgid");

    /**
     * 所属产品code
     */
    public final QueryColumn SY_PRODUCT_CODE = new QueryColumn(this, "sy_product_code");

    /**
     * 所属产品名称
     */
    public final QueryColumn SY_PRODUCT_NAME = new QueryColumn(this, "sy_product_name");

    /**
     * 方法参数
     */
    public final QueryColumn DICTIONARY_FFCS = new QueryColumn(this, "dictionary_ffcs");

    /**
     * 节点类型
     */
    public final QueryColumn DICTIONARY_JDLX = new QueryColumn(this, "dictionary_jdlx");

    /**
     * 是否启用
     */
    public final QueryColumn DICTIONARY_SFQY = new QueryColumn(this, "dictionary_sfqy");

    /**
     * 树形路径
     */
    public final QueryColumn DICTIONARY_SXLJ = new QueryColumn(this, "dictionary_sxlj");

    /**
     * 图标样式
     */
    public final QueryColumn DICTIONARY_TBYS = new QueryColumn(this, "dictionary_tbys");

    /**
     * 字体颜色
     */
    public final QueryColumn DICTIONARY_ZTYS = new QueryColumn(this, "dictionary_ztys");

    /**
     * 登记人id
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 修改人id
     */
    public final QueryColumn SY_MODIFYUSERID = new QueryColumn(this, "sy_modifyuserid");

    /**
     * 类名
     */
    public final QueryColumn DICTIONARY_CLASS = new QueryColumn(this, "dictionary_class");

    /**
     * 父节点路径
     */
    public final QueryColumn DICTIONARY_FJDLJ = new QueryColumn(this, "dictionary_fjdlj");

    /**
     * sql描述
     */
    public final QueryColumn DICTIONARY_SQLMS = new QueryColumn(this, "dictionary_sqlms");

    /**
     * 字典项概要
     */
    public final QueryColumn DICTIONARY_ZDXGY = new QueryColumn(this, "dictionary_zdxgy");

    /**
     * 登记者所在部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 修改人部门
     */
    public final QueryColumn SY_MODIFYORGNAME = new QueryColumn(this, "sy_modifyorgname");

    /**
     * 字典编码
     */
    public final QueryColumn DICTIONARY_DDCODE = new QueryColumn(this, "dictionary_ddcode");

    /**
     * 字典名称
     */
    public final QueryColumn DICTIONARY_DDNAME = new QueryColumn(this, "dictionary_ddname");

    /**
     * 字典类型
     */
    public final QueryColumn DICTIONARY_DDTYPE = new QueryColumn(this, "dictionary_ddtype");

    /**
     * 方法参数描述
     */
    public final QueryColumn DICTIONARY_FFCSMS = new QueryColumn(this, "dictionary_ffcsms");

    /**
     * 过滤条件描述
     */
    public final QueryColumn DICTIONARY_GLTJMS = new QueryColumn(this, "dictionary_gltjms");

    /**
     * 方法名
     */
    public final QueryColumn DICTIONARY_METHOD = new QueryColumn(this, "dictionary_method");

    /**
     * 排序条件描述
     */
    public final QueryColumn DICTIONARY_PXTJMS = new QueryColumn(this, "dictionary_pxtjms");

    /**
     * 所属模块路径
     */
    public final QueryColumn DICTIONARY_SSMKLJ = new QueryColumn(this, "dictionary_ssmklj");

    /**
     * 树形排序字段
     */
    public final QueryColumn DICTIONARY_SXPXZD = new QueryColumn(this, "dictionary_sxpxzd");

    /**
     * 字体背景颜色
     */
    public final QueryColumn DICTIONARY_ZTBJYS = new QueryColumn(this, "dictionary_ztbjys");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 修改人
     */
    public final QueryColumn SY_MODIFYUSERNAME = new QueryColumn(this, "sy_modifyusername");

    /**
     * 类型
     */
    public final QueryColumn DICTIONARY_DICTYPE = new QueryColumn(this, "dictionary_dictype");

    /**
     * sql列表说明
     */
    public final QueryColumn DICTIONARY_SQLLBSM = new QueryColumn(this, "dictionary_sqllbsm");

    /**
     * sql树形说明
     */
    public final QueryColumn DICTIONARY_SQLSXSM = new QueryColumn(this, "dictionary_sqlsxsm");

    /**
     * 排序条件
     */
    public final QueryColumn DICTIONARY_ORDERSQL = new QueryColumn(this, "dictionary_ordersql");

    /**
     * 常用
     */
    public final QueryColumn DICTIONARY_SFCY_CODE = new QueryColumn(this, "dictionary_sfcy_code");

    /**
     * 数据结构_code
     */
    public final QueryColumn DICTIONARY_SJJG_CODE = new QueryColumn(this, "dictionary_sjjg_code");

    /**
     * 数据结构_name
     */
    public final QueryColumn DICTIONARY_SJJG_NAME = new QueryColumn(this, "dictionary_sjjg_name");

    /**
     * 查询条件
     */
    public final QueryColumn DICTIONARY_WHERESQL = new QueryColumn(this, "dictionary_wheresql");

    /**
     * 业务字段编码
     */
    public final QueryColumn DICTIONARY_YWZD_CODE = new QueryColumn(this, "dictionary_ywzd_code");

    /**
     * 业务字段
     */
    public final QueryColumn DICTIONARY_YWZD_NAME = new QueryColumn(this, "dictionary_ywzd_name");

    
    public final QueryColumn TB_CORE_DICTIONARY_ID = new QueryColumn(this, "tb_core_dictionary_id");

    /**
     * 所属子系统
     */
    public final QueryColumn DICTIONARY_BELONGSTO = new QueryColumn(this, "dictionary_belongsto");

    /**
     * 外部自定实体名称
     */
    public final QueryColumn DICTIONARY_CLASSNAME = new QueryColumn(this, "dictionary_classname");

    /**
     * sql配置信息
     */
    public final QueryColumn DICTIONARY_SQLCONFIG = new QueryColumn(this, "dictionary_sqlconfig");

    /**
     * sql配置信息列表
     */
    public final QueryColumn DICTIONARY_SQLPZXXLB = new QueryColumn(this, "dictionary_sqlpzxxlb");

    /**
     * 删除时间
     */
    public final QueryColumn DICTIONARY_DELETETIME = new QueryColumn(this, "dictionary_deletetime");

    /**
     * 字典项根节点id
     */
    public final QueryColumn DICTIONARY_ITEMROOT_ID = new QueryColumn(this, "dictionary_itemroot_id");

    /**
     * 表、视图
     */
    public final QueryColumn DICTIONARY_VTABLE_CODE = new QueryColumn(this, "dictionary_vtable_code");

    /**
     * 表、视图名称
     */
    public final QueryColumn DICTIONARY_VTABLE_NAME = new QueryColumn(this, "dictionary_vtable_name");

    /**
     * 排序条件_sql
     */
    public final QueryColumn DICTIONARY_ORDERSQL_SQL = new QueryColumn(this, "dictionary_ordersql_sql");

    /**
     * 查询条件_sql
     */
    public final QueryColumn DICTIONARY_WHERESQL_SQL = new QueryColumn(this, "dictionary_wheresql_sql");

    /**
     * 删除人id
     */
    public final QueryColumn DICTIONARY_DELETEUSERID = new QueryColumn(this, "dictionary_deleteuserid");

    /**
     * 树形扩展字段
     */
    public final QueryColumn DICTIONARY_FIELDCONFIGS = new QueryColumn(this, "dictionary_fieldconfigs");

    /**
     * 所属子系统
     */
    public final QueryColumn DICTIONARY_BELONGSTONAME = new QueryColumn(this, "dictionary_belongstoname");

    /**
     * 删除人
     */
    public final QueryColumn DICTIONARY_DELETEUSERNAME = new QueryColumn(this, "dictionary_deleteusername");

    /**
     * 部门树形_code
     */
    public final QueryColumn DICTIONARY_DEPARTMENT_WW_CODE = new QueryColumn(this, "dictionary_department_ww_code");

    /**
     * 部门树形
     */
    public final QueryColumn DICTIONARY_DEPARTMENT_WW_NAME = new QueryColumn(this, "dictionary_department_ww_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_DICTIONARY_ID, DICTIONARY_DDCODE, DICTIONARY_DDNAME, DICTIONARY_DDTYPE, DICTIONARY_CLASSNAME, DICTIONARY_BELONGSTO, DICTIONARY_BELONGSTONAME, DICTIONARY_DICTYPE, DICTIONARY_SQLCONFIG, DICTIONARY_SQL, DICTIONARY_SQLLBSM, DICTIONARY_SQLSXSM, DICTIONARY_FIELDCONFIGS, DICTIONARY_SQLPZXXLB, DICTIONARY_WHERESQL, DICTIONARY_ORDERSQL, DICTIONARY_CLASS, DICTIONARY_METHOD, DICTIONARY_ITEMROOT_ID, DICTIONARY_ZDXGY, DICTIONARY_VTABLE_CODE, DICTIONARY_VTABLE_NAME, DICTIONARY_GLTJMS, DICTIONARY_PXTJMS, DICTIONARY_FFCS, DICTIONARY_FFCSMS, DICTIONARY_SQLMS, DICTIONARY_SJJG_NAME, DICTIONARY_SJJG_CODE, DICTIONARY_FGF, DICTIONARY_ZJ, DICTIONARY_BM, DICTIONARY_MC, DICTIONARY_TBYS, DICTIONARY_ZTYS, DICTIONARY_ZTBJYS, DICTIONARY_SXLJ, DICTIONARY_JDLX, DICTIONARY_SXPXZD, DICTIONARY_FJD, DICTIONARY_FJDLJ, DICTIONARY_SFQY, DICTIONARY_DEPARTMENT_WW_NAME, DICTIONARY_DEPARTMENT_WW_CODE, DICTIONARY_SSMKLJ, DICTIONARY_DELETEUSERNAME, DICTIONARY_DELETETIME, DICTIONARY_DELETEUSERID, DICTIONARY_WHERESQL_SQL, DICTIONARY_ORDERSQL_SQL, DICTIONARY_SFCY_CODE, DICTIONARY_YWZD_NAME, DICTIONARY_YWZD_CODE, SY_JECORE, SY_JESYS, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERNAME, SY_FLAG, SY_MODIFYORGNAME, SY_MODIFYTIME, SY_MODIFYUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_PYJZ, SY_PYQC, SY_PRODUCT_ID, SY_PRODUCT_NAME, SY_PRODUCT_CODE, SY_CREATEORGID, SY_CREATEUSERID, SY_MODIFYORGID, SY_MODIFYUSERID};

    public TbCoreDictionaryTableDef() {
        super("", "tb_core_dictionary");
    }

    private TbCoreDictionaryTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreDictionaryTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreDictionaryTableDef("", "tb_core_dictionary", alias));
    }

}
