package com.ssitao.code.common.enums;

/**
 * 审核状态枚举
 *
 * @author ssitao
 * @since 1.0.0
 */
public enum AudFlagStatus {

    /**
     * 未审核
     */
    NOSTATUS("0", "未审核"),

    /**
     * 审核中
     */
    AUDITING("1", "审核中"),

    /**
     * 已通过
     */
    PASSED("2", "已通过"),

    /**
     * 已拒绝
     */
    REJECTED("3", "已拒绝");

    private final String code;
    private final String desc;

    AudFlagStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static AudFlagStatus fromCode(String code) {
        for (AudFlagStatus status : values()) {
            if (status.code.equals(code)) {
                return status;
            }
        }
        return NOSTATUS;
    }
}
