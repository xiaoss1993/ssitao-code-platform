
package com.ssitao.code.frame.mybatisflex.core.row;

import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.core.keygen.KeyGenerators;
import com.ssitao.code.frame.mybatisflex.core.util.SqlUtil;

import java.io.Serializable;

/**
 * row 的主键策略
 */
public class RowKey implements Serializable {

    /**
     * 自增 ID
     */
    public static final RowKey AUTO = RowKey.of("id", KeyType.Auto, null, false);

    /**
     * UUID 的 ID
     */
    public static final RowKey UUID = RowKey.of("id", KeyType.Generator, KeyGenerators.uuid, true);

    /**
     * flexId
     */
    public static final RowKey FLEX_ID = RowKey.of("id", KeyType.Generator, KeyGenerators.flexId, true);

    /**
     * snowFlakeId
     */
    public static final RowKey SNOW_FLAKE_ID = RowKey.of("id", KeyType.Generator, KeyGenerators.snowFlakeId, true);

    /**
     * ulid
     */
    public static final RowKey ULID = RowKey.of("id", KeyType.Generator, KeyGenerators.ulid, true);


    public static RowKey of(String keyColumn) {
        SqlUtil.keepColumnSafely(keyColumn);
        RowKey rowKey = new RowKey();
        rowKey.keyColumn = keyColumn;
        return rowKey;
    }

    public static RowKey of(String keyColumn, KeyType keyType) {
        SqlUtil.keepColumnSafely(keyColumn);
        RowKey rowKey = new RowKey();
        rowKey.keyColumn = keyColumn;
        rowKey.keyType = keyType;
        return rowKey;
    }

    public static RowKey of(String keyColumn, KeyType keyType, String keyTypeValue) {
        SqlUtil.keepColumnSafely(keyColumn);
        RowKey rowKey = new RowKey();
        rowKey.keyColumn = keyColumn;
        rowKey.keyType = keyType;
        rowKey.value = keyTypeValue;
        return rowKey;
    }

    public static RowKey of(String keyColumn, KeyType keyType, String keyTypeValue, boolean before) {
        SqlUtil.keepColumnSafely(keyColumn);
        RowKey rowKey = new RowKey();
        rowKey.keyColumn = keyColumn;
        rowKey.keyType = keyType;
        rowKey.value = keyTypeValue;
        rowKey.before = before;
        return rowKey;
    }

    /**
     * 主键字段
     */
    protected String keyColumn;

    /**
     * 主键类型
     */
    protected KeyType keyType = KeyType.Auto;

    /**
     * 主键类型为 Sequence 和 Generator 时的对应的内容
     */
    protected String value;

    /**
     * 是否前执行
     */
    protected boolean before = true;


    public String getKeyColumn() {
        return keyColumn;
    }


    public KeyType getKeyType() {
        return keyType;
    }


    public String getValue() {
        return value;
    }


    public boolean isBefore() {
        return before;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof RowKey) {
            return keyColumn.equals(((RowKey) o).keyColumn);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return keyColumn.hashCode();
    }

}
