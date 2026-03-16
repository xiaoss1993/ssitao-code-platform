package com.ssitao.code.common.entity;

import com.ssitao.code.common.util.EnumDict;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public enum DataStatusEnum implements EnumDict<Byte> {
    ENABLED((byte) 1, "正常"),
    DISABLED((byte) 0, "禁用"),
    LOCK((byte) -1, "锁定"),
    DELETED((byte) -10, "删除");

    private final Byte value;
    private final String text;

    DataStatusEnum(Byte value, String text) {
        this.value = value;
        this.text = text;
    }

}
