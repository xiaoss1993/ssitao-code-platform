package com.ssitao.code.modular.iam.application.service;

import com.ssitao.code.common.exception.ServiceException;
import com.ssitao.code.common.utils.ShiroUtils;
import com.ssitao.code.modular.iam.application.dto.SysNoticeDTO;
import com.ssitao.code.modular.iam.application.command.CreateNoticeCommand;
import com.ssitao.code.modular.iam.application.command.DeleteNoticeCommand;
import com.ssitao.code.modular.iam.application.command.UpdateNoticeCommand;
import com.ssitao.code.modular.iam.domain.SysNotice;
import com.ssitao.code.modular.iam.domain.model.SysNoticeAggregate;
import com.ssitao.code.modular.iam.domain.repository.SysNoticeRepository;
import com.ssitao.code.modular.iam.infrastructure.converter.SysNoticeConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 通知公告应用服务
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SysNoticeApplicationService {

    private final SysNoticeRepository noticeRepository;
    private final SysNoticeConverter noticeConverter;
    private final ISysNoticeService noticeService;

    /**
     * 查询通知公告列表
     */
    public List<SysNoticeDTO> listNotices(SysNoticeDTO query) {
        SysNotice notice = new SysNotice();
        if (query != null && query.getNoticeTitle() != null) {
            notice.setNoticeTitle(query.getNoticeTitle());
        }
        if (query != null && query.getNoticeType() != null) {
            notice.setNoticeType(query.getNoticeType());
        }
        if (query != null && query.getStatus() != null) {
            notice.setStatus(query.getStatus());
        }
        List<SysNotice> list = noticeService.selectNoticeList(notice);
        return noticeConverter.toDTOListFromNotice(list);
    }

    /**
     * 根据ID查询通知公告
     */
    public SysNoticeDTO getNoticeById(Long noticeId) {
        SysNoticeAggregate aggregate = noticeRepository.findOne(noticeId);
        if (aggregate == null) {
            throw new ServiceException("通知公告不存在");
        }
        return noticeConverter.toDTO(aggregate);
    }

    /**
     * 创建通知公告
     */
    @Transactional
    public Long createNotice(CreateNoticeCommand command) {
        // 创建通知公告聚合根
        SysNoticeAggregate aggregate = new SysNoticeAggregate();
        aggregate.createNotice(
                command.getNoticeTitle(),
                command.getNoticeType(),
                command.getNoticeContent(),
                command.getStatus(),
                ShiroUtils.getLoginName()
        );

        // 保存
        noticeRepository.save(aggregate);

        log.info("创建通知公告: {}", aggregate.getNoticeId());
        return aggregate.getNoticeId();
    }

    /**
     * 更新通知公告
     */
    @Transactional
    public void updateNotice(UpdateNoticeCommand command) {
        SysNoticeAggregate aggregate = noticeRepository.findOne(command.getNoticeId());
        if (aggregate == null) {
            throw new ServiceException("通知公告不存在");
        }

        // 更新通知公告信息
        aggregate.updateNotice(
                command.getNoticeTitle(),
                command.getNoticeType(),
                command.getNoticeContent(),
                command.getStatus(),
                ShiroUtils.getLoginName()
        );

        // 保存
        noticeRepository.save(aggregate);

        log.info("更新通知公告: {}", command.getNoticeId());
    }

    /**
     * 删除通知公告
     */
    @Transactional
    public void deleteNotices(DeleteNoticeCommand command) {
        for (Long noticeId : command.getNoticeIds()) {
            SysNoticeAggregate aggregate = noticeRepository.findOne(noticeId);
            if (aggregate != null) {
                noticeRepository.delete(aggregate);
                log.info("删除通知公告: {}", noticeId);
            }
        }
    }

    /**
     * 修改通知公告状态
     */
    @Transactional
    public void changeStatus(Long noticeId, String status) {
        SysNoticeAggregate aggregate = noticeRepository.findOne(noticeId);
        if (aggregate == null) {
            throw new ServiceException("通知公告不存在");
        }

        aggregate.changeStatus(status, ShiroUtils.getLoginName());
        noticeRepository.save(aggregate);

        log.info("修改通知公告状态: {} -> {}", noticeId, status);
    }
}
