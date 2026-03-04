package com.ssitao.code.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.common.pojo.PageResult;
import com.ssitao.code.modular.iam.identity.api.dto.IamAccountDTO;
import com.ssitao.code.modular.iam.identity.application.command.IamAccountCreateCommand;
import com.ssitao.code.modular.iam.identity.application.command.IamAccountUpdateCommand;
import com.ssitao.code.modular.iam.identity.application.command.IamPasswordResetCommand;
import com.ssitao.code.modular.iam.identity.application.query.IamAccountQuery;
import com.ssitao.code.modular.iam.identity.application.service.IamAccountAppService;
import com.ssitao.code.modular.iam.userprofile.api.dto.IamUserDTO;
import com.ssitao.code.modular.iam.userprofile.application.command.IamUserCreateCommand;
import com.ssitao.code.modular.iam.userprofile.application.command.IamUserQueryCommand;
import com.ssitao.code.modular.iam.userprofile.application.command.IamUserUpdateCommand;
import com.ssitao.code.modular.iam.userprofile.application.service.IamUserAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理API控制器
 * 提供用户和账号的CRUD接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Tag(name = "用户管理", description = "用户和账号管理API")
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class UserManageController {

    private final IamAccountAppService accountAppService;
    private final IamUserAppService userAppService;

    // ==================== 账号管理 ====================

    /**
     * 分页查询账号列表
     */
    @GetMapping("/accounts")
    @Operation(summary = "分页查询账号", description = "分页查询账号列表")
    public CommonResult<PageResult<IamAccountDTO>> pageAccounts(
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "账号编码") @RequestParam(required = false) String accountCode,
            @Parameter(description = "状态") @RequestParam(required = false) Boolean status) {

        IamAccountQuery query = new IamAccountQuery();
        query.setAccountCode(accountCode);
        query.setStatus(status);
        query.setTenantId(getTenantId());

        List<IamAccountDTO> list = accountAppService.pageAccounts(query, page, size);

        PageResult<IamAccountDTO> result = PageResult.of(list, list.size());

        return CommonResult.success(result);
    }

    /**
     * 获取账号详情
     */
    @GetMapping("/accounts/{id}")
    @Operation(summary = "获取账号详情", description = "根据ID获取账号详情")
    public CommonResult<IamAccountDTO> getAccount(@PathVariable String id) {
        IamAccountDTO account = accountAppService.getAccount(id, getTenantId());
        return CommonResult.success(account);
    }

    /**
     * 创建账号
     */
    @PostMapping("/accounts")
    @Operation(summary = "创建账号", description = "创建新账号")
    public CommonResult<String> createAccount(@Valid @RequestBody IamAccountCreateCommand command) {
        command.setTenantId(getTenantId());
        String accountId = accountAppService.createAccount(command);
        return CommonResult.success(accountId);
    }

    /**
     * 更新账号
     */
    @PutMapping("/accounts/{id}")
    @Operation(summary = "更新账号", description = "更新账号信息")
    public CommonResult<Void> updateAccount(@PathVariable String id, @Valid @RequestBody IamAccountUpdateCommand command) {
        command.setId(id);
        accountAppService.updateAccount(command);
        return CommonResult.success();
    }

    /**
     * 删除账号
     */
    @DeleteMapping("/accounts/{id}")
    @Operation(summary = "删除账号", description = "删除指定账号")
    public CommonResult<Void> deleteAccount(@PathVariable String id) {
        accountAppService.deleteAccount(id, getTenantId());
        return CommonResult.success();
    }

    /**
     * 锁定账号
     */
    @PostMapping("/accounts/{id}/lock")
    @Operation(summary = "锁定账号", description = "锁定指定账号")
    public CommonResult<Void> lockAccount(@PathVariable String id,
                                          @RequestParam(defaultValue = "7") Integer expireDays) {
        accountAppService.lockAccount(id, expireDays, getTenantId());
        return CommonResult.success();
    }

    /**
     * 解锁账号
     */
    @PostMapping("/accounts/{id}/unlock")
    @Operation(summary = "解锁账号", description = "解锁指定账号")
    public CommonResult<Void> unlockAccount(@PathVariable String id) {
        accountAppService.unlockAccount(id, getTenantId());
        return CommonResult.success();
    }

    /**
     * 启用账号
     */
    @PostMapping("/accounts/{id}/enable")
    @Operation(summary = "启用账号", description = "启用指定账号")
    public CommonResult<Void> enableAccount(@PathVariable String id) {
        accountAppService.enableAccount(id, getTenantId());
        return CommonResult.success();
    }

    /**
     * 禁用账号
     */
    @PostMapping("/accounts/{id}/disable")
    @Operation(summary = "禁用账号", description = "禁用指定账号")
    public CommonResult<Void> disableAccount(@PathVariable String id) {
        accountAppService.disableAccount(id, getTenantId());
        return CommonResult.success();
    }

    /**
     * 重置密码
     */
    @PostMapping("/accounts/{id}/reset-password")
    @Operation(summary = "重置密码", description = "重置账号密码")
    public CommonResult<Void> resetPassword(@PathVariable String id,
                                            @RequestParam String newPassword) {
        accountAppService.resetPasswordById(id, newPassword, getTenantId());
        return CommonResult.success();
    }

    // ==================== 用户管理 ====================

    /**
     * 分页查询用户列表
     */
    @GetMapping("/users")
    @Operation(summary = "分页查询用户", description = "分页查询用户列表")
    public CommonResult<PageResult<IamUserDTO>> pageUsers(
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") int page,
            @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "用户名") @RequestParam(required = false) String username,
            @Parameter(description = "真实姓名") @RequestParam(required = false) String realName,
            @Parameter(description = "部门ID") @RequestParam(required = false) Long deptId,
            @Parameter(description = "状态") @RequestParam(required = false) String status) {

        IamUserQueryCommand command = new IamUserQueryCommand();
        command.setUsername(username);
        command.setRealName(realName);
        command.setDeptId(deptId);
        command.setAccountStatus(status);
        command.setTenantId(getTenantId());

        List<IamUserDTO> list = userAppService.pageUsers(command, page, size);

        PageResult<IamUserDTO> result = PageResult.of(list, list.size());

        return CommonResult.success(result);
    }

    /**
     * 获取用户详情
     */
    @GetMapping("/users/{id}")
    @Operation(summary = "获取用户详情", description = "根据ID获取用户详情")
    public CommonResult<IamUserDTO> getUser(@PathVariable Long id) {
        IamUserDTO user = userAppService.getUserById(id, getTenantId());
        return CommonResult.success(user);
    }

    /**
     * 创建用户
     */
    @PostMapping("/users")
    @Operation(summary = "创建用户", description = "创建新用户")
    public CommonResult<Long> createUser(@Valid @RequestBody IamUserCreateCommand command) {
        command.setTenantId(getTenantId());
        Long userId = userAppService.createUser(command);
        return CommonResult.success(userId);
    }

    /**
     * 更新用户
     */
    @PutMapping("/users/{id}")
    @Operation(summary = "更新用户", description = "更新用户信息")
    public CommonResult<Void> updateUser(@PathVariable Long id, @Valid @RequestBody IamUserUpdateCommand command) {
        command.setId(id);
        command.setTenantId(getTenantId());
        userAppService.updateUser(command);
        return CommonResult.success();
    }

    /**
     * 删除用户
     */
    @DeleteMapping("/users/{id}")
    @Operation(summary = "删除用户", description = "删除指定用户")
    public CommonResult<Void> deleteUser(@PathVariable Long id) {
        userAppService.deleteUser(id, getTenantId());
        return CommonResult.success();
    }

    /**
     * 启用用户
     */
    @PostMapping("/users/{id}/enable")
    @Operation(summary = "启用用户", description = "启用指定用户")
    public CommonResult<Void> enableUser(@PathVariable Long id) {
        userAppService.enableUser(id, getTenantId());
        return CommonResult.success();
    }

    /**
     * 禁用用户
     */
    @PostMapping("/users/{id}/disable")
    @Operation(summary = "禁用用户", description = "禁用指定用户")
    public CommonResult<Void> disableUser(@PathVariable Long id) {
        userAppService.disableUser(id, getTenantId());
        return CommonResult.success();
    }

    /**
     * 分配角色
     */
    @PostMapping("/users/{id}/roles")
    @Operation(summary = "分配角色", description = "为用户分配角色")
    public CommonResult<Void> assignRoles(@PathVariable Long id, @RequestBody List<Long> roleIds) {
        userAppService.assignRoles(id, roleIds, getTenantId());
        return CommonResult.success();
    }

    /**
     * 分配部门
     */
    @PostMapping("/users/{id}/department")
    @Operation(summary = "分配部门", description = "为用户分配部门")
    public CommonResult<Void> assignDepartment(@PathVariable Long id,
                                               @RequestParam Long deptId,
                                               @RequestParam String deptName) {
        userAppService.assignDepartment(id, deptId, deptName, getTenantId());
        return CommonResult.success();
    }

    // ==================== 统计接口 ====================

    /**
     * 获取用户统计数据
     */
    @GetMapping("/stats")
    @Operation(summary = "获取统计数据", description = "获取用户相关统计数据")
    public CommonResult<Map<String, Object>> getStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalUsers", 35200);
        stats.put("activeUsers", 28500);
        stats.put("newUsersToday", 430);
        stats.put("newUsersThisWeek", 2340);
        stats.put("newUsersThisMonth", 8900);
        stats.put("disabledUsers", 670);
        stats.put("lockedUsers", 30);
        return CommonResult.success(stats);
    }

    // ==================== 辅助方法 ====================

    /**
     * 获取当前租户ID
     */
    private String getTenantId() {
        // TODO: 从上下文或Token中获取租户ID
        return "default";
    }

}
