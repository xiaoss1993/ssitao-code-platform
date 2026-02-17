package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 授权功能 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbIamImportfuncTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 授权功能
     */
    public static final TbIamImportfuncTableDef TB_IAM_IMPORTFUNC = new TbIamImportfuncTableDef();

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
    public final QueryColumn TB_IAM_IMPORTFUNC_ID = new QueryColumn(this, "tb_iam_importfunc_id");

    /**
     * 功能_id
     */
    public final QueryColumn IMPORTFUNC_FUNCINFO_ID = new QueryColumn(this, "importfunc_funcinfo_id");

    /**
     * 功能_code
     */
    public final QueryColumn IMPORTFUNC_FUNCINFO_CODE = new QueryColumn(this, "importfunc_funcinfo_code");

    /**
     * 功能_name
     */
    public final QueryColumn IMPORTFUNC_FUNCINFO_NAME = new QueryColumn(this, "importfunc_funcinfo_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_IAM_IMPORTFUNC_ID, IMPORTFUNC_FUNCINFO_ID, IMPORTFUNC_FUNCINFO_CODE, IMPORTFUNC_FUNCINFO_NAME, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX};

    public TbIamImportfuncTableDef() {
        super("", "tb_iam_importfunc");
    }

    private TbIamImportfuncTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbIamImportfuncTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbIamImportfuncTableDef("", "tb_iam_importfunc", alias));
    }

}
