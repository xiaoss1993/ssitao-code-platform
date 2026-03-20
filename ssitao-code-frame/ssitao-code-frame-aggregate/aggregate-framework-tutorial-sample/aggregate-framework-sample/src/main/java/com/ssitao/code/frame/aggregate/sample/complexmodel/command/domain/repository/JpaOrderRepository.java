package com.ssitao.code.frame.aggregate.sample.complexmodel.command.domain.repository;

import com.ssitao.code.frame.aggregate.repository.DaoAwareAggregateRepository;
import com.ssitao.code.frame.aggregate.sample.complexmodel.command.domain.entity.BookingOrder;
import com.ssitao.code.frame.aggregate.sample.complexmodel.command.domain.entity.UserShardingId;
import org.springframework.stereotype.Repository;

@Repository
public class JpaOrderRepository extends DaoAwareAggregateRepository<BookingOrder, UserShardingId> {

    public JpaOrderRepository() {
        this(BookingOrder.class);
    }

    protected JpaOrderRepository(Class<BookingOrder> aggregateType) {
        super(aggregateType);
    }
}
