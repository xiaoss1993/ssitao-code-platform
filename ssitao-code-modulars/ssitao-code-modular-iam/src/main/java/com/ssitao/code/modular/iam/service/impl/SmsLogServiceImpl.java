package com.ssitao.code.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.frame.satoken.api.LoginUser;
import com.ssitao.code.frame.satoken.core.SecurityUtil;
import com.ssitao.code.modular.iam.controller.vo.sms.SmsLogPageReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.SmsLogDO;
import com.ssitao.code.modular.iam.dal.mapper.SmsLogMapper;
import com.ssitao.code.modular.iam.service.SmsLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 短信日志服务实现
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SmsLogServiceImpl implements SmsLogService {

    private final SmsLogMapper smsLogMapper;

    @Override
    public Long createSmsLog(SmsLogDO smsLog) {
        smsLog.setCreateTime(LocalDateTime.now());

        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            smsLog.setTenantId(loginUser.getTenantId());
        }

        smsLogMapper.insert(smsLog);
        return smsLog.getId();
    }

    @Override
    public List<SmsLogDO> getSmsLogList(SmsLogPageReqVO reqVO) {
        QueryWrapper queryWrapper = QueryWrapper.create();

        if (StringUtils.hasText(reqVO.getMobile())) {
            queryWrapper.like("mobile", reqVO.getMobile());
        }
        if (reqVO.getType() != null) {
            queryWrapper.eq("type", reqVO.getType());
        }
        if (reqVO.getResult() != null) {
            queryWrapper.eq("result", reqVO.getResult());
        }
        if (reqVO.getStartTime() != null) {
            queryWrapper.ge("create_time", reqVO.getStartTime());
        }
        if (reqVO.getEndTime() != null) {
            queryWrapper.le("create_time", reqVO.getEndTime());
        }

        // 多租户过滤
        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null && !loginUser.getSuperAdmin()) {
            queryWrapper.eq("tenant_id", loginUser.getTenantId());
        }

        queryWrapper.orderBy("create_time", false);
        return smsLogMapper.selectListByQuery(queryWrapper);
    }

}
