package com.ssitao.code.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 开发模式配置
 * 在开发环境下，为模板提供默认数据，避免 Thymeleaf 表达式报错
 */
@Configuration
@Profile("dev") // 只在开发环境激活
public class DevModeConfig implements WebMvcConfigurer {

    @Value("${company.simple-name:思涛科技}")
    private String companySimpleName;

    @Value("${company.full-name:思涛科技有限公司}")
    private String companyFullName;

    @Value("${company.logo-address:/img/common/logo.png}")
    private String logoAddress;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DevModeDataInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**", "/css/**", "/js/**", "/img/**", "/favicon.ico");
    }

    /**
     * 开发模式数据拦截器
     * 为所有请求提供默认的模型数据
     */
    public class DevModeDataInterceptor implements HandlerInterceptor {

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler, ModelAndView modelAndView) throws Exception {

            // 只处理有 modelAndView 的请求（即模板页面）
            if (modelAndView == null) {
                return;
            }

            // 获取或创建 session
            HttpSession session = request.getSession(true);

            // 设置默认的公司信息
            Map<String, Object> company = new HashMap<>();
            company.put("simpleName", companySimpleName);
            company.put("fullName", companyFullName);
            company.put("logoAddress", logoAddress);
            modelAndView.addObject("company", company);

            // 设置默认的用户信息
            modelAndView.addObject("name", "系统管理员");
            modelAndView.addObject("picUrl", "/img/common/user-avatar.png");
            modelAndView.addObject("userId", "1");
            modelAndView.addObject("isLogin", "Y");

            // 设置 session 属性
            session.setAttribute("skin", "default");
            session.setAttribute("theme", "theme-light");
            session.setAttribute("userId", "1");
            session.setAttribute("userName", "admin");
            session.setAttribute("user", createMockUser());

            // 设置默认的菜单类型
            modelAndView.addObject("menuType", "desktop");

            // 设置默认的首页
            modelAndView.addObject("homePage", "/dashboard-static.html");

            // 设置父级名称
            modelAndView.addObject("firstTitle", "工作台");
            modelAndView.addObject("secondTitle", "仪表盘");

            // 设置消息状态
            modelAndView.addObject("hasNewMessage", false);

            // 添加默认菜单数据
            modelAndView.addObject("menuList", createDefaultMenuList());
            modelAndView.addObject("menus", createDefaultMenus());

            // 添加默认权限（开发环境下给予所有权限）
            Set<String> permissions = new HashSet<>();
            permissions.add("*:*:*");
            modelAndView.addObject("permissions", permissions);

            // 添加国际化消息
            Map<String, String> messages = new HashMap<>();
            messages.put("common.sidebar.open", "展开菜单");
            messages.put("common.sidebar.close", "收起菜单");
            modelAndView.addObject("messages", messages);
        }

        /**
         * 创建模拟用户对象
         */
        private Map<String, Object> createMockUser() {
            Map<String, Object> user = new HashMap<>();
            user.put("id", "1");
            user.put("username", "admin");
            user.put("realName", "系统管理员");
            user.put("email", "admin@ssitao.com");
            user.put("mobile", "13800138000");
            user.put("picUrl", "/img/common/user-avatar.png");
            user.put("deptId", "1");
            user.put("deptName", "总公司");
            return user;
        }

        /**
         * 创建默认的顶部菜单列表
         */
        private List<Map<String, Object>> createDefaultMenuList() {
            List<Map<String, Object>> menuList = new ArrayList<>();

            Map<String, Object> menu1 = new HashMap<>();
            menu1.put("id", "1");
            menu1.put("name", "工作台");
            menu1.put("url", "myDesktop");
            menu1.put("parentName", "工作台");
            menu1.put("icon", "zmdi zmdi-home");
            menu1.put("sort", 1);
            menuList.add(menu1);

            Map<String, Object> menu2 = new HashMap<>();
            menu2.put("id", "2");
            menu2.put("name", "项目管理");
            menu2.put("url", "projectList");
            menu2.put("parentName", "项目管理");
            menu2.put("icon", "zmdi zmdi-project");
            menu2.put("sort", 2);
            menuList.add(menu2);

            Map<String, Object> menu3 = new HashMap<>();
            menu3.put("id", "3");
            menu3.put("name", "流程管理");
            menu3.put("url", "workflowList");
            menu3.put("parentName", "流程管理");
            menu3.put("icon", "zmdi zmdi-shuffle");
            menu3.put("sort", 3);
            menuList.add(menu3);

            Map<String, Object> menu4 = new HashMap<>();
            menu4.put("id", "4");
            menu4.put("name", "系统管理");
            menu4.put("url", "system");
            menu4.put("parentName", "系统管理");
            menu4.put("icon", "zmdi zmdi-settings");
            menu4.put("sort", 4);
            menuList.add(menu4);

            return menuList;
        }

        /**
         * 创建默认的左侧菜单树
         */
        private List<Map<String, Object>> createDefaultMenus() {
            List<Map<String, Object>> menus = new ArrayList<>();

            // 工作台菜单
            Map<String, Object> menu1 = new HashMap<>();
            menu1.put("text", "工作台");
            menu1.put("id", "1");

            Map<String, Object> attr1 = new HashMap<>();
            attr1.put("icon", "zmdi zmdi-home");
            attr1.put("url", "myDesktop");
            attr1.put("category", "desktop");
            menu1.put("attributes", attr1);

            List<Map<String, Object>> children1 = new ArrayList<>();
            Map<String, Object> cmenu1 = new HashMap<>();
            cmenu1.put("text", "我的任务");
            Map<String, Object> cattr1 = new HashMap<>();
            cattr1.put("url", "task/myTask");
            cattr1.put("category", "task");
            cmenu1.put("attributes", cattr1);
            children1.add(cmenu1);

            Map<String, Object> cmenu2 = new HashMap<>();
            cmenu2.put("text", "我的日历");
            Map<String, Object> cattr2 = new HashMap<>();
            cattr2.put("url", "resource/myCalendar");
            cattr2.put("category", "calendar");
            cmenu2.put("attributes", cattr2);
            children1.add(cmenu2);

            menu1.put("children", children1);
            menus.add(menu1);

            // 实体管理菜单
            Map<String, Object> menu2 = new HashMap<>();
            menu2.put("text", "实体管理");
            menu2.put("id", "2");

            Map<String, Object> attr2 = new HashMap<>();
            attr2.put("icon", "zmdi zmdi-folder");
            attr2.put("url", "entity");
            attr2.put("category", "entity");
            menu2.put("attributes", attr2);

            List<Map<String, Object>> children2 = new ArrayList<>();
            Map<String, Object> cmenu3 = new HashMap<>();
            cmenu3.put("text", "实体列表");
            Map<String, Object> cattr3 = new HashMap<>();
            cattr3.put("url", "entity/entityList");
            cattr3.put("category", "list");
            cmenu3.put("attributes", cattr3);
            children2.add(cmenu3);

            Map<String, Object> cmenu4 = new HashMap<>();
            cmenu4.put("text", "我的实体");
            Map<String, Object> cattr4 = new HashMap<>();
            cattr4.put("url", "entity/myEntityList");
            cattr4.put("category", "list");
            cmenu4.put("attributes", cattr4);
            children2.add(cmenu4);

            menu2.put("children", children2);
            menus.add(menu2);

            // 系统管理菜单
            Map<String, Object> menu3 = new HashMap<>();
            menu3.put("text", "系统管理");
            menu3.put("id", "3");

            Map<String, Object> attr3 = new HashMap<>();
            attr3.put("icon", "zmdi zmdi-settings");
            attr3.put("url", "system");
            attr3.put("category", "system");
            menu3.put("attributes", attr3);

            List<Map<String, Object>> children3 = new ArrayList<>();
            String[] sysMenus = {"用户管理", "角色管理", "部门管理", "菜单管理"};
            String[] sysUrls = {"system/user/userList", "system/role/roleList", "system/dept/deptList", "system/menu/menuTree"};
            String[] sysIcons = {"zmdi zmdi-account", "zmdi zmdi-shield", "zmdi zmdi-accounts", "zmdi zmdi-menu"};

            for (int i = 0; i < sysMenus.length; i++) {
                Map<String, Object> cmenu = new HashMap<>();
                cmenu.put("text", sysMenus[i]);
                Map<String, Object> cattr = new HashMap<>();
                cattr.put("url", sysUrls[i]);
                cattr.put("category", "system");
                cattr.put("replaceIcon", "<i class='" + sysIcons[i] + "'></i>");
                cmenu.put("attributes", cattr);
                children3.add(cmenu);
            }

            menu3.put("children", children3);
            menus.add(menu3);

            // 仪表盘菜单
            Map<String, Object> menu4 = new HashMap<>();
            menu4.put("text", "仪表盘");
            menu4.put("id", "4");

            Map<String, Object> attr4 = new HashMap<>();
            attr4.put("icon", "zmdi zmdi-dashboard");
            attr4.put("url", "dashboard");
            attr4.put("category", "dashboard");
            menu4.put("attributes", attr4);

            List<Map<String, Object>> children4 = new ArrayList<>();
            Map<String, Object> cmenu5 = new HashMap<>();
            cmenu5.put("text", "我的仪表盘");
            Map<String, Object> cattr5 = new HashMap<>();
            cattr5.put("url", "dashboard/myDashboard");
            cattr5.put("category", "dashboard");
            cmenu5.put("attributes", cattr5);
            children4.add(cmenu5);

            Map<String, Object> cmenu6 = new HashMap<>();
            cmenu6.put("text", "仪表盘列表");
            Map<String, Object> cattr6 = new HashMap<>();
            cattr6.put("url", "dashboard/dashboardList");
            cattr6.put("category", "list");
            cmenu6.put("attributes", cattr6);
            children4.add(cmenu6);

            menu4.put("children", children4);
            menus.add(menu4);

            return menus;
        }
    }
}
