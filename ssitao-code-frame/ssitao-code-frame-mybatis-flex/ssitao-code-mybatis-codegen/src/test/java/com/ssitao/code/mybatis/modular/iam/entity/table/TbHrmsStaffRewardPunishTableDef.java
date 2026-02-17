package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.table;

import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.table.TableDef;


/**
 * 员工奖惩信息 表定义层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
public class TbHrmsStaffRewardPunishTableDef extends TableDef {

    private static final long serialVersionUID = 1L;

    /**
     * 员工奖惩信息
     */
    public static final TbHrmsStaffRewardPunishTableDef TB_HRMS_STAFF_REWARD_PUNISH = new TbHrmsStaffRewardPunishTableDef();

    
    public final QueryColumn ID = new QueryColumn(this, "id");

    /**
     * 名称
     */
    public final QueryColumn NAME = new QueryColumn(this, "name");

    /**
     * 奖惩金额
     */
    public final QueryColumn PRICE = new QueryColumn(this, "price");

    /**
     * 备注
     */
    public final QueryColumn REMARK = new QueryColumn(this, "remark");

    /**
     * 奖惩分类
     */
    public final QueryColumn TYPE_ID = new QueryColumn(this, "type_id");

    /**
     * 奖惩事件描述
     */
    public final QueryColumn CONTENT = new QueryColumn(this, "content");

    /**
     * 创建人
     */
    public final QueryColumn CREATE_ID = new QueryColumn(this, "create_id");

    /**
     * 所属第三方业务数据id(员工id)
     */
    public final QueryColumn OBJECT_ID = new QueryColumn(this, "object_id");

    /**
     * 授予单位
     */
    public final QueryColumn AWARD_UNIT = new QueryColumn(this, "award_unit");

    /**
     * 所属第三方业务数据的key(员工key)
     */
    public final QueryColumn OBJECT_KEY = new QueryColumn(this, "object_key");

    /**
     * 录入时间
     */
    public final QueryColumn CREATE_TIME = new QueryColumn(this, "create_time");

    /**
     * 最后更新人
     */
    public final QueryColumn LAST_UPDATE_ID = new QueryColumn(this, "last_update_id");

    /**
     * 最后更新时间
     */
    public final QueryColumn LAST_UPDATE_TIME = new QueryColumn(this, "last_update_time");

    /**
     * 奖惩时间
     */
    public final QueryColumn REWARD_PUNISH_TIME = new QueryColumn(this, "reward_punish_time");

    /**
     * 所有字段。
     */
    public final QueryColumn ALL_COLUMNS = new QueryColumn(this, "*");

    /**
     * 默认字段，不包含逻辑删除或者 large 等字段。
     */
    public final QueryColumn[] DEFAULT_COLUMNS = new QueryColumn[]{ID, REWARD_PUNISH_TIME, NAME, PRICE, CONTENT, REMARK, TYPE_ID, AWARD_UNIT, OBJECT_ID, OBJECT_KEY, CREATE_ID, CREATE_TIME, LAST_UPDATE_ID, LAST_UPDATE_TIME};

    public TbHrmsStaffRewardPunishTableDef() {
        super("", "tb_hrms_staff_reward_punish");
    }

    private TbHrmsStaffRewardPunishTableDef(String schema, String name, String alisa) {
        super(schema, name, alisa);
    }

    public TbHrmsStaffRewardPunishTableDef as(String alias) {
        String key = getNameWithSchema() + "." + alias;
        return getCache(key, k -> new TbHrmsStaffRewardPunishTableDef("", "tb_hrms_staff_reward_punish", alias));
    }

}
