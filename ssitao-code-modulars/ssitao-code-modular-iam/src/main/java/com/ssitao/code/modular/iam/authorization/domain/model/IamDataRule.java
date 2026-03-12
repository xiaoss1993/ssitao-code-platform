package com.ssitao.code.modular.iam.authorization.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * IAM数据权限规则实体
 * 用于定义细粒度的数据权限控制规则
 * 对应表：iam_data_rule
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IamDataRule {

    /**
     * 规则ID
     */
    private String id;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 规则编码
     */
    private String ruleCode;

    /**
     * 实体名称
     */
    private String entityName;

    /**
     * 字段名称
     */
    private String fieldName;

    /**
     * 规则类型：EQ-等于 NE-不等于 IN-包含 LIKE-模糊匹配 BETWEEN-区间 SCRIPT-脚本
     */
    private String ruleType;

    /**
     * 规则值
     */
    private String ruleValue;

    /**
     * 规则描述
     */
    private String ruleDesc;

    /**
     * 状态：1-启用 0-禁用
     */
    private Boolean status;

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
     * 创建数据权限规则
     *
     * @param ruleName   规则名称
     * @param ruleCode   规则编码
     * @param entityName 实体名称
     * @param fieldName  字段名称
     * @param ruleType   规则类型
     * @param ruleValue  规则值
     * @return 数据权限规则实体
     */
    public static IamDataRule create(String ruleName, String ruleCode, String entityName,
                                       String fieldName, String ruleType, String ruleValue) {
        IamDataRule dataRule = new IamDataRule();
        dataRule.setRuleName(ruleName);
        dataRule.setRuleCode(ruleCode);
        dataRule.setEntityName(entityName);
        dataRule.setFieldName(fieldName);
        dataRule.setRuleType(ruleType);
        dataRule.setRuleValue(ruleValue);
        dataRule.setStatus(true);
        dataRule.setDeleted(false);
        dataRule.setCreateTime(LocalDateTime.now());
        return dataRule;
    }

    /**
     * 创建等于规则
     *
     * @param ruleName   规则名称
     * @param ruleCode   规则编码
     * @param entityName 实体名称
     * @param fieldName  字段名称
     * @param ruleValue  规则值
     * @return 数据权限规则实体
     */
    public static IamDataRule createEq(String ruleName, String ruleCode, String entityName,
                                        String fieldName, String ruleValue) {
        return create(ruleName, ruleCode, entityName, fieldName, "EQ", ruleValue);
    }

    /**
     * 创建包含规则
     *
     * @param ruleName   规则名称
     * @param ruleCode   规则编码
     * @param entityName 实体名称
     * @param fieldName  字段名称
     * @param ruleValue  规则值
     * @return 数据权限规则实体
     */
    public static IamDataRule createIn(String ruleName, String ruleCode, String entityName,
                                         String fieldName, String ruleValue) {
        return create(ruleName, ruleCode, entityName, fieldName, "IN", ruleValue);
    }

    /**
     * 创建模糊匹配规则
     *
     * @param ruleName   规则名称
     * @param ruleCode   规则编码
     * @param entityName 实体名称
     * @param fieldName  字段名称
     * @param ruleValue  规则值
     * @return 数据权限规则实体
     */
    public static IamDataRule createLike(String ruleName, String ruleCode, String entityName,
                                           String fieldName, String ruleValue) {
        return create(ruleName, ruleCode, entityName, fieldName, "LIKE", ruleValue);
    }

    /**
     * 禁用规则
     */
    public void disable() {
        this.status = false;
        this.updateTime = LocalDateTime.now();
    }

    /**
     * 启用规则
     */
    public void enable() {
        this.status = true;
        this.updateTime = LocalDateTime.now();
    }

    /**
     * 判断是否为等于规则
     *
     * @return true-是，false-否
     */
    public boolean isEq() {
        return "EQ".equals(this.ruleType);
    }

    /**
     * 判断是否为不等于规则
     *
     * @return true-是，false-否
     */
    public boolean isNe() {
        return "NE".equals(this.ruleType);
    }

    /**
     * 判断是否为包含规则
     *
     * @return true-是，false-否
     */
    public boolean isIn() {
        return "IN".equals(this.ruleType);
    }

    /**
     * 判断是否为模糊匹配规则
     *
     * @return true-是，false-否
     */
    public boolean isLike() {
        return "LIKE".equals(this.ruleType);
    }

    /**
     * 判断是否为区间规则
     *
     * @return true-是，false-否
     */
    public boolean isBetween() {
        return "BETWEEN".equals(this.ruleType);
    }

    /**
     * 判断是否为脚本规则
     *
     * @return true-是，false-否
     */
    public boolean isScript() {
        return "SCRIPT".equals(this.ruleType);
    }

}
