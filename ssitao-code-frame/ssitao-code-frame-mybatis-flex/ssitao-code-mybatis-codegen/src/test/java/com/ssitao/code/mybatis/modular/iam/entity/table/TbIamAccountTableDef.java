package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 账号管理 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbIamAccountTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 账号管理
     */
    public static final TbIamAccountTableDef TB_IAM_ACCOUNT = new TbIamAccountTableDef();

    /**
     * 所属机构_id
     */
    public final QueryColumn SY_ORG_ID = new QueryColumn(this, "sy_org_id");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 所属机构_name
     */
    public final QueryColumn SY_ORG_NAME = new QueryColumn(this, "sy_org_name");

    /**
     * 用户性别
     */
    public final QueryColumn ACCOUNT_SEX = new QueryColumn(this, "account_sex");

    /**
     * 租户_id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * 用户状态
     */
    public final QueryColumn USER_STATUS = new QueryColumn(this, "user_status");

    /**
     * 编码
     */
    public final QueryColumn ACCOUNT_CODE = new QueryColumn(this, "account_code");

    /**
     * 邮件
     */
    public final QueryColumn ACCOUNT_MAIL = new QueryColumn(this, "account_mail");

    /**
     * 名称
     */
    public final QueryColumn ACCOUNT_NAME = new QueryColumn(this, "account_name");

    /**
     * 盐值
     */
    public final QueryColumn ACCOUNT_SALT = new QueryColumn(this, "account_salt");

    /**
     * 电话
     */
    public final QueryColumn ACCOUNT_PHONE = new QueryColumn(this, "account_phone");

    /**
     * 登记时间
     */
    public final QueryColumn SY_CREATETIME = new QueryColumn(this, "sy_createtime");

    /**
     * 排序字段
     */
    public final QueryColumn SY_ORDERINDEX = new QueryColumn(this, "sy_orderindex");

    /**
     * 密级_code
     */
    public final QueryColumn SY_SECRET_CODE = new QueryColumn(this, "sy_secret_code");

    /**
     * 密级_name
     */
    public final QueryColumn SY_SECRET_NAME = new QueryColumn(this, "sy_secret_name");

    /**
     * 租户名称
     */
    public final QueryColumn SY_TENANT_NAME = new QueryColumn(this, "sy_tenant_name");

    /**
     * 头像
     */
    public final QueryColumn ACCOUNT_AVATAR = new QueryColumn(this, "account_avatar");

    /**
     * 是否初始化
     */
    public final QueryColumn ACCOUNT_INITED = new QueryColumn(this, "account_inited");

    /**
     * 开放id
     */
    public final QueryColumn ACCOUNT_OPENID = new QueryColumn(this, "account_openid");

    /**
     * 备注信息
     */
    public final QueryColumn ACCOUNT_REMARK = new QueryColumn(this, "account_remark");

    /**
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 卡号
     */
    public final QueryColumn ACCOUNT_CARDNUM = new QueryColumn(this, "account_cardnum");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 主键id
     */
    public final QueryColumn TB_IAM_ACCOUNT_ID = new QueryColumn(this, "tb_iam_account_id");

    /**
     * 密码
     */
    public final QueryColumn ACCOUNT_PASSWORD = new QueryColumn(this, "account_password");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 过期时间
     */
    public final QueryColumn ACCOUNT_EXPIRE_TIME = new QueryColumn(this, "account_expire_time");

    /**
     * 锁定超时时间
     */
    public final QueryColumn ACCOUNT_LOCK_EXPIRE = new QueryColumn(this, "account_lock_expire");

    /**
     * 是否锁定_code
     */
    public final QueryColumn ACCOUNT_LOCKED_CODE = new QueryColumn(this, "account_locked_code");

    /**
     * 是否锁定_name
     */
    public final QueryColumn ACCOUNT_LOCKED_NAME = new QueryColumn(this, "account_locked_name");

    /**
     * 用户关联_id
     */
    public final QueryColumn USER_ASSOCIATION_ID = new QueryColumn(this, "user_association_id");

    /**
     * 是否锁定
     */
    public final QueryColumn ACCOUNT_LOCKED_STATUS = new QueryColumn(this, "account_locked_status");

    /**
     * 是否永久使用_code
     */
    public final QueryColumn ACCOUNT_PERMANENT_CODE = new QueryColumn(this, "account_permanent_code");

    /**
     * 是否永久使用_name
     */
    public final QueryColumn ACCOUNT_PERMANENT_NAME = new QueryColumn(this, "account_permanent_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_IAM_ACCOUNT_ID, ACCOUNT_NAME, ACCOUNT_CODE, ACCOUNT_OPENID, ACCOUNT_PASSWORD, ACCOUNT_PHONE, ACCOUNT_MAIL, ACCOUNT_AVATAR, USER_ASSOCIATION_ID, ACCOUNT_EXPIRE_TIME, ACCOUNT_LOCKED_CODE, ACCOUNT_LOCKED_NAME, ACCOUNT_CARDNUM, ACCOUNT_REMARK, ACCOUNT_SALT, ACCOUNT_LOCK_EXPIRE, ACCOUNT_INITED, ACCOUNT_PERMANENT_CODE, ACCOUNT_PERMANENT_NAME, ACCOUNT_LOCKED_STATUS, ACCOUNT_SEX, USER_STATUS, SY_TENANT_ID, SY_TENANT_NAME, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_ORG_ID, SY_ORG_NAME, SY_SECRET_CODE, SY_SECRET_NAME};

    public TbIamAccountTableDef() {
        super("", "tb_iam_account");
    }

    private TbIamAccountTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbIamAccountTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbIamAccountTableDef("", "tb_iam_account", alias));
    }

}
