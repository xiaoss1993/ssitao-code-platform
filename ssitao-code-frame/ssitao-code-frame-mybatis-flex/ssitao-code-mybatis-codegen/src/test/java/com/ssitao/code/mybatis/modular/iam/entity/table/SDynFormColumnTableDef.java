package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 动态单列 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class SDynFormColumnTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 动态单列
     */
    public static final SDynFormColumnTableDef SDYN_FORM_COLUMN = new SDynFormColumnTableDef();

    /**
     * ID
     */
    public final QueryColumn U_ID = new QueryColumn(this, "u_id");

    /**
     * 字段名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 别名
     */
    public final QueryColumn ALIAS = new QueryColumn(this, "alias");

    /**
     * 小数点位数
     */
    public final QueryColumn SCALE = new QueryColumn(this, "scale");

    /**
     * 表单ID
     */
    public final QueryColumn FORM_ID = new QueryColumn(this, "form_id");

    /**
     * 长度
     */
    public final QueryColumn LENGTH = new QueryColumn(this, "length");

    /**
     * 数据类型
     */
    public final QueryColumn DATA_TYPE = new QueryColumn(this, "data_type");

    /**
     * 备注
     */
    public final QueryColumn DESCRIBE = new QueryColumn(this, "describe");

    /**
     * java类型
     */
    public final QueryColumn JAVA_TYPE = new QueryColumn(this, "java_type");

    /**
     * jdbc类型
     */
    public final QueryColumn JDBC_TYPE = new QueryColumn(this, "jdbc_type");

    /**
     * 精度
     */
    public final QueryColumn PRECISION = new QueryColumn(this, "precision");

    /**
     * 排序序号
     */
    public final QueryColumn SORT_INDEX = new QueryColumn(this, "sort_index");

    /**
     * 验证器配置
     */
    public final QueryColumn VALIDATOR = new QueryColumn(this, "validator");

    /**
     * 数据库列
     */
    public final QueryColumn COLUMN_NAME = new QueryColumn(this, "column_name");

    /**
     * 字典配置
     */
    public final QueryColumn DICT_CONFIG = new QueryColumn(this, "dict_config");

    /**
     * 其他配置
     */
    public final QueryColumn PROPERTIES = new QueryColumn(this, "properties");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{U_ID, FORM_ID, NAME, COLUMN_NAME, DESCRIBE, ALIAS, JAVA_TYPE, JDBC_TYPE, DATA_TYPE, LENGTH, PRECISION, SCALE, PROPERTIES, DICT_CONFIG, SORT_INDEX, VALIDATOR};

    public SDynFormColumnTableDef() {
        super("", "s_dyn_form_column");
    }

    private SDynFormColumnTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SDynFormColumnTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SDynFormColumnTableDef("", "s_dyn_form_column", alias));
    }

}
