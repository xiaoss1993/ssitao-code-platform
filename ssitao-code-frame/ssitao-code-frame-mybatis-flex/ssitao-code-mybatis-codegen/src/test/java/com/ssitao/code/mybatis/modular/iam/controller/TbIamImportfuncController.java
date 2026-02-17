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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamImportfunc;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamImportfuncService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 授权功能 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbIamImportfunc")
public class TbIamImportfuncController {

    @Autowired
    private TbIamImportfuncService tbIamImportfuncService;

    /**
     * 保存授权功能。
     *
     * @param tbIamImportfunc 授权功能
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbIamImportfunc tbIamImportfunc) {
        return tbIamImportfuncService.save(tbIamImportfunc);
    }

    /**
     * 根据主键删除授权功能。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbIamImportfuncService.removeById(id);
    }

    /**
     * 根据主键更新授权功能。
     *
     * @param tbIamImportfunc 授权功能
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbIamImportfunc tbIamImportfunc) {
        return tbIamImportfuncService.updateById(tbIamImportfunc);
    }

    /**
     * 查询所有授权功能。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbIamImportfunc> list() {
        return tbIamImportfuncService.list();
    }

    /**
     * 根据主键获取授权功能。
     *
     * @param id 授权功能主键
     * @return 授权功能详情
     */
    @GetMapping("getInfo/{id}")
    public TbIamImportfunc getInfo(@PathVariable String id) {
        return tbIamImportfuncService.getById(id);
    }

    /**
     * 分页查询授权功能。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbIamImportfunc> page(Page<TbIamImportfunc> page) {
        return tbIamImportfuncService.page(page);
    }

}
