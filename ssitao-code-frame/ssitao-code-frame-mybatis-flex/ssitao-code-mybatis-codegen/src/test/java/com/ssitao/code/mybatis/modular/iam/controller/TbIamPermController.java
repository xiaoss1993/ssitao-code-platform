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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamPerm;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamPermService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 权限管理 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbIamPerm")
public class TbIamPermController {

    @Autowired
    private TbIamPermService tbIamPermService;

    /**
     * 保存权限管理。
     *
     * @param tbIamPerm 权限管理
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbIamPerm tbIamPerm) {
        return tbIamPermService.save(tbIamPerm);
    }

    /**
     * 根据主键删除权限管理。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbIamPermService.removeById(id);
    }

    /**
     * 根据主键更新权限管理。
     *
     * @param tbIamPerm 权限管理
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbIamPerm tbIamPerm) {
        return tbIamPermService.updateById(tbIamPerm);
    }

    /**
     * 查询所有权限管理。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbIamPerm> list() {
        return tbIamPermService.list();
    }

    /**
     * 根据主键获取权限管理。
     *
     * @param id 权限管理主键
     * @return 权限管理详情
     */
    @GetMapping("getInfo/{id}")
    public TbIamPerm getInfo(@PathVariable String id) {
        return tbIamPermService.getById(id);
    }

    /**
     * 分页查询权限管理。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbIamPerm> page(Page<TbIamPerm> page) {
        return tbIamPermService.page(page);
    }

}
