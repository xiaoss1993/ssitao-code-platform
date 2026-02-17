package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import com.ssitao.code.frame.mybatisflex.codegen.test.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * 员工奖惩信息 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_hrms_staff_reward_punish")
public class TbHrmsStaffRewardPunish extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    /**
     * 奖惩时间
     */
    private String rewardPunishTime;

    /**
     * 名称
     */
    private String name;

    /**
     * 奖惩金额
     */
    private BigDecimal price;

    /**
     * 奖惩事件描述
     */
    private String content;

    /**
     * 备注
     */
    private String remark;

    /**
     * 奖惩分类
     */
    private String typeId;

    /**
     * 授予单位
     */
    private String awardUnit;

    /**
     * 所属第三方业务数据id(员工id)
     */
    private String objectId;

    /**
     * 所属第三方业务数据的key(员工key)
     */
    private String objectKey;

    /**
     * 创建人
     */
    private String createId;

    /**
     * 录入时间
     */
    private LocalDateTime createTime;

    /**
     * 最后更新人
     */
    private String lastUpdateId;

    /**
     * 最后更新时间
     */
    private LocalDateTime lastUpdateTime;

}
