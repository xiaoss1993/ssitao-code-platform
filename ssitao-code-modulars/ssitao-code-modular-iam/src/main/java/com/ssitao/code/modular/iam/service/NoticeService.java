package com.ssitao.code.modular.iam.service;

import com.ssitao.code.modular.iam.controller.vo.notice.NoticeCreateReqVO;
import com.ssitao.code.modular.iam.controller.vo.notice.NoticeListReqVO;
import com.ssitao.code.modular.iam.controller.vo.notice.NoticeUpdateReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.NoticeDO;

import java.util.List;

/**
 * 通知公告服务接口
 *
 * @author ssitao-code
 * @since 1.1.1
 */
public interface NoticeService {

    /**
     * 创建通知公告
     *
     * @param createReqVO 创建请求
     * @return 公告ID
     */
    Long createNotice(NoticeCreateReqVO createReqVO);

    /**
     * 更新通知公告
     *
     * @param updateReqVO 更新请求
     */
    void updateNotice(NoticeUpdateReqVO updateReqVO);

    /**
     * 删除通知公告
     *
     * @param id 公告ID
     */
    void deleteNotice(Long id);

    /**
     * 获取通知公告详情
     *
     * @param id 公告ID
     * @return 通知公告
     */
    NoticeDO getNotice(Long id);

    /**
     * 获取通知公告列表
     *
     * @param reqVO 查询请求
     * @return 通知公告列表
     */
    List<NoticeDO> getNoticeList(NoticeListReqVO reqVO);

    /**
     * 发布通知公告
     *
     * @param id 公告ID
     */
    void publishNotice(Long id);

}
