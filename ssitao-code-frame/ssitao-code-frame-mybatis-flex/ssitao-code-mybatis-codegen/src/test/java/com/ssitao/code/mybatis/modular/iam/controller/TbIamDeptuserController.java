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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamDeptuser;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamDeptuserService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 部门人员关联 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbIamDeptuser")
public class TbIamDeptuserController {

    @Autowired
    private TbIamDeptuserService tbIamDeptuserService;

    /**
     * 保存部门人员关联。
     *
     * @param tbIamDeptuser 部门人员关联
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbIamDeptuser tbIamDeptuser) {
        return tbIamDeptuserService.save(tbIamDeptuser);
    }

    /**
     * 根据主键删除部门人员关联。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbIamDeptuserService.removeById(id);
    }

    /**
     * 根据主键更新部门人员关联。
     *
     * @param tbIamDeptuser 部门人员关联
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbIamDeptuser tbIamDeptuser) {
        return tbIamDeptuserService.updateById(tbIamDeptuser);
    }

    /**
     * 查询所有部门人员关联。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbIamDeptuser> list() {
        return tbIamDeptuserService.list();
    }

    /**
     * 根据主键获取部门人员关联。
     *
     * @param id 部门人员关联主键
     * @return 部门人员关联详情
     */
    @GetMapping("getInfo/{id}")
    public TbIamDeptuser getInfo(@PathVariable String id) {
        return tbIamDeptuserService.getById(id);
    }

    /**
     * 分页查询部门人员关联。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbIamDeptuser> page(Page<TbIamDeptuser> page) {
        return tbIamDeptuserService.page(page);
    }

}
