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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamDeptperm;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamDeptpermService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 部门权限关联 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbIamDeptperm")
public class TbIamDeptpermController {

    @Autowired
    private TbIamDeptpermService tbIamDeptpermService;

    /**
     * 保存部门权限关联。
     *
     * @param tbIamDeptperm 部门权限关联
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbIamDeptperm tbIamDeptperm) {
        return tbIamDeptpermService.save(tbIamDeptperm);
    }

    /**
     * 根据主键删除部门权限关联。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbIamDeptpermService.removeById(id);
    }

    /**
     * 根据主键更新部门权限关联。
     *
     * @param tbIamDeptperm 部门权限关联
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbIamDeptperm tbIamDeptperm) {
        return tbIamDeptpermService.updateById(tbIamDeptperm);
    }

    /**
     * 查询所有部门权限关联。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbIamDeptperm> list() {
        return tbIamDeptpermService.list();
    }

    /**
     * 根据主键获取部门权限关联。
     *
     * @param id 部门权限关联主键
     * @return 部门权限关联详情
     */
    @GetMapping("getInfo/{id}")
    public TbIamDeptperm getInfo(@PathVariable String id) {
        return tbIamDeptpermService.getById(id);
    }

    /**
     * 分页查询部门权限关联。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbIamDeptperm> page(Page<TbIamDeptperm> page) {
        return tbIamDeptpermService.page(page);
    }

}
