

package com.ssitao.code.modular.dictionary.controller;

import com.ssitao.code.commons.controller.SimpleGenericEntityController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.ssitao.code.frame.authorization.annotation.Authorize;
import com.ssitao.code.commons.entity.param.QueryParamEntity;
import com.ssitao.code.commons.controller.message.ResponseMessage;
import com.ssitao.code.commons.utils.dict.DictDefine;
import com.ssitao.code.commons.utils.dict.DictDefineRepository;
import com.ssitao.code.commons.utils.dict.EnumDict;
import com.ssitao.code.modular.dictionary.service.DictionaryService;
import com.ssitao.code.modular.dictionary.entity.DictionaryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static com.ssitao.code.commons.controller.message.ResponseMessage.*;

/**
 * 数据字典
 *
 *
 */
@RestController
@RequestMapping("${ssitao.code.web.mappings.dictionary:dictionary}")
@Authorize(permission = "dictionary", description = "数据字典管理")
@Api(value = "数据字典", tags = "数据字典-字典配置")
public class DictionaryController implements SimpleGenericEntityController<DictionaryEntity, String, QueryParamEntity> {

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private DictDefineRepository repository;

    @Override
    public DictionaryService getService() {
        return dictionaryService;
    }

    @GetMapping("/define/all")
    @Authorize(merge = false)
    @ApiOperation("获取数据全部字典定义信息")
    public ResponseMessage<List<DictDefine>> getAllDefineById() {
        return ok(repository.getAllDefine());
    }

    @GetMapping("/define/{id:.+}")
    @Authorize(merge = false)
    @ApiOperation("获取数据字典定义信息")
    public ResponseMessage<DictDefine> getDefineById(@PathVariable String id) {
        return ok(repository.getDefine(id));
    }

    @GetMapping("/define/{id:.+}/items")
    @Authorize(merge = false)
    @ApiOperation("获取数据字典选项信息")
    public ResponseMessage<List<EnumDict<Object>>> getItemDefineById(@PathVariable String id) {
        return ok(Optional.ofNullable(repository.getDefine(id))
                .map(DictDefine::getItems)
                .orElse(new java.util.ArrayList<>()));
    }

}
