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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamBury;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamBuryService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 操作埋点记录 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbIamBury")
public class TbIamBuryController {

    @Autowired
    private TbIamBuryService tbIamBuryService;

    /**
     * 保存操作埋点记录。
     *
     * @param tbIamBury 操作埋点记录
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbIamBury tbIamBury) {
        return tbIamBuryService.save(tbIamBury);
    }

    /**
     * 根据主键删除操作埋点记录。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbIamBuryService.removeById(id);
    }

    /**
     * 根据主键更新操作埋点记录。
     *
     * @param tbIamBury 操作埋点记录
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbIamBury tbIamBury) {
        return tbIamBuryService.updateById(tbIamBury);
    }

    /**
     * 查询所有操作埋点记录。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbIamBury> list() {
        return tbIamBuryService.list();
    }

    /**
     * 根据主键获取操作埋点记录。
     *
     * @param id 操作埋点记录主键
     * @return 操作埋点记录详情
     */
    @GetMapping("getInfo/{id}")
    public TbIamBury getInfo(@PathVariable String id) {
        return tbIamBuryService.getById(id);
    }

    /**
     * 分页查询操作埋点记录。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbIamBury> page(Page<TbIamBury> page) {
        return tbIamBuryService.page(page);
    }

}
