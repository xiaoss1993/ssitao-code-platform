package com.ssitao.code.modular.iam.authorization.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.iam.identity.api.dto.IamAccountDTO;
import com.ssitao.code.modular.iam.authorization.api.dto.IamRoleDTO;
import com.ssitao.code.modular.iam.authorization.application.service.IamAccountRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ssitao.code.common.pojo.CommonResult.success;

/**
 * IAM账号角色关联管理控制器
 * 管理 tb_iam_accountrole 表，处理账号与角色的关联关系
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Tag(name = "IAM账号角色关联", description = "IAM账号与角色关联管理接口")
@RestController
@RequestMapping("/iam/account-role")
public class IamAccountRoleController {

    private final IamAccountRoleService accountRoleService;

    public IamAccountRoleController(IamAccountRoleService accountRoleService) {
        this.accountRoleService = accountRoleService;
    }

    // ==================== 账号角色关联管理接口 ====================

    @PostMapping("/assign")
    @Operation(summary = "为账号分配角色", description = "为账号分配一个或多个角色")
    public CommonResult<Void> assignRoles(@RequestParam String accountId,
                                           @RequestBody List<String> roleIds,
                                           @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        accountRoleService.assignRoles(accountId, roleIds, tenantId);
        return success();
    }

    @PostMapping("/revoke")
    @Operation(summary = "撤销账号角色", description = "撤销账号的一个或多个角色")
    public CommonResult<Void> revokeRoles(@RequestParam String accountId,
                                          @RequestBody List<String> roleIds,
                                          @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        accountRoleService.revokeRoles(accountId, roleIds, tenantId);
        return success();
    }

    @PostMapping("/revoke-all")
    @Operation(summary = "撤销账号所有角色", description = "撤销账号的所有角色")
    public CommonResult<Void> revokeAllRoles(@RequestParam String accountId,
                                              @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        accountRoleService.revokeAllRoles(accountId, tenantId);
        return success();
    }

    @GetMapping("/account/{accountId}")
    @Operation(summary = "获取账号的角色列表", description = "获取指定账号的所有角色")
    public CommonResult<List<IamRoleDTO>> getAccountRoles(@PathVariable String accountId,
                                                           @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<IamRoleDTO> roles = accountRoleService.getAccountRoles(accountId, tenantId);
        return success(roles);
    }

    @GetMapping("/role/{roleId}")
    @Operation(summary = "获取角色下的账号列表", description = "获取指定角色下的所有账号")
    public CommonResult<List<IamAccountDTO>> getRoleAccounts(@PathVariable String roleId,
                                                              @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        List<IamAccountDTO> accounts = accountRoleService.getRoleAccounts(roleId, tenantId);
        return success(accounts);
    }

    @PostMapping("/check")
    @Operation(summary = "检查账号是否有指定角色", description = "检查账号是否拥有指定角色")
    public CommonResult<Boolean> checkRole(@RequestParam String accountId,
                                           @RequestParam String roleId,
                                           @RequestHeader(value = "tenantId", defaultValue = "default") String tenantId) {
        Boolean hasRole = accountRoleService.checkRole(accountId, roleId, tenantId);
        return success(hasRole);
    }

}
