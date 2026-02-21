package com.ssitao.code.modular.iam.userprofile.application.command;

import lombok.Data;

/**
 * IAM用户查询命令
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
public class IamUserQueryCommand {

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 关键词（用户名、昵称、真实姓名、手机号）
     */
    private String keyword;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 岗位ID
     */
    private Long postId;

    /**
     * 账号状态：NORMAL-正常 LOCKED-锁定 DISABLED-禁用
     */
    private String accountStatus;

    /**
     * 性别：0-未知 1-男 2-女
     */
    private Integer gender;

}
