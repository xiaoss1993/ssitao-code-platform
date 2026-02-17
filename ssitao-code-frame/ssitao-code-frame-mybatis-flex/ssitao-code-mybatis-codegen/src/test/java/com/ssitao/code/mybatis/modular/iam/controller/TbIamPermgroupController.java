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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamPermgroup;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamPermgroupService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 权限组 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbIamPermgroup")
public class TbIamPermgroupController {

    @Autowired
    private TbIamPermgroupService tbIamPermgroupService;

    /**
     * 保存权限组。
     *
     * @param tbIamPermgroup 权限组
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbIamPermgroup tbIamPermgroup) {
        return tbIamPermgroupService.save(tbIamPermgroup);
    }

    /**
     * 根据主键删除权限组。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbIamPermgroupService.removeById(id);
    }

    /**
     * 根据主键更新权限组。
     *
     * @param tbIamPermgroup 权限组
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbIamPermgroup tbIamPermgroup) {
        return tbIamPermgroupService.updateById(tbIamPermgroup);
    }

    /**
     * 查询所有权限组。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbIamPermgroup> list() {
        return tbIamPermgroupService.list();
    }

    /**
     * 根据主键获取权限组。
     *
     * @param id 权限组主键
     * @return 权限组详情
     */
    @GetMapping("getInfo/{id}")
    public TbIamPermgroup getInfo(@PathVariable String id) {
        return tbIamPermgroupService.getById(id);
    }

    /**
     * 分页查询权限组。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbIamPermgroup> page(Page<TbIamPermgroup> page) {
        return tbIamPermgroupService.page(page);
    }

}
