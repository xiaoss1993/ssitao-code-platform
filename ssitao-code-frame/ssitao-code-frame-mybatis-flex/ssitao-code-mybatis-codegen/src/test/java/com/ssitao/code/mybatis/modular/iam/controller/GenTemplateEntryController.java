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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.GenTemplateEntry;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.GenTemplateEntryService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 模板文件目录项 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/genTemplateEntry")
public class GenTemplateEntryController {

    @Autowired
    private GenTemplateEntryService genTemplateEntryService;

    /**
     * 保存模板文件目录项。
     *
     * @param genTemplateEntry 模板文件目录项
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody GenTemplateEntry genTemplateEntry) {
        return genTemplateEntryService.save(genTemplateEntry);
    }

    /**
     * 根据主键删除模板文件目录项。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return genTemplateEntryService.removeById(id);
    }

    /**
     * 根据主键更新模板文件目录项。
     *
     * @param genTemplateEntry 模板文件目录项
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody GenTemplateEntry genTemplateEntry) {
        return genTemplateEntryService.updateById(genTemplateEntry);
    }

    /**
     * 查询所有模板文件目录项。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<GenTemplateEntry> list() {
        return genTemplateEntryService.list();
    }

    /**
     * 根据主键获取模板文件目录项。
     *
     * @param id 模板文件目录项主键
     * @return 模板文件目录项详情
     */
    @GetMapping("getInfo/{id}")
    public GenTemplateEntry getInfo(@PathVariable String id) {
        return genTemplateEntryService.getById(id);
    }

    /**
     * 分页查询模板文件目录项。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<GenTemplateEntry> page(Page<GenTemplateEntry> page) {
        return genTemplateEntryService.page(page);
    }

}
