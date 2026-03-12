
package com.ssitao.code.frame.mybatisflex.core.query;

import com.ssitao.code.frame.mybatisflex.core.dialect.IDialect;
import com.ssitao.code.frame.mybatisflex.core.exception.FlexExceptions;

public class WithStringDetail implements WithDetail {

    private String rawSQL;
    private Object[] params;

    public WithStringDetail(String rawSQL, Object[] params) {
        this.rawSQL = rawSQL;
        this.params = params;
    }

    public String getRawSQL() {
        return rawSQL;
    }

    public void setRawSQL(String rawSQL) {
        this.rawSQL = rawSQL;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    @Override
    public String toSql(IDialect dialect) {
        return rawSQL;
    }

    @Override
    public Object[] getParamValues() {
        return params;
    }

    @Override
    public WithStringDetail clone() {
        try {
            return (WithStringDetail) super.clone();
        } catch (CloneNotSupportedException e) {
            throw FlexExceptions.wrap(e);
        }
    }

}
