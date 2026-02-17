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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamOrgperm;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamOrgpermService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 机构权限关联 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbIamOrgperm")
public class TbIamOrgpermController {

    @Autowired
    private TbIamOrgpermService tbIamOrgpermService;

    /**
     * 保存机构权限关联。
     *
     * @param tbIamOrgperm 机构权限关联
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbIamOrgperm tbIamOrgperm) {
        return tbIamOrgpermService.save(tbIamOrgperm);
    }

    /**
     * 根据主键删除机构权限关联。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbIamOrgpermService.removeById(id);
    }

    /**
     * 根据主键更新机构权限关联。
     *
     * @param tbIamOrgperm 机构权限关联
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbIamOrgperm tbIamOrgperm) {
        return tbIamOrgpermService.updateById(tbIamOrgperm);
    }

    /**
     * 查询所有机构权限关联。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbIamOrgperm> list() {
        return tbIamOrgpermService.list();
    }

    /**
     * 根据主键获取机构权限关联。
     *
     * @param id 机构权限关联主键
     * @return 机构权限关联详情
     */
    @GetMapping("getInfo/{id}")
    public TbIamOrgperm getInfo(@PathVariable String id) {
        return tbIamOrgpermService.getById(id);
    }

    /**
     * 分页查询机构权限关联。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbIamOrgperm> page(Page<TbIamOrgperm> page) {
        return tbIamOrgpermService.page(page);
    }

}
