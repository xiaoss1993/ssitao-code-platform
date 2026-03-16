package com.ssitao.code.controller;

import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面控制器
 * 负责管理后台各模块页面的跳转
 * 注意：已分配的页面请参考对应的专属控制器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Tag(name = "页面控制器", description = "管理后台页面跳转")
@Controller
public class PageController {

    /**
     * 通用页面路由 - 支持 /admin/xxx 格式
     * 返回通用管理页面模板
     */
    @GetMapping("/admin/{page}")
    @Operation(summary = "通用页面路由")
    public String adminPage(@PathVariable String page, Model model) {
        addCommonModel(model, getPageTitle(page), page);
        return "admin/" + page;
    }

    /**
     * 获取页面标题
     */
    private String getPageTitle(String page) {
        switch (page) {
            case "index": return "系统首页";
            case "account": return "账号管理";
            case "role": return "角色管理";
            case "permission": return "权限管理";
            case "menu": return "菜单管理";
            case "user": return "用户管理";
            case "dept": return "部门管理";
            case "post": return "岗位管理";
            case "dict": return "字典管理";
            case "config": return "系统配置";
            default: return "管理页面";
        }
    }

    /**
     * 通用API路由 - 支持 /api/admin/xxx 格式
     * 将 /api/admin/xxx 映射到 /iam/xxx
     */
    @GetMapping("/api/admin/{page}")
    @Operation(summary = "通用API路由")
    public String apiAdminPage(@PathVariable String page, Model model) {
        return "redirect:/api/iam/" + page;
    }

    // ==================== 内容管理 ====================

    /**
     * 单页管理页面
     */
    @GetMapping("/iam/page")
    @Operation(summary = "单页管理页面")
    public String pageManagePage(Model model) {
        addCommonModel(model, "单页管理", "page");
        return "admin/page";
    }

    /**
     * 单页添加页面
     */
    @GetMapping("/iam/page/add")
    @Operation(summary = "单页添加页面")
    public String pageAddPage(Model model) {
        addCommonModel(model, "添加单页", "page");
        return "admin/page-edit";
    }

    /**
     * 单页编辑页面
     */
    @GetMapping("/iam/page/edit")
    @Operation(summary = "单页编辑页面")
    public String pageEditPage(Model model) {
        addCommonModel(model, "编辑单页", "page");
        return "admin/page-edit";
    }

    /**
     * 分类管理页面
     */
    @GetMapping("/iam/category")
    @Operation(summary = "分类管理页面")
    public String categoryPage(Model model) {
        addCommonModel(model, "分类管理", "category");
        return "admin/category";
    }

    /**
     * 分类添加页面
     */
    @GetMapping("/iam/category/add")
    @Operation(summary = "分类添加页面")
    public String categoryAddPage(Model model) {
        addCommonModel(model, "添加分类", "category");
        return "admin/category-edit";
    }

    /**
     * 分类编辑页面
     */
    @GetMapping("/iam/category/edit")
    @Operation(summary = "分类编辑页面")
    public String categoryEditPage(Model model) {
        addCommonModel(model, "编辑分类", "category");
        return "admin/category-edit";
    }

    /**
     * 版本管理页面
     */
    @GetMapping("/iam/version")
    @Operation(summary = "版本管理页面")
    public String versionPage(Model model) {
        addCommonModel(model, "版本管理", "version");
        return "admin/version";
    }

    /**
     * 版本添加页面
     */
    @GetMapping("/iam/version/add")
    @Operation(summary = "版本添加页面")
    public String versionAddPage(Model model) {
        addCommonModel(model, "添加版本", "version");
        return "admin/version-edit";
    }

    /**
     * 版本编辑页面
     */
    @GetMapping("/iam/version/edit")
    @Operation(summary = "版本编辑页面")
    public String versionEditPage(Model model) {
        addCommonModel(model, "编辑版本", "version");
        return "admin/version-edit";
    }

    // ==================== 微信管理 ====================

    /**
     * 微信自动回复页面
     */
    @GetMapping("/iam/autoreply")
    @Operation(summary = "微信自动回复页面")
    public String autoreplyPage(Model model) {
        addCommonModel(model, "微信自动回复", "autoreply");
        return "admin/autoreply";
    }

    /**
     * 微信自动回复添加页面
     */
    @GetMapping("/iam/autoreply/add")
    @Operation(summary = "微信自动回复添加页面")
    public String autoreplyAddPage(Model model) {
        addCommonModel(model, "添加自动回复", "autoreply");
        return "admin/autoreply-edit";
    }

    /**
     * 微信自动回复编辑页面
     */
    @GetMapping("/iam/autoreply/edit")
    @Operation(summary = "微信自动回复编辑页面")
    public String autoreplyEditPage(Model model) {
        addCommonModel(model, "编辑自动回复", "autoreply");
        return "admin/autoreply-edit";
    }

    /**
     * 微信配置页面
     */
    @GetMapping("/iam/wechatconfig")
    @Operation(summary = "微信配置页面")
    public String wechatconfigPage(Model model) {
        addCommonModel(model, "微信配置", "wechatconfig");
        return "admin/wechatconfig";
    }

    /**
     * 微信配置编辑页面
     */
    @GetMapping("/iam/wechatconfig/edit")
    @Operation(summary = "微信配置编辑页面")
    public String wechatconfigEditPage(Model model) {
        addCommonModel(model, "编辑微信配置", "wechatconfig");
        return "admin/wechatconfig-edit";
    }

    /**
     * 微信菜单页面
     */
    @GetMapping("/iam/wechatmenu")
    @Operation(summary = "微信菜单页面")
    public String wechatMenuPage(Model model) {
        addCommonModel(model, "微信菜单", "menu");
        return "admin/menu";
    }

    /**
     * 资源管理页面
     */
    @GetMapping("/iam/response")
    @Operation(summary = "资源管理页面")
    public String responsePage(Model model) {
        addCommonModel(model, "资源管理", "response");
        return "admin/response";
    }

    /**
     * 资源添加页面
     */
    @GetMapping("/iam/response/add")
    @Operation(summary = "资源添加页面")
    public String responseAddPage(Model model) {
        addCommonModel(model, "添加资源", "response");
        return "admin/response-edit";
    }

    /**
     * 资源编辑页面
     */
    @GetMapping("/iam/response/edit")
    @Operation(summary = "资源编辑页面")
    public String responseEditPage(Model model) {
        addCommonModel(model, "编辑资源", "response");
        return "admin/response-edit";
    }

    /**
     * 资源选择页面
     */
    @GetMapping("/iam/response/select")
    @Operation(summary = "资源选择页面")
    public String responseSelectPage(Model model) {
        addCommonModel(model, "选择资源", "response");
        return "admin/response-select";
    }

    // ==================== 示例管理 ====================

    /**
     * 表格完整示例页面
     */
    @GetMapping("/iam/bootstraptable")
    @Operation(summary = "表格完整示例页面")
    public String bootstraptablePage(Model model) {
        addCommonModel(model, "表格完整示例", "bootstraptable");
        return "admin/bootstraptable";
    }

    /**
     * 彩色角标页面
     */
    @GetMapping("/iam/colorbadge")
    @Operation(summary = "彩色角标页面")
    public String colorbadgePage(Model model) {
        addCommonModel(model, "彩色角标", "colorbadge");
        return "admin/colorbadge";
    }

    /**
     * 控制器跳转页面
     */
    @GetMapping("/iam/controllerjump")
    @Operation(summary = "控制器跳转页面")
    public String controllerjumpPage(Model model) {
        addCommonModel(model, "控制器跳转", "controllerjump");
        return "admin/controllerjump";
    }

    /**
     * 多级联动页面
     */
    @GetMapping("/iam/cxselect")
    @Operation(summary = "多级联动页面")
    public String cxselectPage(Model model) {
        addCommonModel(model, "多级联动", "cxselect");
        return "admin/cxselect";
    }

    /**
     * 多表格示例页面
     */
    @GetMapping("/iam/multitable")
    @Operation(summary = "多表格示例页面")
    public String multitablePage(Model model) {
        addCommonModel(model, "多表格示例", "multitable");
        return "admin/multitable";
    }

    /**
     * 多模型关联页面
     */
    @GetMapping("/iam/relationmodel")
    @Operation(summary = "多模型关联页面")
    public String relationmodelPage(Model model) {
        addCommonModel(model, "多模型关联", "relationmodel");
        return "admin/relationmodel";
    }

    /**
     * 表格模板示例页面
     */
    @GetMapping("/iam/tabletemplate")
    @Operation(summary = "表格模板示例页面")
    public String tabletemplatePage(Model model) {
        addCommonModel(model, "表格模板示例", "tabletemplate");
        return "admin/tabletemplate";
    }

    /**
     * 测试管理页面
     */
    @GetMapping("/iam/test")
    @Operation(summary = "测试管理页面")
    public String testPage(Model model) {
        addCommonModel(model, "测试管理", "test");
        return "admin/test";
    }

    /**
     * 测试添加页面
     */
    @GetMapping("/iam/test/add")
    @Operation(summary = "测试添加页面")
    public String testAddPage(Model model) {
        addCommonModel(model, "添加测试", "test");
        return "admin/test-edit";
    }

    /**
     * 测试编辑页面
     */
    @GetMapping("/iam/test/edit")
    @Operation(summary = "测试编辑页面")
    public String testEditPage(Model model) {
        addCommonModel(model, "编辑测试", "test");
        return "admin/test-edit";
    }

    // ==================== 工具页面 ====================

    /**
     * 跳转页面
     */
    @GetMapping("/common/jump")
    @Operation(summary = "跳转页面")
    public String jumpPage(Model model) {
        addCommonModel(model, "跳转中", "jump");
        return "common/jump";
    }

    // ==================== 通用方法 ====================

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
