package com.ssitao.code.modular.iam.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * IAM 模块数据类型枚举
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Getter
@AllArgsConstructor
public enum IamDataTypeEnum {

    COMPANY("company", "公司"),
    DEPARTMENT("department", "部门"),
    POST("post", "岗位"),
    ROLE("role", "角色"),
    PERMISSION("permission", "权限"),
    MENU("menu", "菜单"),
    USER("user", "用户"),
    ACCOUNT("account", "账号"),
    ALL("all", "全部");

    private final String code;
    private final String desc;

    public static IamDataTypeEnum getByCode(String code) {
        for (IamDataTypeEnum value : values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
