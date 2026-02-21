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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbHrmsStaffJobResume;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbHrmsStaffJobResumeService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 员工工作履历 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbHrmsStaffJobResume")
public class TbHrmsStaffJobResumeController {

    @Autowired
    private TbHrmsStaffJobResumeService tbHrmsStaffJobResumeService;

    /**
     * 保存员工工作履历。
     *
     * @param tbHrmsStaffJobResume 员工工作履历
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbHrmsStaffJobResume tbHrmsStaffJobResume) {
        return tbHrmsStaffJobResumeService.save(tbHrmsStaffJobResume);
    }

    /**
     * 根据主键删除员工工作履历。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbHrmsStaffJobResumeService.removeById(id);
    }

    /**
     * 根据主键更新员工工作履历。
     *
     * @param tbHrmsStaffJobResume 员工工作履历
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbHrmsStaffJobResume tbHrmsStaffJobResume) {
        return tbHrmsStaffJobResumeService.updateById(tbHrmsStaffJobResume);
    }

    /**
     * 查询所有员工工作履历。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbHrmsStaffJobResume> list() {
        return tbHrmsStaffJobResumeService.list();
    }

    /**
     * 根据主键获取员工工作履历。
     *
     * @param id 员工工作履历主键
     * @return 员工工作履历详情
     */
    @GetMapping("getInfo/{id}")
    public TbHrmsStaffJobResume getInfo(@PathVariable String id) {
        return tbHrmsStaffJobResumeService.getById(id);
    }

    /**
     * 分页查询员工工作履历。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbHrmsStaffJobResume> page(Page<TbHrmsStaffJobResume> page) {
        return tbHrmsStaffJobResumeService.page(page);
    }

}
