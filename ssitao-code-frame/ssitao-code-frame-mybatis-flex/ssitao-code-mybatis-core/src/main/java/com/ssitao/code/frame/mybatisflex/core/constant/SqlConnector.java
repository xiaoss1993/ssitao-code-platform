
package com.ssitao.code.frame.mybatisflex.core.constant;

/**
 * @author ssitao
 */
public enum SqlConnector {


    /**
     * And
     */
    AND(" AND "),
    //    AND_NOT(" AND NOT "),
//    AND_EXISTS(" AND EXISTS "),
//    AND_NOT_EXISTS(" AND NOT EXISTS "),

    /**
     * OR
     */
    OR(" OR "),
//    OR_NOT(" OR NOT "),
//    OR_EXISTS(" OR EXISTS "),
//    OR_NOT_EXISTS(" OR NOT EXISTS "),
//    NOT(" NOT "),
    ;


    private final String value;

    SqlConnector(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
