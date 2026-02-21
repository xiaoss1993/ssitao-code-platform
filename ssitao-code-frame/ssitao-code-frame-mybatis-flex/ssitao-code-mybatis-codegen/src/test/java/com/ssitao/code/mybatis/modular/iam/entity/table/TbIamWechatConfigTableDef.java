package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 企微设置管理 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbIamWechatConfigTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 企微设置管理
     */
    public static final TbIamWechatConfigTableDef TB_IAM_WECHAT_CONFIG = new TbIamWechatConfigTableDef();

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
     * 公司id
     */
    public final QueryColumn CONFIG_CORPID = new QueryColumn(this, "config_corpid");

    /**
     * 网站域名
     */
    public final QueryColumn CONFIG_DOMAIN = new QueryColumn(this, "config_domain");

    /**
     * 描述信息
     */
    public final QueryColumn CONFIG_REMARK = new QueryColumn(this, "config_remark");

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
     * 应用id
     */
    public final QueryColumn CONFIG_AGENTID = new QueryColumn(this, "config_agentid");

    /**
     * 所属公司名称
     */
    public final QueryColumn SY_COMPANY_NAME = new QueryColumn(this, "sy_company_name");

    /**
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 帮助附件
     */
    public final QueryColumn CONFIG_HELPFILES = new QueryColumn(this, "config_helpfiles");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 通讯录secret
     */
    public final QueryColumn CONFIG_CORPSECRET = new QueryColumn(this, "config_corpsecret");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 所属集团公司id
     */
    public final QueryColumn SY_GROUP_COMPANY_ID = new QueryColumn(this, "sy_group_company_id");

    /**
     * 应用秘钥
     */
    public final QueryColumn CONFIG_AGENTSECRET = new QueryColumn(this, "config_agentsecret");

    /**
     * 所属集团公司名称
     */
    public final QueryColumn SY_GROUP_COMPANY_NAME = new QueryColumn(this, "sy_group_company_name");

    /**
     * 主键id
     */
    public final QueryColumn TB_IAM_WECHAT_CONFIG_ID = new QueryColumn(this, "tb_iam_wechat_config_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_IAM_WECHAT_CONFIG_ID, CONFIG_CORPID, CONFIG_CORPSECRET, CONFIG_AGENTID, CONFIG_AGENTSECRET, CONFIG_HELPFILES, CONFIG_REMARK, CONFIG_DOMAIN, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_COMPANY_ID, SY_COMPANY_NAME, SY_GROUP_COMPANY_ID, SY_GROUP_COMPANY_NAME, SY_ORG_ID, SY_TENANT_ID, SY_TENANT_NAME};

    public TbIamWechatConfigTableDef() {
        super("", "tb_iam_wechat_config");
    }

    private TbIamWechatConfigTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbIamWechatConfigTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbIamWechatConfigTableDef("", "tb_iam_wechat_config", alias));
    }

}
