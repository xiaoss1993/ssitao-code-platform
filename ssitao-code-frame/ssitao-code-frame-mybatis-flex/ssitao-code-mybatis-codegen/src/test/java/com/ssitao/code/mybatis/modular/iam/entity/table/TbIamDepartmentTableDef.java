package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 部门管理 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbIamDepartmentTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 部门管理
     */
    public static final TbIamDepartmentTableDef TB_IAM_DEPARTMENT = new TbIamDepartmentTableDef();

    /**
     * 树形路径
     */
    public final QueryColumn SY_PATH = new QueryColumn(this, "sy_path");

    /**
     * 层次
     */
    public final QueryColumn SY_LAYER = new QueryColumn(this, "sy_layer");

    /**
     * 所属机构_id
     */
    public final QueryColumn SY_ORG_ID = new QueryColumn(this, "sy_org_id");

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
     * 所属公司_id
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
     * 父节点路径
     */
    public final QueryColumn SY_PARENTPATH = new QueryColumn(this, "sy_parentpath");

    /**
     * 租户_name
     */
    public final QueryColumn SY_TENANT_NAME = new QueryColumn(this, "sy_tenant_name");

    /**
     * 所属公司_name
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
     * 部门编码
     */
    public final QueryColumn DEPARTMENT_CODE = new QueryColumn(this, "department_code");

    /**
     * 图标
     */
    public final QueryColumn DEPARTMENT_ICON = new QueryColumn(this, "department_icon");

    /**
     * 部门名称
     */
    public final QueryColumn DEPARTMENT_NAME = new QueryColumn(this, "department_name");

    /**
     * 父级节点类型
     */
    public final QueryColumn PARENT_NODETYPE = new QueryColumn(this, "parent_nodetype");

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
     * 上级
     */
    public final QueryColumn DEPARTMENT_PARENT = new QueryColumn(this, "department_parent");

    /**
     * 备注信息
     */
    public final QueryColumn DEPARTMENT_REMARK = new QueryColumn(this, "department_remark");

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
     * 办公地点
     */
    public final QueryColumn DEPARTMENT_ADDRESS = new QueryColumn(this, "department_address");

    /**
     * 主管_id
     */
    public final QueryColumn DEPARTMENT_MAJOR_ID = new QueryColumn(this, "department_major_id");

    /**
     * 主键id
     */
    public final QueryColumn TB_IAM_DEPARTMENT_ID = new QueryColumn(this, "tb_iam_department_id");

    /**
     * 职能描述
     */
    public final QueryColumn DEPARTMENT_FUNC_DESC = new QueryColumn(this, "department_func_desc");

    /**
     * 上级部门
     */
    public final QueryColumn SUPERIOR_DEPARTMENT = new QueryColumn(this, "superior_department");

    /**
     * 所属集团公司_name
     */
    public final QueryColumn SY_GROUP_COMPANY_NAME = new QueryColumn(this, "sy_group_company_name");

    /**
     * 部门级别_code
     */
    public final QueryColumn DEPARTMENT_LEVEL_CODE = new QueryColumn(this, "department_level_code");

    /**
     * 部门级别_name
     */
    public final QueryColumn DEPARTMENT_LEVEL_NAME = new QueryColumn(this, "department_level_name");

    /**
     * 主管_name
     */
    public final QueryColumn DEPARTMENT_MAJOR_NAME = new QueryColumn(this, "department_major_name");

    /**
     * 部门电话
     */
    public final QueryColumn DEPARTMENT_TELEPHONE = new QueryColumn(this, "department_telephone");

    /**
     * 部门简称
     */
    public final QueryColumn DEPARTMENT_SIMPLE_NAME = new QueryColumn(this, "department_simple_name");

    /**
     * 监管部门_id
     */
    public final QueryColumn DEPARTMENT_MONITORDEPT_ID = new QueryColumn(this, "department_monitordept_id");

    /**
     * 部门人员排序
     */
    public final QueryColumn DEPARTMENT_USER_ORDERINDEX = new QueryColumn(this, "department_user_orderindex");

    /**
     * 监管部门_name
     */
    public final QueryColumn DEPARTMENT_MONITORDEPT_NAME = new QueryColumn(this, "department_monitordept_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_IAM_DEPARTMENT_ID, DEPARTMENT_SIMPLE_NAME, DEPARTMENT_MAJOR_ID, DEPARTMENT_MAJOR_NAME, DEPARTMENT_FUNC_DESC, DEPARTMENT_REMARK, DEPARTMENT_MONITORDEPT_ID, DEPARTMENT_MONITORDEPT_NAME, DEPARTMENT_ICON, DEPARTMENT_LEVEL_CODE, DEPARTMENT_LEVEL_NAME, DEPARTMENT_TELEPHONE, DEPARTMENT_ADDRESS, DEPARTMENT_NAME, DEPARTMENT_CODE, SUPERIOR_DEPARTMENT, DEPARTMENT_USER_ORDERINDEX, DEPARTMENT_PARENT, PARENT_NODETYPE, SY_COMPANY_ID, SY_COMPANY_NAME, SY_TENANT_ID, SY_TENANT_NAME, SY_PARENT, SY_NODETYPE, SY_PARENTPATH, SY_LAYER, SY_PATH, SY_DISABLED, SY_TREEORDERINDEX, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_GROUP_COMPANY_ID, SY_GROUP_COMPANY_NAME, SY_ORG_ID, SY_MODIFYORGID, SY_MODIFYORGNAME, SY_MODIFYUSERID, SY_MODIFYUSERNAME, SY_MODIFYTIME};

    public TbIamDepartmentTableDef() {
        super("", "tb_iam_department");
    }

    private TbIamDepartmentTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbIamDepartmentTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbIamDepartmentTableDef("", "tb_iam_department", alias));
    }

}
