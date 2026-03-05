package com.ssitao.code.modular.meta.infrastructure.repository;

import com.ssitao.code.modular.meta.domain.model.MetaEntity;
import com.ssitao.code.modular.meta.domain.model.MetaField;
import com.ssitao.code.modular.meta.domain.repository.MetaTableRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 表实体仓储实现（内存存储）
 * TODO: 后续需要实现持久化到数据库
 *
 * @author ssitao-code
 */
@Slf4j
@Repository
public class MetaTableRepositoryImpl implements MetaTableRepository {

    /**
     * 实体存储（内存）
     * key: tenantId_entityCode
     */
    private final Map<String, MetaEntity> entityStore = new ConcurrentHashMap<>();

    /**
     * 字段存储（内存）
     * key: tenantId_entityId_fieldId
     */
    private final Map<String, MetaField> fieldStore = new ConcurrentHashMap<>();

    @Override
    public String saveEntity(MetaEntity entity) {
        String key = getEntityKey(entity.getTenantId(), entity.getEntityCode());
        entityStore.put(key, entity);
        log.info("保存实体定义: entityCode={}, tenantId={}", entity.getEntityCode(), entity.getTenantId());
        return entity.getId();
    }

    @Override
    public void updateEntity(MetaEntity entity) {
        String key = getEntityKey(entity.getTenantId(), entity.getEntityCode());
        entityStore.put(key, entity);
        log.info("更新实体定义: entityCode={}, tenantId={}", entity.getEntityCode(), entity.getTenantId());
    }

    @Override
    public void deleteEntityById(String id, String tenantId) {
        // 找到并删除实体
        entityStore.entrySet().removeIf(entry -> {
            MetaEntity entity = entry.getValue();
            boolean match = entity.getId().equals(id) &&
                    (tenantId == null || tenantId.equals(entity.getTenantId()));
            if (match) {
                // 删除关联的字段
                deleteFieldsByEntityId(entity.getId(), tenantId);
            }
            return match;
        });
        log.info("删除实体定义: id={}, tenantId={}", id, tenantId);
    }

    @Override
    public Optional<MetaEntity> findEntityById(String id, String tenantId) {
        return entityStore.values().stream()
                .filter(e -> e.getId().equals(id) && (tenantId == null || tenantId.equals(e.getTenantId())))
                .findFirst();
    }

    @Override
    public Optional<MetaEntity> findEntityByCode(String entityCode, String tenantId) {
        String key = getEntityKey(tenantId, entityCode);
        return Optional.ofNullable(entityStore.get(key));
    }

    @Override
    public Optional<MetaEntity> findEntityByTableName(String tableName, String tenantId) {
        return entityStore.values().stream()
                .filter(e -> tableName.equals(e.getTableName()) && (tenantId == null || tenantId.equals(e.getTenantId())))
                .findFirst();
    }

    @Override
    public List<MetaEntity> findAllEntities(String tenantId) {
        return entityStore.values().stream()
                .filter(e -> tenantId == null || tenantId.equals(e.getTenantId()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsEntityByCode(String entityCode, String tenantId, String excludeId) {
        return entityStore.values().stream()
                .anyMatch(e -> entityCode.equals(e.getEntityCode()) &&
                        (tenantId == null || tenantId.equals(e.getTenantId())) &&
                        (excludeId == null || !excludeId.equals(e.getId())));
    }

    @Override
    public boolean existsEntityByTableName(String tableName, String tenantId, String excludeId) {
        return entityStore.values().stream()
                .anyMatch(e -> tableName.equals(e.getTableName()) &&
                        (tenantId == null || tenantId.equals(e.getTenantId())) &&
                        (excludeId == null || !excludeId.equals(e.getId())));
    }

    @Override
    public String saveField(MetaField field) {
        String key = getFieldKey(field.getTenantId(), field.getEntityId(), field.getId());
        fieldStore.put(key, field);
        log.info("保存字段定义: fieldCode={}, entityId={}", field.getFieldCode(), field.getEntityId());
        return field.getId();
    }

    @Override
    public List<String> batchSaveFields(List<MetaField> fields) {
        return fields.stream()
                .map(this::saveField)
                .collect(Collectors.toList());
    }

    @Override
    public void updateField(MetaField field) {
        String key = getFieldKey(field.getTenantId(), field.getEntityId(), field.getId());
        fieldStore.put(key, field);
        log.info("更新字段定义: fieldCode={}, entityId={}", field.getFieldCode(), field.getEntityId());
    }

    @Override
    public void deleteFieldById(String id, String tenantId) {
        fieldStore.entrySet().removeIf(entry -> {
            MetaField field = entry.getValue();
            return field.getId().equals(id) && (tenantId == null || tenantId.equals(field.getTenantId()));
        });
        log.info("删除字段定义: id={}", id);
    }

    @Override
    public void deleteFieldsByEntityId(String entityId, String tenantId) {
        fieldStore.entrySet().removeIf(entry -> {
            MetaField field = entry.getValue();
            return field.getEntityId().equals(entityId) && (tenantId == null || tenantId.equals(field.getTenantId()));
        });
        log.info("删除实体所有字段: entityId={}", entityId);
    }

    @Override
    public Optional<MetaField> findFieldById(String id, String tenantId) {
        return fieldStore.values().stream()
                .filter(f -> f.getId().equals(id) && (tenantId == null || tenantId.equals(f.getTenantId())))
                .findFirst();
    }

    @Override
    public List<MetaField> findFieldsByEntityId(String entityId, String tenantId) {
        return fieldStore.values().stream()
                .filter(f -> f.getEntityId().equals(entityId) && (tenantId == null || tenantId.equals(f.getTenantId())))
                .collect(Collectors.toList());
    }

    @Override
    public List<MetaField> findFieldsByEntityCode(String entityCode, String tenantId) {
        // 先找到实体
        Optional<MetaEntity> entityOpt = findEntityByCode(entityCode, tenantId);
        if (!entityOpt.isPresent()) {
            return Collections.emptyList();
        }
        return findFieldsByEntityId(entityOpt.get().getId(), tenantId);
    }

    @Override
    public boolean existsFieldByCode(String entityId, String fieldCode, String tenantId, String excludeId) {
        return fieldStore.values().stream()
                .anyMatch(f -> f.getEntityId().equals(entityId) &&
                        fieldCode.equals(f.getFieldCode()) &&
                        (tenantId == null || tenantId.equals(f.getTenantId())) &&
                        (excludeId == null || !excludeId.equals(f.getId())));
    }

    private String getEntityKey(String tenantId, String entityCode) {
        return (tenantId == null ? "default" : tenantId) + "_" + entityCode;
    }

    private String getFieldKey(String tenantId, String entityId, String fieldId) {
        return (tenantId == null ? "default" : tenantId) + "_" + entityId + "_" + fieldId;
    }
}
