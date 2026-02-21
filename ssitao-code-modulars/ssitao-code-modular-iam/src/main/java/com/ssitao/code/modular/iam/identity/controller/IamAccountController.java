package com.ssitao.code.modular.iam.identity.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.iam.identity.api.dto.IamAccountDTO;
import com.ssitao.code.modular.iam.identity.application.command.IamAccountCreateCommand;
import com.ssitao.code.modular.iam.identity.application.command.IamAccountUpdateCommand;
import com.ssitao.code.modular.iam.identity.application.command.IamPasswordChangeCommand;
import com.ssitao.code.modular.iam.identity.application.command.IamPasswordResetCommand;
import com.ssitao.code.modular.iam.identity.application.query.IamAccountQuery;
import com.ssitao.code.modular.iam.identity.application.service.IamAccountAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.ssitao.code.common.pojo.CommonResult.success;

/**
 * IAM账号管理控制器
 * 基于 tb_iam_account 表，管理登录账号（账号名、密码、认证等）
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Tag(name = "IAM账号管理", description = "IAM账号相关接口，基于 tb_iam_account 表")
@RestController
@RequestMapping("/iam/account")
public class IamAccountController {

    private final IamAccountAppService accountAppService;

    public IamAccountController(IamAccountAppService accountAppService) {
        this.accountAppService = accountAppService;
    }

    // ==================== 账号 CRUD 接口 ====================

    @PostMapping
    @Operation(summary = "创建账号", description = "创建新登录账号")
    public CommonResult<String> createAccount(@Valid @RequestBody IamAccountCreateCommand command) {
        String accountId = accountAppService.createAccount(command);
        return success(accountId);
    }

    @PutMapping
    @Operation(summary = "更新账号", description = "更新账号信息")
    public CommonResult<Void> updateAccount(@Valid @RequestBody IamAccountUpdateCommand command) {
        accountAppService.updateAccount(command);
        return success();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除账号", description = "删除指定账号")
    public CommonResult<Void> deleteAccount(@PathVariable String id,
                                             @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        accountAppService.deleteAccount(id, tenantId);
        return success();
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取账号详情", description = "根据ID获取账号详情")
    public CommonResult<IamAccountDTO> getAccount(@PathVariable String id,
                                                   @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        IamAccountDTO account = accountAppService.getAccount(id, tenantId);
        return success(account);
    }

    @GetMapping("/code/{accountCode}")
    @Operation(summary = "根据账号编码获取账号", description = "根据账号编码获取账号信息")
    public CommonResult<IamAccountDTO> getAccountByCode(@PathVariable String accountCode,
                                                         @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        IamAccountDTO account = accountAppService.getAccountByCode(accountCode, tenantId);
        return success(account);
    }

    @PostMapping("/list")
    @Operation(summary = "查询账号列表", description = "根据条件查询账号列表")
    public CommonResult<List<IamAccountDTO>> listAccounts(@RequestBody IamAccountQuery query) {
        List<IamAccountDTO> accounts = accountAppService.listAccounts(query);
        return success(accounts);
    }

    @PostMapping("/page")
    @Operation(summary = "分页查询账号", description = "分页查询账号列表")
    public CommonResult<List<IamAccountDTO>> pageAccounts(@RequestBody IamAccountQuery query,
                                                           @RequestParam(defaultValue = "1") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        List<IamAccountDTO> accounts = accountAppService.pageAccounts(query, page, size);
        return success(accounts);
    }

    // ==================== 账号状态管理接口 ====================

    @PutMapping("/{id}/lock")
    @Operation(summary = "锁定账号", description = "锁定指定账号")
    public CommonResult<Void> lockAccount(@PathVariable String id,
                                          @RequestParam(required = false) Integer expireDays,
                                          @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        accountAppService.lockAccount(id, expireDays, tenantId);
        return success();
    }

    @PutMapping("/{id}/unlock")
    @Operation(summary = "解锁账号", description = "解锁指定账号")
    public CommonResult<Void> unlockAccount(@PathVariable String id,
                                            @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        accountAppService.unlockAccount(id, tenantId);
        return success();
    }

    @PutMapping("/{id}/disable")
    @Operation(summary = "禁用账号", description = "禁用指定账号")
    public CommonResult<Void> disableAccount(@PathVariable String id,
                                             @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        accountAppService.disableAccount(id, tenantId);
        return success();
    }

    @PutMapping("/{id}/enable")
    @Operation(summary = "启用账号", description = "启用指定账号")
    public CommonResult<Void> enableAccount(@PathVariable String id,
                                            @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        accountAppService.enableAccount(id, tenantId);
        return success();
    }

    // ==================== 密码管理接口 ====================

    @PostMapping("/change-password")
    @Operation(summary = "修改密码", description = "用户修改自己的密码")
    public CommonResult<Void> changePassword(@Valid @RequestBody IamPasswordChangeCommand command) {
        accountAppService.changePassword(command);
        return success();
    }

    @PostMapping("/reset-password")
    @Operation(summary = "重置密码", description = "管理员重置用户密码")
    public CommonResult<Void> resetPassword(@Valid @RequestBody IamPasswordResetCommand command) {
        accountAppService.resetPassword(command);
        return success();
    }

    @PutMapping("/{id}/reset-password")
    @Operation(summary = "重置指定用户密码", description = "管理员重置指定用户的密码")
    public CommonResult<Void> resetPasswordById(@PathVariable String id,
                                                @RequestParam String newPassword,
                                                @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        accountAppService.resetPasswordById(id, newPassword, tenantId);
        return success();
    }

    // ==================== 账号关联接口 ====================

    @PutMapping("/{id}/bind-user")
    @Operation(summary = "绑定用户档案", description = "为账号绑定用户档案")
    public CommonResult<Void> bindUser(@PathVariable String id,
                                       @RequestParam String userId,
                                       @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        accountAppService.bindUser(id, userId, tenantId);
        return success();
    }

    @PutMapping("/{id}/unbind-user")
    @Operation(summary = "解绑用户档案", description = "为账号解绑用户档案")
    public CommonResult<Void> unbindUser(@PathVariable String id,
                                         @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        accountAppService.unbindUser(id, tenantId);
        return success();
    }

    @GetMapping("/{id}/user")
    @Operation(summary = "获取账号绑定的用户档案", description = "获取账号绑定的用户档案信息")
    public CommonResult<Object> getBoundUser(@PathVariable String id,
                                             @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        Object user = accountAppService.getBoundUser(id, tenantId);
        return success(user);
    }

}
