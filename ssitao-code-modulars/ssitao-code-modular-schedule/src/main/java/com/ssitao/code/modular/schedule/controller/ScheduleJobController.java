package com.ssitao.code.modular.schedule.controller;

import com.ssitao.code.commons.controller.SimpleGenericEntityController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.ssitao.code.frame.authorization.Permission;
import com.ssitao.code.frame.authorization.annotation.Authorize;
import com.ssitao.code.commons.entity.param.QueryParamEntity;
import com.ssitao.code.commons.controller.message.ResponseMessage;
import com.ssitao.code.modular.schedule.entity.ScheduleJobEntity;
import com.ssitao.code.modular.schedule.service.ScheduleJobExecutor;
import com.ssitao.code.modular.schedule.service.ScheduleJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 调度任务
 *
 *
 */
@RestController
@RequestMapping("${tweb.web.mappings.scheduleJob:schedule/job}")
@Authorize(permission = "schedule-job", description = "定时调度管理")
@Api(value = "定时调度管理",tags = "定时调度管理")
public class ScheduleJobController implements SimpleGenericEntityController<ScheduleJobEntity, String, QueryParamEntity> {

    private ScheduleJobService scheduleJobService;

    private ScheduleJobExecutor scheduleJobExecutor;

    @Autowired
    @Authorize(ignore = true)
    public void setScheduleJobExecutor(ScheduleJobExecutor scheduleJobExecutor) {
        this.scheduleJobExecutor = scheduleJobExecutor;
    }

    @Autowired
    @Authorize(ignore = true)
    public void setScheduleJobService(ScheduleJobService scheduleJobService) {
        this.scheduleJobService = scheduleJobService;
    }

    @Override
    public ScheduleJobService getService() {
        return scheduleJobService;
    }

    @PutMapping("/{id}/enable")
    @Authorize(action = Permission.ACTION_ENABLE)
    @ApiOperation("启用任务")
    public ResponseMessage<Void> enable(@PathVariable String id) {
        scheduleJobService.enable(id);
        return ResponseMessage.ok();
    }

    @PutMapping("/{id}/disable")
    @Authorize(action = Permission.ACTION_DISABLE)
    @ApiOperation("禁用任务")
    public ResponseMessage<Void> disable(@PathVariable String id) {
        scheduleJobService.disable(id);
        return ResponseMessage.ok();
    }

    @PostMapping("/{id}/execute")
    @Authorize(action = "execute", description = "执行任务")
    @ApiOperation("执行任务")
    public ResponseMessage<Object> execute(@PathVariable String id, @RequestBody Map<String, Object> args) {
        return ResponseMessage.ok(scheduleJobExecutor.doExecuteJob(id, args));
    }
}
