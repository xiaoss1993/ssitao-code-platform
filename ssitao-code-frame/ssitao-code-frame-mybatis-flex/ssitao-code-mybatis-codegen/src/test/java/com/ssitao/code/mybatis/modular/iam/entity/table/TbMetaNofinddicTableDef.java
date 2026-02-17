package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 丢失字典信息 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbMetaNofinddicTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 丢失字典信息
     */
    public static final TbMetaNofinddicTableDef TB_META_NOFINDDIC = new TbMetaNofinddicTableDef();

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
     * 所属公司id
     */
    public final QueryColumn SY_COMPANY_ID = new QueryColumn(this, "sy_company_id");

    /**
     * 所属服务_id
     */
    public final QueryColumn SY_PRODUCT_ID = new QueryColumn(this, "sy_product_id");

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
     * 所属模块
     */
    public final QueryColumn NOFINDDIC_SSMK = new QueryColumn(this, "nofinddic_ssmk");

    /**
     * 所属公司名称
     */
    public final QueryColumn SY_COMPANY_NAME = new QueryColumn(this, "sy_company_name");

    /**
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 所属服务
     */
    public final QueryColumn SY_PRODUCT_CODE = new QueryColumn(this, "sy_product_code");

    /**
     * 所属服务_name
     */
    public final QueryColumn SY_PRODUCT_NAME = new QueryColumn(this, "sy_product_name");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 功能编码
     */
    public final QueryColumn FUNCINFO_FUNCCODE = new QueryColumn(this, "funcinfo_funccode");

    /**
     * 功能名称
     */
    public final QueryColumn FUNCINFO_FUNCNAME = new QueryColumn(this, "funcinfo_funcname");

    /**
     * 字典编码
     */
    public final QueryColumn NOFINDDIC_DIC_CODE = new QueryColumn(this, "nofinddic_dic_code");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 所属集团公司id
     */
    public final QueryColumn SY_GROUP_COMPANY_ID = new QueryColumn(this, "sy_group_company_id");

    /**
     * 字段编码
     */
    public final QueryColumn RESOURCEFIELD_CODE = new QueryColumn(this, "resourcefield_code");

    /**
     * 字段名称
     */
    public final QueryColumn RESOURCEFIELD_NAME = new QueryColumn(this, "resourcefield_name");

    /**
     * 主键id
     */
    public final QueryColumn TB_META_NOFINDDIC_ID = new QueryColumn(this, "tb_meta_nofinddic_id");

    /**
     * 所属集团公司名称
     */
    public final QueryColumn SY_GROUP_COMPANY_NAME = new QueryColumn(this, "sy_group_company_name");

    /**
     * 字段配置信息
     */
    public final QueryColumn RESOURCEFIELD_CONFIGINFO = new QueryColumn(this, "resourcefield_configinfo");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_META_NOFINDDIC_ID, NOFINDDIC_DIC_CODE, NOFINDDIC_SSMK, FUNCINFO_FUNCNAME, FUNCINFO_FUNCCODE, RESOURCEFIELD_NAME, RESOURCEFIELD_CODE, RESOURCEFIELD_CONFIGINFO, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_COMPANY_ID, SY_COMPANY_NAME, SY_GROUP_COMPANY_ID, SY_GROUP_COMPANY_NAME, SY_ORG_ID, SY_TENANT_ID, SY_TENANT_NAME, SY_PRODUCT_NAME, SY_PRODUCT_CODE, SY_PRODUCT_ID};

    public TbMetaNofinddicTableDef() {
        super("", "tb_meta_nofinddic");
    }

    private TbMetaNofinddicTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbMetaNofinddicTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbMetaNofinddicTableDef("", "tb_meta_nofinddic", alias));
    }

}
