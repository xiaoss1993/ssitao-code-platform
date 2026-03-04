package com.ssitao.code.modular.meta.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.ssitao.code.modular.meta.application.service.DynamicEntityService;
import com.ssitao.code.modular.meta.application.service.MetaTableService;
import com.ssitao.code.modular.meta.domain.model.MetaEntity;
import com.ssitao.code.modular.meta.domain.model.MetaField;
import com.ssitao.code.modular.meta.domain.repository.MetaTableRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 动态CRUD控制器
 * 实现通用增删改查 API
 *
 * @author ssitao-code
 */
@Slf4j
@RestController
@RequestMapping("/meta/dynamic")
@Tag(name = "动态CRUD管理")
@RequiredArgsConstructor
public class DynamicCrudController {

    private final MetaTableRepository metaTableRepository;
    private final MetaTableService metaTableService;
    private final DynamicEntityService dynamicEntityService;
    private final JdbcTemplate jdbcTemplate;

    // ==================== 表结构操作 ====================

    @PostMapping("/table/create")
    @Operation(summary = "创建表", description = "根据实体定义和字段定义创建数据库表")
    public String createTable(@RequestBody Map<String, Object> request) {
        String entityCode = (String) request.get("entityCode");
        String tenantId = (String) request.get("tenantId");

        // 获取实体定义
        Optional<MetaEntity> entityOpt = metaTableRepository.findEntityByCode(entityCode, tenantId);
        if (!entityOpt.isPresent()) {
            throw new RuntimeException("实体[" + entityCode + "]不存在");
        }

        // 获取字段定义
        List<MetaField> fields = metaTableRepository.findFieldsByEntityCode(entityCode, tenantId);
        if (CollUtil.isEmpty(fields)) {
            throw new RuntimeException("实体[" + entityCode + "]没有定义字段");
        }

        return metaTableService.createTable(entityOpt.get(), fields);
    }

    @PostMapping("/table/alter")
    @Operation(summary = "修改表", description = "修改数据库表结构")
    public void alterTable(@RequestBody Map<String, Object> request) {
        String entityCode = (String) request.get("entityCode");
        String tenantId = (String) request.get("tenantId");
        @SuppressWarnings("unchecked")
        List<MetaField> newFields = (List<MetaField>) request.get("newFields");
        @SuppressWarnings("unchecked")
        List<MetaField> modifiedFields = (List<MetaField>) request.get("modifiedFields");

        // 获取实体定义
        Optional<MetaEntity> entityOpt = metaTableRepository.findEntityByCode(entityCode, tenantId);
        if (!entityOpt.isPresent()) {
            throw new RuntimeException("实体[" + entityCode + "]不存在");
        }

        metaTableService.alterTable(entityOpt.get(), newFields, modifiedFields);
    }

    @DeleteMapping("/table/drop/{tableName}")
    @Operation(summary = "删除表", description = "删除数据库表")
    public void dropTable(
            @Parameter(description = "表名") @PathVariable String tableName) {
        metaTableService.dropTable(tableName);
    }

    @PostMapping("/table/sync/{entityId}")
    @Operation(summary = "同步字段", description = "根据实体定义同步数据库表字段")
    public void syncFields(
            @Parameter(description = "实体ID") @PathVariable String entityId) {
        metaTableService.syncFields(entityId);
    }

    // ==================== 动态实体操作 ====================

    @PostMapping("/entity/register")
    @Operation(summary = "注册动态实体", description = "注册动态实体到系统")
    public void registerEntity(@RequestBody Map<String, Object> request) {
        String entityCode = (String) request.get("entityCode");
        String tableName = (String) request.get("tableName");
        @SuppressWarnings("unchecked")
        List<MetaField> fields = (List<MetaField>) request.get("fields");

        dynamicEntityService.registerDynamicEntity(entityCode, tableName, fields);
    }

    @DeleteMapping("/entity/{entityCode}")
    @Operation(summary = "移除动态实体", description = "移除动态实体")
    public void removeEntity(
            @Parameter(description = "实体编码") @PathVariable String entityCode) {
        dynamicEntityService.removeDynamicEntity(entityCode);
    }

    // ==================== CRUD 操作 ====================

    @PostMapping("/data/{entityCode}")
    @Operation(summary = "新增数据", description = "向指定实体表中插入数据")
    public Map<String, Object> create(
            @Parameter(description = "实体编码") @PathVariable String entityCode,
            @RequestBody Map<String, Object> data) {

        String tableName = getTableName(entityCode);
        if (StrUtil.isBlank(tableName)) {
            throw new RuntimeException("实体[" + entityCode + "]未注册");
        }

        // 构建 INSERT 语句
        if (CollUtil.isEmpty(data)) {
            throw new RuntimeException("数据不能为空");
        }

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ").append(tableName).append(" (");

        StringBuilder placeholders = new StringBuilder();
        List<Object> params = new ArrayList<>();

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            sql.append(entry.getKey()).append(",");
            placeholders.append("?,");
            params.add(entry.getValue());
        }

        sql.deleteCharAt(sql.length() - 1);
        placeholders.deleteCharAt(placeholders.length() - 1);

        sql.append(") VALUES (").append(placeholders).append(")");

        jdbcTemplate.update(sql.toString(), params.toArray());

        log.info("动态新增数据到表[{}]成功", tableName);
        return data;
    }

    @PutMapping("/data/{entityCode}/{id}")
    @Operation(summary = "更新数据", description = "更新指定实体表中的数据")
    public void update(
            @Parameter(description = "实体编码") @PathVariable String entityCode,
            @Parameter(description = "数据ID") @PathVariable Object id,
            @RequestBody Map<String, Object> data) {

        String tableName = getTableName(entityCode);
        if (StrUtil.isBlank(tableName)) {
            throw new RuntimeException("实体[" + entityCode + "]未注册");
        }

        if (CollUtil.isEmpty(data)) {
            throw new RuntimeException("数据不能为空");
        }

        // 构建 UPDATE 语句
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(tableName).append(" SET ");

        List<Object> params = new ArrayList<>();

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            sql.append(entry.getKey()).append("=?,");
            params.add(entry.getValue());
        }

        sql.deleteCharAt(sql.length() - 1);
        sql.append(" WHERE id=?");

        params.add(id);

        jdbcTemplate.update(sql.toString(), params.toArray());

        log.info("动态更新表[{}]数据[{}]成功", tableName, id);
    }

    @DeleteMapping("/data/{entityCode}/{id}")
    @Operation(summary = "删除数据", description = "删除指定实体表中的数据")
    public void delete(
            @Parameter(description = "实体编码") @PathVariable String entityCode,
            @Parameter(description = "数据ID") @PathVariable Object id) {

        String tableName = getTableName(entityCode);
        if (StrUtil.isBlank(tableName)) {
            throw new RuntimeException("实体[" + entityCode + "]未注册");
        }

        String sql = "DELETE FROM " + tableName + " WHERE id=?";
        jdbcTemplate.update(sql, id);

        log.info("动态删除表[{}]数据[{}]成功", tableName, id);
    }

    @GetMapping("/data/{entityCode}/{id}")
    @Operation(summary = "获取单条数据", description = "获取指定实体表中的单条数据")
    public Map<String, Object> getById(
            @Parameter(description = "实体编码") @PathVariable String entityCode,
            @Parameter(description = "数据ID") @PathVariable Object id) {

        String tableName = getTableName(entityCode);
        if (StrUtil.isBlank(tableName)) {
            throw new RuntimeException("实体[" + entityCode + "]未注册");
        }

        String sql = "SELECT * FROM " + tableName + " WHERE id=?";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, id);

        if (CollUtil.isEmpty(results)) {
            return null;
        }

        return results.get(0);
    }

    @GetMapping("/data/{entityCode}")
    @Operation(summary = "获取列表数据", description = "获取指定实体表中的列表数据")
    public List<Map<String, Object>> list(
            @Parameter(description = "实体编码") @PathVariable String entityCode,
            @Parameter(description = "查询参数") @RequestParam Map<String, Object> params) {

        String tableName = getTableName(entityCode);
        if (StrUtil.isBlank(tableName)) {
            throw new RuntimeException("实体[" + entityCode + "]未注册");
        }

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ").append(tableName).append(" WHERE 1=1");

        List<Object> queryParams = new ArrayList<>();

        // 处理查询条件
        if (CollUtil.isNotEmpty(params)) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                String key = entry.getKey();
                // 跳过分页参数
                if ("pageNum".equals(key) || "pageSize".equals(key)) {
                    continue;
                }

                Object value = entry.getValue();
                if (value != null && StrUtil.isNotBlank(value.toString())) {
                    sql.append(" AND ").append(key).append("=?");
                    queryParams.add(value);
                }
            }
        }

        // 排序
        sql.append(" ORDER BY id DESC");

        // 处理分页
        Integer pageNum = params.get("pageNum") != null ? Integer.parseInt(params.get("pageNum").toString()) : 1;
        Integer pageSize = params.get("pageSize") != null ? Integer.parseInt(params.get("pageSize").toString()) : 10;
        int offset = (pageNum - 1) * pageSize;
        sql.append(" LIMIT ").append(offset).append(",").append(pageSize);

        return jdbcTemplate.queryForList(sql.toString(), queryParams.toArray());
    }

    @GetMapping("/data/{entityCode}/count")
    @Operation(summary = "获取数据总数", description = "获取指定实体表中的数据总数")
    public Long count(
            @Parameter(description = "实体编码") @PathVariable String entityCode,
            @Parameter(description = "查询参数") @RequestParam Map<String, Object> params) {

        String tableName = getTableName(entityCode);
        if (StrUtil.isBlank(tableName)) {
            throw new RuntimeException("实体[" + entityCode + "]未注册");
        }

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(*) FROM ").append(tableName).append(" WHERE 1=1");

        List<Object> queryParams = new ArrayList<>();

        // 处理查询条件
        if (CollUtil.isNotEmpty(params)) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value != null && StrUtil.isNotBlank(value.toString())) {
                    sql.append(" AND ").append(key).append("=?");
                    queryParams.add(value);
                }
            }
        }

        return jdbcTemplate.queryForObject(sql.toString(), Long.class, queryParams.toArray());
    }

    @PostMapping("/data/{entityCode}/batch")
    @Operation(summary = "批量新增数据", description = "批量向指定实体表中插入数据")
    public int batchCreate(
            @Parameter(description = "实体编码") @PathVariable String entityCode,
            @RequestBody List<Map<String, Object>> dataList) {

        String tableName = getTableName(entityCode);
        if (StrUtil.isBlank(tableName)) {
            throw new RuntimeException("实体[" + entityCode + "]未注册");
        }

        if (CollUtil.isEmpty(dataList)) {
            return 0;
        }

        int count = 0;
        for (Map<String, Object> data : dataList) {
            // 构建 INSERT 语句
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO ").append(tableName).append(" (");

            StringBuilder placeholders = new StringBuilder();
            List<Object> params = new ArrayList<>();

            for (Map.Entry<String, Object> entry : data.entrySet()) {
                sql.append(entry.getKey()).append(",");
                placeholders.append("?,");
                params.add(entry.getValue());
            }

            sql.deleteCharAt(sql.length() - 1);
            placeholders.deleteCharAt(placeholders.length() - 1);

            sql.append(") VALUES (").append(placeholders).append(")");

            count += jdbcTemplate.update(sql.toString(), params.toArray());
        }

        log.info("动态批量新增数据到表[{}]成功，共{}条", tableName, count);
        return count;
    }

    // ==================== 辅助方法 ====================

    /**
     * 获取表名
     */
    private String getTableName(String entityCode) {
        // 先从实体定义中获取
        Optional<MetaEntity> entityOpt = metaTableRepository.findEntityByCode(entityCode, null);
        if (entityOpt.isPresent() && StrUtil.isNotBlank(entityOpt.get().getTableName())) {
            return entityOpt.get().getTableName();
        }

        return null;
    }

}
