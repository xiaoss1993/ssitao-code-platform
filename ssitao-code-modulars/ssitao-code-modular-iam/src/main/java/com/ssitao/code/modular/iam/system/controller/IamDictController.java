package com.ssitao.code.modular.iam.system.controller;

import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 字典管理页面控制器
 * 处理 /iam/dict 路径的页面渲染
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Tag(name = "字典管理页面", description = "字典管理页面跳转")
@Controller
@RequestMapping("/iam/dict")
public class IamDictController {

    /**
     * 字典管理首页
     */
    @GetMapping
    @Operation(summary = "字典管理页面")
    public String dictPage(Model model) {
        addCommonModel(model, "字典管理", "dict");
        return "iam/dict";
    }

    /**
     * 字典添加页面
     */
    @GetMapping("/add")
    @Operation(summary = "字典添加页面")
    public String dictAddPage(Model model) {
        addCommonModel(model, "添加字典", "dict");
        return "iam/dict-edit";
    }

    /**
     * 字典编辑页面
     */
    @GetMapping("/edit")
    @Operation(summary = "字典编辑页面")
    public String dictEditPage(Model model) {
        addCommonModel(model, "编辑字典", "dict");
        return "iam/dict-edit";
    }

    /**
     * 字典项管理页面
     */
    @GetMapping("/item")
    @Operation(summary = "字典项管理页面")
    public String dictItemPage(Model model) {
        addCommonModel(model, "字典项管理", "dict");
        return "iam/dict-item";
    }

    /**
     * 字典项添加页面
     */
    @GetMapping("/item/add")
    @Operation(summary = "字典项添加页面")
    public String dictItemAddPage(Model model) {
        addCommonModel(model, "添加字典项", "dict");
        return "iam/dict-item-edit";
    }

    /**
     * 字典项编辑页面
     */
    @GetMapping("/item/edit")
    @Operation(summary = "字典项编辑页面")
    public String dictItemEditPage(Model model) {
        addCommonModel(model, "编辑字典项", "dict");
        return "iam/dict-item-edit";
    }

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
