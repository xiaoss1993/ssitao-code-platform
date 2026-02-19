package com.ssitao.code.modular.iam.service;

import com.ssitao.code.modular.iam.controller.vo.log.LoginLogPageReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.LoginLogDO;

import java.util.List;

/**
 * 登录日志服务接口
 *
 * @author ssitao-code
 * @since 1.1.1
 */
public interface LoginLogService {

    /**
     * 创建登录日志
     *
     * @param loginLog 登录日志
     * @return 日志ID
     */
    Long createLoginLog(LoginLogDO loginLog);

    /**
     * 获取登录日志列表
     *
     * @param reqVO 查询请求
     * @return 登录日志列表
     */
    List<LoginLogDO> getLoginLogList(LoginLogPageReqVO reqVO);

}
