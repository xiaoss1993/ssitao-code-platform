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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SAutzMenu;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SAutzMenuService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 权限设置菜单 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/sAutzMenu")
public class SAutzMenuController {

    @Autowired
    private SAutzMenuService sAutzMenuService;

    /**
     * 保存权限设置菜单。
     *
     * @param sAutzMenu 权限设置菜单
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody SAutzMenu sAutzMenu) {
        return sAutzMenuService.save(sAutzMenu);
    }

    /**
     * 根据主键删除权限设置菜单。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return sAutzMenuService.removeById(id);
    }

    /**
     * 根据主键更新权限设置菜单。
     *
     * @param sAutzMenu 权限设置菜单
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody SAutzMenu sAutzMenu) {
        return sAutzMenuService.updateById(sAutzMenu);
    }

    /**
     * 查询所有权限设置菜单。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<SAutzMenu> list() {
        return sAutzMenuService.list();
    }

    /**
     * 根据主键获取权限设置菜单。
     *
     * @param id 权限设置菜单主键
     * @return 权限设置菜单详情
     */
    @GetMapping("getInfo/{id}")
    public SAutzMenu getInfo(@PathVariable String id) {
        return sAutzMenuService.getById(id);
    }

    /**
     * 分页查询权限设置菜单。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<SAutzMenu> page(Page<SAutzMenu> page) {
        return sAutzMenuService.page(page);
    }

}
