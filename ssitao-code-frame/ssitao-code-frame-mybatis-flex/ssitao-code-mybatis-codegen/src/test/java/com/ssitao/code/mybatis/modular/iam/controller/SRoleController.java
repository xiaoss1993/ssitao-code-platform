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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SRole;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SRoleService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 角色 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/sRole")
public class SRoleController {

    @Autowired
    private SRoleService sRoleService;

    /**
     * 保存角色。
     *
     * @param sRole 角色
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody SRole sRole) {
        return sRoleService.save(sRole);
    }

    /**
     * 根据主键删除角色。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return sRoleService.removeById(id);
    }

    /**
     * 根据主键更新角色。
     *
     * @param sRole 角色
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody SRole sRole) {
        return sRoleService.updateById(sRole);
    }

    /**
     * 查询所有角色。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<SRole> list() {
        return sRoleService.list();
    }

    /**
     * 根据主键获取角色。
     *
     * @param id 角色主键
     * @return 角色详情
     */
    @GetMapping("getInfo/{id}")
    public SRole getInfo(@PathVariable String id) {
        return sRoleService.getById(id);
    }

    /**
     * 分页查询角色。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<SRole> page(Page<SRole> page) {
        return sRoleService.page(page);
    }

}
