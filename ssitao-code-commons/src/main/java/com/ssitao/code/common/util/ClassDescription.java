package com.ssitao.code.common.util;

import lombok.Getter;

import java.util.Collection;

/**
 * 类描述信息
 *
 * @since 3.0
 */
@Getter
public class ClassDescription {
    private final Class<?> type;

    private final boolean collectionType;
    private final boolean arrayType;
    private final boolean enumType;
    private final boolean enumDict;
    private final int fieldSize;

    public ClassDescription(Class<?> type) {
        this.type = type;
        collectionType = Collection.class.isAssignableFrom(type);
        enumDict = EnumDict.class.isAssignableFrom(type);
        arrayType = type.isArray();
        enumType = type.isEnum();
        fieldSize = type.getDeclaredFields().length;
    }

}
