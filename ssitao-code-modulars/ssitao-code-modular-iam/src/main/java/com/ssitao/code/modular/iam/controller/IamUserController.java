package com.ssitao.code.modular.iam.controller;

import com.ssitao.code.common.annotation.Log;
import com.ssitao.code.common.core.controller.BaseController;
import com.ssitao.code.common.core.domain.AjaxResult;
import com.ssitao.code.common.core.domain.Ztree;
import com.ssitao.code.common.core.domain.entity.SysDept;
import com.ssitao.code.common.core.domain.entity.SysRole;
import com.ssitao.code.common.core.domain.entity.SysUser;
import com.ssitao.code.common.core.page.TableDataInfo;
import com.ssitao.code.common.core.text.Convert;
import com.ssitao.code.common.enums.BusinessType;
import com.ssitao.code.common.exception.ServiceException;
import com.ssitao.code.common.utils.ShiroUtils;
import com.ssitao.code.common.utils.poi.ExcelUtil;
import com.ssitao.code.modular.iam.application.dto.SysUserDTO;
import com.ssitao.code.modular.iam.application.command.ChangePasswordCommand;
import com.ssitao.code.modular.iam.application.command.CreateUserCommand;
import com.ssitao.code.modular.iam.application.command.DeleteUserCommand;
import com.ssitao.code.modular.iam.application.command.UpdateUserCommand;
import com.ssitao.code.modular.iam.application.service.SysUserApplicationService;
import com.ssitao.code.modular.iam.application.service.ISysDeptService;
import com.ssitao.code.modular.iam.application.service.ISysPostService;
import com.ssitao.code.modular.iam.application.service.ISysRoleService;
import com.ssitao.code.modular.iam.application.service.ISysUserService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户信息
 *
 * 合并后的Controller，同时支持：
 * - 页面跳转：/system/user/*
 * - REST API：/api/iam/user/*
 */
@Controller
public class IamUserController extends BaseController {

    // ==================== 页面跳转相关 ====================
    private String prefix = "system/user";

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysDeptService deptService;

    @Autowired
    private ISysPostService postService;

    // ==================== REST API 相关 ====================
    @Autowired
    private SysUserApplicationService userApplicationService;

    // ==================== 页面跳转接口 ====================

    @RequiresPermissions("system:user:view")
    @GetMapping("/system/user")
    public String user() {
        return prefix + "/user";
    }

    @RequiresPermissions("system:user:add")
    @GetMapping("/system/user/add")
    public String add(ModelMap mmap) {
        mmap.put("roles", roleService.selectRoleAll().stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        mmap.put("posts", postService.selectPostAll());
        return prefix + "/add";
    }

    @RequiresPermissions("system:user:edit")
    @GetMapping("/system/user/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap mmap) {
        userService.checkUserDataScope(userId);
        List<SysRole> roles = roleService.selectRolesByUserId(userId);
        mmap.put("user", userService.selectUserById(userId));
        mmap.put("roles", ShiroUtils.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        mmap.put("posts", postService.selectPostsByUserId(userId));
        return prefix + "/edit";
    }

    @RequiresPermissions("system:user:list")
    @GetMapping("/system/user/view/{userId}")
    public String view(@PathVariable("userId") Long userId, ModelMap mmap) {
        userService.checkUserDataScope(userId);
        mmap.put("user", userService.selectUserById(userId));
        mmap.put("roleGroup", userService.selectUserRoleGroup(userId));
        mmap.put("postGroup", userService.selectUserPostGroup(userId));
        return prefix + "/view";
    }

    @RequiresPermissions("system:user:resetPwd")
    @GetMapping("/system/user/resetPwd/{userId}")
    public String resetPwd(@PathVariable("userId") Long userId, ModelMap mmap) {
        userService.checkUserDataScope(userId);
        mmap.put("user", userService.selectUserById(userId));
        return prefix + "/resetPwd";
    }

    @RequiresPermissions("system:user:edit")
    @GetMapping("/system/user/authRole/{userId}")
    public String authRole(@PathVariable("userId") Long userId, ModelMap mmap) {
        userService.checkUserDataScope(userId);
        SysUser user = userService.selectUserById(userId);
        List<SysRole> roles = roleService.selectRolesByUserId(userId);
        mmap.put("user", user);
        mmap.put("roles", ShiroUtils.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        return prefix + "/authRole";
    }

    @RequiresPermissions("system:user:list")
    @GetMapping("/system/user/selectDeptTree/{deptId}")
    public String selectDeptTree(@PathVariable("deptId") Long deptId, ModelMap mmap) {
        mmap.put("dept", deptService.selectDeptById(deptId));
        return prefix + "/deptTree";
    }

    // ==================== 传统业务接口 (返回JSON) ====================

    @RequiresPermissions("system:user:list")
    @PostMapping("/system/user/list")
    @ResponseBody
    public TableDataInfo list(SysUser user) {
        startPage();
        List<SysUser> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:user:export")
    @PostMapping("/system/user/export")
    @ResponseBody
    public AjaxResult export(SysUser user) {
        List<SysUser> list = userService.selectUserList(user);
        ExcelUtil<SysUser> util = new ExcelUtil<>(SysUser.class);
        return util.exportExcel(list, "用户数据");
    }

    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:user:import")
    @PostMapping("/system/user/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<SysUser> util = new ExcelUtil<>(SysUser.class);
        List<SysUser> userList = util.importExcel(file.getInputStream());
        String message = userService.importUser(userList, updateSupport, getLoginName());
        return AjaxResult.success(message);
    }

    @RequiresPermissions("system:user:view")
    @GetMapping("/system/user/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<SysUser> util = new ExcelUtil<>(SysUser.class);
        return util.importTemplateExcel("用户数据");
    }

    @RequiresPermissions("system:user:add")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/system/user/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysUser user) {
        user.setCreateBy(getLoginName());
        return toAjax(userService.createUser(user, user.getPassword()));
    }

    @RequiresPermissions("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/system/user/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysUser user) {
        user.setUpdateBy(getLoginName());
        return toAjax(userService.createOrUpdateUser(user));
    }

    @RequiresPermissions("system:user:resetPwd")
    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/system/user/resetPwd")
    @ResponseBody
    public AjaxResult resetPwdSave(SysUser user) {
        if (userService.createOrResetUserPwd(user, user.getPassword()) > 0) {
            if (ShiroUtils.getUserId().longValue() == user.getUserId().longValue()) {
                setSysUser(userService.selectUserById(user.getUserId()));
            }
            return success();
        }
        return error();
    }

    @RequiresPermissions("system:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.GRANT)
    @PostMapping("/system/user/authRole/insertAuthRole")
    @ResponseBody
    public AjaxResult insertAuthRole(Long userId, Long[] roleIds) {
        userService.checkUserDataScope(userId);
        roleService.checkRoleDataScope(roleIds);
        userService.insertUserAuth(userId, roleIds);
        return success();
    }

    @RequiresPermissions("system:user:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @PostMapping("/system/user/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        if (ArrayUtils.contains(Convert.toLongArray(ids), getUserId())) {
            return error("当前用户不能删除");
        }
        return toAjax(userService.deleteUserByIds(ids));
    }

    @PostMapping("/system/user/checkLoginNameUnique")
    @ResponseBody
    public boolean checkLoginNameUnique(SysUser user) {
        return userService.checkLoginNameUnique(user);
    }

    @PostMapping("/system/user/checkPhoneUnique")
    @ResponseBody
    public boolean checkPhoneUnique(SysUser user) {
        return userService.checkPhoneUnique(user);
    }

    @PostMapping("/system/user/checkEmailUnique")
    @ResponseBody
    public boolean checkEmailUnique(SysUser user) {
        return userService.checkEmailUnique(user);
    }

    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:user:edit")
    @PostMapping("/system/user/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(SysUser user) {
        return toAjax(userService.changeUserStatus(user));
    }

    @RequiresPermissions("system:user:list")
    @GetMapping("/system/user/deptTreeData")
    @ResponseBody
    public List<Ztree> deptTreeData() {
        return deptService.selectDeptTree(new SysDept());
    }

    // ==================== REST API 接口 (DDD风格) ====================

    @RequiresPermissions("system:user:list")
    @GetMapping("/api/iam/user/list")
    @ResponseBody
    public TableDataInfo apiList(SysUserDTO query) {
        startPage();
        List<SysUserDTO> list = userApplicationService.listUsers(query);
        return getDataTable(list);
    }

    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:user:export")
    @PostMapping("/api/iam/user/export")
    @ResponseBody
    public void apiExport(SysUserDTO query, HttpServletResponse response) {
        List<SysUserDTO> list = userApplicationService.listUsers(query);
        ExcelUtil<SysUserDTO> util = new ExcelUtil<>(SysUserDTO.class);
        util.exportExcel(response, list, "用户数据");
    }

    @RequiresPermissions("system:user:list")
    @GetMapping("/api/iam/user/{userId}")
    @ResponseBody
    public AjaxResult apiGetInfo(@PathVariable("userId") Long userId) {
        SysUserDTO user = userApplicationService.getUserById(userId);
        return success(user);
    }

    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:user:add")
    @PostMapping("/api/iam/user")
    @ResponseBody
    public AjaxResult apiAdd(@Validated @RequestBody CreateUserCommand command) {
        try {
            Long userId = userApplicationService.createUser(command);
            return success(userId);
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:user:edit")
    @PutMapping("/api/iam/user")
    @ResponseBody
    public AjaxResult apiEdit(@Validated @RequestBody UpdateUserCommand command) {
        try {
            userApplicationService.updateUser(command);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:user:remove")
    @DeleteMapping("/api/iam/user/{userIds}")
    @ResponseBody
    public AjaxResult apiRemove(@PathVariable Long[] userIds) {
        DeleteUserCommand command = new DeleteUserCommand();
        command.setUserIds(userIds);
        try {
            userApplicationService.deleteUsers(command);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:user:resetPwd")
    @PutMapping("/api/iam/user/password")
    @ResponseBody
    public AjaxResult apiChangePassword(@RequestBody ChangePasswordCommand command) {
        try {
            command.setUserId(ShiroUtils.getUserId());
            userApplicationService.changePassword(command);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:user:resetPwd")
    @PutMapping("/api/iam/user/resetPwd/{userId}")
    @ResponseBody
    public AjaxResult apiResetPassword(@PathVariable Long userId, @RequestParam String newPassword) {
        try {
            userApplicationService.resetPassword(userId, newPassword);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:user:edit")
    @PutMapping("/api/iam/user/status")
    @ResponseBody
    public AjaxResult apiChangeStatus(@RequestParam Long userId, @RequestParam String status) {
        try {
            userApplicationService.changeStatus(userId, status);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @GetMapping("/api/iam/user/loginName/{loginName}")
    @ResponseBody
    public AjaxResult apiGetByLoginName(@PathVariable String loginName) {
        SysUserDTO user = userApplicationService.getUserByLoginName(loginName);
        if (user == null) {
            return error("用户不存在");
        }
        return success(user);
    }
}