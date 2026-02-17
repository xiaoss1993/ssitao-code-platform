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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreGroupquery;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreGroupqueryService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 高级查询配置 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbCoreGroupquery")
public class TbCoreGroupqueryController {

    @Autowired
    private TbCoreGroupqueryService tbCoreGroupqueryService;

    /**
     * 保存高级查询配置。
     *
     * @param tbCoreGroupquery 高级查询配置
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreGroupquery tbCoreGroupquery) {
        return tbCoreGroupqueryService.save(tbCoreGroupquery);
    }

    /**
     * 根据主键删除高级查询配置。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreGroupqueryService.removeById(id);
    }

    /**
     * 根据主键更新高级查询配置。
     *
     * @param tbCoreGroupquery 高级查询配置
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreGroupquery tbCoreGroupquery) {
        return tbCoreGroupqueryService.updateById(tbCoreGroupquery);
    }

    /**
     * 查询所有高级查询配置。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreGroupquery> list() {
        return tbCoreGroupqueryService.list();
    }

    /**
     * 根据主键获取高级查询配置。
     *
     * @param id 高级查询配置主键
     * @return 高级查询配置详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreGroupquery getInfo(@PathVariable String id) {
        return tbCoreGroupqueryService.getById(id);
    }

    /**
     * 分页查询高级查询配置。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreGroupquery> page(Page<TbCoreGroupquery> page) {
        return tbCoreGroupqueryService.page(page);
    }

}
