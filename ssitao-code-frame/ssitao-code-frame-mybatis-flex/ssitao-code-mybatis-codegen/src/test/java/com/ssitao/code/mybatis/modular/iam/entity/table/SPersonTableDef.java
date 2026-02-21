package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 人员 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class SPersonTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 人员
     */
    public static final SPersonTableDef SPERSON = new SPersonTableDef();

    /**
     * 性别
     */
    public final QueryColumn SEX = new QueryColumn(this, "sex");

    /**
     * ID
     */
    public final QueryColumn U_ID = new QueryColumn(this, "u_id");

    /**
     * 姓名
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 电子邮箱
     */
    public final QueryColumn EMAIL = new QueryColumn(this, "email");

    /**
     * 联系电话
     */
    public final QueryColumn PHONE = new QueryColumn(this, "phone");

    /**
     * 照片
     */
    public final QueryColumn PHOTO = new QueryColumn(this, "photo");

    /**
     * 备注
     */
    public final QueryColumn REMARK = new QueryColumn(this, "remark");

    /**
     * 状态
     */
    public final QueryColumn STATUS = new QueryColumn(this, "status");

    /**
     * 关联用户id
     */
    public final QueryColumn USER_ID = new QueryColumn(this, "user_id");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{U_ID, NAME, SEX, EMAIL, PHONE, PHOTO, USER_ID, STATUS, REMARK};

    public SPersonTableDef() {
        super("", "s_person");
    }

    private SPersonTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public SPersonTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new SPersonTableDef("", "s_person", alias));
    }

}
