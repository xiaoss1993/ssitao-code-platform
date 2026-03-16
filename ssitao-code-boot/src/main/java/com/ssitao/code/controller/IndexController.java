package com.ssitao.code.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.ssitao.code.modular.iam.identity.api.dto.IamAccountDTO;
import com.ssitao.code.modular.iam.identity.api.dto.IamLoginResultDTO;
import com.ssitao.code.modular.iam.identity.application.command.IamLoginCommand;
import com.ssitao.code.modular.iam.identity.application.command.IamLogoutCommand;
import com.ssitao.code.modular.iam.identity.application.service.IamLoginAppService;
import com.ssitao.code.modular.iam.identity.application.service.impl.IamLoginAppServiceImpl;
import com.ssitao.code.modular.iam.menu.application.service.IamMenuAppService;
import com.ssitao.code.modular.iam.menu.api.dto.IamMenuDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
        return "login";
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
            return "login";
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

        return "index";
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

        return "dashboard";
    }

    /**
     * 获取当前用户菜单
     */
    @GetMapping("/api/menus")
    @ResponseBody
    @Operation(summary = "获取菜单", description = "获取当前用户的菜单列表")
    public Map<String, Object> getMenus() {
        Map<String, Object> result = new HashMap<>();

        if (!StpUtil.isLogin()) {
            // 未登录时返回空菜单
            result.put("code", 200);
            result.put("msg", "success");
            result.put("data", new ArrayList<>());
            return result;
        }

        // 从数据库获取菜单数据
        List<IamMenuDTO> menuList = menuAppService.getMyMenus();

        // 转换为前端需要的格式
        List<Map<String, Object>> menus = convertMenusForFrontend(menuList);

        result.put("code", 200);
        result.put("msg", "success");
        result.put("data", menus);

        return result;
    }

    /**
     * 将菜单DTO转换为前端需要的格式
     */
    private List<Map<String, Object>> convertMenusForFrontend(List<IamMenuDTO> menuList) {
        if (menuList == null || menuList.isEmpty()) {
            return new java.util.ArrayList<>();
        }

        List<Map<String, Object>> result = new java.util.ArrayList<>();
        for (IamMenuDTO dto : menuList) {
            Map<String, Object> menu = new HashMap<>();
            menu.put("id", dto.getId());
            menu.put("name", dto.getMenuName());
            menu.put("icon", dto.getIcon() != null ? dto.getIcon() : "fa fa-circle-o");
            // 路径添加 /iam 前缀
            String path = dto.getPath();
            if (path != null && !path.startsWith("/") && !path.startsWith("http")) {
                path = "/iam/" + path;
            } else if (path != null && path.startsWith("/") && !path.startsWith("/iam") && !path.startsWith("/api") && !path.startsWith("/console")) {
                path = "/iam" + path;
            }
            menu.put("url", path);
            menu.put("addtabs", dto.getId());
            menu.put("isHeader", "directory".equals(dto.getMenuType()));

            // 递归处理子菜单
            if (dto.getChildren() != null && !dto.getChildren().isEmpty()) {
                menu.put("children", convertMenusForFrontend(dto.getChildren()));
            }

            result.add(menu);
        }
        return result;
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
