
package com.ssitao.code.frame.mybatisflex.core.query;

import com.ssitao.code.frame.mybatisflex.core.dialect.IDialect;
import com.ssitao.code.frame.mybatisflex.core.exception.FlexExceptions;
import com.ssitao.code.frame.mybatisflex.core.util.CollectionUtil;
import com.ssitao.code.frame.mybatisflex.core.util.ObjectUtil;
import com.ssitao.code.frame.mybatisflex.core.util.StringUtil;

import java.util.List;

import static com.ssitao.code.frame.mybatisflex.core.constant.SqlConsts.*;

public class WithItem implements CloneSupport<WithItem> {

    private String name;
    private List<String> params;

    private WithDetail withDetail;

    public WithItem() {
    }

    public WithItem(String name, List<String> params) {
        this.name = name;
        this.params = params;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }

    public WithDetail getWithDetail() {
        return withDetail;
    }

    public void setWithDetail(WithDetail withDetail) {
        this.withDetail = withDetail;
    }

    public String toSql(IDialect dialect) {
        StringBuilder sql = new StringBuilder(name);
        if (CollectionUtil.isNotEmpty(params)) {
            sql.append(BRACKET_LEFT).append(StringUtil.join(DELIMITER, params)).append(BRACKET_RIGHT);
        }
        sql.append(AS).append(BRACKET_LEFT);
        sql.append(withDetail.toSql(dialect));
        return sql.append(BRACKET_RIGHT).toString();
    }

    public Object[] getParamValues() {
        return withDetail.getParamValues();
    }

    @Override
    public WithItem clone() {
        try {
            WithItem clone = (WithItem) super.clone();
            // deep clone ...
            clone.withDetail = ObjectUtil.clone(this.withDetail);
            clone.params = CollectionUtil.newArrayList(this.params);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw FlexExceptions.wrap(e);
        }
    }

}
