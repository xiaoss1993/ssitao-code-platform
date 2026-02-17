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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SPermission;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SPermissionService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 权限 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/sPermission")
public class SPermissionController {

    @Autowired
    private SPermissionService sPermissionService;

    /**
     * 保存权限。
     *
     * @param sPermission 权限
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody SPermission sPermission) {
        return sPermissionService.save(sPermission);
    }

    /**
     * 根据主键删除权限。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return sPermissionService.removeById(id);
    }

    /**
     * 根据主键更新权限。
     *
     * @param sPermission 权限
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody SPermission sPermission) {
        return sPermissionService.updateById(sPermission);
    }

    /**
     * 查询所有权限。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<SPermission> list() {
        return sPermissionService.list();
    }

    /**
     * 根据主键获取权限。
     *
     * @param id 权限主键
     * @return 权限详情
     */
    @GetMapping("getInfo/{id}")
    public SPermission getInfo(@PathVariable String id) {
        return sPermissionService.getById(id);
    }

    /**
     * 分页查询权限。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<SPermission> page(Page<SPermission> page) {
        return sPermissionService.page(page);
    }

}
