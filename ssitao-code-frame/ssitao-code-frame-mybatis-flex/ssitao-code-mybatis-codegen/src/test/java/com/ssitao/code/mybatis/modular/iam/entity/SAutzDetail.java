package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import com.ssitao.code.frame.mybatisflex.codegen.test.BaseEntity;
import java.io.Serializable;
import java.math.BigDecimal;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * 权限设置详情 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("s_autz_detail")
public class SAutzDetail extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * uid
     */
    @Id
    private String uId;

    /**
     * 权限ID
     */
    private String permissionId;

    /**
     * 设置ID
     */
    private String settingId;

    /**
     * 可操作类型
     */
    private String actions;

    /**
     * 数据权限控制
     */
    private String dataAccesses;

    /**
     * 状态
     */
    private BigDecimal status;

    /**
     * 优先级
     */
    private BigDecimal priority;

    /**
     * 是否合并
     */
    private BigDecimal isMerge;

}
