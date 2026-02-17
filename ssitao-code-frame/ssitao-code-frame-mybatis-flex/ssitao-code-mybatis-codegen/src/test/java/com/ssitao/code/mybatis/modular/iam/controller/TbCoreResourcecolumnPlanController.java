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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreResourcecolumnPlan;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreResourcecolumnPlanService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 列方案 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbCoreResourcecolumnPlan")
public class TbCoreResourcecolumnPlanController {

    @Autowired
    private TbCoreResourcecolumnPlanService tbCoreResourcecolumnPlanService;

    /**
     * 保存列方案。
     *
     * @param tbCoreResourcecolumnPlan 列方案
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreResourcecolumnPlan tbCoreResourcecolumnPlan) {
        return tbCoreResourcecolumnPlanService.save(tbCoreResourcecolumnPlan);
    }

    /**
     * 根据主键删除列方案。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreResourcecolumnPlanService.removeById(id);
    }

    /**
     * 根据主键更新列方案。
     *
     * @param tbCoreResourcecolumnPlan 列方案
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreResourcecolumnPlan tbCoreResourcecolumnPlan) {
        return tbCoreResourcecolumnPlanService.updateById(tbCoreResourcecolumnPlan);
    }

    /**
     * 查询所有列方案。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreResourcecolumnPlan> list() {
        return tbCoreResourcecolumnPlanService.list();
    }

    /**
     * 根据主键获取列方案。
     *
     * @param id 列方案主键
     * @return 列方案详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreResourcecolumnPlan getInfo(@PathVariable String id) {
        return tbCoreResourcecolumnPlanService.getById(id);
    }

    /**
     * 分页查询列方案。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreResourcecolumnPlan> page(Page<TbCoreResourcecolumnPlan> page) {
        return tbCoreResourcecolumnPlanService.page(page);
    }

}
