package com.ssitao.code.frame.aggregate.sample.hierarchicalmodel.command.domain.repository;

import com.ssitao.code.frame.aggregate.repository.DaoAwareAggregateRepository;
import com.ssitao.code.frame.aggregate.sample.hierarchicalmodel.command.domain.entity.JobOrder;

/**
 * Created by changming.xie on 3/30/16.
 */
public class JobOrderRepository extends DaoAwareAggregateRepository<JobOrder, Integer> {
    protected JobOrderRepository(Class<JobOrder> aggregateType) {
        super(aggregateType);
    }

    public JobOrderRepository() {
        this(JobOrder.class);
    }
}
