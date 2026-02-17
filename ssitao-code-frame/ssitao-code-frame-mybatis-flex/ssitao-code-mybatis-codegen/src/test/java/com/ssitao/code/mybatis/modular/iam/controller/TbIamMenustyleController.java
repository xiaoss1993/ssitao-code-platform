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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamMenustyle;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamMenustyleService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 用户菜单样式 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbIamMenustyle")
public class TbIamMenustyleController {

    @Autowired
    private TbIamMenustyleService tbIamMenustyleService;

    /**
     * 保存用户菜单样式。
     *
     * @param tbIamMenustyle 用户菜单样式
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbIamMenustyle tbIamMenustyle) {
        return tbIamMenustyleService.save(tbIamMenustyle);
    }

    /**
     * 根据主键删除用户菜单样式。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbIamMenustyleService.removeById(id);
    }

    /**
     * 根据主键更新用户菜单样式。
     *
     * @param tbIamMenustyle 用户菜单样式
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbIamMenustyle tbIamMenustyle) {
        return tbIamMenustyleService.updateById(tbIamMenustyle);
    }

    /**
     * 查询所有用户菜单样式。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbIamMenustyle> list() {
        return tbIamMenustyleService.list();
    }

    /**
     * 根据主键获取用户菜单样式。
     *
     * @param id 用户菜单样式主键
     * @return 用户菜单样式详情
     */
    @GetMapping("getInfo/{id}")
    public TbIamMenustyle getInfo(@PathVariable String id) {
        return tbIamMenustyleService.getById(id);
    }

    /**
     * 分页查询用户菜单样式。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbIamMenustyle> page(Page<TbIamMenustyle> page) {
        return tbIamMenustyleService.page(page);
    }

}
