package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 发布历史记录 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class ReleaseHistoryTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 发布历史记录
     */
    public static final ReleaseHistoryTableDef RELEASE_HISTORY = new ReleaseHistoryTableDef();

    /**
     * 主键id
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 作业id
     */
    public final QueryColumn JOB_ID = new QueryColumn(this, "job_id");

    /**
     * 持续时间
     */
    public final QueryColumn DURATION = new QueryColumn(this, "duration");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 构建者
     */
    public final QueryColumn PUBLISHER = new QueryColumn(this, "publisher");

    /**
     * 上传的地址
     */
    public final QueryColumn UPLOAD_PATH = new QueryColumn(this, "upload_path");

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
     * 更新内容
     */
    public final QueryColumn UPDATE_CONTENT = new QueryColumn(this, "update_content");

    /**
     * 构建开始时间
     */
    public final QueryColumn BUILD_START_TIME = new QueryColumn(this, "build_start_time");

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
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, JOB_ID, BUILD_START_TIME, PUBLISHER, UPLOAD_PATH, UPDATE_CONTENT, DURATION, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX};

    public ReleaseHistoryTableDef() {
        super("", "release_history");
    }

    private ReleaseHistoryTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public ReleaseHistoryTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new ReleaseHistoryTableDef("", "release_history", alias));
    }

}
