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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreDataflow;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreDataflowService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 数据流转策略 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbCoreDataflow")
public class TbCoreDataflowController {

    @Autowired
    private TbCoreDataflowService tbCoreDataflowService;

    /**
     * 保存数据流转策略。
     *
     * @param tbCoreDataflow 数据流转策略
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreDataflow tbCoreDataflow) {
        return tbCoreDataflowService.save(tbCoreDataflow);
    }

    /**
     * 根据主键删除数据流转策略。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreDataflowService.removeById(id);
    }

    /**
     * 根据主键更新数据流转策略。
     *
     * @param tbCoreDataflow 数据流转策略
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreDataflow tbCoreDataflow) {
        return tbCoreDataflowService.updateById(tbCoreDataflow);
    }

    /**
     * 查询所有数据流转策略。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreDataflow> list() {
        return tbCoreDataflowService.list();
    }

    /**
     * 根据主键获取数据流转策略。
     *
     * @param id 数据流转策略主键
     * @return 数据流转策略详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreDataflow getInfo(@PathVariable String id) {
        return tbCoreDataflowService.getById(id);
    }

    /**
     * 分页查询数据流转策略。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreDataflow> page(Page<TbCoreDataflow> page) {
        return tbCoreDataflowService.page(page);
    }

}
