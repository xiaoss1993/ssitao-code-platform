package com.ssitao.code.modular.iam.authorization.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * IAM角色数据规则关联实体
 * 用于建立角色与数据权限规则的多对多关系
 * 对应表：iam_role_data_rule
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IamRoleDataRule {

    /**
     * 关联ID
     */
    private String id;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 数据规则ID
     */
    private String dataRuleId;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 更新人
     */
    private String updater;

    /**
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 创建角色数据规则关联
     *
     * @param roleId     角色ID
     * @param dataRuleId 数据规则ID
     * @param tenantId   租户ID
     * @return 角色数据规则关联实体
     */
    public static IamRoleDataRule create(String roleId, String dataRuleId, String tenantId) {
        IamRoleDataRule roleDataRule = new IamRoleDataRule();
        roleDataRule.setRoleId(roleId);
        roleDataRule.setDataRuleId(dataRuleId);
        roleDataRule.setTenantId(tenantId);
        roleDataRule.setDeleted(false);
        roleDataRule.setCreateTime(LocalDateTime.now());
        return roleDataRule;
    }

    /**
     * 判断是否为同一条关联记录
     *
     * @param roleId     角色ID
     * @param dataRuleId 数据规则ID
     * @return true-是，false-否
     */
    public boolean matches(String roleId, String dataRuleId) {
        return this.roleId.equals(roleId) && this.dataRuleId.equals(dataRuleId);
    }

}
