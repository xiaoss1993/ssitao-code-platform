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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreMenu;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreMenuService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 菜单信息 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbCoreMenu")
public class TbCoreMenuController {

    @Autowired
    private TbCoreMenuService tbCoreMenuService;

    /**
     * 保存菜单信息。
     *
     * @param tbCoreMenu 菜单信息
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreMenu tbCoreMenu) {
        return tbCoreMenuService.save(tbCoreMenu);
    }

    /**
     * 根据主键删除菜单信息。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreMenuService.removeById(id);
    }

    /**
     * 根据主键更新菜单信息。
     *
     * @param tbCoreMenu 菜单信息
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreMenu tbCoreMenu) {
        return tbCoreMenuService.updateById(tbCoreMenu);
    }

    /**
     * 查询所有菜单信息。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreMenu> list() {
        return tbCoreMenuService.list();
    }

    /**
     * 根据主键获取菜单信息。
     *
     * @param id 菜单信息主键
     * @return 菜单信息详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreMenu getInfo(@PathVariable String id) {
        return tbCoreMenuService.getById(id);
    }

    /**
     * 分页查询菜单信息。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreMenu> page(Page<TbCoreMenu> page) {
        return tbCoreMenuService.page(page);
    }

}
