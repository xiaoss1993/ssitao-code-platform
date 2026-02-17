package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 资源_级联关系 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbCoreViewcascadeTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 资源_级联关系
     */
    public static final TbCoreViewcascadeTableDef TB_CORE_VIEWCASCADE = new TbCoreViewcascadeTableDef();

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 登记时间
     */
    public final QueryColumn SY_CREATETIME = new QueryColumn(this, "sy_createtime");

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
     * 源表编码
     */
    public final QueryColumn VIEWCASCADE_YBBM = new QueryColumn(this, "viewcascade_ybbm");

    /**
     * 源表标识
     */
    public final QueryColumn VIEWCASCADE_YBBS = new QueryColumn(this, "viewcascade_ybbs");

    /**
     * 源表id
     */
    public final QueryColumn VIEWCASCADE_YBID = new QueryColumn(this, "viewcascade_ybid");

    /**
     * 源表字段
     */
    public final QueryColumn VIEWCASCADE_YBZD = new QueryColumn(this, "viewcascade_ybzd");

    /**
     * 子表标识
     */
    public final QueryColumn VIEWCASCADE_ZBBZ = new QueryColumn(this, "viewcascade_zbbz");

    /**
     * 子表id
     */
    public final QueryColumn VIEWCASCADE_ZBID = new QueryColumn(this, "viewcascade_zbid");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 目标表编码
     */
    public final QueryColumn VIEWCASCADE_MBBBM = new QueryColumn(this, "viewcascade_mbbbm");

    /**
     * 目标表字段
     */
    public final QueryColumn VIEWCASCADE_MBBZD = new QueryColumn(this, "viewcascade_mbbzd");

    /**
     * 是否显示线
     */
    public final QueryColumn VIEWCASCADE_SFXSX = new QueryColumn(this, "viewcascade_sfxsx");

    /**
     * 源表表名称
     */
    public final QueryColumn VIEWCASCADE_YBBMC = new QueryColumn(this, "viewcascade_ybbmc");

    /**
     * 子表表名称
     */
    public final QueryColumn VIEWCASCADE_ZBBMC = new QueryColumn(this, "viewcascade_zbbmc");

    /**
     * 主键id
     */
    public final QueryColumn TB_CORE_VIEWCASCADE_ID = new QueryColumn(this, "tb_core_viewcascade_id");

    /**
     * 关联关系_code
     */
    public final QueryColumn VIEWCASCADE_GLGX_CODE = new QueryColumn(this, "viewcascade_glgx_code");

    /**
     * 关联关系_name
     */
    public final QueryColumn VIEWCASCADE_GLGX_NAME = new QueryColumn(this, "viewcascade_glgx_name");

    /**
     * 资源表主键_id
     */
    public final QueryColumn TB_CORE_RESOURCETABLE_ID = new QueryColumn(this, "tb_core_resourcetable_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_VIEWCASCADE_ID, TB_CORE_RESOURCETABLE_ID, VIEWCASCADE_YBBM, VIEWCASCADE_YBZD, VIEWCASCADE_MBBBM, VIEWCASCADE_GLGX_CODE, VIEWCASCADE_GLGX_NAME, VIEWCASCADE_MBBZD, VIEWCASCADE_YBBS, VIEWCASCADE_YBID, VIEWCASCADE_YBBMC, VIEWCASCADE_ZBBMC, VIEWCASCADE_ZBBZ, VIEWCASCADE_ZBID, VIEWCASCADE_SFXSX, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX};

    public TbCoreViewcascadeTableDef() {
        super("", "tb_core_viewcascade");
    }

    private TbCoreViewcascadeTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreViewcascadeTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreViewcascadeTableDef("", "tb_core_viewcascade", alias));
    }

}
