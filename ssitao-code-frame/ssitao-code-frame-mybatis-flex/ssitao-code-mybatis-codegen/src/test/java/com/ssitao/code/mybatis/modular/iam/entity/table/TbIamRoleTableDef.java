package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 角色管理 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbIamRoleTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 角色管理
     */
    public static final TbIamRoleTableDef TB_IAM_ROLE = new TbIamRoleTableDef();

    /**
     * 主键
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 树形路径
     */
    public final QueryColumn SY_PATH = new QueryColumn(this, "sy_path");

    /**
     * 层次
     */
    public final QueryColumn SY_LAYER = new QueryColumn(this, "sy_layer");

    /**
     * 角色编码
     */
    public final QueryColumn ROLE_CODE = new QueryColumn(this, "role_code");

    /**
     * 角色名称
     */
    public final QueryColumn ROLE_NAME = new QueryColumn(this, "role_name");

    /**
     * 父节点id
     */
    public final QueryColumn SY_PARENT = new QueryColumn(this, "sy_parent");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 基础角色_id
     */
    public final QueryColumn ROLE_BASE_ID = new QueryColumn(this, "role_base_id");

    /**
     * 备注信息
     */
    public final QueryColumn ROLE_REMARK = new QueryColumn(this, "role_remark");

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
     * 开发
     */
    public final QueryColumn ROLE_DEVELOP = new QueryColumn(this, "role_develop");

    /**
     * 节点图标
     */
    public final QueryColumn ROLE_ICONCLS = new QueryColumn(this, "role_iconcls");

    /**
     * saas产品
     */
    public final QueryColumn ROLE_SAAS_PID = new QueryColumn(this, "role_saas_pid");

    /**
     * 所属产品_id
     */
    public final QueryColumn SY_PRODUCT_ID = new QueryColumn(this, "sy_product_id");

    /**
     * 主键id
     */
    public final QueryColumn TB_IAM_ROLE_ID = new QueryColumn(this, "tb_iam_role_id");

    /**
     * 基础角色_name
     */
    public final QueryColumn ROLE_BASE_NAME = new QueryColumn(this, "role_base_name");

    /**
     * 角色类型_code
     */
    public final QueryColumn ROLE_TYPE_CODE = new QueryColumn(this, "role_type_code");

    /**
     * 角色类型_name
     */
    public final QueryColumn ROLE_TYPE_NAME = new QueryColumn(this, "role_type_name");

    /**
     * 登记时间
     */
    public final QueryColumn SY_CREATETIME = new QueryColumn(this, "sy_createtime");

    /**
     * 登记人编码
     */
    public final QueryColumn SY_CREATEUSER = new QueryColumn(this, "sy_createuser");

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
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 所属产品_name
     */
    public final QueryColumn SY_PRODUCT_NAME = new QueryColumn(this, "sy_product_name");

    /**
     * 所属权限组_id
     */
    public final QueryColumn ROLE_PERMGROUP_ID = new QueryColumn(this, "role_permgroup_id");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 树形排序字段
     */
    public final QueryColumn SY_TREEORDERINDEX = new QueryColumn(this, "sy_treeorderindex");

    /**
     * 所属权限组_name
     */
    public final QueryColumn ROLE_PERMGROUP_NAME = new QueryColumn(this, "role_permgroup_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_IAM_ROLE_ID, ROLE_NAME, ROLE_CODE, ROLE_BASE_ID, ROLE_BASE_NAME, ROLE_REMARK, ROLE_TYPE_CODE, ROLE_TYPE_NAME, ROLE_ICONCLS, ROLE_DEVELOP, ROLE_PERMGROUP_ID, ROLE_PERMGROUP_NAME, ROLE_SAAS_PID, SY_PARENT, SY_NODETYPE, SY_PARENTPATH, SY_LAYER, SY_PATH, SY_DISABLED, SY_TREEORDERINDEX, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSER, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_PRODUCT_ID, SY_PRODUCT_NAME, SY_TENANT_ID, SY_TENANT_NAME, ID};

    public TbIamRoleTableDef() {
        super("", "tb_iam_role");
    }

    private TbIamRoleTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbIamRoleTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbIamRoleTableDef("", "tb_iam_role", alias));
    }

}
