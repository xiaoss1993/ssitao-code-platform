package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 平台组件事件定义 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbCoreVieweventsTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 平台组件事件定义
     */
    public static final TbCoreVieweventsTableDef TB_CORE_VIEWEVENTS = new TbCoreVieweventsTableDef();

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
     * 禁用
     */
    public final QueryColumn VIEWEVENTS_JY = new QueryColumn(this, "viewevents_jy");

    /**
     * 说明
     */
    public final QueryColumn VIEWEVENTS_SM = new QueryColumn(this, "viewevents_sm");

    /**
     * 登记者所在部门id
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 登记人id
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 事件编码
     */
    public final QueryColumn VIEWEVENTS_CODE = new QueryColumn(this, "viewevents_code");

    /**
     * 方法体
     */
    public final QueryColumn VIEWEVENTS_FUNC = new QueryColumn(this, "viewevents_func");

    /**
     * 事件名称
     */
    public final QueryColumn VIEWEVENTS_NAME = new QueryColumn(this, "viewevents_name");

    /**
     * 类型
     */
    public final QueryColumn VIEWEVENTS_TYPE = new QueryColumn(this, "viewevents_type");

    /**
     * 异步事件
     */
    public final QueryColumn VIEWEVENTS_YBSJ = new QueryColumn(this, "viewevents_ybsj");

    /**
     * 登记者所在部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    
    public final QueryColumn TB_CORE_VIEWEVENTS_ID = new QueryColumn(this, "tb_core_viewevents_id");

    /**
     * js监听事件类型
     */
    public final QueryColumn VIEWEVENTS_TYPE_CODE = new QueryColumn(this, "viewevents_type_code");

    /**
     * js监听事件类型_name
     */
    public final QueryColumn VIEWEVENTS_TYPE_NAME = new QueryColumn(this, "viewevents_type_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{VIEWEVENTS_CODE, VIEWEVENTS_FUNC, VIEWEVENTS_NAME, VIEWEVENTS_TYPE, TB_CORE_VIEWEVENTS_ID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERNAME, SY_ORDERINDEX, SY_STATUS, VIEWEVENTS_TYPE_CODE, VIEWEVENTS_TYPE_NAME, SY_CREATEORGID, SY_CREATEUSERID, VIEWEVENTS_SM, VIEWEVENTS_JY, VIEWEVENTS_YBSJ};

    public TbCoreVieweventsTableDef() {
        super("", "tb_core_viewevents");
    }

    private TbCoreVieweventsTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreVieweventsTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreVieweventsTableDef("", "tb_core_viewevents", alias));
    }

}
