package com.ssitao.code.modular.meta.controller;

import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 元数据/低代码平台页面控制器
 * 处理 /meta/** 路径的页面渲染
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Tag(name = "低代码平台页面", description = "低代码平台页面跳转")
@Controller
@RequestMapping("/meta")
public class MetaPageController {

    /**
     * 低代码平台首页/概览
     */
    @GetMapping
    @Operation(summary = "低代码平台首页")
    public String index(Model model) {
        addCommonModel(model, "低代码平台", "meta");
        return "redirect:/meta/table";
    }

    /**
     * 表格管理页面
     */
    @GetMapping("/table")
    @Operation(summary = "表格管理页面")
    public String tablePage(Model model) {
        addCommonModel(model, "表格管理", "meta/table");
        return "meta/table";
    }

    /**
     * 表格添加页面
     */
    @GetMapping("/table/add")
    @Operation(summary = "表格添加页面")
    public String tableAddPage(Model model) {
        addCommonModel(model, "添加表格", "meta/table");
        return "meta/table-edit";
    }

    /**
     * 表格编辑页面
     */
    @GetMapping("/table/edit")
    @Operation(summary = "表格编辑页面")
    public String tableEditPage(Model model) {
        addCommonModel(model, "编辑表格", "meta/table");
        return "meta/table-edit";
    }

    /**
     * 字段管理页面
     */
    @GetMapping("/column")
    @Operation(summary = "字段管理页面")
    public String columnPage(Model model) {
        addCommonModel(model, "字段管理", "meta/column");
        return "meta/column";
    }

    /**
     * 字段添加页面
     */
    @GetMapping("/column/add")
    @Operation(summary = "字段添加页面")
    public String columnAddPage(Model model) {
        addCommonModel(model, "添加字段", "meta/column");
        return "meta/column-edit";
    }

    /**
     * 字段编辑页面
     */
    @GetMapping("/column/edit")
    @Operation(summary = "字段编辑页面")
    public String columnEditPage(Model model) {
        addCommonModel(model, "编辑字段", "meta/column");
        return "meta/column-edit";
    }

    /**
     * 表单管理页面
     */
    @GetMapping("/form")
    @Operation(summary = "表单管理页面")
    public String formPage(Model model) {
        addCommonModel(model, "表单管理", "meta/form");
        return "meta/form";
    }

    /**
     * 表单添加页面
     */
    @GetMapping("/form/add")
    @Operation(summary = "表单添加页面")
    public String formAddPage(Model model) {
        addCommonModel(model, "添加表单", "meta/form");
        return "meta/form-edit";
    }

    /**
     * 表单编辑页面
     */
    @GetMapping("/form/edit")
    @Operation(summary = "表单编辑页面")
    public String formEditPage(Model model) {
        addCommonModel(model, "编辑表单", "meta/form");
        return "meta/form-edit";
    }

    /**
     * 列表管理页面
     */
    @GetMapping("/list")
    @Operation(summary = "列表管理页面")
    public String listPage(Model model) {
        addCommonModel(model, "列表管理", "meta/list");
        return "meta/list";
    }

    /**
     * 列表添加页面
     */
    @GetMapping("/list/add")
    @Operation(summary = "列表添加页面")
    public String listAddPage(Model model) {
        addCommonModel(model, "添加列表", "meta/list");
        return "meta/list-edit";
    }

    /**
     * 列表编辑页面
     */
    @GetMapping("/list/edit")
    @Operation(summary = "列表编辑页面")
    public String listEditPage(Model model) {
        addCommonModel(model, "编辑列表", "meta/list");
        return "meta/list-edit";
    }

    /**
     * 实体管理页面
     */
    @GetMapping("/entity")
    @Operation(summary = "实体管理页面")
    public String entityPage(Model model) {
        addCommonModel(model, "实体管理", "meta/entity");
        return "meta/entity";
    }

    /**
     * 实体添加页面
     */
    @GetMapping("/entity/add")
    @Operation(summary = "实体添加页面")
    public String entityAddPage(Model model) {
        addCommonModel(model, "添加实体", "meta/entity");
        return "meta/entity-edit";
    }

    /**
     * 实体编辑页面
     */
    @GetMapping("/entity/edit")
    @Operation(summary = "实体编辑页面")
    public String entityEditPage(Model model) {
        addCommonModel(model, "编辑实体", "meta/entity");
        return "meta/entity-edit";
    }

    /**
     * 代码生成页面
     */
    @GetMapping("/code")
    @Operation(summary = "代码生成页面")
    public String codePage(Model model) {
        addCommonModel(model, "代码生成", "meta/code");
        return "meta/code";
    }

    /**
     * 动态CRUD页面
     */
    @GetMapping("/dynamic")
    @Operation(summary = "动态CRUD页面")
    public String dynamicPage(Model model) {
        addCommonModel(model, "动态CRUD", "meta/dynamic");
        return "meta/dynamic";
    }

    // ==================== 字典管理页面 ====================

    /**
     * 字典类型管理页面
     */
    @GetMapping("/dictionary")
    @Operation(summary = "字典类型管理页面")
    public String dictionaryPage(Model model) {
        addCommonModel(model, "字典类型管理", "meta/dictionary");
        return "meta/dictionary";
    }

    /**
     * 字典类型添加页面
     */
    @GetMapping("/dictionary/add")
    @Operation(summary = "字典类型添加页面")
    public String dictionaryAddPage(Model model) {
        addCommonModel(model, "添加字典类型", "meta/dictionary");
        return "meta/dictionary-edit";
    }

    /**
     * 字典类型编辑页面
     */
    @GetMapping("/dictionary/edit")
    @Operation(summary = "字典类型编辑页面")
    public String dictionaryEditPage(Model model) {
        addCommonModel(model, "编辑字典类型", "meta/dictionary");
        return "meta/dictionary-edit";
    }

    /**
     * 字典数据管理页面
     */
    @GetMapping("/dictionary-item")
    @Operation(summary = "字典数据管理页面")
    public String dictionaryItemPage(Model model) {
        addCommonModel(model, "字典数据管理", "meta/dictionary-item");
        return "meta/dictionary-item";
    }

    /**
     * 字典数据添加页面
     */
    @GetMapping("/dictionary-item/add")
    @Operation(summary = "字典数据添加页面")
    public String dictionaryItemAddPage(Model model) {
        addCommonModel(model, "添加字典数据", "meta/dictionary-item");
        return "meta/dictionary-item-edit";
    }

    /**
     * 字典数据编辑页面
     */
    @GetMapping("/dictionary-item/edit")
    @Operation(summary = "字典数据编辑页面")
    public String dictionaryItemEditPage(Model model) {
        addCommonModel(model, "编辑字典数据", "meta/dictionary-item");
        return "meta/dictionary-item-edit";
    }

    /**
     * 添加通用模板变量
     */
    private void addCommonModel(Model model, String title, String controllerName) {
        model.addAttribute("title", title);
        model.addAttribute("controllerName", controllerName);
        model.addAttribute("moduleName", "meta");

        if (StpUtil.isLogin()) {
            model.addAttribute("isLogin", true);
            model.addAttribute("userId", StpUtil.getLoginId());
            model.addAttribute("userName", StpUtil.getLoginIdAsString());
        } else {
            model.addAttribute("isLogin", false);
        }
    }
}
