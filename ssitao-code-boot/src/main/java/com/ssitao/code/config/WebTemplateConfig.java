package com.ssitao.code.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Web模板配置
 * 配置全局模型属性和拦截器，为模板页面提供必要的数据
 */
@Configuration
public class WebTemplateConfig implements WebMvcConfigurer {

    /**
     * 添加模板数据拦截器
     * 为所有请求添加模板所需的公共属性
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TemplateDataInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/api/**", "/css/**", "/js/**", "/img/**", "/fonts/**", "/static/**");
    }

    /**
     * 模板数据拦截器
     * 在每个请求处理前添加模板所需的公共属性
     */
    public static class TemplateDataInterceptor implements HandlerInterceptor {

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
            // 设置session属性
            HttpSession session = request.getSession(true);

            // 设置主题和皮肤（默认值）
            if (session.getAttribute("skin") == null) {
                session.setAttribute("skin", "blue");
            }
            if (session.getAttribute("theme") == null) {
                session.setAttribute("theme", "light");
            }

            return true;
        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                             ModelAndView modelAndView) {

            if (modelAndView != null && modelAndView.getViewName() != null) {
                HttpSession session = request.getSession();

                // 添加用户相关属性
                modelAndView.addObject("userId", getSessionValue(session, "userId", "100001"));
                modelAndView.addObject("name", getSessionValue(session, "name", "管理员"));
                modelAndView.addObject("picUrl", getSessionValue(session, "picUrl", "/img/default-avatar.png"));

                // 添加登录状态
                modelAndView.addObject("isLogin", getSessionValue(session, "isLogin", "Y"));

                // 添加首页配置
                modelAndView.addObject("homePage", getSessionValue(session, "homePage", "/myDesktop"));
                modelAndView.addObject("firstTitle", getSessionValue(session, "firstTitle", "工作台"));
                modelAndView.addObject("secondTitle", getSessionValue(session, "secondTitle", "我的桌面"));

                // 添加菜单类型
                modelAndView.addObject("menuType", getSessionValue(session, "menuType", "sidebar"));

                // 添加公司信息
                Map<String, Object> company = new HashMap<>();
                company.put("simpleName", "SSITAO");
                company.put("fullName", "SSITAO Code Platform");
                company.put("logoAddress", "");
                modelAndView.addObject("company", company);

                // 添加菜单列表
                List<Map<String, Object>> menuList = createMenuList();
                modelAndView.addObject("menuList", menuList);

                // 添加左侧菜单数据
                List<Map<String, Object>> menus = createMenus();
                modelAndView.addObject("menus", menus);

                // 添加消息提醒
                modelAndView.addObject("hasNewMessage", false);

                // 添加模型类型（用于列表页面）
                String requestUri = request.getRequestURI();
                if (requestUri.contains("/dashboard")) {
                    modelAndView.addObject("model", "DASHBOARD");
                } else if (requestUri.contains("/entity")) {
                    modelAndView.addObject("model", "ENTITY");
                } else if (requestUri.contains("/project")) {
                    modelAndView.addObject("model", "PROJECT");
                } else if (requestUri.contains("/pageList")) {
                    modelAndView.addObject("model", "PAGE");
                }

                // 为特定页面添加额外的模型数据
                addPageSpecificData(modelAndView, requestUri, session);

                // 添加按钮权限
                modelAndView.addObject("btnEdit", true);
                modelAndView.addObject("btnDelete", true);
            }
        }

        private String getSessionValue(HttpSession session, String key, String defaultValue) {
            Object value = session.getAttribute(key);
            return value != null ? value.toString() : defaultValue;
        }

        /**
         * 创建顶部菜单列表
         */
        private List<Map<String, Object>> createMenuList() {
            List<Map<String, Object>> menuList = new ArrayList<>();

            String[][] menus = {
                    {"工作台", "myDesktop", "我的桌面"},
                    {"项目管理", "projectList", "项目列表"},
                    {"实体管理", "entityList", "实体列表"},
                    {"流程管理", "workflowList", "流程列表"},
                    {"报表中心", "reportList", "报表列表"},
                    {"系统设置", "systemSetting", "系统设置"}
            };

            for (String[] menu : menus) {
                Map<String, Object> menuMap = new HashMap<>();
                menuMap.put("name", menu[0]);
                menuMap.put("url", menu[1]);
                menuMap.put("parentName", menu[2]);
                menuList.add(menuMap);
            }

            return menuList;
        }

        /**
         * 创建左侧菜单树结构
         */
        private List<Map<String, Object>> createMenus() {
            List<Map<String, Object>> menus = new ArrayList<>();

            // 工作台菜单
            Map<String, Object> workbenchMenu = new HashMap<>();
            workbenchMenu.put("text", "工作台");
            workbenchMenu.put("attributes", createAttributes("zmdi zmdi-home", ""));
            workbenchMenu.put("children", createChildren(new String[][]{
                    {"我的桌面", "myDesktop"},
                    {"我的任务", "myTask"},
                    {"我的日历", "myCalendar"},
                    {"我的消息", "myMessage"}
            }));
            menus.add(workbenchMenu);

            // 项目管理菜单
            Map<String, Object> projectMenu = new HashMap<>();
            projectMenu.put("text", "项目管理");
            projectMenu.put("attributes", createAttributes("zmdi zmdi-folder", ""));
            projectMenu.put("children", createChildren(new String[][]{
                    {"项目列表", "/api/project/list", "api"},
                    {"我的项目", "myProjectList"},
                    {"项目归档", "projectArchive"}
            }));
            menus.add(projectMenu);

            // 实体管理菜单
            Map<String, Object> entityMenu = new HashMap<>();
            entityMenu.put("text", "实体管理");
            entityMenu.put("attributes", createAttributes("zmdi zmdi-file", ""));
            entityMenu.put("children", createChildren(new String[][]{
                    {"实体列表", "entityList"},
                    {"我的实体", "myEntityList"},
                    {"实体类型", "entityTypeList"}
            }));
            menus.add(entityMenu);

            // 仪表板菜单
            Map<String, Object> dashboardMenu = new HashMap<>();
            dashboardMenu.put("text", "仪表板");
            dashboardMenu.put("attributes", createAttributes("zmdi zmdi-chart", ""));
            dashboardMenu.put("children", createChildren(new String[][]{
                    {"仪表板列表", "/dashboard/dashboardList"},
                    {"我的仪表板", "/dashboard/myDashboard"}
            }));
            menus.add(dashboardMenu);

            // 系统管理菜单
            Map<String, Object> systemMenu = new HashMap<>();
            systemMenu.put("text", "系统管理");
            systemMenu.put("attributes", createAttributes("zmdi zmdi-settings", ""));
            systemMenu.put("children", createChildren(new String[][]{
                    {"用户管理", "userList"},
                    {"角色管理", "roleList"},
                    {"权限管理", "permissionList"},
                    {"部门管理", "deptList"},
                    {"字典管理", "dictList"},
                    {"参数设置", "systemSetting"}
            }));
            menus.add(systemMenu);

            return menus;
        }

        private Map<String, Object> createAttributes(String icon, String replaceIcon) {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("icon", icon);
            attributes.put("replaceIcon", replaceIcon);
            return attributes;
        }

        private List<Map<String, Object>> createChildren(String[][] items) {
            List<Map<String, Object>> children = new ArrayList<>();
            for (String[] item : items) {
                Map<String, Object> child = new HashMap<>();
                child.put("text", item[0]);

                Map<String, Object> attributes = new HashMap<>();
                attributes.put("url", item[1]);
                if (item.length > 2) {
                    attributes.put("category", item[2]);
                }
                child.put("attributes", attributes);

                children.add(child);
            }
            return children;
        }

        /**
         * 为特定页面添加额外的模型数据
         */
        private void addPageSpecificData(ModelAndView modelAndView, String requestUri, HttpSession session) {
            // 日历页面数据
            if (requestUri.contains("/myCalendar") || requestUri.contains("/resource/myCalendar")) {
                modelAndView.addObject("btnAdd", "Y");
                modelAndView.addObject("btnEdit", "Y");
                modelAndView.addObject("btnDel", "Y");
                modelAndView.addObject("btnMember", "Y");
                modelAndView.addObject("curUser", createMockUser());
                modelAndView.addObject("userList", createMockUserList());
            }

            // 任务页面数据
            if (requestUri.contains("/myTask") || requestUri.contains("/task")) {
                modelAndView.addObject("data", "");
                modelAndView.addObject("speciData", "");
                modelAndView.addObject("pageTitle", "");
                modelAndView.addObject("objectType", "TSK");
                modelAndView.addObject("action", "");
                modelAndView.addObject("bodyClass", "task-body");
                modelAndView.addObject("task", createMockTask());
                modelAndView.addObject("isAllowSubmit", "Y");
                modelAndView.addObject("isKeepTopWindow", "N");
                modelAndView.addObject("isGantt", "N");
                modelAndView.addObject("isTemplate", "N");
                modelAndView.addObject("tabs", "N");
                modelAndView.addObject("isTransmit", "N");
                modelAndView.addObject("isBatchAudit", "N");
                modelAndView.addObject("isAllowAddChildTask", "Y");
                modelAndView.addObject("isAllowPublishAndEdit", "Y");
                modelAndView.addObject("isAllowChange", "Y");
                modelAndView.addObject("btnAdd", "Y");
                modelAndView.addObject("btnEdit", "Y");
                modelAndView.addObject("btnDelete", "Y");
                modelAndView.addObject("report_id", "");
                modelAndView.addObject("curTotalHour", "0");
                modelAndView.addObject("curRate", "");
                modelAndView.addObject("fillTotalHour", "0");
                modelAndView.addObject("taskIds", "");
            }
        }

        /**
         * 创建模拟用户
         */
        private Map<String, Object> createMockUser() {
            Map<String, Object> user = new HashMap<>();
            user.put("id", "100001");
            user.put("name", "管理员");
            user.put("picId", "/img/default-avatar.png");
            return user;
        }

        /**
         * 创建模拟用户列表
         */
        private List<Map<String, Object>> createMockUserList() {
            List<Map<String, Object>> users = new ArrayList<>();
            String[] names = {"张三", "李四", "王五", "赵六"};
            for (int i = 0; i < names.length; i++) {
                Map<String, Object> user = new HashMap<>();
                user.put("id", "10000" + (i + 2));
                user.put("name", names[i]);
                user.put("picId", "/img/default-avatar.png");
                users.add(user);
            }
            return users;
        }

        /**
         * 创建模拟任务
         */
        private Map<String, Object> createMockTask() {
            Map<String, Object> task = new HashMap<>();
            task.put("id", "1");
            task.put("action", "");
            return task;
        }
    }
}
