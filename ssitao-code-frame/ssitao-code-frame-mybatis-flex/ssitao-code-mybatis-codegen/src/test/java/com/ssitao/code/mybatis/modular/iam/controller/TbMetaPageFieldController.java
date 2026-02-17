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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbMetaPageField;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbMetaPageFieldService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 页面字段 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbMetaPageField")
public class TbMetaPageFieldController {

    @Autowired
    private TbMetaPageFieldService tbMetaPageFieldService;

    /**
     * 保存页面字段。
     *
     * @param tbMetaPageField 页面字段
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbMetaPageField tbMetaPageField) {
        return tbMetaPageFieldService.save(tbMetaPageField);
    }

    /**
     * 根据主键删除页面字段。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbMetaPageFieldService.removeById(id);
    }

    /**
     * 根据主键更新页面字段。
     *
     * @param tbMetaPageField 页面字段
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbMetaPageField tbMetaPageField) {
        return tbMetaPageFieldService.updateById(tbMetaPageField);
    }

    /**
     * 查询所有页面字段。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbMetaPageField> list() {
        return tbMetaPageFieldService.list();
    }

    /**
     * 根据主键获取页面字段。
     *
     * @param id 页面字段主键
     * @return 页面字段详情
     */
    @GetMapping("getInfo/{id}")
    public TbMetaPageField getInfo(@PathVariable String id) {
        return tbMetaPageFieldService.getById(id);
    }

    /**
     * 分页查询页面字段。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbMetaPageField> page(Page<TbMetaPageField> page) {
        return tbMetaPageFieldService.page(page);
    }

}
