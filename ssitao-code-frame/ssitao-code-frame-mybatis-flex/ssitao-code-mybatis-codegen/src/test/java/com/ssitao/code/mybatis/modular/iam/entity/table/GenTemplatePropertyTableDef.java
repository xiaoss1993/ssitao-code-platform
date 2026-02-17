package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 模板属性配置 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class GenTemplatePropertyTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 模板属性配置
     */
    public static final GenTemplatePropertyTableDef GEN_TEMPLATE_PROPERTY = new GenTemplatePropertyTableDef();

    /**
     * ID
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 标题
     */
    public final QueryColumn TITLE = new QueryColumn(this, "title");

    /**
     * 逻辑删除
     */
    public final QueryColumn DELETED = new QueryColumn(this, "deleted");

    /**
     * 属性键
     */
    public final QueryColumn PROP_KEY = new QueryColumn(this, "prop_key");

    /**
     * 备注信息
     */
    public final QueryColumn REMARKS = new QueryColumn(this, "remarks");

    /**
     * 模板组标识
     */
    public final QueryColumn GROUP_KEY = new QueryColumn(this, "group_key");

    /**
     * 属性类型：1=配置属性，2=计算属性
     */
    public final QueryColumn PROP_TYPE = new QueryColumn(this, "prop_type");

    /**
     * 必填，1：是，0：否
     */
    public final QueryColumn REQUIRED = new QueryColumn(this, "required");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 模板引擎类型：0=普通文本，1=Velocity，2=Freemarker
     */
    public final QueryColumn ENGINE_TYPE = new QueryColumn(this, "engine_type");

    /**
     * 表达式内容，仅计算属性使用
     */
    public final QueryColumn EXPRESSION = new QueryColumn(this, "expression");

    /**
     * 排序值，值越小越靠前
     */
    public final QueryColumn ORDER_VALUE = new QueryColumn(this, "order_value");

    /**
     * 修改时间
     */
    public final QueryColumn UPDATE_TIME = new QueryColumn(this, "update_time");

    /**
     * 默认值
     */
    public final QueryColumn DEFAULT_VALUE = new QueryColumn(this, "default_value");

    /**
     * 组件类型，前端显示的组件类型，input、select、radio
     */
    public final QueryColumn COMPONENT_TYPE = new QueryColumn(this, "component_type");

    /**
     * 组件选项，多选组件的选项配置
     */
    public final QueryColumn COMPONENT_OPTIONS = new QueryColumn(this, "component_options");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, GROUP_KEY, TITLE, PROP_KEY, PROP_TYPE, EXPRESSION, ENGINE_TYPE, DEFAULT_VALUE, COMPONENT_TYPE, COMPONENT_OPTIONS, REQUIRED, ORDER_VALUE, REMARKS, DELETED, CREATE_TIME, UPDATE_TIME};

    public GenTemplatePropertyTableDef() {
        super("", "gen_template_property");
    }

    private GenTemplatePropertyTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public GenTemplatePropertyTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new GenTemplatePropertyTableDef("", "gen_template_property", alias));
    }

}
