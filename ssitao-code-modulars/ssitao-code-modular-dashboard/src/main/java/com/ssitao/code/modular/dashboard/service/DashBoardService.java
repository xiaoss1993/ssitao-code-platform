package com.ssitao.code.modular.dashboard.service;

import com.ssitao.code.commons.service.CrudService;

import java.util.List;

public interface DashBoardService extends CrudService<DashBoardConfigEntity, String> {

    List<DashBoardConfigEntity> selectAllDefaults();
}
