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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbHrmsStaffLanguage;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbHrmsStaffLanguageService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 员工语言能力 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbHrmsStaffLanguage")
public class TbHrmsStaffLanguageController {

    @Autowired
    private TbHrmsStaffLanguageService tbHrmsStaffLanguageService;

    /**
     * 保存员工语言能力。
     *
     * @param tbHrmsStaffLanguage 员工语言能力
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbHrmsStaffLanguage tbHrmsStaffLanguage) {
        return tbHrmsStaffLanguageService.save(tbHrmsStaffLanguage);
    }

    /**
     * 根据主键删除员工语言能力。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbHrmsStaffLanguageService.removeById(id);
    }

    /**
     * 根据主键更新员工语言能力。
     *
     * @param tbHrmsStaffLanguage 员工语言能力
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbHrmsStaffLanguage tbHrmsStaffLanguage) {
        return tbHrmsStaffLanguageService.updateById(tbHrmsStaffLanguage);
    }

    /**
     * 查询所有员工语言能力。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbHrmsStaffLanguage> list() {
        return tbHrmsStaffLanguageService.list();
    }

    /**
     * 根据主键获取员工语言能力。
     *
     * @param id 员工语言能力主键
     * @return 员工语言能力详情
     */
    @GetMapping("getInfo/{id}")
    public TbHrmsStaffLanguage getInfo(@PathVariable String id) {
        return tbHrmsStaffLanguageService.getById(id);
    }

    /**
     * 分页查询员工语言能力。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbHrmsStaffLanguage> page(Page<TbHrmsStaffLanguage> page) {
        return tbHrmsStaffLanguageService.page(page);
    }

}
