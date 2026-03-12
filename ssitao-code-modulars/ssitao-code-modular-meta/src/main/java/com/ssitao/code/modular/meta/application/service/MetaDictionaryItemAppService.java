package com.ssitao.code.modular.meta.application.service;

import com.ssitao.code.common.pojo.PageResult;
import com.ssitao.code.modular.meta.domain.model.MetaDictionaryItem;

import java.util.List;

/**
 * 字典数据应用服务接口
 *
 * @author ssitao-code
 */
public interface MetaDictionaryItemAppService {

    /**
     * 根据ID获取字典数据
     */
    MetaDictionaryItem getDictionaryItemById(String id);

    /**
     * 获取字典数据列表
     */
    List<MetaDictionaryItem> listDictionaryItems(String tenantId);

    /**
     * 根据字典类型ID获取字典数据列表
     */
    List<MetaDictionaryItem> listDictionaryItemsByTypeId(String dictTypeId, String tenantId);

    /**
     * 根据字典类型编码获取字典数据列表
     */
    List<MetaDictionaryItem> listDictionaryItemsByTypeCode(String dictTypeCode, String tenantId);

    /**
     * 根据字典类型编码获取启用的字典数据列表
     */
    List<MetaDictionaryItem> listEnabledDictionaryItemsByTypeCode(String dictTypeCode, String tenantId);

    /**
     * 分页获取字典数据列表
     */
    PageResult<MetaDictionaryItem> listDictionaryItemsPage(Integer page, Integer size, String dictId, String tenantId, String sort, String order);

    /**
     * 创建字典数据
     */
    String createDictionaryItem(MetaDictionaryItem item);

    /**
     * 更新字典数据
     */
    void updateDictionaryItem(MetaDictionaryItem item);

    /**
     * 删除字典数据
     */
    void deleteDictionaryItem(String id);

    /**
     * 批量删除字典数据
     */
    void batchDeleteDictionaryItems(List<String> ids);

    /**
     * 根据字典类型ID删除字典数据
     */
    void deleteDictionaryItemsByTypeId(String dictTypeId);
}
