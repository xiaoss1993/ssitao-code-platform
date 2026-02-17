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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamDepartment;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamDepartmentService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 部门管理 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbIamDepartment")
public class TbIamDepartmentController {

    @Autowired
    private TbIamDepartmentService tbIamDepartmentService;

    /**
     * 保存部门管理。
     *
     * @param tbIamDepartment 部门管理
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbIamDepartment tbIamDepartment) {
        return tbIamDepartmentService.save(tbIamDepartment);
    }

    /**
     * 根据主键删除部门管理。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbIamDepartmentService.removeById(id);
    }

    /**
     * 根据主键更新部门管理。
     *
     * @param tbIamDepartment 部门管理
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbIamDepartment tbIamDepartment) {
        return tbIamDepartmentService.updateById(tbIamDepartment);
    }

    /**
     * 查询所有部门管理。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbIamDepartment> list() {
        return tbIamDepartmentService.list();
    }

    /**
     * 根据主键获取部门管理。
     *
     * @param id 部门管理主键
     * @return 部门管理详情
     */
    @GetMapping("getInfo/{id}")
    public TbIamDepartment getInfo(@PathVariable String id) {
        return tbIamDepartmentService.getById(id);
    }

    /**
     * 分页查询部门管理。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbIamDepartment> page(Page<TbIamDepartment> page) {
        return tbIamDepartmentService.page(page);
    }

}
