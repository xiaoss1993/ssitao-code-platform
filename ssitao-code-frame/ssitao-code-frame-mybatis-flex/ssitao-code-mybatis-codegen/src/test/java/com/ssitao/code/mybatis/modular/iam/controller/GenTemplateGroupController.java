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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.GenTemplateGroup;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.GenTemplateGroupService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 模板组 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/genTemplateGroup")
public class GenTemplateGroupController {

    @Autowired
    private GenTemplateGroupService genTemplateGroupService;

    /**
     * 保存模板组。
     *
     * @param genTemplateGroup 模板组
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody GenTemplateGroup genTemplateGroup) {
        return genTemplateGroupService.save(genTemplateGroup);
    }

    /**
     * 根据主键删除模板组。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Integer id) {
        return genTemplateGroupService.removeById(id);
    }

    /**
     * 根据主键更新模板组。
     *
     * @param genTemplateGroup 模板组
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody GenTemplateGroup genTemplateGroup) {
        return genTemplateGroupService.updateById(genTemplateGroup);
    }

    /**
     * 查询所有模板组。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<GenTemplateGroup> list() {
        return genTemplateGroupService.list();
    }

    /**
     * 根据主键获取模板组。
     *
     * @param id 模板组主键
     * @return 模板组详情
     */
    @GetMapping("getInfo/{id}")
    public GenTemplateGroup getInfo(@PathVariable Integer id) {
        return genTemplateGroupService.getById(id);
    }

    /**
     * 分页查询模板组。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<GenTemplateGroup> page(Page<GenTemplateGroup> page) {
        return genTemplateGroupService.page(page);
    }

}
