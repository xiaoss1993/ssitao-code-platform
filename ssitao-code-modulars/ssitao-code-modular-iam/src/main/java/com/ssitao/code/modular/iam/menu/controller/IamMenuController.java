package com.ssitao.code.modular.iam.menu.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.modular.iam.menu.api.dto.IamMenuDTO;
import com.ssitao.code.modular.iam.menu.application.service.IamMenuAppService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RestController
@RequestMapping("/iam/menu")
public class IamMenuController {

    private final IamMenuAppService menuAppService;

    public IamMenuController(IamMenuAppService menuAppService) {
        this.menuAppService = menuAppService;
    }

    /**
     * 获取当前用户的菜单
     */
    @GetMapping("/my")
    @Operation(summary = "获取我的菜单", description = "获取当前登录用户的菜单列表")
    public CommonResult<List<IamMenuDTO>> getMyMenus() {
        List<IamMenuDTO> menus = menuAppService.getMyMenus();
        return success(menus);
    }

    /**
     * 获取菜单列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取菜单列表", description = "获取所有菜单列表")
    public CommonResult<List<IamMenuDTO>> listMenus(
            @RequestParam(required = false) String menuType,
            @RequestParam(required = false) Integer status) {
        List<IamMenuDTO> menus = menuAppService.listMenus(menuType, status);
        return success(menus);
    }

    /**
     * 获取菜单树
     */
    @GetMapping("/tree")
    @Operation(summary = "获取菜单树", description = "获取菜单树形结构")
    public CommonResult<List<IamMenuDTO>> getMenuTree() {
        List<IamMenuDTO> tree = menuAppService.getMenuTree();
        return success(tree);
    }

    /**
     * 获取菜单详情
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取菜单详情", description = "根据ID获取菜单详情")
    public CommonResult<IamMenuDTO> getMenu(@PathVariable Long id) {
        IamMenuDTO menu = menuAppService.getMenuById(id);
        return success(menu);
    }
}
