package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 页面字段 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbMetaPageFieldTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 页面字段
     */
    public static final TbMetaPageFieldTableDef TB_META_PAGE_FIELD = new TbMetaPageFieldTableDef();

    /**
     * 字段ID（主键）
     */
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 字段ID
     */
    public final QueryColumn FID = new QueryColumn(this, "fid");

    /**
     * 字段键
     */
    public final QueryColumn KEY = new QueryColumn(this, "key");

    /**
     * 字段文本
     */
    public final QueryColumn TEXT = new QueryColumn(this, "text");

    /**
     * 字段类型（TEXT/NUMBER/DATE/SELECT/USER/AREA等）
     */
    public final QueryColumn TYPE = new QueryColumn(this, "type");

    /**
     * 字段宽度
     */
    public final QueryColumn WIDTH = new QueryColumn(this, "width");

    /**
     * 页面配置ID
     */
    public final QueryColumn PAGE_ID = new QueryColumn(this, "page_id");

    /**
     * 是否可搜索（1-true，0-false）
     */
    public final QueryColumn SEARCH = new QueryColumn(this, "search");

    /**
     * 是否可排序（1-true，0-false）
     */
    public final QueryColumn SORTING = new QueryColumn(this, "sorting");

    /**
     * 是否必填（1-true，0-false）
     */
    public final QueryColumn REQUIRED = new QueryColumn(this, "required");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 更新时间
     */
    public final QueryColumn UPDATE_TIME = new QueryColumn(this, "update_time");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, PAGE_ID, FID, KEY, TEXT, TYPE, WIDTH, SEARCH, SORTING, REQUIRED, CREATE_TIME, UPDATE_TIME};

    public TbMetaPageFieldTableDef() {
        super("", "tb_meta_page_field");
    }

    private TbMetaPageFieldTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbMetaPageFieldTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbMetaPageFieldTableDef("", "tb_meta_page_field", alias));
    }

}
