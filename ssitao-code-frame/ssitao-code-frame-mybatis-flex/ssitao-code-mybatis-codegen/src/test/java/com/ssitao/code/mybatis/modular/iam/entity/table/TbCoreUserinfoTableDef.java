package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 用户个性化（用户态） 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbCoreUserinfoTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 用户个性化（用户态）
     */
    public static final TbCoreUserinfoTableDef TB_CORE_USERINFO = new TbCoreUserinfoTableDef();

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 租户_id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

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
     * 配置类型
     */
    public final QueryColumn USERINFO_TYPE = new QueryColumn(this, "userinfo_type");

    /**
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 用户个性化配置
     */
    public final QueryColumn USERINFO_CONFIG = new QueryColumn(this, "userinfo_config");

    /**
     * 功能id
     */
    public final QueryColumn USERINFO_FUNCID = new QueryColumn(this, "userinfo_funcid");

    /**
     * 用户id
     */
    public final QueryColumn USERINFO_USERID = new QueryColumn(this, "userinfo_userid");

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
    public final QueryColumn TB_CORE_USERINFO_ID = new QueryColumn(this, "tb_core_userinfo_id");

    /**
     * 功能编码
     */
    public final QueryColumn USERINFO_FUNCCODE = new QueryColumn(this, "userinfo_funccode");

    /**
     * 用户编码
     */
    public final QueryColumn USERINFO_USERCODE = new QueryColumn(this, "userinfo_usercode");

    /**
     * 表名
     */
    public final QueryColumn USERINFO_TABLECODE = new QueryColumn(this, "userinfo_tablecode");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_USERINFO_ID, USERINFO_USERID, USERINFO_USERCODE, USERINFO_CONFIG, USERINFO_TYPE, USERINFO_FUNCCODE, USERINFO_TABLECODE, USERINFO_FUNCID, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_TENANT_ID, SY_TENANT_NAME};

    public TbCoreUserinfoTableDef() {
        super("", "tb_core_userinfo");
    }

    private TbCoreUserinfoTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreUserinfoTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreUserinfoTableDef("", "tb_core_userinfo", alias));
    }

}
