package com.ssitao.code.modular.iam.audit.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.iam.audit.api.dto.IamOperateLogDTO;
import com.ssitao.code.modular.iam.audit.application.query.IamOperateLogQuery;
import com.ssitao.code.modular.iam.audit.application.service.IamOperateLogAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ssitao.code.common.pojo.CommonResult.success;

/**
 * IAM审计日志控制器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Tag(name = "IAM审计日志", description = "IAM操作日志相关接口")
@RestController
@RequestMapping("/iam/audit")
public class IamAuditController {

    private final IamOperateLogAppService operateLogAppService;

    public IamAuditController(IamOperateLogAppService operateLogAppService) {
        this.operateLogAppService = operateLogAppService;
    }

    @PostMapping("/operate-log")
    @Operation(summary = "记录操作日志", description = "记录操作日志")
    public CommonResult<Long> recordLog(@RequestBody IamOperateLogQuery query) {
        Long logId = operateLogAppService.recordLog(query);
        return success(logId);
    }

    @PostMapping("/operate-log/batch")
    @Operation(summary = "批量记录操作日志", description = "批量记录操作日志")
    public CommonResult<List<Long>> recordLogBatch(@RequestBody List<IamOperateLogQuery> queries) {
        List<Long> logIds = operateLogAppService.recordLogBatch(queries);
        return success(logIds);
    }

    @GetMapping("/operate-log/{id}")
    @Operation(summary = "获取操作日志详情", description = "根据ID获取操作日志详情")
    public CommonResult<IamOperateLogDTO> getLog(@PathVariable Long id) {
        IamOperateLogDTO log = operateLogAppService.getLogById(id);
        return success(log);
    }

    @GetMapping("/operate-log/operator/{operatorId}")
    @Operation(summary = "根据操作人获取日志", description = "根据操作人ID获取操作日志")
    public CommonResult<List<IamOperateLogDTO>> getLogsByOperator(@PathVariable Long operatorId,
                                                                    @RequestParam(defaultValue = "1") int page,
                                                                    @RequestParam(defaultValue = "10") int size) {
        List<IamOperateLogDTO> logs = operateLogAppService.getLogsByOperatorId(operatorId, page, size);
        return success(logs);
    }

    @GetMapping("/operate-log/type/{operateType}")
    @Operation(summary = "根据操作类型获取日志", description = "根据操作类型获取操作日志")
    public CommonResult<List<IamOperateLogDTO>> getLogsByType(@PathVariable String operateType,
                                                               @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId,
                                                               @RequestParam(defaultValue = "1") int page,
                                                               @RequestParam(defaultValue = "10") int size) {
        List<IamOperateLogDTO> logs = operateLogAppService.getLogsByOperateType(operateType, tenantId, page, size);
        return success(logs);
    }

    @GetMapping("/operate-log/module/{operateModule}")
    @Operation(summary = "根据操作模块获取日志", description = "根据操作模块获取操作日志")
    public CommonResult<List<IamOperateLogDTO>> getLogsByModule(@PathVariable String operateModule,
                                                                 @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId,
                                                                 @RequestParam(defaultValue = "1") int page,
                                                                 @RequestParam(defaultValue = "10") int size) {
        List<IamOperateLogDTO> logs = operateLogAppService.getLogsByOperateModule(operateModule, tenantId, page, size);
        return success(logs);
    }

    @PostMapping("/operate-log/query")
    @Operation(summary = "查询操作日志", description = "根据条件查询操作日志")
    public CommonResult<List<IamOperateLogDTO>> queryLogs(@RequestBody IamOperateLogQuery query,
                                                           @RequestParam(defaultValue = "1") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        List<IamOperateLogDTO> logs = operateLogAppService.queryLogs(query, page, size);
        return success(logs);
    }

    @PostMapping("/operate-log/count")
    @Operation(summary = "统计操作日志数量", description = "根据条件统计操作日志数量")
    public CommonResult<Long> countLogs(@RequestBody IamOperateLogQuery query) {
        Long count = operateLogAppService.countLogs(query);
        return success(count);
    }

    @DeleteMapping("/operate-log/before/{days}")
    @Operation(summary = "删除历史日志", description = "删除指定天数之前的操作日志")
    public CommonResult<Void> deleteLogsBeforeDays(@PathVariable Integer days,
                                                    @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        operateLogAppService.deleteLogsBeforeDays(days, tenantId);
        return success();
    }

}
