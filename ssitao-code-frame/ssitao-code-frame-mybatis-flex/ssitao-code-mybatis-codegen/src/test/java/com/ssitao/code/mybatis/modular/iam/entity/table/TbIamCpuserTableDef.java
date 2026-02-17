package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 三方人员管理 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbIamCpuserTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 三方人员管理
     */
    public static final TbIamCpuserTableDef TB_IAM_CPUSER = new TbIamCpuserTableDef();

    /**
     * 所属机构id
     */
    public final QueryColumn SY_ORG_ID = new QueryColumn(this, "sy_org_id");

    /**
     * 身份
     */
    public final QueryColumn CPUSER_SF = new QueryColumn(this, "cpuser_sf");

    /**
     * 头像
     */
    public final QueryColumn CPUSER_TX = new QueryColumn(this, "cpuser_tx");

    /**
     * 账号
     */
    public final QueryColumn CPUSER_ZH = new QueryColumn(this, "cpuser_zh");

    /**
     * 座机
     */
    public final QueryColumn CPUSER_ZJ = new QueryColumn(this, "cpuser_zj");

    /**
     * 职务
     */
    public final QueryColumn CPUSER_ZW = new QueryColumn(this, "cpuser_zw");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 批次号
     */
    public final QueryColumn CPUSER_PCH = new QueryColumn(this, "cpuser_pch");

    /**
     * 英文名
     */
    public final QueryColumn CPUSER_YWM = new QueryColumn(this, "cpuser_ywm");

    /**
     * 挂接本地用户
     */
    public final QueryColumn CPUSER_BDYH = new QueryColumn(this, "cpuser_bdyh");

    /**
     * 是否加入
     */
    public final QueryColumn CPUSER_SFJR = new QueryColumn(this, "cpuser_sfjr");

    /**
     * 类型
     */
    public final QueryColumn CPUSER_TYPE = new QueryColumn(this, "cpuser_type");

    /**
     * 租户id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * 邮箱
     */
    public final QueryColumn CPUSER_EMAIL = new QueryColumn(this, "cpuser_email");

    /**
     * 手机
     */
    public final QueryColumn CPUSER_PHONE = new QueryColumn(this, "cpuser_phone");

    /**
     * 所属公司id
     */
    public final QueryColumn SY_COMPANY_ID = new QueryColumn(this, "sy_company_id");

    /**
     * 挂接本地用户_id
     */
    public final QueryColumn CPUSER_BDYH_ID = new QueryColumn(this, "cpuser_bdyh_id");

    /**
     * 第三方部门
     */
    public final QueryColumn CPUSER_CPDEPT = new QueryColumn(this, "cpuser_cpdept");

    /**
     * 可用
     */
    public final QueryColumn CPUSER_ENABLE = new QueryColumn(this, "cpuser_enable");

    /**
     * 用户id
     */
    public final QueryColumn CPUSER_USER_ID = new QueryColumn(this, "cpuser_user_id");

    /**
     * 最近同步时间
     */
    public final QueryColumn CPUSER_ZJTBSJ = new QueryColumn(this, "cpuser_zjtbsj");

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
     * 性别
     */
    public final QueryColumn CPUSER_SEX_CODE = new QueryColumn(this, "cpuser_sex_code");

    /**
     * 所属公司名称
     */
    public final QueryColumn SY_COMPANY_NAME = new QueryColumn(this, "sy_company_name");

    /**
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 第三方部门_外键id
     */
    public final QueryColumn TB_IAM_CPDEPT_ID = new QueryColumn(this, "tb_iam_cpdept_id");

    /**
     * 主键id
     */
    public final QueryColumn TB_IAM_CPUSER_ID = new QueryColumn(this, "tb_iam_cpuser_id");

    /**
     * 岗位
     */
    public final QueryColumn CPUSER_POSITION = new QueryColumn(this, "cpuser_position");

    /**
     * 是否挂接
     */
    public final QueryColumn CPUSER_SFGJ_CODE = new QueryColumn(this, "cpuser_sfgj_code");

    /**
     * 用户
     */
    public final QueryColumn CPUSER_USER_NAME = new QueryColumn(this, "cpuser_user_name");

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
     * 所属集团公司id
     */
    public final QueryColumn SY_GROUP_COMPANY_ID = new QueryColumn(this, "sy_group_company_id");

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
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_IAM_CPUSER_ID, TB_IAM_CPDEPT_ID, CPUSER_USER_NAME, CPUSER_USER_ID, CPUSER_ZJTBSJ, CPUSER_SFGJ_CODE, CPUSER_BDYH, CPUSER_BDYH_ID, CPUSER_CPDEPT, CPUSER_ZW, CPUSER_ZH, CPUSER_SEX_CODE, CPUSER_PHONE, CPUSER_ZJ, CPUSER_EMAIL, CPUSER_YWM, CPUSER_SF, CPUSER_ENABLE, CPUSER_SFJR, CPUSER_TX, CPUSER_TYPE, CPUSER_PCH, CPUSER_POSITION, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_COMPANY_ID, SY_COMPANY_NAME, SY_GROUP_COMPANY_ID, SY_GROUP_COMPANY_NAME, SY_ORG_ID, SY_TENANT_ID, SY_TENANT_NAME};

    public TbIamCpuserTableDef() {
        super("", "tb_iam_cpuser");
    }

    private TbIamCpuserTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbIamCpuserTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbIamCpuserTableDef("", "tb_iam_cpuser", alias));
    }

}
