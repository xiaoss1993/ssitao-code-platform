package com.ssitao.code.modular.dashboard.service;

import com.ssitao.code.frame.authorization.Authentication;
import com.ssitao.code.modular.dashboard.service.DashBoardConfigEntity;

public interface DashBoardExecutorStrategy {

    boolean support(DashBoardConfigEntity entity);

    Object execute(DashBoardConfigEntity entity, Authentication authentication);
}
