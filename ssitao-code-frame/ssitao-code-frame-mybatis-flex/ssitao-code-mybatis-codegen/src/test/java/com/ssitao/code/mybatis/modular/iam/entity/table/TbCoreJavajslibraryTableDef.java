package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 个人脚本库 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbCoreJavajslibraryTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 个人脚本库
     */
    public static final TbCoreJavajslibraryTableDef TB_CORE_JAVAJSLIBRARY = new TbCoreJavajslibraryTableDef();

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 登记时间
     */
    public final QueryColumn SY_CREATETIME = new QueryColumn(this, "sy_createtime");

    /**
     * 排序字段
     */
    public final QueryColumn SY_ORDERINDEX = new QueryColumn(this, "sy_orderindex");

    /**
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 描述
     */
    public final QueryColumn JAVAJSLIBRARY_MS = new QueryColumn(this, "javajslibrary_ms");

    /**
     * 内容
     */
    public final QueryColumn JAVAJSLIBRARY_NR = new QueryColumn(this, "javajslibrary_nr");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 效果展示
     */
    public final QueryColumn JAVAJSLIBRARY_XGZS = new QueryColumn(this, "javajslibrary_xgzs");

    /**
     * 类型_code
     */
    public final QueryColumn JAVAJSLIBRARY_LX_CODE = new QueryColumn(this, "javajslibrary_lx_code");

    /**
     * 类型
     */
    public final QueryColumn JAVAJSLIBRARY_LX_NAME = new QueryColumn(this, "javajslibrary_lx_name");

    /**
     * 主键id
     */
    public final QueryColumn TB_CORE_JAVAJSLIBRARY_ID = new QueryColumn(this, "tb_core_javajslibrary_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_JAVAJSLIBRARY_ID, JAVAJSLIBRARY_MS, JAVAJSLIBRARY_LX_NAME, JAVAJSLIBRARY_LX_CODE, JAVAJSLIBRARY_NR, JAVAJSLIBRARY_XGZS, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX};

    public TbCoreJavajslibraryTableDef() {
        super("", "tb_core_javajslibrary");
    }

    private TbCoreJavajslibraryTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreJavajslibraryTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreJavajslibraryTableDef("", "tb_core_javajslibrary", alias));
    }

}
