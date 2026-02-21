package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 类1 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class Biaolei1TableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 类1
     */
    public static final Biaolei1TableDef BIAOLEI1 = new Biaolei1TableDef();

    
    public final QueryColumn TEST01 = new QueryColumn(this, "test01");

    
    public final QueryColumn TB_CORE_TEST_ID = new QueryColumn(this, "tb_core_test_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TEST01, TB_CORE_TEST_ID};

    public Biaolei1TableDef() {
        super("", "biaolei1");
    }

    private Biaolei1TableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public Biaolei1TableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new Biaolei1TableDef("", "biaolei1", alias));
    }

}
