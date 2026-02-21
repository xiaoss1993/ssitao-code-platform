package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 员工证书 表定义层。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TbHrmsStaffCertificateTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 员工证书
     */
    public static final TbHrmsStaffCertificateTableDef TB_HRMS_STAFF_CERTIFICATE = new TbHrmsStaffCertificateTableDef();

    
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 证书名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 证书类型id
     */
    public final QueryColumn TYPE_ID = new QueryColumn(this, "type_id");

    /**
     * 创建人
     */
    public final QueryColumn CREATE_ID = new QueryColumn(this, "create_id");

    /**
     * 所属第三方业务数据id(员工id)
     */
    public final QueryColumn OBJECT_ID = new QueryColumn(this, "object_id");

    /**
     * 签发时间
     */
    public final QueryColumn ISSUE_TIME = new QueryColumn(this, "issue_time");

    /**
     * 所属第三方业务数据的key(员工key)
     */
    public final QueryColumn OBJECT_KEY = new QueryColumn(this, "object_key");

    /**
     * 创建时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 签发机构
     */
    public final QueryColumn ISSUE_ORGAN = new QueryColumn(this, "issue_organ");

    /**
     * 最后更新人
     */
    public final QueryColumn LAST_UPDATE_ID = new QueryColumn(this, "last_update_id");

    /**
     * 最后更新时间
     */
    public final QueryColumn LAST_UPDATE_TIME = new QueryColumn(this, "last_update_time");

    /**
     * 证书编号
     */
    public final QueryColumn CERTIFICATE_NUMBER = new QueryColumn(this, "certificate_number");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, CERTIFICATE_NUMBER, NAME, TYPE_ID, ISSUE_TIME, ISSUE_ORGAN, OBJECT_ID, OBJECT_KEY, CREATE_ID, CREATE_TIME, LAST_UPDATE_ID, LAST_UPDATE_TIME};

    public TbHrmsStaffCertificateTableDef() {
        super("", "tb_hrms_staff_certificate");
    }

    private TbHrmsStaffCertificateTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbHrmsStaffCertificateTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbHrmsStaffCertificateTableDef("", "tb_hrms_staff_certificate", alias));
    }

}
