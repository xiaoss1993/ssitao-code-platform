package com.ssitao.code.modular.database.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SqlExecuteResult {

    private SqlInfo sqlInfo;

    private Object result;

    private boolean success;

}
