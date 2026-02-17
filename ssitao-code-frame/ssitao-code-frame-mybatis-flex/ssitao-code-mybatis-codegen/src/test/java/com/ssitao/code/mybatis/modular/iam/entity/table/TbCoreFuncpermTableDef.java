package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 数据权限 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbCoreFuncpermTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 数据权限
     */
    public static final TbCoreFuncpermTableDef TB_CORE_FUNCPERM = new TbCoreFuncpermTableDef();

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
     * 所属公司id
     */
    public final QueryColumn SY_COMPANY_ID = new QueryColumn(this, "sy_company_id");

    /**
     * 权限类型
     */
    public final QueryColumn FUNCPERM_TYPE = new QueryColumn(this, "funcperm_type");

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
     * 权限开关
     */
    public final QueryColumn FUNCPERM_ON_OFF = new QueryColumn(this, "funcperm_on_off");

    /**
     * 所属公司名称
     */
    public final QueryColumn SY_COMPANY_NAME = new QueryColumn(this, "sy_company_name");

    /**
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 配置
     */
    public final QueryColumn FUNCPERM_CONFIG = new QueryColumn(this, "funcperm_config");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * saas产品
     */
    public final QueryColumn FUNCPERM_SAAS_PID = new QueryColumn(this, "funcperm_saas_pid");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

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
    public final QueryColumn TB_CORE_FUNCPERM_ID = new QueryColumn(this, "tb_core_funcperm_id");

    /**
     * 功能id
     */
    public final QueryColumn FUNCPERM_FUNCINFO_ID = new QueryColumn(this, "funcperm_funcinfo_id");

    /**
     * 所属集团公司名称
     */
    public final QueryColumn SY_GROUP_COMPANY_NAME = new QueryColumn(this, "sy_group_company_name");

    /**
     * 功能code
     */
    public final QueryColumn FUNCPERM_FUNCINFO_CODE = new QueryColumn(this, "funcperm_funcinfo_code");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_FUNCPERM_ID, FUNCPERM_TYPE, FUNCPERM_ON_OFF, FUNCPERM_CONFIG, FUNCPERM_FUNCINFO_CODE, FUNCPERM_FUNCINFO_ID, FUNCPERM_SAAS_PID, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_COMPANY_ID, SY_COMPANY_NAME, SY_GROUP_COMPANY_ID, SY_GROUP_COMPANY_NAME, SY_ORG_ID, SY_TENANT_ID, SY_TENANT_NAME};

    public TbCoreFuncpermTableDef() {
        super("", "tb_core_funcperm");
    }

    private TbCoreFuncpermTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreFuncpermTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreFuncpermTableDef("", "tb_core_funcperm", alias));
    }

}
