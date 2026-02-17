package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 权限组 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbIamPermgroupTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 权限组
     */
    public static final TbIamPermgroupTableDef TB_IAM_PERMGROUP = new TbIamPermgroupTableDef();

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
     * 权限组编码
     */
    public final QueryColumn PERMGROUP_CODE = new QueryColumn(this, "permgroup_code");

    /**
     * 权限组名称
     */
    public final QueryColumn PERMGROUP_NAME = new QueryColumn(this, "permgroup_name");

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
     * 主键id
     */
    public final QueryColumn TB_IAM_PERMGROUP_ID = new QueryColumn(this, "tb_iam_permgroup_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_IAM_PERMGROUP_ID, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, PERMGROUP_NAME, PERMGROUP_CODE};

    public TbIamPermgroupTableDef() {
        super("", "tb_iam_permgroup");
    }

    private TbIamPermgroupTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbIamPermgroupTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbIamPermgroupTableDef("", "tb_iam_permgroup", alias));
    }

}
