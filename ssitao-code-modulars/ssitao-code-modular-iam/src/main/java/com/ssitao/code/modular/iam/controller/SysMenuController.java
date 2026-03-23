package com.ssitao.code.modular.iam.controller;

import com.ssitao.code.common.annotation.Log;
import com.ssitao.code.common.core.controller.BaseController;
import com.ssitao.code.common.core.domain.AjaxResult;
import com.ssitao.code.common.core.domain.Ztree;
import com.ssitao.code.common.core.domain.entity.SysMenu;
import com.ssitao.code.common.core.domain.entity.SysRole;
import com.ssitao.code.common.enums.BusinessType;
import com.ssitao.code.common.exception.ServiceException;
import com.ssitao.code.common.utils.ShiroUtils;
import com.ssitao.code.modular.iam.application.dto.SysMenuDTO;
import com.ssitao.code.modular.iam.application.command.CreateMenuCommand;
import com.ssitao.code.modular.iam.application.command.DeleteMenuCommand;
import com.ssitao.code.modular.iam.application.command.UpdateMenuCommand;
import com.ssitao.code.modular.iam.application.service.SysMenuApplicationService;
import com.ssitao.code.modular.iam.application.service.ISysMenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单信息
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/menu")
public class SysMenuController extends BaseController
{
    private String prefix = "system/menu";

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysMenuApplicationService menuApplicationService;

    @RequiresPermissions("system:menu:view")
    @GetMapping()
    public String menu()
    {
        return prefix + "/menu";
    }

    @RequiresPermissions("system:menu:list")
    @PostMapping("/list")
    @ResponseBody
    public List<SysMenu> list(SysMenu menu)
    {
        Long userId = ShiroUtils.getUserId();
        List<SysMenu> menuList = menuService.selectMenuList(menu, userId);
        return menuList;
    }

    /**
     * 删除菜单
     */
    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:menu:remove")
    @GetMapping("/remove/{menuId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("menuId") Long menuId)
    {
        if (menuService.selectCountMenuByParentId(menuId) > 0)
        {
            return AjaxResult.warn("存在子菜单,不允许删除");
        }
        if (menuService.selectCountRoleMenuByMenuId(menuId) > 0)
        {
            return AjaxResult.warn("菜单已分配,不允许删除");
        }
        //AuthorizationUtils.clearAllCachedAuthorizationInfo();
        return toAjax(menuService.deleteMenuById(menuId));
    }

    /**
     * 新增
     */
    @RequiresPermissions("system:menu:add")
    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") Long parentId, ModelMap mmap)
    {
        SysMenu menu = null;
        if (0L != parentId)
        {
            menu = menuService.selectMenuById(parentId);
        }
        else
        {
            menu = new SysMenu();
            menu.setMenuId(0L);
            menu.setMenuName("主目录");
        }
        mmap.put("menu", menu);
        return prefix + "/add";
    }

    /**
     * 新增保存菜单
     */
    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:menu:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated SysMenu menu)
    {
        if (!menuService.checkMenuNameUnique(menu))
        {
            return error("新增菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        }
        menu.setCreateBy(getLoginName());
      //  AuthorizationUtils.clearAllCachedAuthorizationInfo();
        return toAjax(menuService.insertMenu(menu));
    }

    /**
     * 修改菜单
     */
    @RequiresPermissions("system:menu:edit")
    @GetMapping("/edit/{menuId}")
    public String edit(@PathVariable("menuId") Long menuId, ModelMap mmap)
    {
        mmap.put("menu", menuService.selectMenuById(menuId));
        return prefix + "/edit";
    }

    /**
     * 修改保存菜单
     */
    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:menu:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated SysMenu menu)
    {
        if (!menuService.checkMenuNameUnique(menu))
        {
            return error("修改菜单'" + menu.getMenuName() + "'失败，菜单名称已存在");
        }
        menu.setUpdateBy(getLoginName());
      //  AuthorizationUtils.clearAllCachedAuthorizationInfo();
        return toAjax(menuService.updateMenu(menu));
    }

    /**
     * 保存菜单排序
     */
    @PostMapping("/updateSort")
    @ResponseBody
    public AjaxResult updateSort(@RequestParam String[] menuIds, @RequestParam String[] orderNums)
    {
        menuService.updateMenuSort(menuIds, orderNums);
        return success();
    }

    /**
     * 选择菜单图标
     */
    @GetMapping("/icon")
    public String icon()
    {
        return prefix + "/icon";
    }

    /**
     * 校验菜单名称
     */
    @PostMapping("/checkMenuNameUnique")
    @ResponseBody
    public boolean checkMenuNameUnique(SysMenu menu)
    {
        return menuService.checkMenuNameUnique(menu);
    }

    /**
     * 加载角色菜单列表树
     */
    @GetMapping("/roleMenuTreeData")
    @ResponseBody
    public List<Ztree> roleMenuTreeData(SysRole role)
    {
        Long userId = ShiroUtils.getUserId();
        List<Ztree> ztrees = menuService.roleMenuTreeData(role, userId);
        return ztrees;
    }

    /**
     * 加载所有菜单列表树
     */
    @GetMapping("/menuTreeData")
    @ResponseBody
    public List<Ztree> menuTreeData()
    {
        Long userId = ShiroUtils.getUserId();
        List<Ztree> ztrees = menuService.menuTreeData(userId);
        return ztrees;
    }

    /**
     * 选择菜单树
     */
    @GetMapping("/selectMenuTree/{menuId}")
    public String selectMenuTree(@PathVariable("menuId") Long menuId, ModelMap mmap)
    {
        mmap.put("menu", menuService.selectMenuById(menuId));
        return prefix + "/tree";
    }

    // ==================== REST API 接口 (DDD风格) ====================

    @RequiresPermissions("system:menu:list")
    @GetMapping("/api/iam/menu/list")
    @ResponseBody
    public List<SysMenuDTO> apiList(SysMenuDTO query) {
        return menuApplicationService.listMenus(query);
    }

    @RequiresPermissions("system:menu:list")
    @GetMapping("/api/iam/menu/{menuId}")
    @ResponseBody
    public AjaxResult apiGetInfo(@PathVariable("menuId") Long menuId) {
        SysMenuDTO menu = menuApplicationService.getMenuById(menuId);
        return success(menu);
    }

    @GetMapping("/api/iam/menu/all")
    @ResponseBody
    public AjaxResult apiListAll() {
        List<SysMenuDTO> list = menuApplicationService.listAllMenus();
        return success(list);
    }

    @Log(title = "菜单管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("system:menu:add")
    @PostMapping("/api/iam/menu")
    @ResponseBody
    public AjaxResult apiAdd(@Validated @RequestBody CreateMenuCommand command) {
        try {
            Long menuId = menuApplicationService.createMenu(command);
            return success(menuId);
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:menu:edit")
    @PutMapping("/api/iam/menu")
    @ResponseBody
    public AjaxResult apiEdit(@Validated @RequestBody UpdateMenuCommand command) {
        try {
            menuApplicationService.updateMenu(command);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @Log(title = "菜单管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("system:menu:remove")
    @DeleteMapping("/api/iam/menu/{menuIds}")
    @ResponseBody
    public AjaxResult apiRemove(@PathVariable Long[] menuIds) {
        DeleteMenuCommand command = new DeleteMenuCommand();
        command.setMenuIds(menuIds);
        try {
            menuApplicationService.deleteMenus(command);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @Log(title = "菜单管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("system:menu:edit")
    @PutMapping("/api/iam/menu/status")
    @ResponseBody
    public AjaxResult apiChangeStatus(@RequestParam Long menuId, @RequestParam String visible) {
        try {
            menuApplicationService.changeStatus(menuId, visible);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }

    @PutMapping("/api/iam/menu/sort")
    @ResponseBody
    public AjaxResult apiUpdateSort(@RequestParam Long menuId, @RequestParam String orderNum) {
        try {
            menuApplicationService.updateSort(menuId, orderNum);
            return success();
        } catch (ServiceException e) {
            return error(e.getMessage());
        }
    }
}