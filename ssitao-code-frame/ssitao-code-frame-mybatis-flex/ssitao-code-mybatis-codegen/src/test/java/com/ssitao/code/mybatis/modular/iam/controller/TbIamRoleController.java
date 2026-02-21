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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamRole;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamRoleService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 角色管理 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbIamRole")
public class TbIamRoleController {

    @Autowired
    private TbIamRoleService tbIamRoleService;

    /**
     * 保存角色管理。
     *
     * @param tbIamRole 角色管理
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbIamRole tbIamRole) {
        return tbIamRoleService.save(tbIamRole);
    }

    /**
     * 根据主键删除角色管理。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbIamRoleService.removeById(id);
    }

    /**
     * 根据主键更新角色管理。
     *
     * @param tbIamRole 角色管理
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbIamRole tbIamRole) {
        return tbIamRoleService.updateById(tbIamRole);
    }

    /**
     * 查询所有角色管理。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbIamRole> list() {
        return tbIamRoleService.list();
    }

    /**
     * 根据主键获取角色管理。
     *
     * @param id 角色管理主键
     * @return 角色管理详情
     */
    @GetMapping("getInfo/{id}")
    public TbIamRole getInfo(@PathVariable String id) {
        return tbIamRoleService.getById(id);
    }

    /**
     * 分页查询角色管理。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbIamRole> page(Page<TbIamRole> page) {
        return tbIamRoleService.page(page);
    }

}
