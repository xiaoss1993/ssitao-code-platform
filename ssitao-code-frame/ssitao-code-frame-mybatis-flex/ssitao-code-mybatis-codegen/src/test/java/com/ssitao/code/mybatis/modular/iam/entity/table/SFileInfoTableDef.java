package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 文件信息 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class SFileInfoTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 文件信息
     */
    public static final SFileInfoTableDef SFILE_INFO = new SFileInfoTableDef();

    /**
     * md5校验值
     */
    public final QueryColumn MD5 = new QueryColumn(this, "md5");

    /**
     * ID
     */
    public final QueryColumn U_ID = new QueryColumn(this, "u_id");

    /**
     * 文件名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 文件大小
     */
    public final QueryColumn SIZE = new QueryColumn(this, "size");

    /**
     * 类型
     */
    public final QueryColumn TYPE = new QueryColumn(this, "type");

    /**
     * 状态
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 文件相对路径
     */
    public final QueryColumn LOCATION = new QueryColumn(this, "location");

    /**
     * 创建人
     */
    public final QueryColumn CREATOR_ID = new QueryColumn(this, "creator_id");

    /**
     * 分类
     */
    public final QueryColumn CLASSIFIED = new QueryColumn(this, "classified");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{U_ID, NAME, LOCATION, TYPE, MD5, SIZE, STATUS, CLASSIFIED, CREATE_TIME, CREATOR_ID};

    public SFileInfoTableDef() {
        super("", "s_file_info");
    }

    private SFileInfoTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SFileInfoTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SFileInfoTableDef("", "s_file_info", alias));
    }

}
