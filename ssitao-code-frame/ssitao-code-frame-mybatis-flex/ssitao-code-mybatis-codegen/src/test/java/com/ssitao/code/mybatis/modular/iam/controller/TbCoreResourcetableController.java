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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreResourcetable;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreResourcetableService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 资源 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbCoreResourcetable")
public class TbCoreResourcetableController {

    @Autowired
    private TbCoreResourcetableService tbCoreResourcetableService;

    /**
     * 保存资源。
     *
     * @param tbCoreResourcetable 资源
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreResourcetable tbCoreResourcetable) {
        return tbCoreResourcetableService.save(tbCoreResourcetable);
    }

    /**
     * 根据主键删除资源。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreResourcetableService.removeById(id);
    }

    /**
     * 根据主键更新资源。
     *
     * @param tbCoreResourcetable 资源
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreResourcetable tbCoreResourcetable) {
        return tbCoreResourcetableService.updateById(tbCoreResourcetable);
    }

    /**
     * 查询所有资源。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreResourcetable> list() {
        return tbCoreResourcetableService.list();
    }

    /**
     * 根据主键获取资源。
     *
     * @param id 资源主键
     * @return 资源详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreResourcetable getInfo(@PathVariable String id) {
        return tbCoreResourcetableService.getById(id);
    }

    /**
     * 分页查询资源。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreResourcetable> page(Page<TbCoreResourcetable> page) {
        return tbCoreResourcetableService.page(page);
    }

}
