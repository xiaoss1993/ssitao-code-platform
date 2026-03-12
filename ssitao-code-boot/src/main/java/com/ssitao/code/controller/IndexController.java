package com.ssitao.code.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.ssitao.code.modular.iam.identity.api.dto.IamAccountDTO;
import com.ssitao.code.modular.iam.identity.api.dto.IamLoginResultDTO;
import com.ssitao.code.modular.iam.identity.application.command.IamLoginCommand;
import com.ssitao.code.modular.iam.identity.application.command.IamLogoutCommand;
import com.ssitao.code.modular.iam.identity.application.service.IamLoginAppService;
import com.ssitao.code.modular.iam.identity.application.service.impl.IamLoginAppServiceImpl;
import com.ssitao.code.modular.iam.menu.api.dto.IamMenuDTO;
import com.ssitao.code.modular.iam.menu.application.service.IamMenuAppService;
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
import org.springframework.web.bind.annotation.ResponseBody;

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
    private final IamMenuAppService menuAppService;

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

        // 获取当前用户的菜单列表
        List<IamMenuDTO> menus = menuAppService.getMyMenus();
        model.addAttribute("menus", menus);

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
     * 获取当前用户菜单（JSON格式）
     */
    @GetMapping("/api/menus")
    @ResponseBody
    @Operation(summary = "获取菜单", description = "获取当前用户的菜单列表")
    public Map<String, Object> getMenus() {
        Map<String, Object> result = new HashMap<>();

        if (!StpUtil.isLogin()) {
            result.put("code", 401);
            result.put("msg", "未登录");
            return result;
        }

        try {
            // 获取当前用户的菜单数据
            List<Map<String, Object>> menus = buildMenus();
            result.put("code", 200);
            result.put("msg", "success");
            result.put("data", menus);
        } catch (Exception e) {
            result.put("code", 500);
            result.put("msg", "获取菜单失败: " + e.getMessage());
        }

        return result;
    }

    /**
     * 获取当前用户信息（JSON格式）
     */
    @GetMapping("/api/userinfo")
    @ResponseBody
    @Operation(summary = "获取用户信息", description = "获取当前登录用户的信息")
    public Map<String, Object> getUserInfo() {
        Map<String, Object> result = new HashMap<>();

        if (!StpUtil.isLogin()) {
            result.put("code", 401);
            result.put("msg", "未登录");
            return result;
        }

        try {
            Object user = loginAppService.getCurrentUser(StpUtil.getTokenValue());
            String userId = IamLoginAppServiceImpl.extractAccountId(StpUtil.getLoginId());
            String userName = StpUtil.getLoginIdAsString();

            Map<String, Object> data = new HashMap<>();
            data.put("userId", userId);
            data.put("userName", userName);
            data.put("realName", userName);
            data.put("avatar", "/assets/img/default-avatar.png");

            result.put("code", 200);
            result.put("msg", "success");
            result.put("data", data);
        } catch (Exception e) {
            result.put("code", 500);
            result.put("msg", "获取用户信息失败: " + e.getMessage());
        }

        return result;
    }

    /**
     * 构建菜单数据
     */
    private List<Map<String, Object>> buildMenus() {
        // 从数据库获取当前用户的菜单
        List<IamMenuDTO> menuDTOs = menuAppService.getMyMenus();

        if (menuDTOs == null || menuDTOs.isEmpty()) {
            // 如果数据库没有菜单，返回空列表
            return new java.util.ArrayList<>();
        }

        // 将 DTO 转换为前端需要的 Map 格式
        return convertMenus(menuDTOs);
    }

    /**
     * 将菜单DTO转换为前端兼容的Map格式
     */
    private List<Map<String, Object>> convertMenus(List<IamMenuDTO> menuDTOs) {
        List<Map<String, Object>> menus = new java.util.ArrayList<>();

        for (IamMenuDTO dto : menuDTOs) {
            Map<String, Object> menu = new HashMap<>();
            menu.put("id", dto.getId());
            menu.put("menuName", dto.getMenuName());
            menu.put("icon", dto.getIcon());
            // 将菜单路径映射到正确的后端路由
            menu.put("path", convertMenuPath(dto.getPath()));
            menu.put("menuType", dto.getMenuType());
            menu.put("children", new java.util.ArrayList<>());

            // 递归处理子菜单
            if (dto.getChildren() != null && !dto.getChildren().isEmpty()) {
                menu.put("children", convertMenus(dto.getChildren()));
            }

            menus.add(menu);
        }

        return menus;
    }

    /**
     * 将菜单路径转换为实际的后端路由
     * 将旧的前端路径映射到新的后端Controller路由
     */
    private String convertMenuPath(String path) {
        if (path == null || path.isEmpty()) {
            return "javascript:;";
        }

        // 旧路径 -> 新路径 映射
        if (path.startsWith("/admin/")) {
            // /admin/system -> /iam/system
            // /admin/org -> /iam/org
            // /admin/menu -> /iam/menu
            // /admin/tenant -> /iam/tenant
            // /admin/monitor -> /iam/monitor
            path = path.replaceFirst("/admin/", "/iam/");
        }

        return path;
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
