
package com.ssitao.code.frame.mybatisflex.core.update;

import com.ssitao.code.frame.mybatisflex.core.constant.SqlConsts;
import com.ssitao.code.frame.mybatisflex.core.dialect.IDialect;
import com.ssitao.code.frame.mybatisflex.core.query.CPI;
import com.ssitao.code.frame.mybatisflex.core.query.HasParamsColumn;
import com.ssitao.code.frame.mybatisflex.core.query.QueryColumn;
import com.ssitao.code.frame.mybatisflex.core.query.QueryCondition;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;

import java.io.Serializable;

/**
 * @author michael
 */
public class RawValue implements Serializable {

    private Object object;

    public RawValue(Object object) {
        this.object = object;
    }


    public String toSql(IDialect dialect) {
        if (object instanceof String) {
            return (String) object;
        }

        if (object instanceof QueryWrapper) {
            return SqlConsts.BRACKET_LEFT + dialect.buildSelectSql((QueryWrapper) object) + SqlConsts.BRACKET_RIGHT;
        }

        if (object instanceof QueryCondition) {
            return ((QueryCondition) object).toSql(null, dialect);
        }

        if (object instanceof QueryColumn) {
            return CPI.toSelectSql((QueryColumn) object, null, dialect);
        }

        return object.toString();
    }

    public Object[] getParams() {
        if (object instanceof String) {
            return new Object[0];
        }

        if (object instanceof QueryWrapper) {
            return CPI.getValueArray((QueryWrapper) object);
        }

        if (object instanceof QueryCondition) {
            return CPI.getConditionParams((QueryCondition) object);
        }

        if (object instanceof HasParamsColumn) {
            return ((HasParamsColumn) object).getParamValues();
        }

        return new Object[0];
    }

}
