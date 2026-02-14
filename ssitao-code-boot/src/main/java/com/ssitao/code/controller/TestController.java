package com.ssitao.code.controller;

import com.ssitao.code.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "Test APIs", description = "APIs for testing functionality")
@RestController
@RequestMapping("/test")
public class TestController {

    @Operation(summary = "Hello World", description = "A simple Hello World endpoint")
    @GetMapping("/hello")
    public Result<String> hello() {
        return Result.success("Hello World from ssitao-code-platform!");
    }

    @Operation(summary = "Echo", description = "Return the input parameter")
    @GetMapping("/echo")
    public Result<String> echo(@Parameter(description = "Message to echo") @RequestParam String message) {
        return Result.success("Echo: " + message);
    }

    @Operation(summary = "Get User Info", description = "Get user info by ID (mock)")
    @GetMapping("/user/{id}")
    public Result<Map<String, Object>> getUser(@Parameter(description = "User ID") @PathVariable Long id) {
        Map<String, Object> user = new HashMap<>();
        user.put("id", id);
        user.put("name", "Test User");
        user.put("email", "test@ssitao.com");
        user.put("status", 1);
        return Result.success(user);
    }

    @Operation(summary = "Create User", description = "Create a new user (mock)")
    @PostMapping("/user")
    public Result<Map<String, Object>> createUser(@RequestBody Map<String, Object> user) {
        user.put("id", System.currentTimeMillis());
        user.put("createTime", System.currentTimeMillis());
        return Result.success("User created successfully", user);
    }

    @Operation(summary = "Calculate", description = "Perform simple arithmetic operations")
    @GetMapping("/calculate")
    public Result<Map<String, Object>> calculate(
            @Parameter(description = "First number") @RequestParam Double a,
            @Parameter(description = "Second number") @RequestParam Double b) {
        Map<String, Object> result = new HashMap<>();
        result.put("a", a);
        result.put("b", b);
        result.put("sum", a + b);
        result.put("difference", a - b);
        result.put("product", a * b);
        result.put("quotient", b != 0 ? a / b : "Cannot divide by zero");
        return Result.success(result);
    }
}
