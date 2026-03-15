package com.ssitao.code.modular.iam.authorization.controller.page;

import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 角色管理页面控制器
 * 负责角色管理相关页面的跳转
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Tag(name = "角色管理页面", description = "角色管理页面跳转")
@Controller
@RequestMapping("/iam/role")
public class RolePageController {

    /**
     * 角色管理页面
     */
    @GetMapping
    @Operation(summary = "角色管理页面")
    public String listPage(Model model) {
        addCommonModel(model, "角色管理", "role");
        return "iam/role";
    }

    /**
     * 角色添加页面
     */
    @GetMapping("/add")
    @Operation(summary = "角色添加页面")
    public String addPage(Model model) {
        addCommonModel(model, "添加角色", "role");
        return "iam/role-edit";
    }

    /**
     * 角色编辑页面
     */
    @GetMapping("/edit")
    @Operation(summary = "角色编辑页面")
    public String editPage(Model model) {
        addCommonModel(model, "编辑角色", "role");
        return "iam/role-edit";
    }

    /**
     * 添加通用模板变量
     */
    private void addCommonModel(Model model, String title, String controllerName) {
        model.addAttribute("title", title);
        model.addAttribute("controllerName", controllerName);
        model.addAttribute("moduleName", "admin");

        if (StpUtil.isLogin()) {
            model.addAttribute("isLogin", true);
            model.addAttribute("userId", StpUtil.getLoginId());
            model.addAttribute("userName", StpUtil.getLoginIdAsString());
        } else {
            model.addAttribute("isLogin", false);
        }
    }
}
