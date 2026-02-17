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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbMetaMenufunc;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbMetaMenufuncService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 菜单功能关系 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbMetaMenufunc")
public class TbMetaMenufuncController {

    @Autowired
    private TbMetaMenufuncService tbMetaMenufuncService;

    /**
     * 保存菜单功能关系。
     *
     * @param tbMetaMenufunc 菜单功能关系
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbMetaMenufunc tbMetaMenufunc) {
        return tbMetaMenufuncService.save(tbMetaMenufunc);
    }

    /**
     * 根据主键删除菜单功能关系。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbMetaMenufuncService.removeById(id);
    }

    /**
     * 根据主键更新菜单功能关系。
     *
     * @param tbMetaMenufunc 菜单功能关系
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbMetaMenufunc tbMetaMenufunc) {
        return tbMetaMenufuncService.updateById(tbMetaMenufunc);
    }

    /**
     * 查询所有菜单功能关系。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbMetaMenufunc> list() {
        return tbMetaMenufuncService.list();
    }

    /**
     * 根据主键获取菜单功能关系。
     *
     * @param id 菜单功能关系主键
     * @return 菜单功能关系详情
     */
    @GetMapping("getInfo/{id}")
    public TbMetaMenufunc getInfo(@PathVariable String id) {
        return tbMetaMenufuncService.getById(id);
    }

    /**
     * 分页查询菜单功能关系。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbMetaMenufunc> page(Page<TbMetaMenufunc> page) {
        return tbMetaMenufuncService.page(page);
    }

}
