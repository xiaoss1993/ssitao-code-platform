package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.controller;

import com.ssitao.code.frame.mybatisflex.core.paginate.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreDictionary;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreDictionaryService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 数据字典 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbCoreDictionary")
public class TbCoreDictionaryController {

    @Autowired
    private TbCoreDictionaryService tbCoreDictionaryService;

    /**
     * 保存数据字典。
     *
     * @param tbCoreDictionary 数据字典
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreDictionary tbCoreDictionary) {
        return tbCoreDictionaryService.save(tbCoreDictionary);
    }

    /**
     * 根据主键删除数据字典。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreDictionaryService.removeById(id);
    }

    /**
     * 根据主键更新数据字典。
     *
     * @param tbCoreDictionary 数据字典
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreDictionary tbCoreDictionary) {
        return tbCoreDictionaryService.updateById(tbCoreDictionary);
    }

    /**
     * 查询所有数据字典。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreDictionary> list() {
        return tbCoreDictionaryService.list();
    }

    /**
     * 根据主键获取数据字典。
     *
     * @param id 数据字典主键
     * @return 数据字典详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreDictionary getInfo(@PathVariable String id) {
        return tbCoreDictionaryService.getById(id);
    }

    /**
     * 分页查询数据字典。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreDictionary> page(Page<TbCoreDictionary> page) {
        return tbCoreDictionaryService.page(page);
    }

}
