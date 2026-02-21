package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 三方组织管理 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbIamCpdeptTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 三方组织管理
     */
    public static final TbIamCpdeptTableDef TB_IAM_CPDEPT = new TbIamCpdeptTableDef();

    /**
     * 树形路径
     */
    public final QueryColumn SY_PATH = new QueryColumn(this, "sy_path");

    /**
     * 层次
     */
    public final QueryColumn SY_LAYER = new QueryColumn(this, "sy_layer");

    /**
     * 所属机构id
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
     * 批次号
     */
    public final QueryColumn CPDEPT_PCH = new QueryColumn(this, "cpdept_pch");

    /**
     * 节点编码
     */
    public final QueryColumn CPDEPT_CODE = new QueryColumn(this, "cpdept_code");

    /**
     * 部门名称
     */
    public final QueryColumn CPDEPT_NAME = new QueryColumn(this, "cpdept_name");

    /**
     * 节点名称
     */
    public final QueryColumn CPDEPT_TEXT = new QueryColumn(this, "cpdept_text");

    /**
     * 类型
     */
    public final QueryColumn CPDEPT_TYPE = new QueryColumn(this, "cpdept_type");

    /**
     * 第三方排序字段
     */
    public final QueryColumn CPDEPT_WXPX = new QueryColumn(this, "cpdept_wxpx");

    /**
     * 是否启用
     */
    public final QueryColumn SY_DISABLED = new QueryColumn(this, "sy_disabled");

    /**
     * 节点类型
     */
    public final QueryColumn SY_NODETYPE = new QueryColumn(this, "sy_nodetype");

    /**
     * 租户id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * 所属公司id
     */
    public final QueryColumn SY_COMPANY_ID = new QueryColumn(this, "sy_company_id");

    /**
     * 第三方部门id
     */
    public final QueryColumn CPDEPT_DEPTID = new QueryColumn(this, "cpdept_deptid");

    /**
     * 挂接部门_id
     */
    public final QueryColumn CPDEPT_GJBM_ID = new QueryColumn(this, "cpdept_gjbm_id");

    /**
     * 最近同步时间
     */
    public final QueryColumn CPDEPT_ZJTBSJ = new QueryColumn(this, "cpdept_zjtbsj");

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
     * 所属公司名称
     */
    public final QueryColumn SY_COMPANY_NAME = new QueryColumn(this, "sy_company_name");

    /**
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 主键id
     */
    public final QueryColumn TB_IAM_CPDEPT_ID = new QueryColumn(this, "tb_iam_cpdept_id");

    /**
     * 挂接部门_name
     */
    public final QueryColumn CPDEPT_GJBM_NAME = new QueryColumn(this, "cpdept_gjbm_name");

    /**
     * 部门全路径
     */
    public final QueryColumn CPDEPT_NAMEPATH = new QueryColumn(this, "cpdept_namepath");

    /**
     * 第三方父部门id
     */
    public final QueryColumn CPDEPT_PARENTID = new QueryColumn(this, "cpdept_parentid");

    /**
     * 是否挂接
     */
    public final QueryColumn CPDEPT_SFGJ_CODE = new QueryColumn(this, "cpdept_sfgj_code");

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
     * 树形排序字段
     */
    public final QueryColumn SY_TREEORDERINDEX = new QueryColumn(this, "sy_treeorderindex");

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
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_IAM_CPDEPT_ID, CPDEPT_TEXT, CPDEPT_CODE, CPDEPT_NAME, CPDEPT_GJBM_NAME, CPDEPT_GJBM_ID, CPDEPT_ZJTBSJ, CPDEPT_SFGJ_CODE, CPDEPT_WXPX, CPDEPT_PARENTID, CPDEPT_DEPTID, CPDEPT_TYPE, CPDEPT_PCH, CPDEPT_NAMEPATH, SY_PARENT, SY_NODETYPE, SY_LAYER, SY_PATH, SY_DISABLED, SY_TREEORDERINDEX, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_COMPANY_ID, SY_COMPANY_NAME, SY_GROUP_COMPANY_ID, SY_GROUP_COMPANY_NAME, SY_ORG_ID, SY_TENANT_ID, SY_TENANT_NAME};

    public TbIamCpdeptTableDef() {
        super("", "tb_iam_cpdept");
    }

    private TbIamCpdeptTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbIamCpdeptTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbIamCpdeptTableDef("", "tb_iam_cpdept", alias));
    }

}
