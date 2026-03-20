package com.ssitao.code.frame.aggregate.session;

import com.ssitao.code.frame.aggregate.cache.IdentifiedEntityMap;

/**
 * Created by changmingxie on 7/17/15.
 */
public class AggregateContext {

    private IdentifiedEntityMap entityMap = new IdentifiedEntityMap();
    private boolean isAggregateChanged;

    public IdentifiedEntityMap getEntityMap() {
        return entityMap;
    }

    public boolean isAggregateChanged() {
        return isAggregateChanged;
    }

    public void setAggregateChanged(boolean isAggregateDirty) {
        this.isAggregateChanged = isAggregateDirty;
    }
}
