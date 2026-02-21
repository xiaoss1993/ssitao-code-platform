package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 开发者机构人员 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbIamDeveloperOrgTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 开发者机构人员
     */
    public static final TbIamDeveloperOrgTableDef TB_IAM_DEVELOPER_ORG = new TbIamDeveloperOrgTableDef();

    /**
     * 用户性别
     */
    public final QueryColumn TB_SEX = new QueryColumn(this, "tb_sex");

    /**
     * 用户编码
     */
    public final QueryColumn TB_CODE = new QueryColumn(this, "tb_code");

    /**
     * 用户名称
     */
    public final QueryColumn TB_NAME = new QueryColumn(this, "tb_name");

    /**
     * 所属机构id
     */
    public final QueryColumn SY_ORG_ID = new QueryColumn(this, "sy_org_id");

    /**
     * 用户邮箱
     */
    public final QueryColumn TB_EMAIL = new QueryColumn(this, "tb_email");

    /**
     * 手机号
     */
    public final QueryColumn TB_PHONE = new QueryColumn(this, "tb_phone");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 备注
     */
    public final QueryColumn TB_REMARK = new QueryColumn(this, "tb_remark");

    /**
     * 角色id
     */
    public final QueryColumn TB_ROLEID = new QueryColumn(this, "tb_roleid");

    /**
     * 租户id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * 角色名称
     */
    public final QueryColumn TB_ROLENAME = new QueryColumn(this, "tb_rolename");

    /**
     * 头像
     */
    public final QueryColumn TB_TOUXIANG = new QueryColumn(this, "tb_touxiang");

    /**
     * 所属公司id
     */
    public final QueryColumn SY_COMPANY_ID = new QueryColumn(this, "sy_company_id");

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
     * 租户名称
     */
    public final QueryColumn SY_TENANT_NAME = new QueryColumn(this, "sy_tenant_name");

    /**
     * 用户状态
     */
    public final QueryColumn TB_USER_STATUS = new QueryColumn(this, "tb_user_status");

    /**
     * 所属公司名称
     */
    public final QueryColumn SY_COMPANY_NAME = new QueryColumn(this, "sy_company_name");

    /**
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 修改人部门主键
     */
    public final QueryColumn SY_MODIFYORGID = new QueryColumn(this, "sy_modifyorgid");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 修改人主键
     */
    public final QueryColumn SY_MODIFYUSERID = new QueryColumn(this, "sy_modifyuserid");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 修改人部门
     */
    public final QueryColumn SY_MODIFYORGNAME = new QueryColumn(this, "sy_modifyorgname");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 所属集团公司id
     */
    public final QueryColumn SY_GROUP_COMPANY_ID = new QueryColumn(this, "sy_group_company_id");

    /**
     * 修改人
     */
    public final QueryColumn SY_MODIFYUSERNAME = new QueryColumn(this, "sy_modifyusername");

    /**
     * 主键id
     */
    public final QueryColumn TB_IAM_DEVELOP_ORG_ID = new QueryColumn(this, "tb_iam_develop_org_id");

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
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_IAM_DEVELOP_ORG_ID, TB_PHONE, TB_TOUXIANG, TB_NAME, TB_CODE, TB_EMAIL, TB_SEX, TB_ROLENAME, TB_ROLEID, TB_REMARK, TB_USER_STATUS, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_COMPANY_ID, SY_COMPANY_NAME, SY_GROUP_COMPANY_ID, SY_GROUP_COMPANY_NAME, SY_ORG_ID, SY_TENANT_ID, SY_TENANT_NAME, SY_MODIFYORGID, SY_MODIFYORGNAME, SY_MODIFYUSERID, SY_MODIFYUSERNAME, SY_MODIFYTIME};

    public TbIamDeveloperOrgTableDef() {
        super("", "tb_iam_developer_org");
    }

    private TbIamDeveloperOrgTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbIamDeveloperOrgTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbIamDeveloperOrgTableDef("", "tb_iam_developer_org", alias));
    }

}
