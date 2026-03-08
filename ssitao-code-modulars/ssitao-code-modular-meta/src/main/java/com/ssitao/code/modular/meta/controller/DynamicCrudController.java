package com.ssitao.code.modular.meta.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.ssitao.code.common.exception.BusinessException;
import com.ssitao.code.common.pojo.CommonResult;
import com.ssitao.code.common.pojo.PageResult;
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
import java.util.regex.Pattern;

import static com.ssitao.code.common.pojo.CommonResult.success;

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

    /**
     * 合法的字段名正则表达式（只允许字母、数字、下划线）
     */
    private static final Pattern VALID_FIELD_PATTERN = Pattern.compile("^[a-zA-Z_][a-zA-Z0-9_]*$");

    /**
     * 查询操作符映射
     */
    private static final Map<String, String> QUERY_OPERATORS = new HashMap<>();

    static {
        QUERY_OPERATORS.put("eq", "=");
        QUERY_OPERATORS.put("ne", "!=");
        QUERY_OPERATORS.put("gt", ">");
        QUERY_OPERATORS.put("ge", ">=");
        QUERY_OPERATORS.put("lt", "<");
        QUERY_OPERATORS.put("le", "<=");
        QUERY_OPERATORS.put("like", "LIKE");
        QUERY_OPERATORS.put("in", "IN");
    }

    // ==================== 表结构操作 ====================

    @PostMapping("/table/create")
    @Operation(summary = "创建表", description = "根据实体定义和字段定义创建数据库表")
    public CommonResult<String> createTable(@RequestBody Map<String, Object> request,
                                            @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        String entityCode = (String) request.get("entityCode");

        // 获取实体定义
        Optional<MetaEntity> entityOpt = metaTableRepository.findEntityByCode(entityCode, tenantId);
        if (!entityOpt.isPresent()) {
            throw new BusinessException("实体[" + entityCode + "]不存在");
        }

        // 获取字段定义
        List<MetaField> fields = metaTableRepository.findFieldsByEntityCode(entityCode, tenantId);
        if (CollUtil.isEmpty(fields)) {
            throw new BusinessException("实体[" + entityCode + "]没有定义字段");
        }

        String tableName = metaTableService.createTable(entityOpt.get(), fields);
        return success(tableName);
    }

    @PostMapping("/table/alter")
    @Operation(summary = "修改表", description = "修改数据库表结构")
    public CommonResult<Void> alterTable(@RequestBody Map<String, Object> request,
                                          @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        String entityCode = (String) request.get("entityCode");
        @SuppressWarnings("unchecked")
        List<MetaField> newFields = (List<MetaField>) request.get("newFields");
        @SuppressWarnings("unchecked")
        List<MetaField> modifiedFields = (List<MetaField>) request.get("modifiedFields");

        // 获取实体定义
        Optional<MetaEntity> entityOpt = metaTableRepository.findEntityByCode(entityCode, tenantId);
        if (!entityOpt.isPresent()) {
            throw new BusinessException("实体[" + entityCode + "]不存在");
        }

        metaTableService.alterTable(entityOpt.get(), newFields, modifiedFields);
        return success();
    }

    @DeleteMapping("/table/drop/{tableName}")
    @Operation(summary = "删除表", description = "删除数据库表")
    public CommonResult<Void> dropTable(
            @Parameter(description = "表名") @PathVariable String tableName) {
        metaTableService.dropTable(tableName);
        return success();
    }

    @PostMapping("/table/sync/{entityId}")
    @Operation(summary = "同步字段", description = "根据实体定义同步数据库表字段")
    public CommonResult<Void> syncFields(
            @Parameter(description = "实体ID") @PathVariable String entityId,
            @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        metaTableService.syncFields(entityId);
        return success();
    }

    // ==================== 动态实体操作 ====================

    @PostMapping("/entity/register")
    @Operation(summary = "注册动态实体", description = "注册动态实体到系统")
    public CommonResult<Void> registerEntity(@RequestBody Map<String, Object> request,
                                              @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {
        String entityCode = (String) request.get("entityCode");
        String tableName = (String) request.get("tableName");
        @SuppressWarnings("unchecked")
        List<MetaField> fields = (List<MetaField>) request.get("fields");

        dynamicEntityService.registerDynamicEntity(entityCode, tableName, fields);
        return success();
    }

    @DeleteMapping("/entity/{entityCode}")
    @Operation(summary = "移除动态实体", description = "移除动态实体")
    public CommonResult<Void> removeEntity(
            @Parameter(description = "实体编码") @PathVariable String entityCode) {
        dynamicEntityService.removeDynamicEntity(entityCode);
        return success();
    }

    // ==================== CRUD 操作 ====================

    @PostMapping("/data/{entityCode}")
    @Operation(summary = "新增数据", description = "向指定实体表中插入数据")
    public CommonResult<Map<String, Object>> create(
            @Parameter(description = "实体编码") @PathVariable String entityCode,
            @RequestBody Map<String, Object> data,
            @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {

        String tableName = getTableName(entityCode, tenantId);
        if (StrUtil.isBlank(tableName)) {
            throw new BusinessException("实体[" + entityCode + "]未注册");
        }

        // 构建 INSERT 语句
        if (CollUtil.isEmpty(data)) {
            throw new BusinessException("数据不能为空");
        }

        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ").append(tableName).append(" (");

        StringBuilder placeholders = new StringBuilder();
        List<Object> params = new ArrayList<>();

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            // 验证字段名，防止SQL注入
            validateFieldName(entry.getKey());
            sql.append(entry.getKey()).append(",");
            placeholders.append("?,");
            params.add(entry.getValue());
        }

        // 添加租户ID
        sql.append("tenant_id,");
        placeholders.append("?,");
        params.add(tenantId);

        sql.deleteCharAt(sql.length() - 1);
        placeholders.deleteCharAt(placeholders.length() - 1);

        sql.append(") VALUES (").append(placeholders).append(")");

        jdbcTemplate.update(sql.toString(), params.toArray());

        log.info("动态新增数据到表[{}]成功，tenantId: {}", tableName, tenantId);
        return success(data);
    }

    @PutMapping("/data/{entityCode}/{id}")
    @Operation(summary = "更新数据", description = "更新指定实体表中的数据")
    public CommonResult<Void> update(
            @Parameter(description = "实体编码") @PathVariable String entityCode,
            @Parameter(description = "数据ID") @PathVariable Object id,
            @RequestBody Map<String, Object> data,
            @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {

        String tableName = getTableName(entityCode, tenantId);
        if (StrUtil.isBlank(tableName)) {
            throw new BusinessException("实体[" + entityCode + "]未注册");
        }

        if (CollUtil.isEmpty(data)) {
            throw new BusinessException("数据不能为空");
        }

        // 构建 UPDATE 语句
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ").append(tableName).append(" SET ");

        List<Object> params = new ArrayList<>();

        for (Map.Entry<String, Object> entry : data.entrySet()) {
            // 验证字段名，防止SQL注入
            validateFieldName(entry.getKey());
            sql.append(entry.getKey()).append("=?,");
            params.add(entry.getValue());
        }

        sql.deleteCharAt(sql.length() - 1);
        sql.append(" WHERE id=? AND tenant_id=?");

        params.add(id);
        params.add(tenantId);

        jdbcTemplate.update(sql.toString(), params.toArray());

        log.info("动态更新表[{}]数据[{}]成功，tenantId: {}", tableName, id, tenantId);
        return success();
    }

    @DeleteMapping("/data/{entityCode}/{id}")
    @Operation(summary = "删除数据", description = "删除指定实体表中的数据")
    public CommonResult<Void> delete(
            @Parameter(description = "实体编码") @PathVariable String entityCode,
            @Parameter(description = "数据ID") @PathVariable Object id,
            @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {

        String tableName = getTableName(entityCode, tenantId);
        if (StrUtil.isBlank(tableName)) {
            throw new BusinessException("实体[" + entityCode + "]未注册");
        }

        String sql = "DELETE FROM " + tableName + " WHERE id=? AND tenant_id=?";
        jdbcTemplate.update(sql, id, tenantId);

        log.info("动态删除表[{}]数据[{}]成功，tenantId: {}", tableName, id, tenantId);
        return success();
    }

    @GetMapping("/data/{entityCode}/{id}")
    @Operation(summary = "获取单条数据", description = "获取指定实体表中的单条数据")
    public CommonResult<Map<String, Object>> getById(
            @Parameter(description = "实体编码") @PathVariable String entityCode,
            @Parameter(description = "数据ID") @PathVariable Object id,
            @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {

        String tableName = getTableName(entityCode, tenantId);
        if (StrUtil.isBlank(tableName)) {
            throw new BusinessException("实体[" + entityCode + "]未注册");
        }

        String sql = "SELECT * FROM " + tableName + " WHERE id=? AND tenant_id=?";
        List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, id, tenantId);

        if (CollUtil.isEmpty(results)) {
            return success(null);
        }

        return success(results.get(0));
    }

    @GetMapping("/data/{entityCode}")
    @Operation(summary = "获取列表数据", description = "获取指定实体表中的列表数据，支持分页和高级查询")
    public CommonResult<PageResult<Map<String, Object>>> list(
            @Parameter(description = "实体编码") @PathVariable String entityCode,
            @Parameter(description = "查询参数") @RequestParam Map<String, Object> params,
            @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {

        String tableName = getTableName(entityCode, tenantId);
        if (StrUtil.isBlank(tableName)) {
            throw new BusinessException("实体[" + entityCode + "]未注册");
        }

        // 构建查询条件
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ").append(tableName).append(" WHERE tenant_id=?");

        List<Object> queryParams = new ArrayList<>();
        queryParams.add(tenantId);

        // 处理查询条件
        buildQueryConditions(params, sql, queryParams);

        // 排序
        String orderBy = (String) params.get("orderBy");
        String sortOrder = (String) params.get("sortOrder");
        if (StrUtil.isNotBlank(orderBy)) {
            validateFieldName(orderBy);
            sql.append(" ORDER BY ").append(orderBy);
            if ("asc".equalsIgnoreCase(sortOrder)) {
                sql.append(" ASC");
            } else {
                sql.append(" DESC");
            }
        } else {
            sql.append(" ORDER BY id DESC");
        }

        // 处理分页
        Integer pageNum = params.get("pageNum") != null ? Integer.parseInt(params.get("pageNum").toString()) : 1;
        Integer pageSize = params.get("pageSize") != null ? Integer.parseInt(params.get("pageSize").toString()) : 10;
        int offset = (pageNum - 1) * pageSize;
        sql.append(" LIMIT ").append(offset).append(",").append(pageSize);

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql.toString(), queryParams.toArray());

        // 查询总数
        String countSql = sql.toString().replaceAll("SELECT \\* FROM", "SELECT COUNT(*) FROM");
        countSql = countSql.substring(0, countSql.lastIndexOf("ORDER BY") > 0 ? countSql.lastIndexOf("ORDER BY") : countSql.length());
        countSql = countSql.substring(0, countSql.lastIndexOf("LIMIT") > 0 ? countSql.lastIndexOf("LIMIT") : countSql.length());
        Long total = jdbcTemplate.queryForObject(countSql, Long.class, queryParams.toArray());

        return success(PageResult.of(rows, total.intValue()));
    }

    @GetMapping("/data/{entityCode}/count")
    @Operation(summary = "获取数据总数", description = "获取指定实体表中的数据总数")
    public CommonResult<Long> count(
            @Parameter(description = "实体编码") @PathVariable String entityCode,
            @Parameter(description = "查询参数") @RequestParam Map<String, Object> params,
            @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {

        String tableName = getTableName(entityCode, tenantId);
        if (StrUtil.isBlank(tableName)) {
            throw new BusinessException("实体[" + entityCode + "]未注册");
        }

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(*) FROM ").append(tableName).append(" WHERE tenant_id=?");

        List<Object> queryParams = new ArrayList<>();
        queryParams.add(tenantId);

        // 处理查询条件（排除分页参数）
        if (CollUtil.isNotEmpty(params)) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                String key = entry.getKey();
                if ("pageNum".equals(key) || "pageSize".equals(key) || "orderBy".equals(key) || "sortOrder".equals(key)) {
                    continue;
                }
                handleQueryParam(key, entry.getValue(), sql, queryParams);
            }
        }

        Long total = jdbcTemplate.queryForObject(sql.toString(), Long.class, queryParams.toArray());
        return success(total);
    }

    @PostMapping("/data/{entityCode}/batch")
    @Operation(summary = "批量新增数据", description = "批量向指定实体表中插入数据")
    public CommonResult<Integer> batchCreate(
            @Parameter(description = "实体编码") @PathVariable String entityCode,
            @RequestBody List<Map<String, Object>> dataList,
            @RequestHeader(value = "tenantId", defaultValue = "1") String tenantId) {

        String tableName = getTableName(entityCode, tenantId);
        if (StrUtil.isBlank(tableName)) {
            throw new BusinessException("实体[" + entityCode + "]未注册");
        }

        if (CollUtil.isEmpty(dataList)) {
            return success(0);
        }

        int count = 0;
        for (Map<String, Object> data : dataList) {
            // 构建 INSERT 语句
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO ").append(tableName).append(" (");

            StringBuilder placeholders = new StringBuilder();
            List<Object> params = new ArrayList<>();

            for (Map.Entry<String, Object> entry : data.entrySet()) {
                // 验证字段名，防止SQL注入
                validateFieldName(entry.getKey());
                sql.append(entry.getKey()).append(",");
                placeholders.append("?,");
                params.add(entry.getValue());
            }

            // 添加租户ID
            sql.append("tenant_id,");
            placeholders.append("?,");
            params.add(tenantId);

            sql.deleteCharAt(sql.length() - 1);
            placeholders.deleteCharAt(placeholders.length() - 1);

            sql.append(") VALUES (").append(placeholders).append(")");

            count += jdbcTemplate.update(sql.toString(), params.toArray());
        }

        log.info("动态批量新增数据到表[{}]成功，共{}条，tenantId: {}", tableName, count, tenantId);
        return success(count);
    }

    // ==================== 辅助方法 ====================

    /**
     * 验证字段名，防止SQL注入
     */
    private void validateFieldName(String fieldName) {
        if (StrUtil.isBlank(fieldName)) {
            throw new BusinessException("字段名不能为空");
        }
        if (!VALID_FIELD_PATTERN.matcher(fieldName).matches()) {
            throw new BusinessException("字段名[" + fieldName + "]不合法");
        }
    }

    /**
     * 构建查询条件
     */
    private void buildQueryConditions(Map<String, Object> params, StringBuilder sql, List<Object> queryParams) {
        if (CollUtil.isEmpty(params)) {
            return;
        }

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            // 跳过分页和排序参数
            if ("pageNum".equals(key) || "pageSize".equals(key) || "orderBy".equals(key) || "sortOrder".equals(key)) {
                continue;
            }
            handleQueryParam(key, entry.getValue(), sql, queryParams);
        }
    }

    /**
     * 处理单个查询参数，支持多种操作符
     */
    private void handleQueryParam(String key, Object value, StringBuilder sql, List<Object> queryParams) {
        if (value == null || (value instanceof String && StrUtil.isBlank((String) value))) {
            return;
        }

        // 解析字段名和操作符
        String fieldName = key;
        String operator = "=";

        // 检查是否包含操作符后缀，如 name_like, age_gt
        for (Map.Entry<String, String> opEntry : QUERY_OPERATORS.entrySet()) {
            String suffix = "_" + opEntry.getKey();
            if (key.endsWith(suffix)) {
                fieldName = key.substring(0, key.length() - suffix.length());
                operator = opEntry.getValue();
                break;
            }
        }

        // 验证字段名
        validateFieldName(fieldName);

        // 根据操作符处理
        if ("IN".equals(operator)) {
            // IN 查询
            if (value instanceof List) {
                List<?> list = (List<?>) value;
                if (!list.isEmpty()) {
                    StringBuilder inPlaceholders = new StringBuilder("(");
                    for (int i = 0; i < list.size(); i++) {
                        inPlaceholders.append("?,");
                        queryParams.add(list.get(i));
                    }
                    inPlaceholders.deleteCharAt(inPlaceholders.length() - 1);
                    inPlaceholders.append(")");
                    sql.append(" AND ").append(fieldName).append(" IN ").append(inPlaceholders);
                }
            } else if (value instanceof String) {
                // 逗号分隔的字符串
                String[] arr = ((String) value).split(",");
                if (arr.length > 0) {
                    StringBuilder inPlaceholders = new StringBuilder("(");
                    for (String s : arr) {
                        inPlaceholders.append("?,");
                        queryParams.add(s.trim());
                    }
                    inPlaceholders.deleteCharAt(inPlaceholders.length() - 1);
                    inPlaceholders.append(")");
                    sql.append(" AND ").append(fieldName).append(" IN ").append(inPlaceholders);
                }
            }
        } else if ("LIKE".equals(operator)) {
            // LIKE 查询
            sql.append(" AND ").append(fieldName).append(" LIKE ?");
            queryParams.add("%" + value + "%");
        } else {
            // 普通查询
            sql.append(" AND ").append(fieldName).append(" ").append(operator).append(" ?");
            queryParams.add(value);
        }
    }

    /**
     * 获取表名
     */
    private String getTableName(String entityCode, String tenantId) {
        // 先从实体定义中获取
        Optional<MetaEntity> entityOpt = metaTableRepository.findEntityByCode(entityCode, tenantId);
        if (entityOpt.isPresent() && StrUtil.isNotBlank(entityOpt.get().getTableName())) {
            return entityOpt.get().getTableName();
        }

        return null;
    }

}
