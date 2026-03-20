package com.ssitao.code.modular.iam.api.controller;

import com.ssitao.code.common.annotation.Log;
import com.ssitao.code.common.core.controller.BaseController;
import com.ssitao.code.common.core.domain.AjaxResult;
import com.ssitao.code.common.core.page.TableDataInfo;
import com.ssitao.code.common.enums.BusinessType;
import com.ssitao.code.common.exception.ServiceException;
import com.ssitao.code.common.utils.poi.ExcelUtil;
import com.ssitao.code.modular.iam.api.dto.SysRoleDTO;
import com.ssitao.code.modular.iam.application.command.CreateRoleCommand;
import com.ssitao.code.modular.iam.application.command.DeleteRoleCommand;
import com.ssitao.code.modular.iam.application.command.UpdateRoleCommand;
import com.ssitao.code.modular.iam.application.service.SysRoleApplicationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * DDD架构下的角色管理控制器
 */
@RestController
@RequestMapping("/api/iam/role")
public class SysRoleController extends BaseController {

    @Autowired
    private SysRoleApplicationService roleApplicationService;

    /**
     * 查询角色列表
     */
    @RequiresPermissions("system:role:list")
    @GetMapping("/list")
    public TableDataInfo list(SysRoleDTO query) {
        startPage();
        List<SysRoleDTO> list = roleApplicationService.listRoles(query);
        return getDataTable(list);
    }

    /**
     * 导出角色列表
     */
    @Log(title = "角色管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("system:role:export")
    @PostMapping("/export")
    public void export(SysRoleDTO query, HttpServletResponse response) {
        List<SysRoleDTO> list = roleApplicationService.listRoles(query);
        ExcelUtil<SysRoleDTO> util = new ExcelUtil<>(SysRoleDTO.class);
        util.exportExcel(response, list, "角色数据");
    }

    /**
     * 获取角色详细信息
     */
    @RequiresPermissions("system:role:list")
    @GetMapping("/{roleId}")
    public AjaxResult getInfo(@PathVariable("roleId") Long roleId) {
        SysRoleDTO role = roleApplicationService.getRoleById(roleId);
        return success(role);
    }

    /**
     * 新增角色
     */
    @Log(title = "角色管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:role:add")
    @PostMapping
    public AjaxResult add(@Validated @RequestBody CreateRoleCommand command) {
        try {
            Long roleId = roleApplicationService.createRole(command);
            return success(roleId);
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    /**
     * 修改角色
     */
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:role:edit")
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody UpdateRoleCommand command) {
        try {
            roleApplicationService.updateRole(command);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    /**
     * 删除角色
     */
    @Log(title = "角色管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:role:remove")
    @DeleteMapping("/{roleIds}")
    public AjaxResult remove(@PathVariable Long[] roleIds) {
        DeleteRoleCommand command = new DeleteRoleCommand();
        command.setRoleIds(roleIds);
        try {
            roleApplicationService.deleteRoles(command);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    /**
     * 修改角色状态
     */
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:role:edit")
    @PutMapping("/status")
    public AjaxResult changeStatus(@RequestParam Long roleId, @RequestParam String status) {
        try {
            roleApplicationService.changeStatus(roleId, status);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    /**
     * 分配菜单权限
     */
    @Log(title = "角色管理", businessType = BusinessType.GRANT)
    @RequiresPermissions("system:role:edit")
    @PutMapping("/menus")
    public AjaxResult assignMenus(@RequestParam Long roleId, @RequestParam Long[] menuIds) {
        try {
            roleApplicationService.assignMenus(roleId, menuIds);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    /**
     * 根据角色Key获取角色信息
     */
    @GetMapping("/roleKey/{roleKey}")
    public AjaxResult getByRoleKey(@PathVariable String roleKey) {
        SysRoleDTO role = roleApplicationService.getRoleByRoleKey(roleKey);
        if (role == null) {
            return error("角色不存在");
        }
        return success(role);
    }
}
