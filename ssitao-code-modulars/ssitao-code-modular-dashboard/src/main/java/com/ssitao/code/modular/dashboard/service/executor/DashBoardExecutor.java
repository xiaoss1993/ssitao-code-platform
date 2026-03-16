package com.ssitao.code.modular.dashboard.service.executor;

import com.ssitao.code.frame.authorization.Authentication;
import com.ssitao.code.modular.dashboard.service.DashBoardConfigEntity;

public interface DashBoardExecutor {
    Object execute(DashBoardConfigEntity entity, Authentication authentication);
}
