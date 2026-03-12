package com.ssitao.code.modular.meta.domain.repository;

import com.ssitao.code.modular.meta.domain.model.MetaEntity;

import java.util.List;
import java.util.Optional;

/**
 * 元数据实体仓储接口
 *
 * @author ssitao-code
 */
public interface MetaEntityRepository {

    /**
     * 保存实体
     *
     * @param entity 实体
     * @return 实体ID
     */
    String save(MetaEntity entity);

    /**
     * 更新实体
     *
     * @param entity 实体
     */
    void update(MetaEntity entity);

    /**
     * 删除实体
     *
     * @param id 实体ID
     * @param tenantId 租户ID
     */
    void delete(String id, String tenantId);

    /**
     * 根据ID查询
     *
     * @param id 实体ID
     * @param tenantId 租户ID
     * @return 实体
     */
    Optional<MetaEntity> findById(String id, String tenantId);

    /**
     * 查询所有
     *
     * @param tenantId 租户ID
     * @return 实体列表
     */
    List<MetaEntity> findAll(String tenantId);

    /**
     * 分页查询
     *
     * @param keyword 关键词
     * @param page 页码
     * @param limit 每页条数
     * @param sort 排序字段
     * @param order 排序方式
     * @param tenantId 租户ID
     * @return 实体列表
     */
    List<MetaEntity> page(String keyword, int page, int limit, String sort, String order, String tenantId);

    /**
     * 检查编码是否存在
     *
     * @param entityCode 实体编码
     * @param tenantId 租户ID
     * @param excludeId 排除的ID
     * @return 是否存在
     */
    boolean existsByCode(String entityCode, String tenantId, String excludeId);
}
