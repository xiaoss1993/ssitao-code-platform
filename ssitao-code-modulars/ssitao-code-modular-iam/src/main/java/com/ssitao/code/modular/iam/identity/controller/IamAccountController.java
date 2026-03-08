package com.ssitao.code.modular.iam.identity.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cn.dev33.satoken.stp.StpUtil;
import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.common.pojo.PageResult;
import com.ssitao.code.modular.iam.identity.api.dto.IamAccountDTO;
import com.ssitao.code.modular.iam.identity.application.command.IamAccountCreateCommand;
import com.ssitao.code.modular.iam.identity.application.command.IamAccountUpdateCommand;
import com.ssitao.code.modular.iam.identity.application.command.IamPasswordChangeCommand;
import com.ssitao.code.modular.iam.identity.application.command.IamPasswordResetCommand;
import com.ssitao.code.modular.iam.identity.application.query.IamAccountQuery;
import com.ssitao.code.modular.iam.identity.application.service.IamAccountAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@Controller
@RequestMapping("/iam/account")
public class IamAccountController {

    private final IamAccountAppService accountAppService;
    private final ObjectMapper objectMapper;

    public IamAccountController(IamAccountAppService accountAppService, ObjectMapper objectMapper) {
        this.accountAppService = accountAppService;
        this.objectMapper = objectMapper;
    }

    // ==================== 页面跳转 ====================

    /**
     * 账号管理页面
     */
    @GetMapping
    @Operation(summary = "账号管理页面")
    public String accountPage(Model model) {
        addCommonModel(model, "账号管理", "account");
        return "iam/account";
    }

    /**
     * 账号添加页面
     */
    @GetMapping("/add")
    @Operation(summary = "账号添加页面")
    public String accountAddPage(Model model) {
        addCommonModel(model, "添加账号", "account");
        return "iam/account-edit";
    }

    /**
     * 账号编辑页面
     */
    @GetMapping("/edit")
    @Operation(summary = "账号编辑页面")
    public String accountEditPage(Model model,
                                  @RequestParam(required = false) String id,
                                  @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        addCommonModel(model, "编辑账号", "account");

        // 如果有 ID，获取数据并转换为 JSON
        if (id != null && !id.isEmpty()) {
            try {
                IamAccountDTO account = accountAppService.getAccount(id, tenantId);
                if (account != null) {
                    String jsonData = objectMapper.writeValueAsString(account);
                    model.addAttribute("rowData", jsonData);
                }
            } catch (JsonProcessingException e) {
                // 忽略转换错误
            }
        }

        return "iam/account-edit";
    }

    // ==================== 账号 CRUD 接口 ====================

    @PostMapping
    @Operation(summary = "创建账号", description = "创建新登录账号")
    @ResponseBody
    public CommonResult<String> createAccount(@Valid @RequestBody IamAccountCreateCommand command) {
        String accountId = accountAppService.createAccount(command);
        return success(accountId);
    }

    @PutMapping
    @Operation(summary = "更新账号", description = "更新账号信息")
    @ResponseBody
    public CommonResult<Void> updateAccount(@Valid @RequestBody IamAccountUpdateCommand command) {
        accountAppService.updateAccount(command);
        return success();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除账号", description = "删除指定账号")
    @ResponseBody
    public CommonResult<Void> deleteAccount(@PathVariable String id,
                                             @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        accountAppService.deleteAccount(id, tenantId);
        return success();
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取账号详情", description = "根据ID获取账号详情")
    @ResponseBody
    public CommonResult<IamAccountDTO> getAccount(@PathVariable String id,
                                                   @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        IamAccountDTO account = accountAppService.getAccount(id, tenantId);
        return success(account);
    }

    @GetMapping("/code/{accountCode}")
    @Operation(summary = "根据账号编码获取账号", description = "根据账号编码获取账号信息")
    @ResponseBody
    public CommonResult<IamAccountDTO> getAccountByCode(@PathVariable String accountCode,
                                                         @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        IamAccountDTO account = accountAppService.getAccountByCode(accountCode, tenantId);
        return success(account);
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询账号", description = "分页查询账号列表")
    @ResponseBody
    public CommonResult<PageResult<IamAccountDTO>> pageAccounts(IamAccountQuery query,
                                                            @RequestParam(defaultValue = "1") int page,
                                                            @RequestParam(defaultValue = "10") int size,
                                                            @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        query.setTenantId(tenantId);
        PageResult<IamAccountDTO> pageResult = accountAppService.pageAccounts(query, page, size);
        return success(pageResult);
    }

    @PostMapping("/change-password")
    @Operation(summary = "修改密码", description = "修改账号密码")
    @ResponseBody
    public CommonResult<Void> changePassword(@Valid @RequestBody IamPasswordChangeCommand command) {
        accountAppService.changePassword(command);
        return success();
    }

    @PostMapping("/reset-password")
    @Operation(summary = "重置密码", description = "重置账号密码")
    @ResponseBody
    public CommonResult<Void> resetPassword(@Valid @RequestBody IamPasswordResetCommand command) {
        accountAppService.resetPassword(command);
        return success();
    }

    @PutMapping("/{id}/enable")
    @Operation(summary = "启用账号", description = "启用指定账号")
    @ResponseBody
    public CommonResult<Void> enableAccount(@PathVariable String id,
                                            @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        accountAppService.enableAccount(id, tenantId);
        return success();
    }

    @PutMapping("/{id}/disable")
    @Operation(summary = "禁用账号", description = "禁用指定账号")
    @ResponseBody
    public CommonResult<Void> disableAccount(@PathVariable String id,
                                             @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        accountAppService.disableAccount(id, tenantId);
        return success();
    }

    @GetMapping("/{id}/user")
    @Operation(summary = "获取账号关联用户", description = "获取账号关联的用户信息")
    @ResponseBody
    public CommonResult<Object> getAccountUser(@PathVariable String id,
                                               @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        Object user = accountAppService.getBoundUser(id, tenantId);
        return success(user);
    }

    // ==================== 通用方法 ====================

    /**
     * 添加通用模板变量
     */
    private void addCommonModel(Model model, String title, String controllerName) {
        model.addAttribute("title", title);
        model.addAttribute("controllerName", controllerName);
        model.addAttribute("moduleName", "iam");

        if (StpUtil.isLogin()) {
            model.addAttribute("isLogin", true);
            model.addAttribute("userId", StpUtil.getLoginId());
            model.addAttribute("userName", StpUtil.getLoginIdAsString());
        } else {
            model.addAttribute("isLogin", false);
        }
    }

}
