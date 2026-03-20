package com.ssitao.code.modular.iam.api.controller;

import com.ssitao.code.common.annotation.Log;
import com.ssitao.code.common.core.controller.BaseController;
import com.ssitao.code.common.core.domain.AjaxResult;
import com.ssitao.code.common.core.page.TableDataInfo;
import com.ssitao.code.common.enums.BusinessType;
import com.ssitao.code.common.exception.ServiceException;
import com.ssitao.code.common.utils.ShiroUtils;
import com.ssitao.code.common.utils.StringUtils;
import com.ssitao.code.common.utils.poi.ExcelUtil;
import com.ssitao.code.modular.iam.api.dto.SysUserDTO;
import com.ssitao.code.modular.iam.application.command.ChangePasswordCommand;
import com.ssitao.code.modular.iam.application.command.CreateUserCommand;
import com.ssitao.code.modular.iam.application.command.DeleteUserCommand;
import com.ssitao.code.modular.iam.application.command.UpdateUserCommand;
import com.ssitao.code.modular.iam.application.service.SysUserApplicationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * DDD架构下的用户管理控制器
 *
 * 采用分层架构：
 * - API层：接收HTTP请求，返回HTTP响应
 * - 应用层：处理业务用例，协调领域对象
 * - 领域层：核心业务逻辑，领域模型
 * - 基础设施层：持久化、外部服务调用
 */
@RestController
@RequestMapping("/api/iam/user")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserApplicationService userApplicationService;

    /**
     * 查询用户列表
     */
    @RequiresPermissions("system:user:list")
    @GetMapping("/list")
    public TableDataInfo list(SysUserDTO query) {
        startPage();
        List<SysUserDTO> list = userApplicationService.listUsers(query);
        return getDataTable(list);
    }

    /**
     * 导出用户列表
     */
    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:user:export")
    @PostMapping("/export")
    public void export(SysUserDTO query, HttpServletResponse response) {
        List<SysUserDTO> list = userApplicationService.listUsers(query);
        ExcelUtil<SysUserDTO> util = new ExcelUtil<>(SysUserDTO.class);
        util.exportExcel(response, list, "用户数据");
    }

    /**
     * 获取用户详细信息
     */
    @RequiresPermissions("system:user:list")
    @GetMapping("/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId) {
        SysUserDTO user = userApplicationService.getUserById(userId);
        return success(user);
    }

    /**
     * 新增用户
     */
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:user:add")
    @PostMapping
    public AjaxResult add(@Validated @RequestBody CreateUserCommand command) {
        try {
            Long userId = userApplicationService.createUser(command);
            return success(userId);
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    /**
     * 修改用户
     */
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:user:edit")
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody UpdateUserCommand command) {
        try {
            userApplicationService.updateUser(command);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    /**
     * 删除用户
     */
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:user:remove")
    @DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds) {
        DeleteUserCommand command = new DeleteUserCommand();
        command.setUserIds(userIds);
        try {
            userApplicationService.deleteUsers(command);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    /**
     * 修改密码
     */
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:user:resetPwd")
    @PutMapping("/password")
    public AjaxResult changePassword(@RequestBody ChangePasswordCommand command) {
        try {
            // 设置当前用户ID
            command.setUserId(ShiroUtils.getUserId());
            userApplicationService.changePassword(command);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    /**
     * 重置用户密码
     */
    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:user:resetPwd")
    @PutMapping("/resetPwd/{userId}")
    public AjaxResult resetPassword(@PathVariable Long userId, @RequestParam String newPassword) {
        try {
            userApplicationService.resetPassword(userId, newPassword);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    /**
     * 修改用户状态
     */
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:user:edit")
    @PutMapping("/status")
    public AjaxResult changeStatus(@RequestParam Long userId, @RequestParam String status) {
        try {
            userApplicationService.changeStatus(userId, status);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    /**
     * 根据登录名获取用户信息
     */
    @GetMapping("/loginName/{loginName}")
    public AjaxResult getByLoginName(@PathVariable String loginName) {
        SysUserDTO user = userApplicationService.getUserByLoginName(loginName);
        if (user == null) {
            return error("用户不存在");
        }
        return success(user);
    }
}
