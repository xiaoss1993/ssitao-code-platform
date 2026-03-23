package com.ssitao.code.modular.iam.domain.repository;

import com.ssitao.code.frame.aggregate.repository.AggregateRepository;
import com.ssitao.code.modular.iam.domain.model.SysNoticeAggregate;

/**
 * 通知公告仓储接口
 */
public interface SysNoticeRepository extends AggregateRepository<SysNoticeAggregate, Long> {
}
