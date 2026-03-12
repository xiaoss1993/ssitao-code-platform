
package com.ssitao.code.frame.mybatisflex.core.query;

import com.ssitao.code.frame.mybatisflex.core.dialect.IDialect;

public interface WithDetail extends CloneSupport<WithDetail> {

    String toSql(IDialect dialect);

    Object[] getParamValues();

}
