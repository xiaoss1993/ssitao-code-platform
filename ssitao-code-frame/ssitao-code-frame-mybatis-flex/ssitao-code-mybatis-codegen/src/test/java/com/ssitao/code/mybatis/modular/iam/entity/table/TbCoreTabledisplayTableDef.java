package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 资源展示信息 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbCoreTabledisplayTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 资源展示信息
     */
    public static final TbCoreTabledisplayTableDef TB_CORE_TABLEDISPLAY = new TbCoreTabledisplayTableDef();

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
     * 登记者所在部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * x
     */
    public final QueryColumn TABLEDISPLAY_X = new QueryColumn(this, "tabledisplay_x");

    /**
     * y
     */
    public final QueryColumn TABLEDISPLAY_Y = new QueryColumn(this, "tabledisplay_y");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 登记者所在部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 类型
     */
    public final QueryColumn TABLEDISPLAY_TYPE = new QueryColumn(this, "tabledisplay_type");

    /**
     * 宽度
     */
    public final QueryColumn TABLEDISPLAY_WIDTH = new QueryColumn(this, "tabledisplay_width");

    /**
     * 高度
     */
    public final QueryColumn TABLEDISPLAY_HEIGHT = new QueryColumn(this, "tabledisplay_height");

    /**
     * 表主键
     */
    public final QueryColumn TABLEDISPLAY_TABLE_ID = new QueryColumn(this, "tabledisplay_table_id");

    
    public final QueryColumn TB_CORE_TABLEDISPLAY_ID = new QueryColumn(this, "tb_core_tabledisplay_id");

    /**
     * 表编码
     */
    public final QueryColumn TABLEDISPLAY_TABLECODE = new QueryColumn(this, "tabledisplay_tablecode");

    /**
     * 表名
     */
    public final QueryColumn TABLEDISPLAY_TABLENAME = new QueryColumn(this, "tabledisplay_tablename");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_TABLEDISPLAY_ID, TABLEDISPLAY_HEIGHT, TABLEDISPLAY_X, TABLEDISPLAY_TABLE_ID, TABLEDISPLAY_WIDTH, TABLEDISPLAY_TABLECODE, TABLEDISPLAY_TYPE, TABLEDISPLAY_TABLENAME, TABLEDISPLAY_Y, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX};

    public TbCoreTabledisplayTableDef() {
        super("", "tb_core_tabledisplay");
    }

    private TbCoreTabledisplayTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreTabledisplayTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreTabledisplayTableDef("", "tb_core_tabledisplay", alias));
    }

}
