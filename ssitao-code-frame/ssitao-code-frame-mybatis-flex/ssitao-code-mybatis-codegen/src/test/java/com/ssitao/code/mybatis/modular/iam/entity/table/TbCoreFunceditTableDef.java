package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 阅读标记 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbCoreFunceditTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 阅读标记
     */
    public static final TbCoreFunceditTableDef TB_CORE_FUNCEDIT = new TbCoreFunceditTableDef();

    /**
     * 所属机构id
     */
    public final QueryColumn SY_ORG_ID = new QueryColumn(this, "sy_org_id");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 租户id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * 标识
     */
    public final QueryColumn FUNCEDIT_NEW = new QueryColumn(this, "funcedit_new");

    /**
     * 所属公司id
     */
    public final QueryColumn SY_COMPANY_ID = new QueryColumn(this, "sy_company_id");

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
     * 所属公司名称
     */
    public final QueryColumn SY_COMPANY_NAME = new QueryColumn(this, "sy_company_name");

    /**
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 用户id
     */
    public final QueryColumn FUNCEDIT_USERID = new QueryColumn(this, "funcedit_userid");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 主键值
     */
    public final QueryColumn FUNCEDIT_PKVALUE = new QueryColumn(this, "funcedit_pkvalue");

    /**
     * 几率标识
     */
    public final QueryColumn FUNCEDIT_YESORNO = new QueryColumn(this, "funcedit_yesorno");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 功能编码
     */
    public final QueryColumn FUNCEDIT_FUNCCODE = new QueryColumn(this, "funcedit_funccode");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 所属集团公司id
     */
    public final QueryColumn SY_GROUP_COMPANY_ID = new QueryColumn(this, "sy_group_company_id");

    /**
     * 主键id
     */
    public final QueryColumn TB_CORE_FUNCEDIT_ID = new QueryColumn(this, "tb_core_funcedit_id");

    /**
     * 表名
     */
    public final QueryColumn FUNCEDIT_TABLECODE = new QueryColumn(this, "funcedit_tablecode");

    /**
     * 所属集团公司名称
     */
    public final QueryColumn SY_GROUP_COMPANY_NAME = new QueryColumn(this, "sy_group_company_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_FUNCEDIT_ID, FUNCEDIT_FUNCCODE, FUNCEDIT_TABLECODE, FUNCEDIT_PKVALUE, FUNCEDIT_USERID, FUNCEDIT_NEW, FUNCEDIT_YESORNO, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_COMPANY_ID, SY_COMPANY_NAME, SY_GROUP_COMPANY_ID, SY_GROUP_COMPANY_NAME, SY_ORG_ID, SY_TENANT_ID, SY_TENANT_NAME};

    public TbCoreFunceditTableDef() {
        super("", "tb_core_funcedit");
    }

    private TbCoreFunceditTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreFunceditTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreFunceditTableDef("", "tb_core_funcedit", alias));
    }

}
