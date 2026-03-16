package com.ssitao.code.modular.schedule.service;

import com.ssitao.code.modular.schedule.entity.ScheduleJobEntity;
import com.ssitao.code.commons.service.CrudService;

/**
 * 调度任务 服务类
 *
 *
 */
public interface ScheduleJobService extends CrudService<ScheduleJobEntity, String> {

    void enable(String id);

    void disable(String id);
}
