package com.ssitao.code.modular.iam.organization.controller.page;

import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 组织管理页面控制器
 * 负责组织管理相关页面的跳转
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Tag(name = "组织管理页面", description = "组织管理页面跳转")
@Controller
@RequestMapping("/iam/org")
public class OrgPageController {

    /**
     * 组织管理页面
     */
    @GetMapping
    @Operation(summary = "组织管理页面")
    public String listPage(Model model) {
        addCommonModel(model, "组织管理", "org");
        return "iam/org";
    }

    /**
     * 组织添加页面
     */
    @GetMapping("/add")
    @Operation(summary = "组织添加页面")
    public String addPage(Model model) {
        addCommonModel(model, "添加组织", "org");
        return "iam/org-edit";
    }

    /**
     * 组织编辑页面
     */
    @GetMapping("/edit")
    @Operation(summary = "组织编辑页面")
    public String editPage(Model model) {
        addCommonModel(model, "编辑组织", "org");
        return "iam/org-edit";
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
