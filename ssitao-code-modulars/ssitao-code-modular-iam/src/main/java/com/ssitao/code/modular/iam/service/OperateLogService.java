package com.ssitao.code.modular.iam.service;

import com.ssitao.code.modular.iam.controller.vo.log.OperateLogPageReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.OperateLogDO;

import java.util.List;

/**
 * 操作日志服务接口
 *
 * @author ssitao-code
 * @since 1.1.1
 */
public interface OperateLogService {

    /**
     * 创建操作日志
     *
     * @param operateLog 操作日志
     * @return 日志ID
     */
    Long createOperateLog(OperateLogDO operateLog);

    /**
     * 获取操作日志分页
     *
     * @param reqVO 查询请求
     * @return 操作日志列表
     */
    List<OperateLogDO> getOperateLogList(OperateLogPageReqVO reqVO);

}
