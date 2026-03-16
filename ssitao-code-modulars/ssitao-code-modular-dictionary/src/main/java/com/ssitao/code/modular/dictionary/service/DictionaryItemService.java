
package com.ssitao.code.modular.dictionary.service;

import com.ssitao.code.modular.dictionary.entity.DictionaryItemEntity;
import com.ssitao.code.commons.service.CrudService;
import com.ssitao.code.commons.service.TreeService;

import java.util.List;

/**
 * 数据字典选项 服务类
 *
 *
 */
public interface DictionaryItemService extends TreeService<DictionaryItemEntity, String>
        , CrudService<DictionaryItemEntity, String> {

    List<DictionaryItemEntity> selectByDictId(String dictId);
}
