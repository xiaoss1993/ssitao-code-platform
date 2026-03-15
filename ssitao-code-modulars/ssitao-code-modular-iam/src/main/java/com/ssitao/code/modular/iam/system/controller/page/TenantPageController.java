package com.ssitao.code.modular.iam.system.controller.page;

import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 租户管理页面控制器
 * 负责租户管理相关页面的跳转
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Tag(name = "租户管理页面", description = "租户管理页面跳转")
@Controller
@RequestMapping("/iam/tenant")
public class TenantPageController {

    /**
     * 租户管理页面
     */
    @GetMapping
    @Operation(summary = "租户管理页面")
    public String listPage(Model model) {
        addCommonModel(model, "租户管理", "tenant");
        return "iam/tenant";
    }

    /**
     * 租户添加页面
     */
    @GetMapping("/add")
    @Operation(summary = "租户添加页面")
    public String addPage(Model model) {
        addCommonModel(model, "添加租户", "tenant");
        return "iam/tenant-edit";
    }

    /**
     * 租户编辑页面
     */
    @GetMapping("/edit")
    @Operation(summary = "租户编辑页面")
    public String editPage(Model model) {
        addCommonModel(model, "编辑租户", "tenant");
        return "iam/tenant-edit";
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
