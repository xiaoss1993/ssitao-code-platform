package com.ssitao.code.controller;

import cn.dev33.satoken.stp.StpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面控制器
 * 负责管理后台各模块页面的跳转
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Tag(name = "页面控制器", description = "管理后台页面跳转")
@Controller
@RequestMapping("/admin")
public class PageController {

    // ==================== 常规管理 ====================

    /**
     * 账号管理页面
     */
    @GetMapping("/account")
    @Operation(summary = "账号管理页面")
    public String accountPage(Model model) {
        addCommonModel(model, "账号管理", "account");
        return "iam/account";
    }

    /**
     * 账号添加页面
     */
    @GetMapping("/account/add")
    @Operation(summary = "账号添加页面")
    public String accountAddPage(Model model) {
        addCommonModel(model, "添加账号", "account");
        return "iam/account-edit";
    }

    /**
     * 账号编辑页面
     */
    @GetMapping("/account/edit")
    @Operation(summary = "账号编辑页面")
    public String accountEditPage(Model model) {
        addCommonModel(model, "编辑账号", "account");
        return "iam/account-edit";
    }

    // ==================== 角色管理 ====================

    /**
     * 角色管理页面
     */
    @GetMapping("/role")
    @Operation(summary = "角色管理页面")
    public String rolePage(Model model) {
        addCommonModel(model, "角色管理", "role");
        return "iam/role";
    }

    /**
     * 角色添加页面
     */
    @GetMapping("/role/add")
    @Operation(summary = "角色添加页面")
    public String roleAddPage(Model model) {
        addCommonModel(model, "添加角色", "role");
        return "iam/role-edit";
    }

    /**
     * 角色编辑页面
     */
    @GetMapping("/role/edit")
    @Operation(summary = "角色编辑页面")
    public String roleEditPage(Model model) {
        addCommonModel(model, "编辑角色", "role");
        return "iam/role-edit";
    }

    // ==================== 权限管理 ====================

    /**
     * 权限管理页面
     */
    @GetMapping("/permission")
    @Operation(summary = "权限管理页面")
    public String permissionPage(Model model) {
        addCommonModel(model, "权限管理", "permission");
        return "iam/permission";
    }

    /**
     * 权限添加页面
     */
    @GetMapping("/permission/add")
    @Operation(summary = "权限添加页面")
    public String permissionAddPage(Model model) {
        addCommonModel(model, "添加权限", "permission");
        return "iam/permission-edit";
    }

    /**
     * 权限编辑页面
     */
    @GetMapping("/permission/edit")
    @Operation(summary = "权限编辑页面")
    public String permissionEditPage(Model model) {
        addCommonModel(model, "编辑权限", "permission");
        return "iam/permission-edit";
    }

    // ==================== 菜单管理 ====================

    /**
     * 菜单管理页面
     */
    @GetMapping("/menu")
    @Operation(summary = "菜单管理页面")
    public String menuPage(Model model) {
        addCommonModel(model, "菜单管理", "menu");
        return "iam/menu";
    }

    /**
     * 菜单添加页面
     */
    @GetMapping("/menu/add")
    @Operation(summary = "菜单添加页面")
    public String menuAddPage(Model model) {
        addCommonModel(model, "添加菜单", "menu");
        return "iam/menu-edit";
    }

    /**
     * 菜单编辑页面
     */
    @GetMapping("/menu/edit")
    @Operation(summary = "菜单编辑页面")
    public String menuEditPage(Model model) {
        addCommonModel(model, "编辑菜单", "menu");
        return "iam/menu-edit";
    }

    // ==================== 组织管理 ====================

    /**
     * 组织管理页面
     */
    @GetMapping("/org")
    @Operation(summary = "组织管理页面")
    public String orgPage(Model model) {
        addCommonModel(model, "组织管理", "org");
        return "iam/org";
    }

    /**
     * 组织添加页面
     */
    @GetMapping("/org/add")
    @Operation(summary = "组织添加页面")
    public String orgAddPage(Model model) {
        addCommonModel(model, "添加组织", "org");
        return "iam/org-edit";
    }

    /**
     * 组织编辑页面
     */
    @GetMapping("/org/edit")
    @Operation(summary = "组织编辑页面")
    public String orgEditPage(Model model) {
        addCommonModel(model, "编辑组织", "org");
        return "iam/org-edit";
    }

    // ==================== 租户管理 ====================

    /**
     * 租户管理页面
     */
    @GetMapping("/tenant")
    @Operation(summary = "租户管理页面")
    public String tenantPage(Model model) {
        addCommonModel(model, "租户管理", "tenant");
        return "iam/tenant";
    }

    /**
     * 租户添加页面
     */
    @GetMapping("/tenant/add")
    @Operation(summary = "租户添加页面")
    public String tenantAddPage(Model model) {
        addCommonModel(model, "添加租户", "tenant");
        return "iam/tenant-edit";
    }

    /**
     * 租户编辑页面
     */
    @GetMapping("/tenant/edit")
    @Operation(summary = "租户编辑页面")
    public String tenantEditPage(Model model) {
        addCommonModel(model, "编辑租户", "tenant");
        return "iam/tenant-edit";
    }

    // ==================== 用户档案管理 ====================

    /**
     * 用户档案管理页面
     */
    @GetMapping("/userprofile")
    @Operation(summary = "用户档案管理页面")
    public String userprofilePage(Model model) {
        addCommonModel(model, "用户档案管理", "userprofile");
        return "iam/userprofile";
    }

    /**
     * 用户档案添加页面
     */
    @GetMapping("/userprofile/add")
    @Operation(summary = "用户档案添加页面")
    public String userprofileAddPage(Model model) {
        addCommonModel(model, "添加用户档案", "userprofile");
        return "iam/userprofile-edit";
    }

    /**
     * 用户档案编辑页面
     */
    @GetMapping("/userprofile/edit")
    @Operation(summary = "用户档案编辑页面")
    public String userprofileEditPage(Model model) {
        addCommonModel(model, "编辑用户档案", "userprofile");
        return "iam/userprofile-edit";
    }

    // ==================== 审计日志 ====================

    /**
     * 审计日志页面
     */
    @GetMapping("/audit")
    @Operation(summary = "审计日志页面")
    public String auditPage(Model model) {
        addCommonModel(model, "审计日志", "audit");
        return "iam/audit";
    }

    // ==================== 常规管理 ====================

    /**
     * 系统配置页面
     */
    @GetMapping("/config")
    @Operation(summary = "系统配置页面")
    public String configPage(Model model) {
        addCommonModel(model, "系统配置", "config");
        return "admin/config";
    }

    /**
     * 附件管理页面
     */
    @GetMapping("/attachment")
    @Operation(summary = "附件管理页面")
    public String attachmentPage(Model model) {
        addCommonModel(model, "附件管理", "attachment");
        return "admin/attachment";
    }

    /**
     * 定时任务页面
     */
    @GetMapping("/crontab")
    @Operation(summary = "定时任务页面")
    public String crontabPage(Model model) {
        addCommonModel(model, "定时任务", "crontab");
        return "admin/crontab";
    }

    /**
     * 定时任务添加页面
     */
    @GetMapping("/crontab/add")
    @Operation(summary = "定时任务添加页面")
    public String crontabAddPage(Model model) {
        addCommonModel(model, "添加定时任务", "crontab");
        return "admin/crontab-edit";
    }

    /**
     * 定时任务编辑页面
     */
    @GetMapping("/crontab/edit")
    @Operation(summary = "定时任务编辑页面")
    public String crontabEditPage(Model model) {
        addCommonModel(model, "编辑定时任务", "crontab");
        return "admin/crontab-edit";
    }

    /**
     * 数据库管理页面
     */
    @GetMapping("/database")
    @Operation(summary = "数据库管理页面")
    public String databasePage(Model model) {
        addCommonModel(model, "数据库管理", "database");
        return "admin/database";
    }

    /**
     * 个人配置页面
     */
    @GetMapping("/profile")
    @Operation(summary = "个人配置页面")
    public String profilePage(Model model) {
        addCommonModel(model, "个人配置", "profile");
        if (StpUtil.isLogin()) {
            model.addAttribute("userId", StpUtil.getLoginId());
        }
        return "common/profile";
    }

    // ==================== 权限管理 ====================

    /**
     * 管理员管理页面
     */
    @GetMapping("/admin")
    @Operation(summary = "管理员管理页面")
    public String adminPage(Model model) {
        addCommonModel(model, "管理员管理", "admin");
        return "admin/admin";
    }

    /**
     * 管理员添加页面
     */
    @GetMapping("/admin/add")
    @Operation(summary = "管理员添加页面")
    public String adminAddPage(Model model) {
        addCommonModel(model, "添加管理员", "admin");
        return "admin/admin-edit";
    }

    /**
     * 管理员编辑页面
     */
    @GetMapping("/admin/edit")
    @Operation(summary = "管理员编辑页面")
    public String adminEditPage(Model model) {
        addCommonModel(model, "编辑管理员", "admin");
        return "admin/admin-edit";
    }

    /**
     * 管理员日志页面
     */
    @GetMapping("/adminlog")
    @Operation(summary = "管理员日志页面")
    public String adminlogPage(Model model) {
        addCommonModel(model, "管理员日志", "adminlog");
        return "admin/adminlog";
    }

    /**
     * 角色组页面
     */
    @GetMapping("/group")
    @Operation(summary = "角色组页面")
    public String groupPage(Model model) {
        addCommonModel(model, "角色组", "group");
        return "admin/group";
    }

    /**
     * 角色组添加页面
     */
    @GetMapping("/group/add")
    @Operation(summary = "角色组添加页面")
    public String groupAddPage(Model model) {
        addCommonModel(model, "添加角色组", "group");
        return "admin/group-edit";
    }

    /**
     * 角色组编辑页面
     */
    @GetMapping("/group/edit")
    @Operation(summary = "角色组编辑页面")
    public String groupEditPage(Model model) {
        addCommonModel(model, "编辑角色组", "group");
        return "admin/group-edit";
    }

    /**
     * 规则管理页面
     */
    @GetMapping("/rule")
    @Operation(summary = "规则管理页面")
    public String rulePage(Model model) {
        addCommonModel(model, "规则管理", "rule");
        return "admin/rule";
    }

    /**
     * 规则添加页面
     */
    @GetMapping("/rule/add")
    @Operation(summary = "规则添加页面")
    public String ruleAddPage(Model model) {
        addCommonModel(model, "添加规则", "rule");
        return "admin/rule-edit";
    }

    /**
     * 规则编辑页面
     */
    @GetMapping("/rule/edit")
    @Operation(summary = "规则编辑页面")
    public String ruleEditPage(Model model) {
        addCommonModel(model, "编辑规则", "rule");
        return "admin/rule-edit";
    }

    // ==================== 内容管理 ====================

    /**
     * 单页管理页面
     */
    @GetMapping("/page")
    @Operation(summary = "单页管理页面")
    public String pageManagePage(Model model) {
        addCommonModel(model, "单页管理", "page");
        return "admin/page";
    }

    /**
     * 单页添加页面
     */
    @GetMapping("/page/add")
    @Operation(summary = "单页添加页面")
    public String pageAddPage(Model model) {
        addCommonModel(model, "添加单页", "page");
        return "admin/page-edit";
    }

    /**
     * 单页编辑页面
     */
    @GetMapping("/page/edit")
    @Operation(summary = "单页编辑页面")
    public String pageEditPage(Model model) {
        addCommonModel(model, "编辑单页", "page");
        return "admin/page-edit";
    }

    /**
     * 分类管理页面
     */
    @GetMapping("/category")
    @Operation(summary = "分类管理页面")
    public String categoryPage(Model model) {
        addCommonModel(model, "分类管理", "category");
        return "admin/category";
    }

    /**
     * 分类添加页面
     */
    @GetMapping("/category/add")
    @Operation(summary = "分类添加页面")
    public String categoryAddPage(Model model) {
        addCommonModel(model, "添加分类", "category");
        return "admin/category-edit";
    }

    /**
     * 分类编辑页面
     */
    @GetMapping("/category/edit")
    @Operation(summary = "分类编辑页面")
    public String categoryEditPage(Model model) {
        addCommonModel(model, "编辑分类", "category");
        return "admin/category-edit";
    }

    /**
     * 版本管理页面
     */
    @GetMapping("/version")
    @Operation(summary = "版本管理页面")
    public String versionPage(Model model) {
        addCommonModel(model, "版本管理", "version");
        return "admin/version";
    }

    /**
     * 版本添加页面
     */
    @GetMapping("/version/add")
    @Operation(summary = "版本添加页面")
    public String versionAddPage(Model model) {
        addCommonModel(model, "添加版本", "version");
        return "admin/version-edit";
    }

    /**
     * 版本编辑页面
     */
    @GetMapping("/version/edit")
    @Operation(summary = "版本编辑页面")
    public String versionEditPage(Model model) {
        addCommonModel(model, "编辑版本", "version");
        return "admin/version-edit";
    }

    // ==================== 微信管理 ====================

    /**
     * 微信自动回复页面
     */
    @GetMapping("/autoreply")
    @Operation(summary = "微信自动回复页面")
    public String autoreplyPage(Model model) {
        addCommonModel(model, "微信自动回复", "autoreply");
        return "admin/autoreply";
    }

    /**
     * 微信自动回复添加页面
     */
    @GetMapping("/autoreply/add")
    @Operation(summary = "微信自动回复添加页面")
    public String autoreplyAddPage(Model model) {
        addCommonModel(model, "添加自动回复", "autoreply");
        return "admin/autoreply-edit";
    }

    /**
     * 微信自动回复编辑页面
     */
    @GetMapping("/autoreply/edit")
    @Operation(summary = "微信自动回复编辑页面")
    public String autoreplyEditPage(Model model) {
        addCommonModel(model, "编辑自动回复", "autoreply");
        return "admin/autoreply-edit";
    }

    /**
     * 微信配置页面
     */
    @GetMapping("/wechatconfig")
    @Operation(summary = "微信配置页面")
    public String wechatconfigPage(Model model) {
        addCommonModel(model, "微信配置", "wechatconfig");
        return "admin/wechatconfig";
    }

    /**
     * 微信配置编辑页面
     */
    @GetMapping("/wechatconfig/edit")
    @Operation(summary = "微信配置编辑页面")
    public String wechatconfigEditPage(Model model) {
        addCommonModel(model, "编辑微信配置", "wechatconfig");
        return "admin/wechatconfig-edit";
    }

    /**
     * 微信菜单页面
     */
    @GetMapping("/wechatmenu")
    @Operation(summary = "微信菜单页面")
    public String wechatMenuPage(Model model) {
        addCommonModel(model, "微信菜单", "menu");
        return "iam/menu";
    }

    /**
     * 资源管理页面
     */
    @GetMapping("/response")
    @Operation(summary = "资源管理页面")
    public String responsePage(Model model) {
        addCommonModel(model, "资源管理", "response");
        return "admin/response";
    }

    /**
     * 资源添加页面
     */
    @GetMapping("/response/add")
    @Operation(summary = "资源添加页面")
    public String responseAddPage(Model model) {
        addCommonModel(model, "添加资源", "response");
        return "admin/response-edit";
    }

    /**
     * 资源编辑页面
     */
    @GetMapping("/response/edit")
    @Operation(summary = "资源编辑页面")
    public String responseEditPage(Model model) {
        addCommonModel(model, "编辑资源", "response");
        return "admin/response-edit";
    }

    /**
     * 资源选择页面
     */
    @GetMapping("/response/select")
    @Operation(summary = "资源选择页面")
    public String responseSelectPage(Model model) {
        addCommonModel(model, "选择资源", "response");
        return "admin/response-select";
    }

    // ==================== 示例管理 ====================

    /**
     * 表格完整示例页面
     */
    @GetMapping("/bootstraptable")
    @Operation(summary = "表格完整示例页面")
    public String bootstraptablePage(Model model) {
        addCommonModel(model, "表格完整示例", "bootstraptable");
        return "admin/bootstraptable";
    }

    /**
     * 彩色角标页面
     */
    @GetMapping("/colorbadge")
    @Operation(summary = "彩色角标页面")
    public String colorbadgePage(Model model) {
        addCommonModel(model, "彩色角标", "colorbadge");
        return "admin/colorbadge";
    }

    /**
     * 控制器跳转页面
     */
    @GetMapping("/controllerjump")
    @Operation(summary = "控制器跳转页面")
    public String controllerjumpPage(Model model) {
        addCommonModel(model, "控制器跳转", "controllerjump");
        return "admin/controllerjump";
    }

    /**
     * 多级联动页面
     */
    @GetMapping("/cxselect")
    @Operation(summary = "多级联动页面")
    public String cxselectPage(Model model) {
        addCommonModel(model, "多级联动", "cxselect");
        return "admin/cxselect";
    }

    /**
     * 多表格示例页面
     */
    @GetMapping("/multitable")
    @Operation(summary = "多表格示例页面")
    public String multitablePage(Model model) {
        addCommonModel(model, "多表格示例", "multitable");
        return "admin/multitable";
    }

    /**
     * 多模型关联页面
     */
    @GetMapping("/relationmodel")
    @Operation(summary = "多模型关联页面")
    public String relationmodelPage(Model model) {
        addCommonModel(model, "多模型关联", "relationmodel");
        return "admin/relationmodel";
    }

    /**
     * 表格模板示例页面
     */
    @GetMapping("/tabletemplate")
    @Operation(summary = "表格模板示例页面")
    public String tabletemplatePage(Model model) {
        addCommonModel(model, "表格模板示例", "tabletemplate");
        return "admin/tabletemplate";
    }

    /**
     * 测试管理页面
     */
    @GetMapping("/test")
    @Operation(summary = "测试管理页面")
    public String testPage(Model model) {
        addCommonModel(model, "测试管理", "test");
        return "admin/test";
    }

    /**
     * 测试添加页面
     */
    @GetMapping("/test/add")
    @Operation(summary = "测试添加页面")
    public String testAddPage(Model model) {
        addCommonModel(model, "添加测试", "test");
        return "admin/test-edit";
    }

    /**
     * 测试编辑页面
     */
    @GetMapping("/test/edit")
    @Operation(summary = "测试编辑页面")
    public String testEditPage(Model model) {
        addCommonModel(model, "编辑测试", "test");
        return "admin/test-edit";
    }

    // ==================== 工具页面 ====================

    /**
     * 跳转页面
     */
    @GetMapping("/jump")
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
