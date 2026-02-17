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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamSupplier;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamSupplierService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 供应商管理 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbIamSupplier")
public class TbIamSupplierController {

    @Autowired
    private TbIamSupplierService tbIamSupplierService;

    /**
     * 保存供应商管理。
     *
     * @param tbIamSupplier 供应商管理
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbIamSupplier tbIamSupplier) {
        return tbIamSupplierService.save(tbIamSupplier);
    }

    /**
     * 根据主键删除供应商管理。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbIamSupplierService.removeById(id);
    }

    /**
     * 根据主键更新供应商管理。
     *
     * @param tbIamSupplier 供应商管理
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbIamSupplier tbIamSupplier) {
        return tbIamSupplierService.updateById(tbIamSupplier);
    }

    /**
     * 查询所有供应商管理。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbIamSupplier> list() {
        return tbIamSupplierService.list();
    }

    /**
     * 根据主键获取供应商管理。
     *
     * @param id 供应商管理主键
     * @return 供应商管理详情
     */
    @GetMapping("getInfo/{id}")
    public TbIamSupplier getInfo(@PathVariable String id) {
        return tbIamSupplierService.getById(id);
    }

    /**
     * 分页查询供应商管理。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbIamSupplier> page(Page<TbIamSupplier> page) {
        return tbIamSupplierService.page(page);
    }

}
