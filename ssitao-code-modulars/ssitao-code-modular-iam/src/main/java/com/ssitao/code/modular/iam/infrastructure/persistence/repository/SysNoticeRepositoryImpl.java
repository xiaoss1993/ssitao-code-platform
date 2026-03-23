package com.ssitao.code.modular.iam.infrastructure.persistence.repository;

import com.ssitao.code.frame.aggregate.repository.AbstractAggregateRepository;
import com.ssitao.code.modular.iam.domain.model.SysNoticeAggregate;
import com.ssitao.code.modular.iam.domain.repository.SysNoticeRepository;
import com.ssitao.code.modular.iam.domain.SysNotice;
import com.ssitao.code.modular.iam.infrastructure.persistence.mapper.SysNoticeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 通知公告仓储实现
 */
@Repository
public class SysNoticeRepositoryImpl extends AbstractAggregateRepository<SysNoticeAggregate, Long> implements SysNoticeRepository {

    @Autowired
    private SysNoticeMapper noticeMapper;

    public SysNoticeRepositoryImpl() {
        super(SysNoticeAggregate.class);
    }

    @Override
    protected Collection<SysNoticeAggregate> doSave(Collection<SysNoticeAggregate> aggregates) {
        List<SysNoticeAggregate> saved = new ArrayList<>();
        for (SysNoticeAggregate aggregate : aggregates) {
            if (aggregate.isNew()) {
                noticeMapper.insertNotice(toEntity(aggregate));
            } else {
                noticeMapper.updateNotice(toEntity(aggregate));
            }
            saved.add(aggregate);
        }
        return saved;
    }

    @Override
    protected void doRemove(Collection<SysNoticeAggregate> aggregates) {
        List<String> ids = new ArrayList<>();
        for (SysNoticeAggregate aggregate : aggregates) {
            ids.add(String.valueOf(aggregate.getNoticeId()));
        }
        noticeMapper.deleteNoticeByIds(ids.toArray(new String[0]));
    }

    @Override
    protected SysNoticeAggregate doFindOne(Long id) {
        SysNotice notice = noticeMapper.selectNoticeById(id);
        if (notice == null) {
            return null;
        }
        return toAggregate(notice);
    }

    @Override
    protected boolean doExists(Long id) {
        return noticeMapper.selectNoticeById(id) != null;
    }

    @Override
    protected List<Long> doFindAllIds() {
        List<SysNotice> notices = noticeMapper.selectNoticeList(new SysNotice());
        List<Long> ids = new ArrayList<>();
        if (notices != null) {
            for (SysNotice notice : notices) {
                ids.add(notice.getNoticeId());
            }
        }
        return ids;
    }

    @Override
    protected List<SysNoticeAggregate> doFindAll(Collection<Long> ids) {
        List<SysNoticeAggregate> result = new ArrayList<>();
        for (Long id : ids) {
            SysNoticeAggregate aggregate = doFindOne(id);
            if (aggregate != null) {
                result.add(aggregate);
            }
        }
        return result;
    }

    @Override
    protected long doCount() {
        return noticeMapper.selectNoticeList(new SysNotice()).size();
    }

    /**
     * 将聚合根转换为实体
     */
    private SysNotice toEntity(SysNoticeAggregate aggregate) {
        SysNotice notice = new SysNotice();
        notice.setNoticeId(aggregate.getNoticeId());
        notice.setNoticeTitle(aggregate.getNoticeTitle());
        notice.setNoticeType(aggregate.getNoticeType());
        notice.setNoticeContent(aggregate.getNoticeContent());
        notice.setStatus(aggregate.getStatus());
        notice.setCreateBy(aggregate.getCreateBy());
        notice.setCreateTime(aggregate.getCreateTime());
        notice.setUpdateBy(aggregate.getUpdateBy());
        notice.setUpdateTime(aggregate.getUpdateTime());
        notice.setRemark(aggregate.getRemark());
        return notice;
    }

    /**
     * 将实体转换为聚合根
     */
    private SysNoticeAggregate toAggregate(SysNotice notice) {
        SysNoticeAggregate aggregate = new SysNoticeAggregate();
        aggregate.setNoticeId(notice.getNoticeId());
        aggregate.setNoticeTitle(notice.getNoticeTitle());
        aggregate.setNoticeType(notice.getNoticeType());
        aggregate.setNoticeContent(notice.getNoticeContent());
        aggregate.setStatus(notice.getStatus());
        aggregate.setCreateBy(notice.getCreateBy());
        aggregate.setCreateTime(notice.getCreateTime());
        aggregate.setUpdateBy(notice.getUpdateBy());
        aggregate.setUpdateTime(notice.getUpdateTime());
        aggregate.setRemark(notice.getRemark());
        return aggregate;
    }
}
