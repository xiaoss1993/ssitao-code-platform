
package com.ssitao.code.frame.mybatisflex.core.query;

import com.ssitao.code.frame.mybatisflex.core.FlexConsts;
import com.ssitao.code.frame.mybatisflex.core.dialect.IDialect;
import com.ssitao.code.frame.mybatisflex.core.exception.FlexExceptions;
import com.ssitao.code.frame.mybatisflex.core.util.ArrayUtil;
import com.ssitao.code.frame.mybatisflex.core.util.CollectionUtil;

import java.util.ArrayList;
import java.util.List;

import static com.ssitao.code.frame.mybatisflex.core.constant.SqlConsts.*;

public class With implements CloneSupport<With> {

    private boolean recursive;
    private List<WithItem> withItems;

    public With() {
    }

    public With(boolean recursive) {
        this.recursive = recursive;
    }

    public boolean isRecursive() {
        return recursive;
    }

    public void setRecursive(boolean recursive) {
        this.recursive = recursive;
    }

    public List<WithItem> getWithItems() {
        return withItems;
    }

    public void setWithItems(List<WithItem> withItems) {
        this.withItems = withItems;
    }

    public void addWithItem(WithItem withItem) {
        if (withItems == null) {
            withItems = new ArrayList<>();
        }
        withItems.add(withItem);
    }

    public String toSql(IDialect dialect) {
        StringBuilder sql = new StringBuilder(WITH);
        if (recursive) {
            sql.append(RECURSIVE);
        }
        for (int i = 0; i < withItems.size(); i++) {
            sql.append(withItems.get(i).toSql(dialect));
            if (i != withItems.size() - 1) {
                sql.append(DELIMITER);
            }
        }
        return sql.append(BLANK).toString();
    }

    public Object[] getParamValues() {
        Object[] paramValues = FlexConsts.EMPTY_ARRAY;
        for (WithItem withItem : withItems) {
            paramValues = ArrayUtil.concat(paramValues, withItem.getParamValues());
        }
        return paramValues;
    }

    @Override
    public With clone() {
        try {
            With clone = (With) super.clone();
            // deep clone ...
            clone.withItems = CollectionUtil.cloneArrayList(this.withItems);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw FlexExceptions.wrap(e);
        }
    }

}
