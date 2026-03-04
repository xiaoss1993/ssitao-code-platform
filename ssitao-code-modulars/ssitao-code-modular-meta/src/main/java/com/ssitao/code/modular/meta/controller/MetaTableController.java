package com.ssitao.code.modular.meta.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

/**
 * 元数据表管理 Controller
 *
 * @author ssitao-code
 */
@RestController
@RequestMapping("/meta/table")
@Tag(name = "元数据表管理", description = "元数据表相关接口")
public class MetaTableController {

    @GetMapping("/list")
    @Operation(summary = "获取元数据表列表")
    public String list() {
        return "meta table list";
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取元数据表详情")
    public String getById(@PathVariable String id) {
        return "meta table: " + id;
    }

    @PostMapping
    @Operation(summary = "创建元数据表")
    public String create(@RequestBody String request) {
        return "create meta table";
    }

    @PutMapping
    @Operation(summary = "更新元数据表")
    public String update(@RequestBody String request) {
        return "update meta table";
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除元数据表")
    public String delete(@PathVariable String id) {
        return "delete meta table: " + id;
    }

    @PostMapping("/generate/{id}")
    @Operation(summary = "生成代码")
    public String generate(@PathVariable String id) {
        return "generate code for table: " + id;
    }

}
