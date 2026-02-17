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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SMenuGroupBind;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SMenuGroupBindService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 菜单分组关联 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/sMenuGroupBind")
public class SMenuGroupBindController {

    @Autowired
    private SMenuGroupBindService sMenuGroupBindService;

    /**
     * 保存菜单分组关联。
     *
     * @param sMenuGroupBind 菜单分组关联
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody SMenuGroupBind sMenuGroupBind) {
        return sMenuGroupBindService.save(sMenuGroupBind);
    }

    /**
     * 根据主键删除菜单分组关联。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return sMenuGroupBindService.removeById(id);
    }

    /**
     * 根据主键更新菜单分组关联。
     *
     * @param sMenuGroupBind 菜单分组关联
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody SMenuGroupBind sMenuGroupBind) {
        return sMenuGroupBindService.updateById(sMenuGroupBind);
    }

    /**
     * 查询所有菜单分组关联。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<SMenuGroupBind> list() {
        return sMenuGroupBindService.list();
    }

    /**
     * 根据主键获取菜单分组关联。
     *
     * @param id 菜单分组关联主键
     * @return 菜单分组关联详情
     */
    @GetMapping("getInfo/{id}")
    public SMenuGroupBind getInfo(@PathVariable String id) {
        return sMenuGroupBindService.getById(id);
    }

    /**
     * 分页查询菜单分组关联。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<SMenuGroupBind> page(Page<SMenuGroupBind> page) {
        return sMenuGroupBindService.page(page);
    }

}
