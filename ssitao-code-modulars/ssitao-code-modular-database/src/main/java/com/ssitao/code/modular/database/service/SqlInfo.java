package com.ssitao.code.modular.database.service;

import lombok.Data;

import java.io.Serializable;

/**
 *
 */
@Data
public class SqlInfo implements Serializable {
    private static final long serialVersionUID = -2119739552930123239L;
    private String sql;

    private String datasourceId;

    private String type;
}
