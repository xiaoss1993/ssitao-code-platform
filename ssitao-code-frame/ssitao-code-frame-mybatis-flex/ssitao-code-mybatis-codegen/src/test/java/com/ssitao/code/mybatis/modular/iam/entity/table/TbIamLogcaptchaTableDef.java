package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 登录验证码 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbIamLogcaptchaTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 登录验证码
     */
    public static final TbIamLogcaptchaTableDef TB_IAM_LOGCAPTCHA = new TbIamLogcaptchaTableDef();

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 所属公司_id
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
     * 验证码
     */
    public final QueryColumn LOGCAPTCHA_NUM = new QueryColumn(this, "logcaptcha_num");

    /**
     * 所属公司_name
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
     * 账号_id
     */
    public final QueryColumn TB_IAM_ACCOUNT_ID = new QueryColumn(this, "tb_iam_account_id");

    /**
     * 手机号
     */
    public final QueryColumn LOGCAPTCHA_PHONE = new QueryColumn(this, "logcaptcha_phone");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 登录设备
     */
    public final QueryColumn LOGCAPTCHA_DEVICE = new QueryColumn(this, "logcaptcha_device");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 所属集团公司_id
     */
    public final QueryColumn SY_GROUP_COMPANY_ID = new QueryColumn(this, "sy_group_company_id");

    /**
     * 主键id
     */
    public final QueryColumn TB_IAM_LOGCAPTCHA_ID = new QueryColumn(this, "tb_iam_logcaptcha_id");

    /**
     * 所属集团公司_name
     */
    public final QueryColumn SY_GROUP_COMPANY_NAME = new QueryColumn(this, "sy_group_company_name");

    /**
     * 实效时间
     */
    public final QueryColumn LOGCAPTCHA_EXPIRETIME = new QueryColumn(this, "logcaptcha_expiretime");

    /**
     * 账号_name
     */
    public final QueryColumn LOGCAPTCHA_ACCOUNT_NAME = new QueryColumn(this, "logcaptcha_account_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_IAM_ACCOUNT_ID, TB_IAM_LOGCAPTCHA_ID, LOGCAPTCHA_NUM, LOGCAPTCHA_EXPIRETIME, LOGCAPTCHA_PHONE, LOGCAPTCHA_ACCOUNT_NAME, LOGCAPTCHA_DEVICE, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_COMPANY_ID, SY_COMPANY_NAME, SY_GROUP_COMPANY_ID, SY_GROUP_COMPANY_NAME};

    public TbIamLogcaptchaTableDef() {
        super("", "tb_iam_logcaptcha");
    }

    private TbIamLogcaptchaTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbIamLogcaptchaTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbIamLogcaptchaTableDef("", "tb_iam_logcaptcha", alias));
    }

}
