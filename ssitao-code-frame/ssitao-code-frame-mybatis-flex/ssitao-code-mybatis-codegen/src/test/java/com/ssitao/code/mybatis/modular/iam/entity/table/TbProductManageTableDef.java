package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 产品管理 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbProductManageTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 产品管理
     */
    public static final TbProductManageTableDef TB_PRODUCT_MANAGE = new TbProductManageTableDef();

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 编码
     */
    public final QueryColumn PRODUCT_CODE = new QueryColumn(this, "product_code");

    /**
     * 图标
     */
    public final QueryColumn PRODUCT_ICON = new QueryColumn(this, "product_icon");

    /**
     * 产品名称
     */
    public final QueryColumn PRODUCT_NAME = new QueryColumn(this, "product_name");

    /**
     * 类型
     */
    public final QueryColumn PRODUCT_TYPE = new QueryColumn(this, "product_type");

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
     * 开放业务授权_id
     */
    public final QueryColumn MANAGE_KFYWSQ_ID = new QueryColumn(this, "manage_kfywsq_id");

    /**
     * 版本
     */
    public final QueryColumn PRODUCT_VERSION = new QueryColumn(this, "product_version");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 修改人主键
     */
    public final QueryColumn SY_MODIFYUSERID = new QueryColumn(this, "sy_modifyuserid");

    /**
     * 产品描述
     */
    public final QueryColumn PRODUCT_DESCRIBE = new QueryColumn(this, "product_describe");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 修改人部门
     */
    public final QueryColumn SY_MODIFYORGNAME = new QueryColumn(this, "sy_modifyorgname");

    /**
     * 开放业务授权
     */
    public final QueryColumn MANAGE_KFYWSQ_CODE = new QueryColumn(this, "manage_kfywsq_code");

    /**
     * 开放业务授权_name
     */
    public final QueryColumn MANAGE_KFYWSQ_NAME = new QueryColumn(this, "manage_kfywsq_name");

    /**
     * 开发人员
     */
    public final QueryColumn PRODUCT_DEVELOPER = new QueryColumn(this, "product_developer");

    /**
     * 图标颜色
     */
    public final QueryColumn PRODUCT_ICON_COLOR = new QueryColumn(this, "product_icon_color");

    /**
     * 开放
     */
    public final QueryColumn PRODUCT_OPEN_STATE = new QueryColumn(this, "product_open_state");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 修改人
     */
    public final QueryColumn SY_MODIFYUSERNAME = new QueryColumn(this, "sy_modifyusername");

    /**
     * 主键id
     */
    public final QueryColumn TB_PRODUCT_MANAGE_ID = new QueryColumn(this, "tb_product_manage_id");

    /**
     * 开发人员id
     */
    public final QueryColumn PRODUCT_DEVELOPER_ID = new QueryColumn(this, "product_developer_id");

    /**
     * 启用
     */
    public final QueryColumn PRODUCT_ENABLE_STATE = new QueryColumn(this, "product_enable_state");

    /**
     * 服务目录
     */
    public final QueryColumn PRODUCT_SERVICE_LIST = new QueryColumn(this, "product_service_list");

    /**
     * 适用行业
     */
    public final QueryColumn PRODUCT_APPLY_INDUSTRY = new QueryColumn(this, "product_apply_industry");

    /**
     * 产品分类
     */
    public final QueryColumn PRODUCT_CLASSIFICATION = new QueryColumn(this, "product_classification");

    /**
     * 图标背景色
     */
    public final QueryColumn PRODUCT_ICON_BACKGROUND = new QueryColumn(this, "product_icon_background");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_PRODUCT_MANAGE_ID, PRODUCT_NAME, PRODUCT_CODE, PRODUCT_ICON, PRODUCT_ICON_BACKGROUND, PRODUCT_ICON_COLOR, PRODUCT_VERSION, PRODUCT_DESCRIBE, PRODUCT_DEVELOPER, PRODUCT_CLASSIFICATION, PRODUCT_APPLY_INDUSTRY, PRODUCT_SERVICE_LIST, PRODUCT_TYPE, PRODUCT_ENABLE_STATE, PRODUCT_OPEN_STATE, PRODUCT_DEVELOPER_ID, MANAGE_KFYWSQ_CODE, MANAGE_KFYWSQ_NAME, MANAGE_KFYWSQ_ID, SY_STATUS, SY_ORDERINDEX, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_MODIFYORGID, SY_MODIFYORGNAME, SY_MODIFYUSERID, SY_MODIFYUSERNAME, SY_MODIFYTIME};

    public TbProductManageTableDef() {
        super("", "tb_product_manage");
    }

    private TbProductManageTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbProductManageTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbProductManageTableDef("", "tb_product_manage", alias));
    }

}
