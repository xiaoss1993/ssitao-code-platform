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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamMenuhistory;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamMenuhistoryService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 用户菜单历史 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbIamMenuhistory")
public class TbIamMenuhistoryController {

    @Autowired
    private TbIamMenuhistoryService tbIamMenuhistoryService;

    /**
     * 保存用户菜单历史。
     *
     * @param tbIamMenuhistory 用户菜单历史
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbIamMenuhistory tbIamMenuhistory) {
        return tbIamMenuhistoryService.save(tbIamMenuhistory);
    }

    /**
     * 根据主键删除用户菜单历史。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbIamMenuhistoryService.removeById(id);
    }

    /**
     * 根据主键更新用户菜单历史。
     *
     * @param tbIamMenuhistory 用户菜单历史
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbIamMenuhistory tbIamMenuhistory) {
        return tbIamMenuhistoryService.updateById(tbIamMenuhistory);
    }

    /**
     * 查询所有用户菜单历史。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbIamMenuhistory> list() {
        return tbIamMenuhistoryService.list();
    }

    /**
     * 根据主键获取用户菜单历史。
     *
     * @param id 用户菜单历史主键
     * @return 用户菜单历史详情
     */
    @GetMapping("getInfo/{id}")
    public TbIamMenuhistory getInfo(@PathVariable String id) {
        return tbIamMenuhistoryService.getById(id);
    }

    /**
     * 分页查询用户菜单历史。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbIamMenuhistory> page(Page<TbIamMenuhistory> page) {
        return tbIamMenuhistoryService.page(page);
    }

}
