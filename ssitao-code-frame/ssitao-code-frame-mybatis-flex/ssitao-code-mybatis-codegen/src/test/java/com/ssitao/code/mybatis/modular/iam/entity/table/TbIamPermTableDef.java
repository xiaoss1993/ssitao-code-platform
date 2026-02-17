package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 权限管理 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbIamPermTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 权限管理
     */
    public static final TbIamPermTableDef TB_IAM_PERM = new TbIamPermTableDef();

    /**
     * 权限编码
     */
    public final QueryColumn PERM_CODE = new QueryColumn(this, "perm_code");

    /**
     * 权限名称
     */
    public final QueryColumn PERM_NAME = new QueryColumn(this, "perm_name");

    /**
     * 数据状态
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 所属对象
     */
    public final QueryColumn PERM_OBJECT = new QueryColumn(this, "perm_object");

    /**
     * 备注信息
     */
    public final QueryColumn PERM_REMARK = new QueryColumn(this, "perm_remark");

    /**
     * 租户_id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * 主键id
     */
    public final QueryColumn TB_IAM_PERM_ID = new QueryColumn(this, "tb_iam_perm_id");

    /**
     * 目标对象_id
     */
    public final QueryColumn PERM_TARGET_ID = new QueryColumn(this, "perm_target_id");

    /**
     * 权限类型_code
     */
    public final QueryColumn PERM_TYPE_CODE = new QueryColumn(this, "perm_type_code");

    /**
     * 权限类型_name
     */
    public final QueryColumn PERM_TYPE_NAME = new QueryColumn(this, "perm_type_name");

    /**
     * 登记时间
     */
    public final QueryColumn SY_CREATETIME = new QueryColumn(this, "sy_createtime");

    /**
     * 排序字段
     */
    public final QueryColumn SY_ORDERINDEX = new QueryColumn(this, "sy_orderindex");

    /**
     * 租户_name
     */
    public final QueryColumn SY_TENANT_NAME = new QueryColumn(this, "sy_tenant_name");

    /**
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 是否输出权限_code
     */
    public final QueryColumn PERM_OUTPUT_CODE = new QueryColumn(this, "perm_output_code");

    /**
     * 是否输出权限_name
     */
    public final QueryColumn PERM_OUTPUT_NAME = new QueryColumn(this, "perm_output_name");

    /**
     * 目标对象_code
     */
    public final QueryColumn PERM_TARGET_CODE = new QueryColumn(this, "perm_target_code");

    /**
     * 目标对象_name
     */
    public final QueryColumn PERM_TARGET_NAME = new QueryColumn(this, "perm_target_name");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 操作类型_code
     */
    public final QueryColumn PERM_OPERATE_CODE = new QueryColumn(this, "perm_operate_code");

    /**
     * 操作类型_name
     */
    public final QueryColumn PERM_OPERATE_NAME = new QueryColumn(this, "perm_operate_name");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 输出模板
     */
    public final QueryColumn PERM_OUTPUT_TEMPLATE = new QueryColumn(this, "perm_output_template");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{PERM_NAME, PERM_CODE, PERM_TYPE_CODE, PERM_TYPE_NAME, PERM_OUTPUT_CODE, PERM_OUTPUT_NAME, PERM_OUTPUT_TEMPLATE, PERM_REMARK, PERM_OBJECT, PERM_OPERATE_CODE, PERM_OPERATE_NAME, PERM_TARGET_ID, PERM_TARGET_CODE, PERM_TARGET_NAME, SY_TENANT_ID, SY_TENANT_NAME, TB_IAM_PERM_ID, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX};

    public TbIamPermTableDef() {
        super("", "tb_iam_perm");
    }

    private TbIamPermTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbIamPermTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbIamPermTableDef("", "tb_iam_perm", alias));
    }

}
