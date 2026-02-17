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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.GenTemplateProperty;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.GenTemplatePropertyService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 模板属性配置 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/genTemplateProperty")
public class GenTemplatePropertyController {

    @Autowired
    private GenTemplatePropertyService genTemplatePropertyService;

    /**
     * 保存模板属性配置。
     *
     * @param genTemplateProperty 模板属性配置
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody GenTemplateProperty genTemplateProperty) {
        return genTemplatePropertyService.save(genTemplateProperty);
    }

    /**
     * 根据主键删除模板属性配置。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Integer id) {
        return genTemplatePropertyService.removeById(id);
    }

    /**
     * 根据主键更新模板属性配置。
     *
     * @param genTemplateProperty 模板属性配置
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody GenTemplateProperty genTemplateProperty) {
        return genTemplatePropertyService.updateById(genTemplateProperty);
    }

    /**
     * 查询所有模板属性配置。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<GenTemplateProperty> list() {
        return genTemplatePropertyService.list();
    }

    /**
     * 根据主键获取模板属性配置。
     *
     * @param id 模板属性配置主键
     * @return 模板属性配置详情
     */
    @GetMapping("getInfo/{id}")
    public GenTemplateProperty getInfo(@PathVariable Integer id) {
        return genTemplatePropertyService.getById(id);
    }

    /**
     * 分页查询模板属性配置。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<GenTemplateProperty> page(Page<GenTemplateProperty> page) {
        return genTemplatePropertyService.page(page);
    }

}
