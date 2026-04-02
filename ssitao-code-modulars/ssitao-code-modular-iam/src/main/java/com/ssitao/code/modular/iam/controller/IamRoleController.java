package com.ssitao.code.modular.iam.controller;

import com.ssitao.code.common.annotation.Log;
import com.ssitao.code.common.core.controller.BaseController;
import com.ssitao.code.common.core.domain.AjaxResult;
import com.ssitao.code.common.core.domain.Ztree;
import com.ssitao.code.common.core.domain.entity.SysRole;
import com.ssitao.code.common.core.domain.entity.SysUser;
import com.ssitao.code.common.core.page.TableDataInfo;
import com.ssitao.code.common.enums.BusinessType;
import com.ssitao.code.common.exception.ServiceException;
import com.ssitao.code.common.utils.poi.ExcelUtil;
import com.ssitao.code.modular.iam.application.dto.SysRoleDTO;
import com.ssitao.code.modular.iam.application.command.CreateRoleCommand;
import com.ssitao.code.modular.iam.application.command.DeleteRoleCommand;
import com.ssitao.code.modular.iam.application.command.UpdateRoleCommand;
import com.ssitao.code.modular.iam.application.service.SysRoleApplicationService;
import com.ssitao.code.modular.iam.domain.SysUserRole;
import com.ssitao.code.modular.iam.application.service.ISysDeptService;
import com.ssitao.code.modular.iam.application.service.ISysRoleService;
import com.ssitao.code.modular.iam.application.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 角色信息
 *
 * 合并后的Controller，同时支持：
 * - 页面跳转：/system/role/*
 * - REST API：/api/iam/role/*
 */
@Controller
public class IamRoleController extends BaseController {

    private String prefix = "system/role";

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysDeptService deptService;

    @Autowired
    private SysRoleApplicationService roleApplicationService;

    // ==================== 页面跳转接口 ====================

    @RequiresPermissions("system:role:view")
    @GetMapping("/system/role")
    public String role() {
        return prefix + "/role";
    }

    @RequiresPermissions("system:role:add")
    @GetMapping("/system/role/add")
    public String add() {
        return prefix + "/add";
    }

    @RequiresPermissions("system:role:edit")
    @GetMapping("/system/role/edit/{roleId}")
    public String edit(@PathVariable("roleId") Long roleId, ModelMap mmap) {
        roleService.checkRoleDataScope(roleId);
        mmap.put("role", roleService.selectRoleById(roleId));
        return prefix + "/edit";
    }

    @RequiresPermissions("system:role:edit")
    @GetMapping("/system/role/authDataScope/{roleId}")
    public String authDataScope(@PathVariable("roleId") Long roleId, ModelMap mmap) {
        roleService.checkRoleDataScope(roleId);
        mmap.put("role", roleService.selectRoleById(roleId));
        return prefix + "/dataScope";
    }

    @RequiresPermissions("system:role:edit")
    @GetMapping("/system/role/authUser/{roleId}")
    public String authUser(@PathVariable("roleId") Long roleId, ModelMap mmap) {
        roleService.checkRoleDataScope(roleId);
        mmap.put("role", roleService.selectRoleById(roleId));
        return prefix + "/authUser";
    }

    @GetMapping("/system/role/selectMenuTree")
    public String selectMenuTree() {
        return prefix + "/tree";
    }

    @RequiresPermissions("system:role:list")
    @GetMapping("/system/role/authUser/selectUser/{roleId}")
    public String selectUser(@PathVariable("roleId") Long roleId, ModelMap mmap) {
        mmap.put("role", roleService.selectRoleById(roleId));
        return prefix + "/selectUser";
    }

    // ==================== 传统业务接口 (返回JSON) ====================

    @RequiresPermissions("system:role:list")
    @PostMapping("/system/role/list")
    @ResponseBody
    public TableDataInfo list(SysRole role) {
        startPage();
        List<SysRole> list = roleService.selectRoleList(role);
        return getDataTable(list);
    }

    @Log(title = "角色管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:role:export")
    @PostMapping("/system/role/export")
    @ResponseBody
    public AjaxResult export(SysRole role) {
        List<SysRole> list = roleService.selectRoleList(role);
        ExcelUtil<SysRole> util = new ExcelUtil<>(SysRole.class);
        return util.exportExcel(list, "角色数据");
    }

    @Log(title = "角色管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:role:import")
    @PostMapping("/system/role/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<SysRole> util = new ExcelUtil<>(SysRole.class);
        List<SysRole> roleList = util.importExcel(file.getInputStream());
        String message = roleService.importRole(roleList, updateSupport, getLoginName());
        return AjaxResult.success(message);
    }

    @RequiresPermissions("system:role:view")
    @GetMapping("/system/role/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() {
        ExcelUtil<SysRole> util = new ExcelUtil<>(SysRole.class);
        return util.importTemplateExcel("角色数据");
    }

    @Log(title = "角色管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:role:add")
    @PostMapping("/system/role/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysRole role) {
        role.setCreateBy(getLoginName());
        return toAjax(roleService.createRole(role));
    }

    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:role:edit")
    @PostMapping("/system/role/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysRole role) {
        role.setUpdateBy(getLoginName());
        return toAjax(roleService.createOrUpdateRole(role));
    }

    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:role:edit")
    @PostMapping("/system/role/authDataScope")
    @ResponseBody
    public AjaxResult authDataScopeSave(SysRole role) {
        role.setUpdateBy(getLoginName());
        if (roleService.createOrUpdateDataScope(role) > 0) {
            setSysUser(userService.selectUserById(getUserId()));
            return success();
        }
        return error();
    }

    @Log(title = "角色管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:dept:remove")
    @PostMapping("/system/role/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(roleService.deleteRoleByIds(ids));
    }

    @PostMapping("/system/role/checkRoleNameUnique")
    @ResponseBody
    public boolean checkRoleNameUnique(SysRole role) {
        return roleService.checkRoleNameUnique(role);
    }

    @PostMapping("/system/role/checkRoleKeyUnique")
    @ResponseBody
    public boolean checkRoleKeyUnique(SysRole role) {
        return roleService.checkRoleKeyUnique(role);
    }

    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:role:edit")
    @PostMapping("/system/role/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(SysRole role) {
        return toAjax(roleService.createOrChangeStatus(role));
    }

    @RequiresPermissions("system:role:list")
    @PostMapping("/system/role/authUser/allocatedList")
    @ResponseBody
    public TableDataInfo allocatedList(SysUser user) {
        startPage();
        List<SysUser> list = userService.selectAllocatedList(user);
        return getDataTable(list);
    }

    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @RequiresPermissions("system:role:edit")
    @PostMapping("/system/role/authUser/cancel")
    @ResponseBody
    public AjaxResult cancelAuthUser(SysUserRole userRole) {
        return toAjax(roleService.deleteAuthUser(userRole));
    }

    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @RequiresPermissions("system:role:edit")
    @PostMapping("/system/role/authUser/cancelAll")
    @ResponseBody
    public AjaxResult cancelAuthUserAll(Long roleId, String userIds) {
        return toAjax(roleService.deleteAuthUsers(roleId, userIds));
    }

    @RequiresPermissions("system:role:list")
    @PostMapping("/system/role/authUser/unallocatedList")
    @ResponseBody
    public TableDataInfo unallocatedList(SysUser user) {
        startPage();
        List<SysUser> list = userService.selectUnallocatedList(user);
        return getDataTable(list);
    }

    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @RequiresPermissions("system:role:edit")
    @PostMapping("/system/role/authUser/selectAll")
    @ResponseBody
    public AjaxResult selectAuthUserAll(Long roleId, String userIds) {
        roleService.checkRoleDataScope(roleId);
        return toAjax(roleService.insertAuthUsers(roleId, userIds));
    }

    @RequiresPermissions("system:role:edit")
    @GetMapping("/system/role/deptTreeData")
    @ResponseBody
    public List<Ztree> deptTreeData(SysRole role) {
        return deptService.roleDeptTreeData(role);
    }

    // ==================== REST API 接口 (DDD风格) ====================

    @RequiresPermissions("system:role:list")
    @GetMapping("/api/iam/role/list")
    @ResponseBody
    public TableDataInfo apiList(SysRoleDTO query) {
        startPage();
        List<SysRoleDTO> list = roleApplicationService.listRoles(query);
        return getDataTable(list);
    }

    @Log(title = "角色管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:role:export")
    @PostMapping("/api/iam/role/export")
    @ResponseBody
    public void apiExport(SysRoleDTO query, HttpServletResponse response) {
        List<SysRoleDTO> list = roleApplicationService.listRoles(query);
        ExcelUtil<SysRoleDTO> util = new ExcelUtil<>(SysRoleDTO.class);
        util.exportExcel(response, list, "角色数据");
    }

    @RequiresPermissions("system:role:list")
    @GetMapping("/api/iam/role/{roleId}")
    @ResponseBody
    public AjaxResult apiGetInfo(@PathVariable("roleId") Long roleId) {
        SysRoleDTO role = roleApplicationService.getRoleById(roleId);
        return success(role);
    }

    @Log(title = "角色管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:role:add")
    @PostMapping("/api/iam/role")
    @ResponseBody
    public AjaxResult apiAdd(@Validated @RequestBody CreateRoleCommand command) {
        try {
            Long roleId = roleApplicationService.createRole(command);
            return success(roleId);
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:role:edit")
    @PutMapping("/api/iam/role")
    @ResponseBody
    public AjaxResult apiEdit(@Validated @RequestBody UpdateRoleCommand command) {
        try {
            roleApplicationService.updateRole(command);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @Log(title = "角色管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:role:remove")
    @DeleteMapping("/api/iam/role/{roleIds}")
    @ResponseBody
    public AjaxResult apiRemove(@PathVariable Long[] roleIds) {
        DeleteRoleCommand command = new DeleteRoleCommand();
        command.setRoleIds(roleIds);
        try {
            roleApplicationService.deleteRoles(command);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:role:edit")
    @PutMapping("/api/iam/role/status")
    @ResponseBody
    public AjaxResult apiChangeStatus(@RequestParam Long roleId, @RequestParam String status) {
        try {
            roleApplicationService.changeStatus(roleId, status);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @RequiresPermissions("system:role:edit")
    @PutMapping("/api/iam/role/menus")
    @ResponseBody
    public AjaxResult apiAssignMenus(@RequestParam Long roleId, @RequestParam Long[] menuIds) {
        try {
            roleApplicationService.assignMenus(roleId, menuIds);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @GetMapping("/api/iam/role/roleKey/{roleKey}")
    @ResponseBody
    public AjaxResult apiGetByRoleKey(@PathVariable String roleKey) {
        SysRoleDTO role = roleApplicationService.getRoleByRoleKey(roleKey);
        if (role == null) {
            return error("角色不存在");
        }
        return success(role);
    }
}