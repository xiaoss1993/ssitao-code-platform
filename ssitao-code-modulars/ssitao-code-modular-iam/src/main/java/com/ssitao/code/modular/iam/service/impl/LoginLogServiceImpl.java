package com.ssitao.code.modular.iam.service.impl;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.frame.satoken.api.LoginUser;
import com.ssitao.code.frame.satoken.core.SecurityUtil;
import com.ssitao.code.modular.iam.controller.vo.log.LoginLogPageReqVO;
import com.ssitao.code.modular.iam.dal.dataobject.LoginLogDO;
import com.ssitao.code.modular.iam.dal.mapper.LoginLogMapper;
import com.ssitao.code.modular.iam.service.LoginLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 登录日志服务实现
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginLogServiceImpl implements LoginLogService {

    private final LoginLogMapper loginLogMapper;

    @Override
    public Long createLoginLog(LoginLogDO loginLog) {
        loginLog.setCreateTime(LocalDateTime.now());

        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            loginLog.setTenantId(loginUser.getTenantId());
        }

        loginLogMapper.insert(loginLog);
        return loginLog.getId();
    }

    @Override
    public List<LoginLogDO> getLoginLogList(LoginLogPageReqVO reqVO) {
        QueryWrapper queryWrapper = QueryWrapper.create();

        if (StringUtils.hasText(reqVO.getUsername())) {
            queryWrapper.like("username", reqVO.getUsername());
        }
        if (reqVO.getLoginType() != null) {
            queryWrapper.eq("login_type", reqVO.getLoginType());
        }
        if (reqVO.getResult() != null) {
            queryWrapper.eq("result", reqVO.getResult());
        }
        if (reqVO.getStartTime() != null) {
            queryWrapper.ge("login_time", reqVO.getStartTime());
        }
        if (reqVO.getEndTime() != null) {
            queryWrapper.le("login_time", reqVO.getEndTime());
        }

        // 多租户过滤
        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null && !loginUser.getSuperAdmin()) {
            queryWrapper.eq("tenant_id", loginUser.getTenantId());
        }

        queryWrapper.orderBy("login_time", false);
        return loginLogMapper.selectListByQuery(queryWrapper);
    }

}
