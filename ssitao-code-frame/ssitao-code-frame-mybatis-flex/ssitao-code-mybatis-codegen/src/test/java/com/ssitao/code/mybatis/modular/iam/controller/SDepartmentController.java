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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SDepartment;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SDepartmentService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 部门 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/sDepartment")
public class SDepartmentController {

    @Autowired
    private SDepartmentService sDepartmentService;

    /**
     * 保存部门。
     *
     * @param sDepartment 部门
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody SDepartment sDepartment) {
        return sDepartmentService.save(sDepartment);
    }

    /**
     * 根据主键删除部门。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return sDepartmentService.removeById(id);
    }

    /**
     * 根据主键更新部门。
     *
     * @param sDepartment 部门
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody SDepartment sDepartment) {
        return sDepartmentService.updateById(sDepartment);
    }

    /**
     * 查询所有部门。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<SDepartment> list() {
        return sDepartmentService.list();
    }

    /**
     * 根据主键获取部门。
     *
     * @param id 部门主键
     * @return 部门详情
     */
    @GetMapping("getInfo/{id}")
    public SDepartment getInfo(@PathVariable String id) {
        return sDepartmentService.getById(id);
    }

    /**
     * 分页查询部门。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<SDepartment> page(Page<SDepartment> page) {
        return sDepartmentService.page(page);
    }

}
