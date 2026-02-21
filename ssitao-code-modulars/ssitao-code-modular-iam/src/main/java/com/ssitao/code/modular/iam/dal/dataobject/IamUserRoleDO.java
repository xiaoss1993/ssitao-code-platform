package com.ssitao.code.modular.iam.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * IAM用户角色关联数据对象
 * 对应数据库表：iam_user_role
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Table(value = "iam_user_role")
public class IamUserRoleDO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 是否主角色：1-是 0-否
     */
    private Boolean isMain;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
