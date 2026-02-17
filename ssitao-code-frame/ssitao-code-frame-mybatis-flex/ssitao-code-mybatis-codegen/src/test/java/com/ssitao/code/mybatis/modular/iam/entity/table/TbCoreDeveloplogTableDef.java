package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 开发日志 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbCoreDeveloplogTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 开发日志
     */
    public static final TbCoreDeveloplogTableDef TB_CORE_DEVELOPLOG = new TbCoreDeveloplogTableDef();

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 产品id
     */
    public final QueryColumn SY_PRODUCT_ID = new QueryColumn(this, "sy_product_id");

    /**
     * 资源主键
     */
    public final QueryColumn DEVELOPLOG_ID = new QueryColumn(this, "developlog_id");

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
     * 编码
     */
    public final QueryColumn DEVELOPLOG_CODE = new QueryColumn(this, "developlog_code");

    /**
     * 名称
     */
    public final QueryColumn DEVELOPLOG_NAME = new QueryColumn(this, "developlog_name");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 用户id
     */
    public final QueryColumn DEVELOPLOG_USERID = new QueryColumn(this, "developlog_userid");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 动作
     */
    public final QueryColumn DEVELOPLOG_ACT_CODE = new QueryColumn(this, "developlog_act_code");

    /**
     * 动作_name
     */
    public final QueryColumn DEVELOPLOG_ACT_NAME = new QueryColumn(this, "developlog_act_name");

    /**
     * 类型
     */
    public final QueryColumn DEVELOPLOG_TYPE_CODE = new QueryColumn(this, "developlog_type_code");

    /**
     * 类型_name
     */
    public final QueryColumn DEVELOPLOG_TYPE_NAME = new QueryColumn(this, "developlog_type_name");

    /**
     * 用户名称
     */
    public final QueryColumn DEVELOPLOG_USERNAME = new QueryColumn(this, "developlog_username");

    /**
     * 主键id
     */
    public final QueryColumn TB_CORE_DEVELOPLOG_ID = new QueryColumn(this, "tb_core_developlog_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_DEVELOPLOG_ID, DEVELOPLOG_USERNAME, DEVELOPLOG_USERID, DEVELOPLOG_TYPE_NAME, DEVELOPLOG_TYPE_CODE, DEVELOPLOG_ACT_NAME, DEVELOPLOG_ACT_CODE, DEVELOPLOG_NAME, DEVELOPLOG_CODE, DEVELOPLOG_ID, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_PRODUCT_ID};

    public TbCoreDeveloplogTableDef() {
        super("", "tb_core_developlog");
    }

    private TbCoreDeveloplogTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreDeveloplogTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreDeveloplogTableDef("", "tb_core_developlog", alias));
    }

}
