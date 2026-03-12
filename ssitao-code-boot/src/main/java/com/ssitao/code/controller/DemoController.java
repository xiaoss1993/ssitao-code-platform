package com.ssitao.code.controller;

import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.common.pojo.PageResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 演示接口控制器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Tag(name = "演示接口", description = "用于前端演示的接口")
@RestController
@RequestMapping("/demo")
public class DemoController {

    /**
     * 获取版本信息
     */
    @GetMapping("/ver")
    @Operation(summary = "获取版本信息", description = "获取系统版本信息")
    public CommonResult<Map<String, Object>> getVersion(@RequestParam(required = false) String value) {
        Map<String, Object> result = new HashMap<>();
        result.put("version", "1.6.9");
        result.put("buildDate", LocalDateTime.now().toString());
        result.put("description", "ssitao-code-platform");
        result.put("receivedValue", value);
        return CommonResult.success(result);
    }

    /**
     * 模拟POST请求
     */
    @PostMapping("/post")
    @Operation(summary = "模拟POST请求", description = "用于前端演示的POST接口")
    public CommonResult<Map<String, Object>> postDemo(@RequestBody(required = false) Map<String, Object> data) {
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("message", "操作成功");
        result.put("data", data);
        return CommonResult.success(result);
    }

    /**
     * 获取数据列表（用于scTable组件）
     */
    @GetMapping("/list")
    @Operation(summary = "获取数据列表", description = "用于前端表格组件的数据列表")
    public CommonResult<PageResult<Map<String, Object>>> getList(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String prop,
            @RequestParam(required = false) String order) {

        List<Map<String, Object>> list = generateMockData(keyword);

        PageResult<Map<String, Object>> result = new PageResult<>();
        result.setRows(list);
        result.setTotal(list.size());
        return CommonResult.success(result);
    }

    /**
     * 分页查询数据
     */
    @GetMapping("/page")
    @Operation(summary = "分页查询数据", description = "分页查询演示数据")
    public CommonResult<PageResult<Map<String, Object>>> getPage(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(required = false) String keyword) {

        List<Map<String, Object>> allData = generateMockData(keyword);

        int fromIndex = (page - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, allData.size());

        List<Map<String, Object>> pageData = fromIndex < allData.size()
                ? allData.subList(fromIndex, toIndex)
                : new ArrayList<>();

        PageResult<Map<String, Object>> result = new PageResult<>();
        result.setRows(pageData);
        result.setTotal(allData.size());
        return CommonResult.success(result);
    }

    /**
     * 获取普通用户菜单
     */
    @GetMapping("/menu")
    @Operation(summary = "获取普通用户菜单", description = "获取普通用户的菜单列表")
    public CommonResult<List<Map<String, Object>>> getMenu() {
        List<Map<String, Object>> menus = new ArrayList<>();

        Map<String, Object> dashboard = new HashMap<>();
        dashboard.put("name", "dashboard");
        dashboard.put("path", "/dashboard");
        dashboard.put("component", "dashboard/index");
        dashboard.put("meta", createMeta("控制台", "el-icon-monitor"));
        menus.add(dashboard);

        Map<String, Object> userCenter = new HashMap<>();
        userCenter.put("name", "userCenter");
        userCenter.put("path", "/userCenter");
        userCenter.put("component", "userCenter/index");
        userCenter.put("meta", createMeta("个人中心", "el-icon-user"));
        menus.add(userCenter);

        return CommonResult.success(menus);
    }

    /**
     * 模拟不同状态码
     */
    @GetMapping("/status")
    @Operation(summary = "模拟不同状态码", description = "用于测试不同HTTP状态码")
    public CommonResult<Map<String, Object>> getStatus(
            @RequestParam(defaultValue = "200") Integer code) {

        if (code == 401) {
            return CommonResult.error(401, "未授权，请先登录");
        } else if (code == 403) {
            return CommonResult.error(403, "无权限访问");
        } else if (code == 404) {
            return CommonResult.error(404, "资源不存在");
        } else if (code == 500) {
            return CommonResult.error(500, "服务器内部错误");
        }

        Map<String, Object> result = new HashMap<>();
        result.put("status", "ok");
        result.put("code", code);
        return CommonResult.success(result);
    }

    /**
     * 生成模拟数据
     */
    private List<Map<String, Object>> generateMockData(String keyword) {
        List<Map<String, Object>> list = new ArrayList<>();
        String[] names = {"张三", "李四", "王五", "赵六", "孙七", "周八", "吴九", "郑十", "钱十一", "陈十二"};
        String[] depts = {"技术部", "产品部", "市场部", "人事部", "财务部"};
        String[] roles = {"管理员", "普通用户", "部门经理", "超级管理员"};

        for (int i = 1; i <= 50; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("id", String.valueOf(i));
            item.put("label", names[i % names.length] + i);
            item.put("name", names[i % names.length]);
            item.put("account", "user" + i);
            item.put("dept", depts[i % depts.length]);
            item.put("role", roles[i % roles.length]);
            item.put("status", i % 5 == 0 ? "0" : "1");
            item.put("sort", i);
            item.put("date", LocalDateTime.now().minusDays(i).toString());
            item.put("avatar", "https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png");
            item.put("remark", "这是第" + i + "条模拟数据");

            // 如果有搜索关键字，过滤数据
            if (keyword != null && !keyword.isEmpty()) {
                if (item.get("name").toString().contains(keyword) ||
                    item.get("account").toString().contains(keyword)) {
                    list.add(item);
                }
            } else {
                list.add(item);
            }
        }
        return list;
    }

    /**
     * 创建菜单meta信息
     */
    private Map<String, Object> createMeta(String title, String icon) {
        Map<String, Object> meta = new HashMap<>();
        meta.put("title", title);
        meta.put("icon", icon);
        return meta;
    }
}
