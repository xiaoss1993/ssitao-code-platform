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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.STemplate;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.STemplateService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 模板 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/sTemplate")
public class STemplateController {

    @Autowired
    private STemplateService sTemplateService;

    /**
     * 保存模板。
     *
     * @param sTemplate 模板
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody STemplate sTemplate) {
        return sTemplateService.save(sTemplate);
    }

    /**
     * 根据主键删除模板。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return sTemplateService.removeById(id);
    }

    /**
     * 根据主键更新模板。
     *
     * @param sTemplate 模板
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody STemplate sTemplate) {
        return sTemplateService.updateById(sTemplate);
    }

    /**
     * 查询所有模板。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<STemplate> list() {
        return sTemplateService.list();
    }

    /**
     * 根据主键获取模板。
     *
     * @param id 模板主键
     * @return 模板详情
     */
    @GetMapping("getInfo/{id}")
    public STemplate getInfo(@PathVariable String id) {
        return sTemplateService.getById(id);
    }

    /**
     * 分页查询模板。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<STemplate> page(Page<STemplate> page) {
        return sTemplateService.page(page);
    }

}
