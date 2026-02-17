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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SDictItem;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SDictItemService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 数据字典选项配置 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/sDictItem")
public class SDictItemController {

    @Autowired
    private SDictItemService sDictItemService;

    /**
     * 保存数据字典选项配置。
     *
     * @param sDictItem 数据字典选项配置
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody SDictItem sDictItem) {
        return sDictItemService.save(sDictItem);
    }

    /**
     * 根据主键删除数据字典选项配置。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return sDictItemService.removeById(id);
    }

    /**
     * 根据主键更新数据字典选项配置。
     *
     * @param sDictItem 数据字典选项配置
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody SDictItem sDictItem) {
        return sDictItemService.updateById(sDictItem);
    }

    /**
     * 查询所有数据字典选项配置。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<SDictItem> list() {
        return sDictItemService.list();
    }

    /**
     * 根据主键获取数据字典选项配置。
     *
     * @param id 数据字典选项配置主键
     * @return 数据字典选项配置详情
     */
    @GetMapping("getInfo/{id}")
    public SDictItem getInfo(@PathVariable String id) {
        return sDictItemService.getById(id);
    }

    /**
     * 分页查询数据字典选项配置。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<SDictItem> page(Page<SDictItem> page) {
        return sDictItemService.page(page);
    }

}
