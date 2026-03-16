package com.ssitao.code.modular.database.service.exception;

import com.tweb.commons.utils.BusinessException;

public class SqlExecuteException extends BusinessException {
    private static final long serialVersionUID = -2109245893594218935L;
    private String sql;

    public SqlExecuteException(String message, Throwable cause, String sql) {
        super(message, cause);
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }
}
