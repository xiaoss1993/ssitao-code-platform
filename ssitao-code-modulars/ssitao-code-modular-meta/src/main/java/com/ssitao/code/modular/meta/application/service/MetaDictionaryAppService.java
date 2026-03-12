package com.ssitao.code.modular.meta.application.service;

import com.ssitao.code.common.pojo.PageResult;
import com.ssitao.code.modular.meta.domain.model.MetaDictionary;

import java.util.List;

/**
 * 字典类型应用服务接口
 *
 * @author ssitao-code
 */
public interface MetaDictionaryAppService {

    /**
     * 根据ID获取字典类型
     */
    MetaDictionary getDictionaryById(String id);

    /**
     * 获取字典类型列表
     */
    List<MetaDictionary> listDictionaries(String tenantId);

    /**
     * 分页获取字典类型列表
     */
    PageResult<MetaDictionary> listDictionariesPage(Integer page, Integer size, String tenantId, String sort, String order);

    /**
     * 根据字典类型编码获取字典类型
     */
    MetaDictionary getDictionaryByCode(String dictTypeCode, String tenantId);

    /**
     * 创建字典类型
     */
    String createDictionary(MetaDictionary dictionary);

    /**
     * 更新字典类型
     */
    void updateDictionary(MetaDictionary dictionary);

    /**
     * 删除字典类型
     */
    void deleteDictionary(String id);

    /**
     * 批量删除字典类型
     */
    void batchDeleteDictionaries(List<String> ids);
}
