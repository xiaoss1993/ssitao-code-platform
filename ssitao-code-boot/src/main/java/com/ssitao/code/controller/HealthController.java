package com.ssitao.code.controller;

import com.ssitao.code.common.result.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "System APIs", description = "System health and information endpoints")
@RestController
@RequestMapping("/health")
public class HealthController {

    @Operation(summary = "Health Check", description = "Check system health status")
    @GetMapping("/check")
    public Result<Map<String, Object>> healthCheck() {
        Map<String, Object> health = new HashMap<>();
        health.put("application", "ssitao-code-platform");
        health.put("version", "1.1.1");
        health.put("status", "UP");
        health.put("timestamp", System.currentTimeMillis());
        return Result.success(health);
    }

    @Operation(summary = "System Info", description = "Get system basic information")
    @GetMapping("/info")
    public Result<Map<String, Object>> systemInfo() {
        Map<String, Object> info = new HashMap<>();
        info.put("application", "ssitao-code-platform");
        info.put("version", "1.1.1");
        info.put("javaVersion", System.getProperty("java.version"));
        info.put("osName", System.getProperty("os.name"));
        info.put("osArch", System.getProperty("os.arch"));
        info.put("timestamp", System.currentTimeMillis());
        return Result.success(info);
    }
}
