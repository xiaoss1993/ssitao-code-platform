package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 异常日志 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbCoreExceptionlogTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 异常日志
     */
    public static final TbCoreExceptionlogTableDef TB_CORE_EXCEPTIONLOG = new TbCoreExceptionlogTableDef();

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
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 请求url
     */
    public final QueryColumn EXCEPTIONLOG_URL = new QueryColumn(this, "exceptionlog_url");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 异常编号
     */
    public final QueryColumn EXCEPTIONLOG_CODE = new QueryColumn(this, "exceptionlog_code");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 所属集团公司id
     */
    public final QueryColumn SY_GROUP_COMPANY_ID = new QueryColumn(this, "sy_group_company_id");

    /**
     * 所属集团公司名称
     */
    public final QueryColumn SY_GROUP_COMPANY_NAME = new QueryColumn(this, "sy_group_company_name");

    /**
     * 异常消息
     */
    public final QueryColumn EXCEPTIONLOG_MESSAGE = new QueryColumn(this, "exceptionlog_message");

    /**
     * 主键id
     */
    public final QueryColumn TB_CORE_EXCEPTIONLOG_ID = new QueryColumn(this, "tb_core_exceptionlog_id");

    /**
     * 请求参数
     */
    public final QueryColumn EXCEPTIONLOG_PARAMETER = new QueryColumn(this, "exceptionlog_parameter");

    /**
     * 异常栈信息
     */
    public final QueryColumn EXCEPTIONLOG_STACKTRACE = new QueryColumn(this, "exceptionlog_stacktrace");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_EXCEPTIONLOG_ID, EXCEPTIONLOG_STACKTRACE, EXCEPTIONLOG_PARAMETER, EXCEPTIONLOG_URL, EXCEPTIONLOG_CODE, EXCEPTIONLOG_MESSAGE, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_COMPANY_ID, SY_COMPANY_NAME, SY_GROUP_COMPANY_ID, SY_GROUP_COMPANY_NAME, SY_ORG_ID, SY_TENANT_ID, SY_TENANT_NAME};

    public TbCoreExceptionlogTableDef() {
        super("", "tb_core_exceptionlog");
    }

    private TbCoreExceptionlogTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreExceptionlogTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreExceptionlogTableDef("", "tb_core_exceptionlog", alias));
    }

}
