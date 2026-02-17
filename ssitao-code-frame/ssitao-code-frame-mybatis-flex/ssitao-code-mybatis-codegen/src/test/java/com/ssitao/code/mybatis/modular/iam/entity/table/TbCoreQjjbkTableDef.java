package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 全局脚本库 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbCoreQjjbkTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 全局脚本库
     */
    public static final TbCoreQjjbkTableDef TB_CORE_QJJBK = new TbCoreQjjbkTableDef();

    /**
     * 所属机构id
     */
    public final QueryColumn SY_ORG_ID = new QueryColumn(this, "sy_org_id");

    /**
     * 方法名
     */
    public final QueryColumn QJJBK_FFM = new QueryColumn(this, "qjjbk_ffm");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 脚本代码
     */
    public final QueryColumn QJJBK_JBDM = new QueryColumn(this, "qjjbk_jbdm");

    /**
     * 首屏加载
     */
    public final QueryColumn QJJBK_SPJZ = new QueryColumn(this, "qjjbk_spjz");

    /**
     * 租户id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * 脚本功能描述
     */
    public final QueryColumn QJJBK_JBGNMS = new QueryColumn(this, "qjjbk_jbgnms");

    /**
     * 启用
     */
    public final QueryColumn QJJBK_QY_CODE = new QueryColumn(this, "qjjbk_qy_code");

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
     * 脚本样式作用范围
     */
    public final QueryColumn QJJBK_ZYFW_CODE = new QueryColumn(this, "qjjbk_zyfw_code");

    /**
     * 脚本样式作用范围_name
     */
    public final QueryColumn QJJBK_ZYFW_NAME = new QueryColumn(this, "qjjbk_zyfw_name");

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
     * 主键id
     */
    public final QueryColumn TB_CORE_QJJBK_ID = new QueryColumn(this, "tb_core_qjjbk_id");

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
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_QJJBK_ID, QJJBK_JBDM, QJJBK_JBGNMS, QJJBK_QY_CODE, QJJBK_ZYFW_NAME, QJJBK_ZYFW_CODE, QJJBK_FFM, QJJBK_SPJZ, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_MODIFYORGID, SY_MODIFYORGNAME, SY_MODIFYUSERID, SY_MODIFYUSERNAME, SY_MODIFYTIME, SY_COMPANY_ID, SY_COMPANY_NAME, SY_GROUP_COMPANY_ID, SY_GROUP_COMPANY_NAME, SY_ORG_ID, SY_TENANT_ID, SY_TENANT_NAME};

    public TbCoreQjjbkTableDef() {
        super("", "tb_core_qjjbk");
    }

    private TbCoreQjjbkTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreQjjbkTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreQjjbkTableDef("", "tb_core_qjjbk", alias));
    }

}
