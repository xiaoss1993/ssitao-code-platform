package com.ssitao.code.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.ssitao.code.modular.iam.identity.api.dto.IamAccountDTO;
import com.ssitao.code.modular.iam.identity.api.dto.IamLoginResultDTO;
import com.ssitao.code.modular.iam.identity.application.command.IamLoginCommand;
import com.ssitao.code.modular.iam.identity.application.command.IamLogoutCommand;
import com.ssitao.code.modular.iam.identity.application.service.IamLoginAppService;
import com.ssitao.code.modular.iam.identity.application.service.impl.IamLoginAppServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 主页控制器
 * 负责页面跳转和模板渲染
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Tag(name = "页面控制器", description = "负责页面跳转和模板渲染")
@Controller
@RequestMapping
@RequiredArgsConstructor
public class IndexController {

    private final IamLoginAppService loginAppService;

    /**
     * 登录页面
     */
    @GetMapping("/login")
    @Operation(summary = "登录页面", description = "跳转到登录页面")
    public String loginPage(Model model) {
        // 如果已登录，直接跳转到首页
        if (StpUtil.isLogin()) {
            return "redirect:/";
        }
        model.addAttribute("siteName", "SSITAO Code Platform");
        return "common/login";
    }

    /**
     * 登录处理
     */
    @PostMapping("/login")
    @Operation(summary = "登录处理", description = "处理用户登录请求")
    public String doLogin(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam(required = false, defaultValue = "false") boolean keeplogin,
            HttpServletRequest request,
            Model model) {

        try {
            IamLoginCommand command = new IamLoginCommand();
            command.setUsername(username);
            command.setPassword(password);
            command.setLoginType("PASSWORD");
            command.setKeepLogin(keeplogin);

            IamLoginResultDTO result = loginAppService.login(command);

            // 登录成功，跳转到首页
            return "redirect:/";

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("username", username);
            return "common/login";
        }
    }

    /**
     * 登出
     */
    @GetMapping("/logout")
    @Operation(summary = "登出", description = "用户登出")
    public String logout(HttpServletRequest request) {
        try {
            String token = StpUtil.getTokenValue();
            loginAppService.logout(token, new IamLogoutCommand());
        } catch (Exception ignored) {
        }
        return "redirect:/login";
    }

    /**
     * 首页/控制台
     */
    @GetMapping({"", "/", "/index", "/dashboard"})
    @Operation(summary = "首页", description = "跳转到首页/控制台")
    public String indexPage(Model model) {
        // 检查是否登录
        if (!StpUtil.isLogin()) {
            return "redirect:/login";
        }

        // 获取当前用户信息
        Object user = loginAppService.getCurrentUser(StpUtil.getTokenValue());
        model.addAttribute("user", user);
        // 从 loginId 中提取真正的 accountId（格式：accountId_tenantId）
        model.addAttribute("userId", IamLoginAppServiceImpl.extractAccountId(StpUtil.getLoginId()));
        model.addAttribute("userName", StpUtil.getLoginIdAsString());

        return "common/index";
    }

    /**
     * 控制台页面（iframe内容）
     */
    @GetMapping("/console")
    @Operation(summary = "控制台内容", description = "控制台iframe内容")
    public String consolePage(Model model) {
        if (!StpUtil.isLogin()) {
            return "redirect:/login";
        }

        // 添加统计数据
        model.addAttribute("totalUsers", 35200);
        model.addAttribute("totalVisits", 219390);
        model.addAttribute("totalOrders", 32143);
        model.addAttribute("totalAmount", 174800);

        return "common/dashboard";
    }

    /**
     * 通用页面跳转
     * 根据页面名称跳转到对应的模板
     */
    @GetMapping("/page/{page}")
    @Operation(summary = "通用页面跳转", description = "根据页面名称跳转到对应模板")
    public String page(@PathVariable String page, Model model) {
        if (!StpUtil.isLogin()) {
            return "redirect:/login";
        }
        // 映射页面名称到模板路径
        String templatePath = getTemplatePath(page);
        return templatePath;
    }

    /**
     * 获取模板路径
     * 根据页面名称映射到对应的模板路径
     */
    private String getTemplatePath(String page) {
        // 页面名称到模板路径的映射
        switch (page) {
            // 常规管理
            case "config":
                return "admin/config";
            case "attachment":
                return "admin/attachment";
            case "profile":
                return "common/profile";
            // 权限管理
            case "admin":
                return "admin/admin";
            case "adminlog":
                return "admin/adminlog";
            case "group":
                return "admin/group";
            case "rule":
                return "admin/rule";
            // 内容管理
            case "page":
                return "admin/page";
            case "category":
                return "admin/category";
            // IAM模块
            case "account":
                return "iam/account";
            case "role":
                return "iam/role";
            case "permission":
                return "iam/permission";
            case "menu":
                return "iam/menu";
            case "org":
                return "iam/org";
            case "tenant":
                return "iam/tenant";
            case "userprofile":
                return "iam/userprofile";
            case "audit":
                return "iam/audit";
            // Meta模块
            case "table":
                return "meta/table";
            case "column":
                return "meta/column";
            case "form":
                return "meta/form";
            case "list":
                return "meta/list";
            default:
                return page;
        }
    }

    /**
     * 获取当前用户菜单
     */
    @GetMapping("/api/menus")
    @Operation(summary = "获取菜单", description = "获取当前用户的菜单列表")
    public String getMenus(Model model) {
        if (!StpUtil.isLogin()) {
            return "redirect:/login";
        }

        // 获取用户权限
        Object permissions = loginAppService.getCurrentUserPermissions(StpUtil.getTokenValue());

        // 构建菜单数据
        List<Map<String, Object>> menus = buildMenus();
        model.addAttribute("menus", menus);

        return "fragments/menu :: sidebar";
    }

    /**
     * 构建菜单数据
     */
    private List<Map<String, Object>> buildMenus() {
        // TODO: 从数据库获取菜单
        return Arrays.asList(
                createMenu("dashboard", "控制台", "fa fa-dashboard", "/dashboard", true),
                createMenu("general", "常规管理", "fa fa-cogs", null, false,
                        createSubMenu("config", "系统配置", "fa fa-cog", "/page/config"),
                        createSubMenu("attachment", "附件管理", "fa fa-file-image-o", "/page/attachment"),
                        createSubMenu("profile", "个人配置", "fa fa-user", "/page/profile")
                ),
                createMenu("auth", "权限管理", "fa fa-group", null, false,
                        createSubMenu("admin", "管理员管理", "fa fa-user", "/page/admin"),
                        createSubMenu("adminlog", "管理员日志", "fa fa-list-alt", "/page/adminlog"),
                        createSubMenu("group", "角色组", "fa fa-group", "/page/group"),
                        createSubMenu("rule", "规则管理", "fa fa-bars", "/page/rule")
                ),
                createMenu("content", "内容管理", "fa fa-tags", null, false,
                        createSubMenu("page", "单页管理", "fa fa-tags", "/page/page"),
                        createSubMenu("category", "分类管理", "fa fa-list", "/page/category")
                )
        );
    }

    private Map<String, Object> createMenu(String id, String name, String icon, String url, boolean isActive, Map<String, Object>... children) {
        Map<String, Object> menu = new HashMap<>();
        menu.put("id", id);
        menu.put("name", name);
        menu.put("icon", icon);
        menu.put("url", url);
        menu.put("isActive", isActive);
        if (children.length > 0) {
            menu.put("children", Arrays.asList(children));
        }
        return menu;
    }

    private Map<String, Object> createSubMenu(String id, String name, String icon, String url) {
        Map<String, Object> menu = new HashMap<>();
        menu.put("id", id);
        menu.put("name", name);
        menu.put("icon", icon);
        menu.put("url", url);
        return menu;
    }
}
