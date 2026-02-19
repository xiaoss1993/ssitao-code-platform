package com.ssitao.code.modular.iam.service;

import com.ssitao.code.modular.iam.controller.vo.sms.SmsLogPageReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.SmsLogDO;

import java.util.List;

/**
 * 短信日志服务接口
 *
 * @author ssitao-code
 * @since 1.1.1
 */
public interface SmsLogService {

    /**
     * 创建短信日志
     *
     * @param smsLog 短信日志
     * @return 日志ID
     */
    Long createSmsLog(SmsLogDO smsLog);

    /**
     * 获取短信日志列表
     *
     * @param reqVO 查询请求
     * @return 短信日志列表
     */
    List<SmsLogDO> getSmsLogList(SmsLogPageReqVO reqVO);

}
