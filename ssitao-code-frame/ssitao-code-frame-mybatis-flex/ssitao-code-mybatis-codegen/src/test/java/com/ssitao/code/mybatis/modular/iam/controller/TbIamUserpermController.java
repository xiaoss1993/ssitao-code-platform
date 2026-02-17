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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamUserperm;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamUserpermService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 用户权限关联 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbIamUserperm")
public class TbIamUserpermController {

    @Autowired
    private TbIamUserpermService tbIamUserpermService;

    /**
     * 保存用户权限关联。
     *
     * @param tbIamUserperm 用户权限关联
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbIamUserperm tbIamUserperm) {
        return tbIamUserpermService.save(tbIamUserperm);
    }

    /**
     * 根据主键删除用户权限关联。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbIamUserpermService.removeById(id);
    }

    /**
     * 根据主键更新用户权限关联。
     *
     * @param tbIamUserperm 用户权限关联
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbIamUserperm tbIamUserperm) {
        return tbIamUserpermService.updateById(tbIamUserperm);
    }

    /**
     * 查询所有用户权限关联。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbIamUserperm> list() {
        return tbIamUserpermService.list();
    }

    /**
     * 根据主键获取用户权限关联。
     *
     * @param id 用户权限关联主键
     * @return 用户权限关联详情
     */
    @GetMapping("getInfo/{id}")
    public TbIamUserperm getInfo(@PathVariable String id) {
        return tbIamUserpermService.getById(id);
    }

    /**
     * 分页查询用户权限关联。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbIamUserperm> page(Page<TbIamUserperm> page) {
        return tbIamUserpermService.page(page);
    }

}
