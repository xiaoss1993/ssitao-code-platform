package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * wps预览缓存 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbWpsViewfielsTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * wps预览缓存
     */
    public static final TbWpsViewfielsTableDef TB_WPS_VIEWFIELS = new TbWpsViewfielsTableDef();

    /**
     * 流程定义id
     */
    public final QueryColumn SY_PDID = new QueryColumn(this, "sy_pdid");

    /**
     * 流程实例id
     */
    public final QueryColumn SY_PIID = new QueryColumn(this, "sy_piid");

    /**
     * 集团公司id
     */
    public final QueryColumn SY_JTGSID = new QueryColumn(this, "sy_jtgsid");

    /**
     * 集团公司名称
     */
    public final QueryColumn SY_JTGSMC = new QueryColumn(this, "sy_jtgsmc");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 审核标记
     */
    public final QueryColumn SY_AUDFLAG = new QueryColumn(this, "sy_audflag");

    /**
     * 登记部门编码
     */
    public final QueryColumn SY_CREATEORG = new QueryColumn(this, "sy_createorg");

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
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

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
     * 主键id
     */
    public final QueryColumn TB_WPS_VIEWFIELS_ID = new QueryColumn(this, "tb_wps_viewfiels_id");

    /**
     * 文件key
     */
    public final QueryColumn VIEWFIELS_FILEKEY = new QueryColumn(this, "viewfiels_filekey");

    /**
     * wps缓存key
     */
    public final QueryColumn VIEWFIELS_WPS_FILE_KEY = new QueryColumn(this, "viewfiels_wps_file_key");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{VIEWFIELS_WPS_FILE_KEY, VIEWFIELS_FILEKEY, TB_WPS_VIEWFIELS_ID, SY_AUDFLAG, SY_CREATEORGID, SY_CREATEORG, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSER, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_PIID, SY_PDID, SY_JTGSMC, SY_JTGSID};

    public TbWpsViewfielsTableDef() {
        super("", "tb_wps_viewfiels");
    }

    private TbWpsViewfielsTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbWpsViewfielsTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbWpsViewfielsTableDef("", "tb_wps_viewfiels", alias));
    }

}
