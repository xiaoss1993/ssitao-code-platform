package com.ssitao.code.modular.iam.userprofile.controller.page;

import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户档案管理页面控制器
 * 负责用户档案管理相关页面的跳转
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Tag(name = "用户档案管理页面", description = "用户档案管理页面跳转")
@Controller
@RequestMapping("/iam/userprofile")
public class UserProfilePageController {

    /**
     * 用户档案管理页面
     */
    @GetMapping
    @Operation(summary = "用户档案管理页面")
    public String listPage(Model model) {
        addCommonModel(model, "用户档案管理", "userprofile");
        return "iam/userprofile";
    }

    /**
     * 用户档案添加页面
     */
    @GetMapping("/add")
    @Operation(summary = "用户档案添加页面")
    public String addPage(Model model) {
        addCommonModel(model, "添加用户档案", "userprofile");
        return "iam/userprofile-edit";
    }

    /**
     * 用户档案编辑页面
     */
    @GetMapping("/edit")
    @Operation(summary = "用户档案编辑页面")
    public String editPage(Model model) {
        addCommonModel(model, "编辑用户档案", "userprofile");
        return "iam/userprofile-edit";
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
