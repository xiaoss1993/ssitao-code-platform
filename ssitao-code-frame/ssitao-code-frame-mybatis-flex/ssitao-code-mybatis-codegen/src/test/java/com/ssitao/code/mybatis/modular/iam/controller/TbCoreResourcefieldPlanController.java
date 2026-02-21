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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreResourcefieldPlan;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreResourcefieldPlanService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 单方案 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbCoreResourcefieldPlan")
public class TbCoreResourcefieldPlanController {

    @Autowired
    private TbCoreResourcefieldPlanService tbCoreResourcefieldPlanService;

    /**
     * 保存单方案。
     *
     * @param tbCoreResourcefieldPlan 单方案
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreResourcefieldPlan tbCoreResourcefieldPlan) {
        return tbCoreResourcefieldPlanService.save(tbCoreResourcefieldPlan);
    }

    /**
     * 根据主键删除单方案。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreResourcefieldPlanService.removeById(id);
    }

    /**
     * 根据主键更新单方案。
     *
     * @param tbCoreResourcefieldPlan 单方案
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreResourcefieldPlan tbCoreResourcefieldPlan) {
        return tbCoreResourcefieldPlanService.updateById(tbCoreResourcefieldPlan);
    }

    /**
     * 查询所有单方案。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreResourcefieldPlan> list() {
        return tbCoreResourcefieldPlanService.list();
    }

    /**
     * 根据主键获取单方案。
     *
     * @param id 单方案主键
     * @return 单方案详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreResourcefieldPlan getInfo(@PathVariable String id) {
        return tbCoreResourcefieldPlanService.getById(id);
    }

    /**
     * 分页查询单方案。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreResourcefieldPlan> page(Page<TbCoreResourcefieldPlan> page) {
        return tbCoreResourcefieldPlanService.page(page);
    }

}
