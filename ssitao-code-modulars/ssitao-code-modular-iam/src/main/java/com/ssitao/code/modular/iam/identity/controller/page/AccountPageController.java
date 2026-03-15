package com.ssitao.code.modular.iam.identity.controller.page;

import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 账号管理页面控制器
 * 负责账号管理相关页面的跳转
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Tag(name = "账号管理页面", description = "账号管理页面跳转")
@Controller
@RequestMapping("/iam/account")
public class AccountPageController {

    /**
     * 账号管理页面
     */
    @GetMapping
    @Operation(summary = "账号管理页面")
    public String listPage(Model model) {
        addCommonModel(model, "账号管理", "account");
        return "iam/account";
    }

    /**
     * 账号添加页面
     */
    @GetMapping("/add")
    @Operation(summary = "账号添加页面")
    public String addPage(Model model) {
        addCommonModel(model, "添加账号", "account");
        return "iam/account-edit";
    }

    /**
     * 账号编辑页面
     */
    @GetMapping("/edit")
    @Operation(summary = "账号编辑页面")
    public String editPage(Model model) {
        addCommonModel(model, "编辑账号", "account");
        return "iam/account-edit";
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
