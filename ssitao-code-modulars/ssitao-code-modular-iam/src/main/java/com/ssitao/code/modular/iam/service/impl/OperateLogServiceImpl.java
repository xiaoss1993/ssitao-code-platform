package com.ssitao.code.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.frame.satoken.api.LoginUser;
import com.ssitao.code.frame.satoken.core.SecurityUtil;
import com.ssitao.code.modular.iam.controller.vo.log.OperateLogPageReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.OperateLogDO;
import com.ssitao.code.modular.iam.dal.mapper.OperateLogMapper;
import com.ssitao.code.modular.iam.service.OperateLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 操作日志服务实现
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OperateLogServiceImpl implements OperateLogService {

    private final OperateLogMapper operateLogMapper;

    @Override
    public Long createOperateLog(OperateLogDO operateLog) {
        operateLog.setCreateTime(LocalDateTime.now());

        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            operateLog.setTenantId(loginUser.getTenantId());
            if (operateLog.getUserId() == null) {
                operateLog.setUserId(loginUser.getId());
            }
            if (!StringUtils.hasText(operateLog.getUsername())) {
                operateLog.setUsername(loginUser.getUsername());
            }
        }

        operateLogMapper.insert(operateLog);
        return operateLog.getId();
    }

    @Override
    public List<OperateLogDO> getOperateLogList(OperateLogPageReqVO reqVO) {
        QueryWrapper queryWrapper = QueryWrapper.create();

        if (StringUtils.hasText(reqVO.getUsername())) {
            queryWrapper.like("username", reqVO.getUsername());
        }
        if (StringUtils.hasText(reqVO.getModule())) {
            queryWrapper.like("module", reqVO.getModule());
        }
        if (StringUtils.hasText(reqVO.getName())) {
            queryWrapper.like("name", reqVO.getName());
        }
        if (reqVO.getType() != null) {
            queryWrapper.eq("type", reqVO.getType());
        }
        if (reqVO.getReturnCode() != null) {
            queryWrapper.eq("return_code", reqVO.getReturnCode());
        }
        if (reqVO.getStartTime() != null) {
            queryWrapper.ge("operate_time", reqVO.getStartTime());
        }
        if (reqVO.getEndTime() != null) {
            queryWrapper.le("operate_time", reqVO.getEndTime());
        }

        // 多租户过滤
        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null && !loginUser.getSuperAdmin()) {
            queryWrapper.eq("tenant_id", loginUser.getTenantId());
        }

        queryWrapper.orderBy("operate_time", false);
        return operateLogMapper.selectListByQuery(queryWrapper);
    }

}
