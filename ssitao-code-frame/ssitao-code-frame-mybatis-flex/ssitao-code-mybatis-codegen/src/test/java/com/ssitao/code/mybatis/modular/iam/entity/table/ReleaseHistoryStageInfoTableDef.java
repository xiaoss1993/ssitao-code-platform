package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 发布历史阶段信息 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class ReleaseHistoryStageInfoTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 发布历史阶段信息
     */
    public static final ReleaseHistoryStageInfoTableDef RELEASE_HISTORY_STAGE_INFO = new ReleaseHistoryStageInfoTableDef();

    /**
     * 主键id
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 是否成功
     */
    public final QueryColumn SUCCESS = new QueryColumn(this, "success");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 阶段名称
     */
    public final QueryColumn STAGE_NAME = new QueryColumn(this, "stage_name");

    /**
     * 审核标记
     */
    public final QueryColumn SY_AUDFLAG = new QueryColumn(this, "sy_audflag");

    /**
     * 阶段状态
     */
    public final QueryColumn STAGE_STATE = new QueryColumn(this, "stage_state");

    /**
     * 阶段日志路径
     */
    public final QueryColumn STAGE_LOG_PATH = new QueryColumn(this, "stage_log_path");

    /**
     * 登记时间
     */
    public final QueryColumn SY_CREATETIME = new QueryColumn(this, "sy_createtime");

    /**
     * 排序字段
     */
    public final QueryColumn SY_ORDERINDEX = new QueryColumn(this, "sy_orderindex");

    /**
     * 阶段耗时
     */
    public final QueryColumn STAGE_DURATION = new QueryColumn(this, "stage_duration");

    /**
     * 阶段信息全名
     */
    public final QueryColumn STAGE_FULL_NAME = new QueryColumn(this, "stage_full_name");

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
     * 发布历史id
     */
    public final QueryColumn RELEASE_HISTORY_ID = new QueryColumn(this, "release_history_id");

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
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, RELEASE_HISTORY_ID, STAGE_FULL_NAME, STAGE_NAME, STAGE_DURATION, STAGE_STATE, STAGE_LOG_PATH, SUCCESS, SY_AUDFLAG, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX};

    public ReleaseHistoryStageInfoTableDef() {
        super("", "release_history_stage_info");
    }

    private ReleaseHistoryStageInfoTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public ReleaseHistoryStageInfoTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new ReleaseHistoryStageInfoTableDef("", "release_history_stage_info", alias));
    }

}
