package com.ssitao.code.modular.meta.domain.repository;

import com.ssitao.code.modular.meta.domain.model.MetaDictionary;

import java.util.List;
import java.util.Optional;

/**
 * 字典类型仓储接口
 *
 * @author ssitao-code
 */
public interface MetaDictionaryRepository {

    /**
     * 根据ID查询
     */
    Optional<MetaDictionary> findById(String id);

    /**
     * 查询所有
     */
    List<MetaDictionary> findAll(String tenantId);

    /**
     * 根据字典类型编码查询
     */
    Optional<MetaDictionary> findByDictTypeCode(String dictTypeCode, String tenantId);

    /**
     * 保存
     */
    String save(MetaDictionary dictionary);

    /**
     * 更新
     */
    void update(MetaDictionary dictionary);

    /**
     * 删除
     */
    void delete(String id);

    /**
     * 批量删除
     */
    void batchDelete(List<String> ids);

    /**
     * 判断字典类型编码是否存在
     */
    boolean existsByDictTypeCode(String dictTypeCode, String tenantId, String excludeId);
}
