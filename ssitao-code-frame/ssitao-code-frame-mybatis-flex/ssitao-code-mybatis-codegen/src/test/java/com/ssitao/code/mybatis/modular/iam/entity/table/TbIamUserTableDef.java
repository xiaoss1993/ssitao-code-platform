package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 部门人员 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbIamUserTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 部门人员
     */
    public static final TbIamUserTableDef TB_IAM_USER = new TbIamUserTableDef();

    /**
     * 所属机构_id
     */
    public final QueryColumn SY_ORG_ID = new QueryColumn(this, "sy_org_id");

    /**
     * 年龄
     */
    public final QueryColumn USER_AGE = new QueryColumn(this, "user_age");

    /**
     * 是否允许
     */
    public final QueryColumn SY_STATUS = new QueryColumn(this, "sy_status");

    /**
     * 编码
     */
    public final QueryColumn USER_CODE = new QueryColumn(this, "user_code");

    /**
     * 邮箱
     */
    public final QueryColumn USER_MAIL = new QueryColumn(this, "user_mail");

    /**
     * 名称
     */
    public final QueryColumn USER_NAME = new QueryColumn(this, "user_name");

    /**
     * 生日
     */
    public final QueryColumn USER_BIRTH = new QueryColumn(this, "user_birth");

    /**
     * 手机号
     */
    public final QueryColumn USER_PHONE = new QueryColumn(this, "user_phone");

    /**
     * 照片
     */
    public final QueryColumn USER_PHOTO = new QueryColumn(this, "user_photo");

    /**
     * 租户_id
     */
    public final QueryColumn SY_TENANT_ID = new QueryColumn(this, "sy_tenant_id");

    /**
     * 头像
     */
    public final QueryColumn USER_AVATAR = new QueryColumn(this, "user_avatar");

    /**
     * 工号
     */
    public final QueryColumn USER_JOBNUM = new QueryColumn(this, "user_jobnum");

    /**
     * 备注信息
     */
    public final QueryColumn USER_REMARK = new QueryColumn(this, "user_remark");

    /**
     * 角色_id
     */
    public final QueryColumn USER_ROLE_ID = new QueryColumn(this, "user_role_id");

    /**
     * 主键id
     */
    public final QueryColumn TB_IAM_USER_ID = new QueryColumn(this, "tb_iam_user_id");

    /**
     * 通讯地址
     */
    public final QueryColumn USER_ADDRESS = new QueryColumn(this, "user_address");

    /**
     * 身份卡号
     */
    public final QueryColumn USER_CARDNUM = new QueryColumn(this, "user_cardnum");

    /**
     * 性别_code
     */
    public final QueryColumn USER_SEX_CODE = new QueryColumn(this, "user_sex_code");

    /**
     * 性别_name
     */
    public final QueryColumn USER_SEX_NAME = new QueryColumn(this, "user_sex_name");

    /**
     * 工位
     */
    public final QueryColumn USER_STATION = new QueryColumn(this, "user_station");

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
     * 密级_code
     */
    public final QueryColumn SY_SECRET_CODE = new QueryColumn(this, "sy_secret_code");

    /**
     * 密级_name
     */
    public final QueryColumn SY_SECRET_NAME = new QueryColumn(this, "sy_secret_name");

    /**
     * 租户_name
     */
    public final QueryColumn SY_TENANT_NAME = new QueryColumn(this, "sy_tenant_name");

    /**
     * 行政职务_code
     */
    public final QueryColumn USER_POST_CODE = new QueryColumn(this, "user_post_code");

    /**
     * 行政职务_name
     */
    public final QueryColumn USER_POST_NAME = new QueryColumn(this, "user_post_name");

    /**
     * 角色_name
     */
    public final QueryColumn USER_ROLE_NAME = new QueryColumn(this, "user_role_name");

    /**
     * 登记部门主键
     */
    public final QueryColumn SY_CREATEORGID = new QueryColumn(this, "sy_createorgid");

    /**
     * 修改人部门主键
     */
    public final QueryColumn SY_MODIFYORGID = new QueryColumn(this, "sy_modifyorgid");

    /**
     * 入职日期
     */
    public final QueryColumn USER_ENTRY_DATE = new QueryColumn(this, "user_entry_date");

    /**
     * 离职日期
     */
    public final QueryColumn USER_LEAVE_DATE = new QueryColumn(this, "user_leave_date");

    /**
     * 座机
     */
    public final QueryColumn USER_TELEPHONE = new QueryColumn(this, "user_telephone");

    /**
     * 登记人主键
     */
    public final QueryColumn SY_CREATEUSERID = new QueryColumn(this, "sy_createuserid");

    /**
     * 修改人主键
     */
    public final QueryColumn SY_MODIFYUSERID = new QueryColumn(this, "sy_modifyuserid");

    /**
     * 证件类型_code
     */
    public final QueryColumn USER_IDTYPE_CODE = new QueryColumn(this, "user_idtype_code");

    /**
     * 证件类型_name
     */
    public final QueryColumn USER_IDTYPE_NAME = new QueryColumn(this, "user_idtype_name");

    /**
     * 已初始化_code
     */
    public final QueryColumn USER_INITED_CODE = new QueryColumn(this, "user_inited_code");

    /**
     * 已初始化_name
     */
    public final QueryColumn USER_INITED_NAME = new QueryColumn(this, "user_inited_name");

    /**
     * 民族_code
     */
    public final QueryColumn USER_NATION_CODE = new QueryColumn(this, "user_nation_code");

    /**
     * 现居地址
     */
    public final QueryColumn USER_NOWADDRESS = new QueryColumn(this, "user_nowaddress");

    /**
     * 登记部门
     */
    public final QueryColumn SY_CREATEORGNAME = new QueryColumn(this, "sy_createorgname");

    /**
     * 修改人部门
     */
    public final QueryColumn SY_MODIFYORGNAME = new QueryColumn(this, "sy_modifyorgname");

    /**
     * 已婚_code
     */
    public final QueryColumn USER_MARRIED_CODE = new QueryColumn(this, "user_married_code");

    /**
     * 已婚_name
     */
    public final QueryColumn USER_MARRIED_NAME = new QueryColumn(this, "user_married_name");

    /**
     * 民族_name
     */
    public final QueryColumn USER_NATION_NAME = new QueryColumn(this, "user_nation__name");

    /**
     * 籍贯
     */
    public final QueryColumn USER_NATIVE_PLACE = new QueryColumn(this, "user_native_place");

    /**
     * 登记人
     */
    public final QueryColumn SY_CREATEUSERNAME = new QueryColumn(this, "sy_createusername");

    /**
     * 修改人
     */
    public final QueryColumn SY_MODIFYUSERNAME = new QueryColumn(this, "sy_modifyusername");

    /**
     * 紧急联系人名称
     */
    public final QueryColumn USER_CONTACTOR_NAME = new QueryColumn(this, "user_contactor_name");

    /**
     * 文化程度_code
     */
    public final QueryColumn USER_EDUCATION_CODE = new QueryColumn(this, "user_education_code");

    /**
     * 文化程度_name
     */
    public final QueryColumn USER_EDUCATION_NAME = new QueryColumn(this, "user_education_name");

    /**
     * 户籍性质_code
     */
    public final QueryColumn USER_HOUSEHOLD_CODE = new QueryColumn(this, "user_household_code");

    /**
     * 户籍性质_name
     */
    public final QueryColumn USER_HOUSEHOLD_NAME = new QueryColumn(this, "user_household_name");

    /**
     * 监管部门_id
     */
    public final QueryColumn USER_MONITORDEPT_ID = new QueryColumn(this, "user_monitordept_id");

    /**
     * 政治面貌_code
     */
    public final QueryColumn USER_POLITICAL_CODE = new QueryColumn(this, "user_political_code");

    /**
     * 政治面貌_name
     */
    public final QueryColumn USER_POLITICAL_NAME = new QueryColumn(this, "user_political_name");

    /**
     * 紧急联系人电话
     */
    public final QueryColumn USER_CONTACTOR_PHONE = new QueryColumn(this, "user_contactor_phone");

    /**
     * 人员状态
     */
    public final QueryColumn USER_EMPLOYEE_STATUS = new QueryColumn(this, "user_employee_status");

    /**
     * 监管部门_name
     */
    public final QueryColumn USER_MONITORDEPT_NAME = new QueryColumn(this, "user_monitordept_name");

    /**
     * 国籍_code
     */
    public final QueryColumn USER_NATIONALITY_CODE = new QueryColumn(this, "user_nationality_code");

    /**
     * 国籍_name
     */
    public final QueryColumn USER_NATIONALITY_NAME = new QueryColumn(this, "user_nationality_name");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{TB_IAM_USER_ID, USER_NAME, USER_CODE, USER_SEX_CODE, USER_SEX_NAME, USER_PHONE, USER_MAIL, USER_MARRIED_CODE, USER_MARRIED_NAME, USER_AGE, USER_BIRTH, USER_ENTRY_DATE, USER_LEAVE_DATE, USER_INITED_CODE, USER_INITED_NAME, USER_PHOTO, USER_AVATAR, USER_CARDNUM, USER_IDTYPE_CODE, USER_IDTYPE_NAME, USER_NATIVE_PLACE, USER_EDUCATION_CODE, USER_EDUCATION_NAME, USER_POST_CODE, USER_POST_NAME, USER_MONITORDEPT_ID, USER_MONITORDEPT_NAME, USER_ROLE_ID, USER_ROLE_NAME, USER_REMARK, USER_ADDRESS, USER_STATION, USER_JOBNUM, USER_NOWADDRESS, USER_POLITICAL_CODE, USER_POLITICAL_NAME, USER_CONTACTOR_NAME, USER_CONTACTOR_PHONE, USER_TELEPHONE, USER_HOUSEHOLD_CODE, USER_HOUSEHOLD_NAME, USER_NATION_NAME, USER_NATION_CODE, USER_NATIONALITY_NAME, USER_NATIONALITY_CODE, USER_EMPLOYEE_STATUS, SY_SECRET_CODE, SY_SECRET_NAME, SY_MODIFYORGID, SY_MODIFYORGNAME, SY_MODIFYUSERID, SY_MODIFYUSERNAME, SY_MODIFYTIME, SY_CREATEORGID, SY_CREATEORGNAME, SY_CREATETIME, SY_CREATEUSERID, SY_CREATEUSERNAME, SY_STATUS, SY_ORDERINDEX, SY_ORG_ID, SY_TENANT_ID, SY_TENANT_NAME};

    public TbIamUserTableDef() {
        super("", "tb_iam_user");
    }

    private TbIamUserTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbIamUserTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbIamUserTableDef("", "tb_iam_user", alias));
    }

}
