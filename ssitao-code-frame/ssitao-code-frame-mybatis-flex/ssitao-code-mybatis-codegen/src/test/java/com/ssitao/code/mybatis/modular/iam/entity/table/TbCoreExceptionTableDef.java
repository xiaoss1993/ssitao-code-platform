package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 执行异常 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbCoreExceptionTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 执行异常
     */
    public static final TbCoreExceptionTableDef TB_CORE_EXCEPTION = new TbCoreExceptionTableDef();

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
     * 异常编号
     */
    public final QueryColumn EXCEPTION_CODE = new QueryColumn(this, "exception_code");

    /**
     * 登记者所在部门id
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 登记人id
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 数据源
     */
    public final QueryColumn EXCEPTION_DSNAME = new QueryColumn(this, "exception_dsname");

    /**
     * 登记者所在部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 异常消息
     */
    public final QueryColumn EXCEPTION_MESSAGE = new QueryColumn(this, "exception_message");

    /**
     * tokenid
     */
    public final QueryColumn EXCEPTION_TOKENID = new QueryColumn(this, "exception_tokenid");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    
    public final QueryColumn TB_CORE_EXCEPTION_ID = new QueryColumn(this, "tb_core_exception_id");

    /**
     * 执行时间
     */
    public final QueryColumn EXCEPTION_EXECUTIME = new QueryColumn(this, "exception_executime");

    /**
     * 执行人
     */
    public final QueryColumn EXCEPTION_EXECUUSER = new QueryColumn(this, "exception_execuuser");

    /**
     * 请求url
     */
    public final QueryColumn EXCEPTION_REQUESTURL = new QueryColumn(this, "exception_requesturl");

    /**
     * 异常栈信息
     */
    public final QueryColumn EXCEPTION_STACKTRACE = new QueryColumn(this, "exception_stacktrace");

    /**
     * 执行人id
     */
    public final QueryColumn EXCEPTION_EXECUUSERID = new QueryColumn(this, "exception_execuuserid");

    /**
     * 执行人编码
     */
    public final QueryColumn EXCEPTION_EXECUUSERCODE = new QueryColumn(this, "exception_execuusercode");

    /**
     * 请求参数
     */
    public final QueryColumn EXCEPTION_REQUESTPARAMS = new QueryColumn(this, "exception_requestparams");

    /**
     * 异常参数
     */
    public final QueryColumn EXCEPTION_EXCEPTIONPARAM = new QueryColumn(this, "exception_exceptionparam");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_EXCEPTION_ID, EXCEPTION_EXECUTIME, EXCEPTION_EXECUUSER, EXCEPTION_EXECUUSERCODE, EXCEPTION_REQUESTPARAMS, EXCEPTION_REQUESTURL, EXCEPTION_STACKTRACE, EXCEPTION_EXCEPTIONPARAM, EXCEPTION_CODE, EXCEPTION_MESSAGE, EXCEPTION_DSNAME, EXCEPTION_TOKENID, EXCEPTION_EXECUUSERID, SY_CREATEORGID, SY_CREATEUSERID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERNAME, SY_ORDERINDEX, SY_STATUS};

    public TbCoreExceptionTableDef() {
        super("", "tb_core_exception");
    }

    private TbCoreExceptionTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreExceptionTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreExceptionTableDef("", "tb_core_exception", alias));
    }

}
