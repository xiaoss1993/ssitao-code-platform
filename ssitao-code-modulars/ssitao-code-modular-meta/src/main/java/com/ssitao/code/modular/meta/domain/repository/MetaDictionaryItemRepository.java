package com.ssitao.code.modular.meta.domain.repository;

import com.ssitao.code.modular.meta.domain.model.MetaDictionaryItem;

import java.util.List;
import java.util.Optional;

/**
 * 字典数据仓储接口
 *
 * @author ssitao-code
 */
public interface MetaDictionaryItemRepository {

    /**
     * 根据ID查询
     */
    Optional<MetaDictionaryItem> findById(String id);

    /**
     * 查询所有
     */
    List<MetaDictionaryItem> findAll(String tenantId);

    /**
     * 根据字典类型ID查询
     */
    List<MetaDictionaryItem> findByDictTypeId(String dictTypeId, String tenantId);

    /**
     * 根据字典类型编码查询
     */
    List<MetaDictionaryItem> findByDictTypeCode(String dictTypeCode, String tenantId);

    /**
     * 根据字典类型编码查询启用的字典项
     */
    List<MetaDictionaryItem> findByDictTypeCodeAndStatus(String dictTypeCode, Integer status, String tenantId);

    /**
     * 保存
     */
    String save(MetaDictionaryItem item);

    /**
     * 更新
     */
    void update(MetaDictionaryItem item);

    /**
     * 删除
     */
    void delete(String id);

    /**
     * 批量删除
     */
    void batchDelete(List<String> ids);

    /**
     * 根据字典类型ID删除
     */
    void deleteByDictTypeId(String dictTypeId);
}
