package com.ssitao.code.modular.iam.service.impl;

import com.ssitao.code.common.exception.NotFoundException;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.frame.satoken.api.LoginUser;
import com.ssitao.code.frame.satoken.core.SecurityUtil;
import com.ssitao.code.modular.iam.controller.vo.notice.NoticeCreateReqVO;
import com.ssitao.code.modular.iam.controller.vo.notice.NoticeListReqVO;
import com.ssitao.code.modular.iam.controller.vo.notice.NoticeUpdateReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.NoticeDO;
import com.ssitao.code.modular.iam.dal.mapper.NoticeMapper;
import com.ssitao.code.modular.iam.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 通知公告服务实现
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeMapper noticeMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createNotice(NoticeCreateReqVO createReqVO) {
        NoticeDO notice = new NoticeDO();
        BeanUtils.copyProperties(createReqVO, notice);

        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            notice.setTenantId(loginUser.getTenantId());
            notice.setCreator(loginUser.getUsername());
        }

        notice.setCreateTime(LocalDateTime.now());
        notice.setUpdateTime(LocalDateTime.now());
        notice.setDeleted(0);

        // 如果状态是已发布，设置发布时间和发布人
        if (notice.getStatus() != null && notice.getStatus() == 1) {
            notice.setPublishTime(LocalDateTime.now());
            if (loginUser != null) {
                notice.setPublisher(loginUser.getUsername());
            }
        }

        noticeMapper.insert(notice);
        return notice.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateNotice(NoticeUpdateReqVO updateReqVO) {
        NoticeDO existNotice = noticeMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("id", updateReqVO.getId())
                        .eq("deleted", 0)
        );
        if (existNotice == null) {
            throw new NotFoundException("通知公告不存在: " + updateReqVO.getId());
        }

        // 保存原状态
        Integer oldStatus = existNotice.getStatus();

        BeanUtils.copyProperties(updateReqVO, existNotice, "id", "createTime", "creator", "publishTime", "publisher");

        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            existNotice.setUpdater(loginUser.getUsername());
        }
        existNotice.setUpdateTime(LocalDateTime.now());

        // 如果状态从草稿变为已发布，设置发布时间和发布人
        if ((oldStatus == null || oldStatus == 0) && updateReqVO.getStatus() != null && updateReqVO.getStatus() == 1) {
            existNotice.setPublishTime(LocalDateTime.now());
            if (loginUser != null) {
                existNotice.setPublisher(loginUser.getUsername());
            }
        }

        noticeMapper.update(existNotice);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteNotice(Long id) {
        NoticeDO notice = noticeMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("id", id)
                        .eq("deleted", 0)
        );
        if (notice == null) {
            throw new NotFoundException("通知公告不存在: " + id);
        }

        // 逻辑删除
        notice.setDeleted(1);
        notice.setUpdateTime(LocalDateTime.now());
        noticeMapper.update(notice);
    }

    @Override
    public NoticeDO getNotice(Long id) {
        NoticeDO notice = noticeMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("id", id)
                        .eq("deleted", 0)
        );
        if (notice == null) {
            throw new NotFoundException("通知公告不存在: " + id);
        }
        return notice;
    }

    @Override
    public List<NoticeDO> getNoticeList(NoticeListReqVO reqVO) {
        QueryWrapper queryWrapper = QueryWrapper.create()
                .eq("deleted", 0);

        if (StringUtils.hasText(reqVO.getTitle())) {
            queryWrapper.like("title", reqVO.getTitle());
        }
        if (reqVO.getType() != null) {
            queryWrapper.eq("type", reqVO.getType());
        }
        if (reqVO.getStatus() != null) {
            queryWrapper.eq("status", reqVO.getStatus());
        }

        // 多租户过滤
        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null && !loginUser.getSuperAdmin()) {
            queryWrapper.eq("tenant_id", loginUser.getTenantId());
        }

        queryWrapper.orderBy("is_top", false).orderBy("publish_time", false).orderBy("id", false);
        return noticeMapper.selectListByQuery(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void publishNotice(Long id) {
        NoticeDO notice = noticeMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("id", id)
                        .eq("deleted", 0)
        );
        if (notice == null) {
            throw new NotFoundException("通知公告不存在: " + id);
        }

        notice.setStatus(1);
        notice.setPublishTime(LocalDateTime.now());

        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            notice.setPublisher(loginUser.getUsername());
        }

        notice.setUpdateTime(LocalDateTime.now());
        noticeMapper.update(notice);
    }

}
