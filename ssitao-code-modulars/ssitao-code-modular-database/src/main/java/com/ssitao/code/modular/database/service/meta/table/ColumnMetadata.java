package com.ssitao.code.modular.database.service.meta.table;

import lombok.Data;

import java.io.Serializable;
import java.sql.JDBCType;

/**
 *
 */
@Data
public class ColumnMetadata implements Serializable {
    private static final long serialVersionUID = 2068679809718583039L;

    /**
     * 列名
     */
    private String name;

    /**
     * 备注
     */
    private String comment;

    /**
     * 数据类型
     */
    private String dataType;

    /**
     * 长度
     */
    private int length;

    /**
     * 精度
     */
    private int precision;

    /**
     * 小数点位数
     */
    private int scale;

    /**
     * 是否不能为空
     */
    private boolean notNull;

    /**
     * JDBCType
     */
    private JDBCType jdbcType;

}
