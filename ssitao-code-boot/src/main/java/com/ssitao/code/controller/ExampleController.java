package com.ssitao.code.controller;

import com.ssitao.code.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 示例控制器 - 展示 Knife4j 注解的使用方法
 *
 * @author ssitao
 */
@Tag(name = "示例接口", description = "Knife4j 示例接口,展示各种注解的使用方法")
@RestController
@RequestMapping("/api/example")
public class ExampleController {

    @Operation(summary = "获取示例数据", description = "简单的 GET 请求示例")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "操作成功", content = @Content(schema = @Schema(implementation = Result.class))),
            @ApiResponse(responseCode = "500", description = "操作失败")
    })
    @GetMapping("/data")
    public Result<Map<String, Object>> getData() {
        Map<String, Object> data = new HashMap<>();
        data.put("id", 1);
        data.put("name", "示例数据");
        data.put("description", "这是一个示例接口");
        return Result.success(data);
    }

    @Operation(summary = "根据ID获取用户", description = "通过路径变量获取用户信息")
    @Parameter(name = "id", description = "用户ID", required = true, in = ParameterIn.PATH, example = "100")
    @GetMapping("/user/{id}")
    public Result<Map<String, Object>> getUserById(
            @PathVariable("id") Long id) {
        Map<String, Object> user = new HashMap<>();
        user.put("id", id);
        user.put("name", "用户" + id);
        user.put("email", "user" + id + "@example.com");
        user.put("age", 25);
        return Result.success(user);
    }

    @Operation(summary = "根据名称搜索", description = "通过查询参数搜索数据")
    @Parameters({
            @Parameter(name = "keyword", description = "搜索关键词", required = false, in = ParameterIn.QUERY, example = "test"),
            @Parameter(name = "page", description = "页码", required = false, in = ParameterIn.QUERY, example = "1"),
            @Parameter(name = "size", description = "每页数量", required = false, in = ParameterIn.QUERY, example = "10")
    })
    @GetMapping("/search")
    public Result<Map<String, Object>> search(
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        Map<String, Object> result = new HashMap<>();
        result.put("keyword", keyword);
        result.put("page", page);
        result.put("size", size);
        result.put("total", 100);
        result.put("items", "查询结果列表...");
        return Result.success(result);
    }

    @Operation(summary = "创建用户", description = "POST 请求示例,创建新用户")
    @PostMapping("/user")
    public Result<Map<String, Object>> createUser(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "用户信息",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = UserRequest.class)
                    )
            )
            @RequestBody UserRequest userRequest) {
        Map<String, Object> user = new HashMap<>();
        user.put("id", System.currentTimeMillis());
        user.put("name", userRequest.getName());
        user.put("email", userRequest.getEmail());
        user.put("age", userRequest.getAge());
        return Result.success("用户创建成功", user);
    }

    @Operation(summary = "更新用户", description = "PUT 请求示例,更新用户信息")
    @Parameter(name = "id", description = "用户ID", required = true, in = ParameterIn.PATH)
    @PutMapping("/user/{id}")
    public Result<Map<String, Object>> updateUser(
            @PathVariable("id") Long id,
            @RequestBody UserRequest userRequest) {
        Map<String, Object> user = new HashMap<>();
        user.put("id", id);
        user.put("name", userRequest.getName());
        user.put("email", userRequest.getEmail());
        user.put("age", userRequest.getAge());
        return Result.success("用户更新成功", user);
    }

    @Operation(summary = "删除用户", description = "DELETE 请求示例,删除指定用户")
    @Parameter(name = "id", description = "用户ID", required = true, in = ParameterIn.PATH)
    @DeleteMapping("/user/{id}")
    public Result<String> deleteUser(@PathVariable("id") Long id) {
        return Result.success("用户删除成功");
    }

    /**
     * 用户请求参数类
     */
    @Schema(description = "用户请求参数")
    public static class UserRequest {
        @Schema(description = "用户名称", example = "张三", required = true)
        private String name;

        @Schema(description = "用户邮箱", example = "zhangsan@example.com", required = true)
        private String email;

        @Schema(description = "用户年龄", example = "25", required = false)
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
