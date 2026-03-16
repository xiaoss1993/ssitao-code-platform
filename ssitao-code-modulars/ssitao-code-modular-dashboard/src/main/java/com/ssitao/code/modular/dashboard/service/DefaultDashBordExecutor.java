package com.ssitao.code.modular.dashboard.service;

import com.ssitao.code.frame.authorization.Authentication;
import com.ssitao.code.frame.authorization.AuthenticationPredicate;
import com.ssitao.code.modular.dashboard.service.executor.DashBoardExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public class DefaultDashBordExecutor implements DashBoardExecutor {

    @Autowired
    private List<DashBoardExecutorStrategy> strategies;

    @Override
    public Object execute(DashBoardConfigEntity entity, Authentication authentication) {

        if (entity == null) {
            return null;
        }
        if (StringUtils.hasText(entity.getPermission())) {
            AuthenticationPredicate.has(entity.getPermission()).assertHas(authentication);
        }

        return strategies.stream()
                .filter(strategy -> strategy.support(entity))
                .findFirst()
                .map(strategy -> strategy.execute(entity, authentication))
                .orElse(null);
    }
}
