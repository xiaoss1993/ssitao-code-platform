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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamRoleperm;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamRolepermService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 角色权限关联 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbIamRoleperm")
public class TbIamRolepermController {

    @Autowired
    private TbIamRolepermService tbIamRolepermService;

    /**
     * 保存角色权限关联。
     *
     * @param tbIamRoleperm 角色权限关联
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbIamRoleperm tbIamRoleperm) {
        return tbIamRolepermService.save(tbIamRoleperm);
    }

    /**
     * 根据主键删除角色权限关联。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbIamRolepermService.removeById(id);
    }

    /**
     * 根据主键更新角色权限关联。
     *
     * @param tbIamRoleperm 角色权限关联
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbIamRoleperm tbIamRoleperm) {
        return tbIamRolepermService.updateById(tbIamRoleperm);
    }

    /**
     * 查询所有角色权限关联。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbIamRoleperm> list() {
        return tbIamRolepermService.list();
    }

    /**
     * 根据主键获取角色权限关联。
     *
     * @param id 角色权限关联主键
     * @return 角色权限关联详情
     */
    @GetMapping("getInfo/{id}")
    public TbIamRoleperm getInfo(@PathVariable String id) {
        return tbIamRolepermService.getById(id);
    }

    /**
     * 分页查询角色权限关联。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbIamRoleperm> page(Page<TbIamRoleperm> page) {
        return tbIamRolepermService.page(page);
    }

}
