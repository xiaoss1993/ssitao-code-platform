package com.ssitao.code.modular.database.service.meta.table;

import lombok.Data;

/**
 *
 */
@Data
public class ForeignConstraint extends Constraint {
    private static final long serialVersionUID = -7146549641064694467L;
    private String targetTable;

    private String targetColumn;

}
