package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 平台帮助信息说明 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbCoreHelpmsgTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 平台帮助信息说明
     */
    public static final TbCoreHelpmsgTableDef TB_CORE_HELPMSG = new TbCoreHelpmsgTableDef();

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 数据
     */
    public final QueryColumn HELPMSG_SJ = new QueryColumn(this, "helpmsg_sj");

    /**
     * 描述
     */
    public final QueryColumn HELPMSG_MSG = new QueryColumn(this, "helpmsg_msg");

    /**
     * 数据项
     */
    public final QueryColumn HELPMSG_SJX = new QueryColumn(this, "helpmsg_sjx");

    /**
     * 编码
     */
    public final QueryColumn HELPMSG_CODE = new QueryColumn(this, "helpmsg_code");

    /**
     * 名称
     */
    public final QueryColumn HELPMSG_NAME = new QueryColumn(this, "helpmsg_name");

    /**
     * 批量描述
     */
    public final QueryColumn HELPMSG_PLMS = new QueryColumn(this, "helpmsg_plms");

    /**
     * 类型
     */
    public final QueryColumn HELPMSG_TYPE = new QueryColumn(this, "helpmsg_type");

    /**
     * 登记时间
     */
    public final QueryColumn SY_CREATETIME = new QueryColumn(this, "sy_createtime");

    /**
     * 排序字段
     */
    public final QueryColumn SY_ORDERINDEX = new QueryColumn(this, "sy_orderindex");

    /**
     * 数据配置描述
     */
    public final QueryColumn HELPMSG_SJPZMS = new QueryColumn(this, "helpmsg_sjpzms");

    /**
     * 登记者所在部门id
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 登记人id
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 类型名称
     */
    public final QueryColumn HELPMSG_TYPE_NAME = new QueryColumn(this, "helpmsg_type_name");

    /**
     * 登记者所在部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    
    public final QueryColumn TB_CORE_HELPMSG_ID = new QueryColumn(this, "tb_core_helpmsg_id");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_HELPMSG_ID, HELPMSG_CODE, HELPMSG_MSG, HELPMSG_NAME, HELPMSG_TYPE, HELPMSG_SJ, HELPMSG_SJPZMS, HELPMSG_SJX, HELPMSG_PLMS, HELPMSG_TYPE_NAME, SY_CREATEORGID, SY_CREATEUSERID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERNAME, SY_ORDERINDEX, SY_STATUS};

    public TbCoreHelpmsgTableDef() {
        super("", "tb_core_helpmsg");
    }

    private TbCoreHelpmsgTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreHelpmsgTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreHelpmsgTableDef("", "tb_core_helpmsg", alias));
    }

}
