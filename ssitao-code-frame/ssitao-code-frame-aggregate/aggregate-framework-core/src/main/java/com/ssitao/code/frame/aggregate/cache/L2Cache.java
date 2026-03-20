package com.ssitao.code.frame.aggregate.cache;

import com.ssitao.code.frame.aggregate.entity.AggregateRoot;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by changming.xie on 9/14/17.
 */
public interface L2Cache<T extends AggregateRoot<ID>, ID extends Serializable> {

    void remove(Collection<T> entities);

    void write(Collection<T> entities);

    T findOne(Class<T> clazz, ID id);

    Collection<T> findAll(Class<T> aggregateType, Collection<ID> ids);
}
