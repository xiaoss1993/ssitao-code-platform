package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 机构管理 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbIamOrgTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 机构管理
     */
    public static final TbIamOrgTableDef TB_IAM_ORG = new TbIamOrgTableDef();

    /**
     * 编码
     */
    public final QueryColumn ORG_CODE = new QueryColumn(this, "org_code");

    /**
     * 名称
     */
    public final QueryColumn ORG_NAME = new QueryColumn(this, "org_name");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 备注信息
     */
    public final QueryColumn ORG_REMARK = new QueryColumn(this, "org_remark");

    /**
     * 账户主键字段
     */
    public final QueryColumn ORG_FIELD_PK = new QueryColumn(this, "org_field_pk");

    /**
     * 租户_id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * 主键id
     */
    public final QueryColumn TB_IAM_ORG_ID = new QueryColumn(this, "tb_iam_org_id");

    /**
     * 登记时间
     */
    public final QueryColumn SY_CREATETIME = new QueryColumn(this, "sy_createtime");

    /**
     * 修改时间
     */
    public final QueryColumn SY_MODIFYTIME = new QueryColumn(this, "sy_modifytime");

    /**
     * 排序字段
     */
    public final QueryColumn SY_ORDERINDEX = new QueryColumn(this, "sy_orderindex");

    /**
     * 租户_name
     */
    public final QueryColumn SY_TENANT_NAME = new QueryColumn(this, "sy_tenant_name");

    /**
     * 账号性别字段
     */
    public final QueryColumn ORG_ACCOUNT_SEX = new QueryColumn(this, "org_account_sex");

    /**
     * 账号密级编码字段
     */
    public final QueryColumn ORG_SECRET_CODE = new QueryColumn(this, "org_secret_code");

    /**
     * 账号密级名称字段
     */
    public final QueryColumn ORG_SECRET_NAME = new QueryColumn(this, "org_secret_name");

    /**
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 修改人部门主键
     */
    public final QueryColumn SY_MODIFYORGID = new QueryColumn(this, "sy_modifyorgid");

    /**
     * 账户编码字段
     */
    public final QueryColumn ORG_ACCOUNT_CODE = new QueryColumn(this, "org_account_code");

    /**
     * 账号邮箱字段
     */
    public final QueryColumn ORG_ACCOUNT_MAIL = new QueryColumn(this, "org_account_mail");

    /**
     * 账户名称字段
     */
    public final QueryColumn ORG_ACCOUNT_NAME = new QueryColumn(this, "org_account_name");

    /**
     * 角色字段_id
     */
    public final QueryColumn ORG_ROLEFIELD_ID = new QueryColumn(this, "org_rolefield_id");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 修改人主键
     */
    public final QueryColumn SY_MODIFYUSERID = new QueryColumn(this, "sy_modifyuserid");

    /**
     * 账号手机字段
     */
    public final QueryColumn ORG_ACCOUNT_PHONE = new QueryColumn(this, "org_account_phone");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 修改人部门
     */
    public final QueryColumn SY_MODIFYORGNAME = new QueryColumn(this, "sy_modifyorgname");

    /**
     * 账号头像字段
     */
    public final QueryColumn ORG_ACCOUNT_AVATAR = new QueryColumn(this, "org_account_avatar");

    /**
     * 账号真实用户状态
     */
    public final QueryColumn ORG_ACCOUNT_STATUS = new QueryColumn(this, "org_account_status");

    /**
     * 角色字段_name
     */
    public final QueryColumn ORG_ROLEFIELD_NAME = new QueryColumn(this, "org_rolefield_name");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 修改人
     */
    public final QueryColumn SY_MODIFYUSERNAME = new QueryColumn(this, "sy_modifyusername");

    /**
     * 资源表_code
     */
    public final QueryColumn ORG_RESOURCETABLE_CODE = new QueryColumn(this, "org_resourcetable_code");

    /**
     * 资源表_name
     */
    public final QueryColumn ORG_RESOURCETABLE_NAME = new QueryColumn(this, "org_resourcetable_name");

    /**
     * 资源表_id
     */
    public final QueryColumn TB_CORE_RESOURCETABLE_ID = new QueryColumn(this, "tb_core_resourcetable_id");

    /**
     * 账号访问状态
     */
    public final QueryColumn ORG_ACCOUNTACCESS_STATUS = new QueryColumn(this, "org_accountaccess_status");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_RESOURCETABLE_ID, TB_IAM_ORG_ID, ORG_NAME, ORG_CODE, ORG_RESOURCETABLE_CODE, ORG_RESOURCETABLE_NAME, ORG_ACCOUNT_PHONE, ORG_ACCOUNT_MAIL, ORG_ACCOUNT_AVATAR, ORG_REMARK, ORG_ROLEFIELD_ID, ORG_ROLEFIELD_NAME, ORG_FIELD_PK, ORG_ACCOUNT_NAME, ORG_ACCOUNT_CODE, ORG_ACCOUNT_SEX, ORG_ACCOUNT_STATUS, ORG_ACCOUNTACCESS_STATUS, ORG_SECRET_CODE, ORG_SECRET_NAME, SY_TENANT_ID, SY_TENANT_NAME, SY_MODIFYORGID, SY_MODIFYORGNAME, SY_MODIFYUSERID, SY_MODIFYUSERNAME, SY_MODIFYTIME, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX};

    public TbIamOrgTableDef() {
        super("", "tb_iam_org");
    }

    private TbIamOrgTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbIamOrgTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbIamOrgTableDef("", "tb_iam_org", alias));
    }

}
