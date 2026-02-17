package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 执行日志 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbCoreExecutionlogTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 执行日志
     */
    public static final TbCoreExecutionlogTableDef TB_CORE_EXECUTIONLOG = new TbCoreExecutionlogTableDef();

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 审核标记
     */
    public final QueryColumn SY_AUDFLAG = new QueryColumn(this, "sy_audflag");

    /**
     * 登记时间
     */
    public final QueryColumn SY_CREATETIME = new QueryColumn(this, "sy_createtime");

    /**
     * 排序字段
     */
    public final QueryColumn SY_ORDERINDEX = new QueryColumn(this, "sy_orderindex");

    /**
     * 登记者所在部门id
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 登记人id
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 登记者所在部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 数据源
     */
    public final QueryColumn EXECUTIONLOG_DSNAME = new QueryColumn(this, "executionlog_dsname");

    /**
     * tokenid
     */
    public final QueryColumn EXECUTIONLOG_TOKENID = new QueryColumn(this, "executionlog_tokenid");

    /**
     * 请求耗时
     */
    public final QueryColumn EXECUTIONLOG_DURATION = new QueryColumn(this, "executionlog_duration");

    /**
     * 执行时间
     */
    public final QueryColumn EXECUTIONLOG_EXECTIME = new QueryColumn(this, "executionlog_exectime");

    
    public final QueryColumn TB_CORE_EXECUTIONLOG_ID = new QueryColumn(this, "tb_core_executionlog_id");

    /**
     * 执行人
     */
    public final QueryColumn EXECUTIONLOG_EXECUUSER = new QueryColumn(this, "executionlog_execuuser");

    /**
     * 表名
     */
    public final QueryColumn EXECUTIONLOG_TABLECODE = new QueryColumn(this, "executionlog_tablecode");

    /**
     * 请求url
     */
    public final QueryColumn EXECUTIONLOG_REQUESTURL = new QueryColumn(this, "executionlog_requesturl");

    /**
     * 耗时描述
     */
    public final QueryColumn EXECUTIONLOG_DURATIONSTR = new QueryColumn(this, "executionlog_durationstr");

    /**
     * 执行人id
     */
    public final QueryColumn EXECUTIONLOG_EXECUUSERID = new QueryColumn(this, "executionlog_execuuserid");

    /**
     * 执行人编码
     */
    public final QueryColumn EXECUTIONLOG_EXECUUSERCODE = new QueryColumn(this, "executionlog_execuusercode");

    /**
     * 请求参数
     */
    public final QueryColumn EXECUTIONLOG_REQUESTPARAMS = new QueryColumn(this, "executionlog_requestparams");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_EXECUTIONLOG_ID, EXECUTIONLOG_DURATION, EXECUTIONLOG_DURATIONSTR, EXECUTIONLOG_EXECTIME, EXECUTIONLOG_EXECUUSER, EXECUTIONLOG_EXECUUSERCODE, EXECUTIONLOG_REQUESTPARAMS, EXECUTIONLOG_REQUESTURL, EXECUTIONLOG_TABLECODE, EXECUTIONLOG_EXECUUSERID, EXECUTIONLOG_TOKENID, EXECUTIONLOG_DSNAME, SY_CREATEORGID, SY_CREATEUSERID, SY_AUDFLAG, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERNAME, SY_ORDERINDEX, SY_STATUS};

    public TbCoreExecutionlogTableDef() {
        super("", "tb_core_executionlog");
    }

    private TbCoreExecutionlogTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreExecutionlogTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreExecutionlogTableDef("", "tb_core_executionlog", alias));
    }

}
