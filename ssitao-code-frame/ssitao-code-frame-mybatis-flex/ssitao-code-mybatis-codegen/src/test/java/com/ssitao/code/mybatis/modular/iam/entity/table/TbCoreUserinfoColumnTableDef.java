package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 用户列个性化 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbCoreUserinfoColumnTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 用户列个性化
     */
    public static final TbCoreUserinfoColumnTableDef TB_CORE_USERINFO_COLUMN = new TbCoreUserinfoColumnTableDef();

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 编码
     */
    public final QueryColumn COLUMN_CODE = new QueryColumn(this, "column_code");

    /**
     * 名称
     */
    public final QueryColumn COLUMN_NAME = new QueryColumn(this, "column_name");

    /**
     * 租户_id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * 功能外键
     */
    public final QueryColumn COLUMN_FUNCID = new QueryColumn(this, "column_funcid");

    /**
     * 列表方案外键
     */
    public final QueryColumn COLUMN_PLAN_ID = new QueryColumn(this, "column_plan_id");

    /**
     * 登记时间
     */
    public final QueryColumn SY_CREATETIME = new QueryColumn(this, "sy_createtime");

    /**
     * 排序字段
     */
    public final QueryColumn SY_ORDERINDEX = new QueryColumn(this, "sy_orderindex");

    /**
     * 租户名称
     */
    public final QueryColumn SY_TENANT_NAME = new QueryColumn(this, "sy_tenant_name");

    /**
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 增量类型
     */
    public final QueryColumn COLUMN_BULKING_TYPE = new QueryColumn(this, "column_bulking_type");

    /**
     * 列表字段外键
     */
    public final QueryColumn COLUMN_RESOURCECOLUMN_ID = new QueryColumn(this, "column_resourcecolumn_id");

    /**
     * 主键id
     */
    public final QueryColumn TB_CORE_USERINFO_COLUMN_ID = new QueryColumn(this, "tb_core_userinfo_column_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_USERINFO_COLUMN_ID, COLUMN_PLAN_ID, COLUMN_NAME, COLUMN_CODE, COLUMN_FUNCID, COLUMN_RESOURCECOLUMN_ID, COLUMN_BULKING_TYPE, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_TENANT_ID, SY_TENANT_NAME};

    public TbCoreUserinfoColumnTableDef() {
        super("", "tb_core_userinfo_column");
    }

    private TbCoreUserinfoColumnTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreUserinfoColumnTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreUserinfoColumnTableDef("", "tb_core_userinfo_column", alias));
    }

}
