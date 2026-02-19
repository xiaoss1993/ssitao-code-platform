package com.ssitao.code.modular.iam.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.ssitao.code.frame.satoken.api.LoginUser;
import com.ssitao.code.frame.satoken.core.SecurityUtil;
import com.ssitao.code.modular.iam.controller.vo.online.OnlineUserRespVO;
import com.ssitao.code.modular.iam.service.OnlineUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 在线用户服务实现
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OnlineUserServiceImpl implements OnlineUserService {

    @Override
    public List<OnlineUserRespVO> getOnlineUsers() {
        List<OnlineUserRespVO> onlineUsers = new ArrayList<>();

        // Sa-Token 没有直接获取所有token的API
        // 这里简化实现：只获取当前会话信息
        // 实际生产环境可能需要维护自己的在线用户列表或通过数据库查询

        try {
            LoginUser currentUser = SecurityUtil.getLoginUser();
            if (currentUser != null) {
                OnlineUserRespVO vo = new OnlineUserRespVO();
                vo.setUserId(currentUser.getId());
                vo.setUsername(currentUser.getUsername());
                vo.setNickname(currentUser.getNickname());
                vo.setAvatar(currentUser.getAvatar());
                vo.setDeptId(currentUser.getDeptId());
                vo.setToken(StpUtil.getTokenValue());
                vo.setLastActiveTime(LocalDateTime.now());
                onlineUsers.add(vo);
            }
        } catch (Exception e) {
            log.warn("获取在线用户信息失败", e);
        }

        return onlineUsers;
    }

    @Override
    public void forceLogout(Long userId) {
        // 根据用户ID踢出所有登录
        StpUtil.kickout(userId);
    }

    @Override
    public void forceLogoutByToken(String token) {
        // 根据Token踢出指定会话
        try {
            Object loginId = StpUtil.getLoginIdByToken(token);
            if (loginId != null) {
                StpUtil.kickout(loginId);
            }
        } catch (Exception e) {
            log.warn("踢出用户失败: token={}", token, e);
        }
    }

}
