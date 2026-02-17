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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SMenuGroup;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SMenuGroupService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 菜单分组 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/sMenuGroup")
public class SMenuGroupController {

    @Autowired
    private SMenuGroupService sMenuGroupService;

    /**
     * 保存菜单分组。
     *
     * @param sMenuGroup 菜单分组
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody SMenuGroup sMenuGroup) {
        return sMenuGroupService.save(sMenuGroup);
    }

    /**
     * 根据主键删除菜单分组。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return sMenuGroupService.removeById(id);
    }

    /**
     * 根据主键更新菜单分组。
     *
     * @param sMenuGroup 菜单分组
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody SMenuGroup sMenuGroup) {
        return sMenuGroupService.updateById(sMenuGroup);
    }

    /**
     * 查询所有菜单分组。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<SMenuGroup> list() {
        return sMenuGroupService.list();
    }

    /**
     * 根据主键获取菜单分组。
     *
     * @param id 菜单分组主键
     * @return 菜单分组详情
     */
    @GetMapping("getInfo/{id}")
    public SMenuGroup getInfo(@PathVariable String id) {
        return sMenuGroupService.getById(id);
    }

    /**
     * 分页查询菜单分组。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<SMenuGroup> page(Page<SMenuGroup> page) {
        return sMenuGroupService.page(page);
    }

}
