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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbHrmsStaffEducation;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbHrmsStaffEducationService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 员工教育背景 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbHrmsStaffEducation")
public class TbHrmsStaffEducationController {

    @Autowired
    private TbHrmsStaffEducationService tbHrmsStaffEducationService;

    /**
     * 保存员工教育背景。
     *
     * @param tbHrmsStaffEducation 员工教育背景
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbHrmsStaffEducation tbHrmsStaffEducation) {
        return tbHrmsStaffEducationService.save(tbHrmsStaffEducation);
    }

    /**
     * 根据主键删除员工教育背景。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbHrmsStaffEducationService.removeById(id);
    }

    /**
     * 根据主键更新员工教育背景。
     *
     * @param tbHrmsStaffEducation 员工教育背景
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbHrmsStaffEducation tbHrmsStaffEducation) {
        return tbHrmsStaffEducationService.updateById(tbHrmsStaffEducation);
    }

    /**
     * 查询所有员工教育背景。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbHrmsStaffEducation> list() {
        return tbHrmsStaffEducationService.list();
    }

    /**
     * 根据主键获取员工教育背景。
     *
     * @param id 员工教育背景主键
     * @return 员工教育背景详情
     */
    @GetMapping("getInfo/{id}")
    public TbHrmsStaffEducation getInfo(@PathVariable String id) {
        return tbHrmsStaffEducationService.getById(id);
    }

    /**
     * 分页查询员工教育背景。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbHrmsStaffEducation> page(Page<TbHrmsStaffEducation> page) {
        return tbHrmsStaffEducationService.page(page);
    }

}
