package com.ssitao.code.modular.iam.menu.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.common.pojo.PageResult;
import com.ssitao.code.modular.iam.menu.api.dto.IamMenuDTO;
import com.ssitao.code.modular.iam.menu.application.service.IamMenuAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ssitao.code.common.pojo.CommonResult.success;

/**
 * IAM菜单管理控制器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Tag(name = "IAM菜单管理", description = "IAM菜单相关接口")
@Controller
@RequestMapping("/iam/menu")
public class IamMenuController {

    private final IamMenuAppService menuAppService;

    public IamMenuController(IamMenuAppService menuAppService) {
        this.menuAppService = menuAppService;
    }

    // ==================== 页面跳转 ====================

    /**
     * 菜单管理页面
     */
    @GetMapping
    @Operation(summary = "菜单管理页面")
    public String menuPage(Model model) {
        addCommonModel(model, "菜单管理", "menu");
        return "iam/menu";
    }

    /**
     * 菜单添加页面
     */
    @GetMapping("/add")
    @Operation(summary = "菜单添加页面")
    public String menuAddPage(Model model) {
        addCommonModel(model, "添加菜单", "menu");
        return "iam/menu-edit";
    }

    /**
     * 菜单编辑页面
     */
    @GetMapping("/edit")
    @Operation(summary = "菜单编辑页面")
    public String menuEditPage(Model model) {
        addCommonModel(model, "编辑菜单", "menu");
        return "iam/menu-edit";
    }

    // ==================== 菜单API ====================

    /**
     * 获取当前用户的菜单
     */
    @GetMapping("/my")
    @Operation(summary = "获取我的菜单", description = "获取当前登录用户的菜单列表")
    @ResponseBody
    public CommonResult<List<IamMenuDTO>> getMyMenus() {
        List<IamMenuDTO> menus = menuAppService.getMyMenus();
        return success(menus);
    }

    /**
     * 获取菜单列表（支持分页）
     */
    @GetMapping("/list")
    @Operation(summary = "获取菜单列表", description = "获取菜单列表，支持按父节点筛选和分页")
    @ResponseBody
    public CommonResult<PageResult<IamMenuDTO>> listMenus(
            @RequestParam(required = false) String parentId,
            @RequestParam(required = false) String menuType,
            @RequestParam(required = false) Integer status,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) String order) {
        PageResult<IamMenuDTO> result = menuAppService.listMenusPage(page, size, parentId, menuType, status, sort, order);
        return success(result);
    }

    /**
     * 分页获取菜单列表
     */
    @GetMapping("/page")
    @Operation(summary = "分页获取菜单列表", description = "分页获取菜单列表")
    @ResponseBody
    public CommonResult<List<IamMenuDTO>> pageMenus(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String parentId,
            @RequestParam(required = false) String menuType,
            @RequestParam(required = false) Integer status) {
        List<IamMenuDTO> menus = menuAppService.pageMenus(page, size, parentId, menuType, status);
        return success(menus);
    }

    /**
     * 获取菜单树
     */
    @GetMapping("/tree")
    @Operation(summary = "获取菜单树", description = "获取菜单树形结构")
    @ResponseBody
    public CommonResult<List<IamMenuDTO>> getMenuTree() {
        List<IamMenuDTO> tree = menuAppService.getMenuTree();
        return success(tree);
    }

    /**
     * 获取菜单详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取菜单详情", description = "根据ID获取菜单详情")
    @ResponseBody
    public CommonResult<IamMenuDTO> getMenu(@PathVariable String id) {
        IamMenuDTO menu = menuAppService.getMenuById(id);
        return success(menu);
    }

    /**
     * 创建菜单
     */
    @PostMapping
    @Operation(summary = "创建菜单", description = "创建新菜单")
    @ResponseBody
    public CommonResult<String> createMenu(@RequestBody IamMenuDTO menuDTO) {
        String menuId = menuAppService.createMenu(menuDTO);
        return success(menuId);
    }

    /**
     * 更新菜单
     */
    @PutMapping
    @Operation(summary = "更新菜单", description = "更新菜单信息")
    @ResponseBody
    public CommonResult<Boolean> updateMenu(@RequestBody IamMenuDTO menuDTO) {
        menuAppService.updateMenu(menuDTO);
        return success(true);
    }

    /**
     * 删除菜单
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除菜单", description = "根据ID删除菜单")
    @ResponseBody
    public CommonResult<Boolean> deleteMenu(@PathVariable String id) {
        menuAppService.deleteMenu(id);
        return success(true);
    }

    /**
     * 批量删除菜单
     */
    @PostMapping("/batch")
    @Operation(summary = "批量删除菜单", description = "批量删除菜单")
    @ResponseBody
    public CommonResult<Boolean> batchDeleteMenus(@RequestBody List<String> ids) {
        menuAppService.batchDeleteMenus(ids);
        return success(true);
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
