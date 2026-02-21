
package com.ssitao.code.frame.mybatisflex.core.query;

import com.ssitao.code.frame.mybatisflex.core.constant.SqlConsts;
import com.ssitao.code.frame.mybatisflex.core.dialect.IDialect;
import com.ssitao.code.frame.mybatisflex.core.dialect.OperateType;
import com.ssitao.code.frame.mybatisflex.core.exception.FlexExceptions;
import com.ssitao.code.frame.mybatisflex.core.util.ObjectUtil;

import java.util.List;
import java.util.function.Supplier;

/**
 * @author ssitao yang (fuhai999@gmail.com)
 * @Date: 2020/1/14
 */
public class Join implements CloneSupport<Join> {

    private static final long serialVersionUID = 1L;


    protected final String type;
    protected QueryTable queryTable;
    protected QueryCondition on;
    protected boolean effective;

    public Join(String type, QueryTable table, boolean when) {
        this.type = type;
        this.queryTable = table;
        this.effective = when;
    }

    public Join(String type, QueryWrapper queryWrapper, boolean when) {
        this.type = type;
        this.queryTable = new SelectQueryTable(queryWrapper);
        this.effective = when;
    }


    QueryTable getQueryTable() {
        return queryTable;
    }


    public void on(QueryCondition condition) {
        replaceConditionColumn(condition);
        this.on = condition;
    }

    private void replaceConditionColumn(QueryCondition condition) {
        if (condition != null) {
            if (condition.checkEffective() && condition.column != null) {
                QueryTable table = condition.column.getTable();
                if (queryTable.isSameTable(table)) {
                    QueryColumn newColumn = condition.column.clone();
                    newColumn.table.alias = queryTable.alias;
                    condition.column = newColumn;
                }
            } else if (condition instanceof Brackets) {
                replaceConditionColumn(((Brackets) condition).getChildCondition());
            } else if (condition instanceof OperatorQueryCondition) {
                replaceConditionColumn(((OperatorQueryCondition) condition).getChildCondition());
            } else if (condition instanceof OperatorSelectCondition) {
                QueryWrapper qw = ((OperatorSelectCondition) condition).getQueryWrapper();
                replaceConditionColumn(qw.whereQueryCondition);
            }
            replaceConditionColumn(condition.next);
        }
    }


    QueryCondition getOnCondition() {
        return on;
    }

    public boolean checkEffective() {
        return effective;
    }

    public void when(boolean when) {
        this.effective = when;
    }

    public void when(Supplier<Boolean> fn) {
        this.effective = fn.get();
    }

    public String toSql(List<QueryTable> queryTables, IDialect dialect, OperateType operateType) {
        return type + queryTable.toSql(dialect, operateType) + SqlConsts.ON + on.toSql(queryTables, dialect);
    }

    @Override
    public Join clone() {
        try {
            Join clone = (Join) super.clone();
            // deep clone ...
            clone.queryTable = ObjectUtil.clone(this.queryTable);
            clone.on = ObjectUtil.clone(this.on);
            return clone;
        } catch (CloneNotSupportedException e) {
            throw FlexExceptions.wrap(e);
        }
    }

}
