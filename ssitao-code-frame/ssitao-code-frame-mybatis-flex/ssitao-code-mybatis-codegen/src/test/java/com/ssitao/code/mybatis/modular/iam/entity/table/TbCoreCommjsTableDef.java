package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 全局脚本库 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbCoreCommjsTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 全局脚本库
     */
    public static final TbCoreCommjsTableDef TB_CORE_COMMJS = new TbCoreCommjsTableDef();

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 方法体
     */
    public final QueryColumn COMMJS_FFT = new QueryColumn(this, "commjs_fft");

    /**
     * 返回值
     */
    public final QueryColumn COMMJS_FHZ = new QueryColumn(this, "commjs_fhz");

    /**
     * 脚本
     */
    public final QueryColumn COMMJS_CODE = new QueryColumn(this, "commjs_code");

    /**
     * 功能描述
     */
    public final QueryColumn COMMJS_GNMS = new QueryColumn(this, "commjs_gnms");

    /**
     * 名称名称
     */
    public final QueryColumn SY_NAMR_MAME = new QueryColumn(this, "sy_namr_mame");

    /**
     * 初始化执行
     */
    public final QueryColumn COMMJS_CSHZX = new QueryColumn(this, "commjs_cshzx");

    /**
     * 所属产品id
     */
    public final QueryColumn SY_PRODUCT_ID = new QueryColumn(this, "sy_product_id");

    /**
     * 启用
     */
    public final QueryColumn COMMJS_QY_CODE = new QueryColumn(this, "commjs_qy_code");

    /**
     * 启用_name
     */
    public final QueryColumn COMMJS_QY_NAME = new QueryColumn(this, "commjs_qy_name");

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
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 修改人部门主键
     */
    public final QueryColumn SY_MODIFYORGID = new QueryColumn(this, "sy_modifyorgid");

    /**
     * 所属产品code
     */
    public final QueryColumn SY_PRODUCT_CODE = new QueryColumn(this, "sy_product_code");

    /**
     * 所属产品名称
     */
    public final QueryColumn SY_PRODUCT_NAME = new QueryColumn(this, "sy_product_name");

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
    public final QueryColumn TB_CORE_COMMJS_ID = new QueryColumn(this, "tb_core_commjs_id");

    /**
     * 个人脚本库分类
     */
    public final QueryColumn COMMJS_TYPES_CODE = new QueryColumn(this, "commjs_types_code");

    /**
     * 个人脚本库分类_name
     */
    public final QueryColumn COMMJS_TYPES_NAME = new QueryColumn(this, "commjs_types_name");

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
     * 修改人
     */
    public final QueryColumn SY_MODIFYUSERNAME = new QueryColumn(this, "sy_modifyusername");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_CORE_COMMJS_ID, COMMJS_CODE, COMMJS_GNMS, COMMJS_FFT, COMMJS_QY_NAME, COMMJS_QY_CODE, COMMJS_FHZ, COMMJS_TYPES_NAME, COMMJS_TYPES_CODE, COMMJS_CSHZX, SY_PRODUCT_ID, SY_PRODUCT_NAME, SY_PRODUCT_CODE, SY_NAMR_MAME, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_MODIFYORGID, SY_MODIFYORGNAME, SY_MODIFYUSERID, SY_MODIFYUSERNAME, SY_MODIFYTIME};

    public TbCoreCommjsTableDef() {
        super("", "tb_core_commjs");
    }

    private TbCoreCommjsTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbCoreCommjsTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbCoreCommjsTableDef("", "tb_core_commjs", alias));
    }

}
