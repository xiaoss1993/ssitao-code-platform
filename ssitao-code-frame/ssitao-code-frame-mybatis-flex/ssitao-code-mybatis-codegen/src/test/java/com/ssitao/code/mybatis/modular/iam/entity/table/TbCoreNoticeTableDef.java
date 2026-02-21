package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 首页新闻公告 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbCoreNoticeTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 首页新闻公告
     */
    public static final TbCoreNoticeTableDef TB_CORE_NOTICE = new TbCoreNoticeTableDef();

    /**
     * 所属机构id
     */
    public final QueryColumn SY_ORG_ID = new QueryColumn(this, "sy_org_id");

    /**
     * 附件
     */
    public final QueryColumn NOTICE_FJ = new QueryColumn(this, "notice_fj");

    /**
     * 封面
     */
    public final QueryColumn NOTICE_FM = new QueryColumn(this, "notice_fm");

    /**
     * 启用
     */
    public final QueryColumn NOTICE_QY = new QueryColumn(this, "notice_qy");

    /**
     * 摘要
     */
    public final QueryColumn NOTICE_ZY = new QueryColumn(this, "notice_zy");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 点赞量
     */
    public final QueryColumn NOTICE_DZL = new QueryColumn(this, "notice_dzl");

    /**
     * 阅读量
     */
    public final QueryColumn NOTICE_YDL = new QueryColumn(this, "notice_ydl");

    /**
     * 租户id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * pdf文件
     */
    public final QueryColumn NOTICE_PDFWJ = new QueryColumn(this, "notice_pdfwj");

    /**
     * 标题
     */
    public final QueryColumn NOTICE_TITLE = new QueryColumn(this, "notice_title");

    /**
     * 所属公司id
     */
    public final QueryColumn SY_COMPANY_ID = new QueryColumn(this, "sy_company_id");

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
     * 内容
     */
    public final QueryColumn NOTICE_CONTEXT = new QueryColumn(this, "notice_context");

    /**
     * 所属公司名称
     */
    public final QueryColumn SY_COMPANY_NAME = new QueryColumn(this, "sy_company_name");

    /**
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 展示方式
     */
    public final QueryColumn NOTICE_TPFS_CODE = new QueryColumn(this, "notice_tpfs_code");

    /**
     * 展示方式_name
     */
    public final QueryColumn NOTICE_TPFS_NAME = new QueryColumn(this, "notice_tpfs_name");

    /**
     * 公告栏目
     */
    public final QueryColumn NOTICE_TYPE_CODE = new QueryColumn(this, "notice_type_code");

    /**
     * 公告栏目_name
     */
    public final QueryColumn NOTICE_TYPE_NAME = new QueryColumn(this, "notice_type_name");

    /**
     * 新闻类型
     */
    public final QueryColumn NOTICE_XWLX_CODE = new QueryColumn(this, "notice_xwlx_code");

    /**
     * 新闻类型_name
     */
    public final QueryColumn NOTICE_XWLX_NAME = new QueryColumn(this, "notice_xwlx_name");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 主键id
     */
    public final QueryColumn TB_CORE_NOTICE_ID = new QueryColumn(this, "tb_core_notice_id");

    /**
     * 点赞的人
     */
    public final QueryColumn NOTICE_DZUSERIDS = new QueryColumn(this, "notice_dzuserids");

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
     * 看过的人员
     */
    public final QueryColumn NOTICE_LOOKUSERIDS = new QueryColumn(this, "notice_lookuserids");

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
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_NOTICE_ID, NOTICE_TYPE_NAME, NOTICE_TYPE_CODE, NOTICE_CONTEXT, NOTICE_TITLE, NOTICE_LOOKUSERIDS, NOTICE_QY, NOTICE_FM, NOTICE_DZUSERIDS, NOTICE_YDL, NOTICE_DZL, NOTICE_ZY, NOTICE_TPFS_NAME, NOTICE_TPFS_CODE, NOTICE_PDFWJ, NOTICE_XWLX_CODE, NOTICE_XWLX_NAME, NOTICE_FJ, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_COMPANY_ID, SY_COMPANY_NAME, SY_GROUP_COMPANY_ID, SY_GROUP_COMPANY_NAME, SY_ORG_ID, SY_TENANT_ID, SY_TENANT_NAME};

    public TbCoreNoticeTableDef() {
        super("", "tb_core_notice");
    }

    private TbCoreNoticeTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreNoticeTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreNoticeTableDef("", "tb_core_notice", alias));
    }

}
