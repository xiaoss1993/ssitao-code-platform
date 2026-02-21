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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamAccountonjob;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamAccountonjobService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 在职人员按月统计 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbIamAccountonjob")
public class TbIamAccountonjobController {

    @Autowired
    private TbIamAccountonjobService tbIamAccountonjobService;

    /**
     * 保存在职人员按月统计。
     *
     * @param tbIamAccountonjob 在职人员按月统计
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbIamAccountonjob tbIamAccountonjob) {
        return tbIamAccountonjobService.save(tbIamAccountonjob);
    }

    /**
     * 根据主键删除在职人员按月统计。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbIamAccountonjobService.removeById(id);
    }

    /**
     * 根据主键更新在职人员按月统计。
     *
     * @param tbIamAccountonjob 在职人员按月统计
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbIamAccountonjob tbIamAccountonjob) {
        return tbIamAccountonjobService.updateById(tbIamAccountonjob);
    }

    /**
     * 查询所有在职人员按月统计。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbIamAccountonjob> list() {
        return tbIamAccountonjobService.list();
    }

    /**
     * 根据主键获取在职人员按月统计。
     *
     * @param id 在职人员按月统计主键
     * @return 在职人员按月统计详情
     */
    @GetMapping("getInfo/{id}")
    public TbIamAccountonjob getInfo(@PathVariable String id) {
        return tbIamAccountonjobService.getById(id);
    }

    /**
     * 分页查询在职人员按月统计。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbIamAccountonjob> page(Page<TbIamAccountonjob> page) {
        return tbIamAccountonjobService.page(page);
    }

}
