package com.ssitao.code.frame.aggregate.basic.usage.entity;


import lombok.*;
import com.ssitao.code.frame.aggregate.basic.usage.event.AccountCreateEvent;
import com.ssitao.code.frame.aggregate.entity.AbstractSimpleAggregateRoot;
import com.ssitao.code.frame.aggregate.entity.DaoAwareQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nervose.Wu
 * @date 2023/6/26 14:11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account extends AbstractSimpleAggregateRoot<Long> {

    private Long id;

    private String accountId;

    private Integer eventStatus;

    @DaoAwareQuery(mappedBy = "account", select = "findByParentId")
    private List<SubAccount> subAccounts = new ArrayList<>();

    public void applyAccountCreateEvent(){
        apply(new AccountCreateEvent(accountId));
    }
}
