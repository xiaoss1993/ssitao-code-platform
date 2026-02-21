package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 公司管理 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbIamCompanyTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 公司管理
     */
    public static final TbIamCompanyTableDef TB_IAM_COMPANY = new TbIamCompanyTableDef();

    /**
     * 树形路径
     */
    public final QueryColumn SY_PATH = new QueryColumn(this, "sy_path");

    /**
     * 层次
     */
    public final QueryColumn SY_LAYER = new QueryColumn(this, "sy_layer");

    /**
     * 父节点id
     */
    public final QueryColumn SY_PARENT = new QueryColumn(this, "sy_parent");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 是否启用
     */
    public final QueryColumn SY_DISABLED = new QueryColumn(this, "sy_disabled");

    /**
     * 节点类型
     */
    public final QueryColumn SY_NODETYPE = new QueryColumn(this, "sy_nodetype");

    /**
     * 租户_id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * 成立日期
     */
    public final QueryColumn COMPANY_CLRQ = new QueryColumn(this, "company_clrq");

    /**
     * 公司编码
     */
    public final QueryColumn COMPANY_CODE = new QueryColumn(this, "company_code");

    /**
     * 图标
     */
    public final QueryColumn COMPANY_ICON = new QueryColumn(this, "company_icon");

    /**
     * 经营范围
     */
    public final QueryColumn COMPANY_JYFW = new QueryColumn(this, "company_jyfw");

    /**
     * 公司名称
     */
    public final QueryColumn COMPANY_NAME = new QueryColumn(this, "company_name");

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
     * 父节点路径
     */
    public final QueryColumn SY_PARENTPATH = new QueryColumn(this, "sy_parentpath");

    /**
     * 租户_name
     */
    public final QueryColumn SY_TENANT_NAME = new QueryColumn(this, "sy_tenant_name");

    /**
     * 备注信息
     */
    public final QueryColumn COMPANY_REMARK = new QueryColumn(this, "company_remark");

    /**
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 修改人部门主键
     */
    public final QueryColumn SY_MODIFYORGID = new QueryColumn(this, "sy_modifyorgid");

    /**
     * 办公地址
     */
    public final QueryColumn COMPANY_ADDRESS = new QueryColumn(this, "company_address");

    /**
     * 主管_id
     */
    public final QueryColumn COMPANY_MAJOR_ID = new QueryColumn(this, "company_major_id");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 修改人主键
     */
    public final QueryColumn SY_MODIFYUSERID = new QueryColumn(this, "sy_modifyuserid");

    /**
     * 主键id
     */
    public final QueryColumn TB_IAM_COMPANY_ID = new QueryColumn(this, "tb_iam_company_id");

    /**
     * 职能描述
     */
    public final QueryColumn COMPANY_FUNC_DESC = new QueryColumn(this, "company_func_desc");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 修改人部门
     */
    public final QueryColumn SY_MODIFYORGNAME = new QueryColumn(this, "sy_modifyorgname");

    /**
     * 公司级别_code
     */
    public final QueryColumn COMPANY_LEVEL_CODE = new QueryColumn(this, "company_level_code");

    /**
     * 公司级别_name
     */
    public final QueryColumn COMPANY_LEVEL_NAME = new QueryColumn(this, "company_level_name");

    /**
     * 主管_name
     */
    public final QueryColumn COMPANY_MAJOR_NAME = new QueryColumn(this, "company_major_name");

    /**
     * 管理员_id
     */
    public final QueryColumn COMPANY_MANAGER_ID = new QueryColumn(this, "company_manager_id");

    /**
     * 公司电话
     */
    public final QueryColumn COMPANY_TELEPHONE = new QueryColumn(this, "company_telephone");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 所属集团公司_id
     */
    public final QueryColumn SY_GROUP_COMPANY_ID = new QueryColumn(this, "sy_group_company_id");

    /**
     * 修改人
     */
    public final QueryColumn SY_MODIFYUSERNAME = new QueryColumn(this, "sy_modifyusername");

    /**
     * 树形排序字段
     */
    public final QueryColumn SY_TREEORDERINDEX = new QueryColumn(this, "sy_treeorderindex");

    /**
     * 简称
     */
    public final QueryColumn COMPANY_SIMPLENAME = new QueryColumn(this, "company_simplename");

    /**
     * 监管公司_id
     */
    public final QueryColumn COMPANY_JGCOMPANY_ID = new QueryColumn(this, "company_jgcompany_id");

    /**
     * 管理员名称
     */
    public final QueryColumn COMPANY_MANAGER_NAME = new QueryColumn(this, "company_manager_name");

    /**
     * 所属集团公司_name
     */
    public final QueryColumn SY_GROUP_COMPANY_NAME = new QueryColumn(this, "sy_group_company_name");

    /**
     * 监管公司_name
     */
    public final QueryColumn COMPANY_JGCOMPANY_NAME = new QueryColumn(this, "company_jgcompany_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_IAM_COMPANY_ID, COMPANY_REMARK, COMPANY_NAME, COMPANY_CODE, COMPANY_MANAGER_ID, COMPANY_MAJOR_ID, COMPANY_MAJOR_NAME, COMPANY_LEVEL_CODE, COMPANY_LEVEL_NAME, COMPANY_ADDRESS, COMPANY_MANAGER_NAME, COMPANY_ICON, COMPANY_JGCOMPANY_ID, COMPANY_SIMPLENAME, COMPANY_TELEPHONE, COMPANY_JGCOMPANY_NAME, COMPANY_FUNC_DESC, COMPANY_JYFW, COMPANY_CLRQ, SY_GROUP_COMPANY_ID, SY_GROUP_COMPANY_NAME, SY_MODIFYORGID, SY_MODIFYORGNAME, SY_MODIFYUSERID, SY_MODIFYUSERNAME, SY_MODIFYTIME, SY_TENANT_ID, SY_TENANT_NAME, SY_PARENT, SY_NODETYPE, SY_PARENTPATH, SY_LAYER, SY_PATH, SY_DISABLED, SY_TREEORDERINDEX, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX};

    public TbIamCompanyTableDef() {
        super("", "tb_iam_company");
    }

    private TbIamCompanyTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbIamCompanyTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbIamCompanyTableDef("", "tb_iam_company", alias));
    }

}
