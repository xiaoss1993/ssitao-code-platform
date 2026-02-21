package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 供应商管理 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbIamSupplierTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 供应商管理
     */
    public static final TbIamSupplierTableDef TB_IAM_SUPPLIER = new TbIamSupplierTableDef();

    /**
     * 所属机构id
     */
    public final QueryColumn SY_ORG_ID = new QueryColumn(this, "sy_org_id");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 租户id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * 用户性别
     */
    public final QueryColumn SUPPLIER_SEX = new QueryColumn(this, "supplier_sex");

    /**
     * 所属公司id
     */
    public final QueryColumn SY_COMPANY_ID = new QueryColumn(this, "sy_company_id");

    /**
     * 用户编码
     */
    public final QueryColumn SUPPLIER_CODE = new QueryColumn(this, "supplier_code");

    /**
     * 用户名称
     */
    public final QueryColumn SUPPLIER_NAME = new QueryColumn(this, "supplier_name");

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
     * 用户邮箱
     */
    public final QueryColumn SUPPLIER_EMAIL = new QueryColumn(this, "supplier_email");

    /**
     * 手机号
     */
    public final QueryColumn SUPPLIER_PHONE = new QueryColumn(this, "supplier_phone");

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
     * 备注
     */
    public final QueryColumn SUPPLIER_REMARK = new QueryColumn(this, "supplier_remark");

    /**
     * 角色id
     */
    public final QueryColumn SUPPLIER_ROLEID = new QueryColumn(this, "supplier_roleid");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 修改人主键
     */
    public final QueryColumn SY_MODIFYUSERID = new QueryColumn(this, "sy_modifyuserid");

    /**
     * 联系人
     */
    public final QueryColumn SUPPLIER_LIAISON = new QueryColumn(this, "supplier_liaison");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 修改人部门
     */
    public final QueryColumn SY_MODIFYORGNAME = new QueryColumn(this, "sy_modifyorgname");

    /**
     * 主键id
     */
    public final QueryColumn TB_IAM_SUPPLIER_ID = new QueryColumn(this, "tb_iam_supplier_id");

    /**
     * 角色名称
     */
    public final QueryColumn SUPPLIER_ROLENAME = new QueryColumn(this, "supplier_rolename");

    /**
     * 头像
     */
    public final QueryColumn SUPPLIER_TOUXIANG = new QueryColumn(this, "supplier_touxiang");

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
     * 用户状态
     */
    public final QueryColumn SUPPLIER_USER_STATUS = new QueryColumn(this, "supplier_user_status");

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
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_IAM_SUPPLIER_ID, SUPPLIER_PHONE, SUPPLIER_TOUXIANG, SUPPLIER_NAME, SUPPLIER_CODE, SUPPLIER_EMAIL, SUPPLIER_SEX, SUPPLIER_ROLENAME, SUPPLIER_ROLEID, SUPPLIER_REMARK, SUPPLIER_USER_STATUS, SUPPLIER_LIAISON, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_MODIFYORGID, SY_MODIFYORGNAME, SY_MODIFYUSERID, SY_MODIFYUSERNAME, SY_MODIFYTIME, SY_COMPANY_ID, SY_COMPANY_NAME, SY_GROUP_COMPANY_ID, SY_GROUP_COMPANY_NAME, SY_ORG_ID, SY_TENANT_ID, SY_TENANT_NAME};

    public TbIamSupplierTableDef() {
        super("", "tb_iam_supplier");
    }

    private TbIamSupplierTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbIamSupplierTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbIamSupplierTableDef("", "tb_iam_supplier", alias));
    }

}
