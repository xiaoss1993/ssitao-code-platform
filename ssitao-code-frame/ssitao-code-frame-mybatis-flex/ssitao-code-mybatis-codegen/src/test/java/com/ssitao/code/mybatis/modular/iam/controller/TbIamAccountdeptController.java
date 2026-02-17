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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamAccountdept;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamAccountdeptService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 账号部门关联 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbIamAccountdept")
public class TbIamAccountdeptController {

    @Autowired
    private TbIamAccountdeptService tbIamAccountdeptService;

    /**
     * 保存账号部门关联。
     *
     * @param tbIamAccountdept 账号部门关联
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbIamAccountdept tbIamAccountdept) {
        return tbIamAccountdeptService.save(tbIamAccountdept);
    }

    /**
     * 根据主键删除账号部门关联。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbIamAccountdeptService.removeById(id);
    }

    /**
     * 根据主键更新账号部门关联。
     *
     * @param tbIamAccountdept 账号部门关联
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbIamAccountdept tbIamAccountdept) {
        return tbIamAccountdeptService.updateById(tbIamAccountdept);
    }

    /**
     * 查询所有账号部门关联。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbIamAccountdept> list() {
        return tbIamAccountdeptService.list();
    }

    /**
     * 根据主键获取账号部门关联。
     *
     * @param id 账号部门关联主键
     * @return 账号部门关联详情
     */
    @GetMapping("getInfo/{id}")
    public TbIamAccountdept getInfo(@PathVariable String id) {
        return tbIamAccountdeptService.getById(id);
    }

    /**
     * 分页查询账号部门关联。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbIamAccountdept> page(Page<TbIamAccountdept> page) {
        return tbIamAccountdeptService.page(page);
    }

}
