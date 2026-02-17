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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SMenu;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SMenuService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 系统菜单 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/sMenu")
public class SMenuController {

    @Autowired
    private SMenuService sMenuService;

    /**
     * 保存系统菜单。
     *
     * @param sMenu 系统菜单
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody SMenu sMenu) {
        return sMenuService.save(sMenu);
    }

    /**
     * 根据主键删除系统菜单。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return sMenuService.removeById(id);
    }

    /**
     * 根据主键更新系统菜单。
     *
     * @param sMenu 系统菜单
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody SMenu sMenu) {
        return sMenuService.updateById(sMenu);
    }

    /**
     * 查询所有系统菜单。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<SMenu> list() {
        return sMenuService.list();
    }

    /**
     * 根据主键获取系统菜单。
     *
     * @param id 系统菜单主键
     * @return 系统菜单详情
     */
    @GetMapping("getInfo/{id}")
    public SMenu getInfo(@PathVariable String id) {
        return sMenuService.getById(id);
    }

    /**
     * 分页查询系统菜单。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<SMenu> page(Page<SMenu> page) {
        return sMenuService.page(page);
    }

}
