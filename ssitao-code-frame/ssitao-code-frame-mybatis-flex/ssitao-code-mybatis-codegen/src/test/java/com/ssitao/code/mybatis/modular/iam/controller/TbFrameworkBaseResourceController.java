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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbFrameworkBaseResource;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbFrameworkBaseResourceService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 基础资源 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbFrameworkBaseResource")
public class TbFrameworkBaseResourceController {

    @Autowired
    private TbFrameworkBaseResourceService tbFrameworkBaseResourceService;

    /**
     * 保存基础资源。
     *
     * @param tbFrameworkBaseResource 基础资源
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbFrameworkBaseResource tbFrameworkBaseResource) {
        return tbFrameworkBaseResourceService.save(tbFrameworkBaseResource);
    }

    /**
     * 根据主键删除基础资源。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbFrameworkBaseResourceService.removeById(id);
    }

    /**
     * 根据主键更新基础资源。
     *
     * @param tbFrameworkBaseResource 基础资源
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbFrameworkBaseResource tbFrameworkBaseResource) {
        return tbFrameworkBaseResourceService.updateById(tbFrameworkBaseResource);
    }

    /**
     * 查询所有基础资源。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbFrameworkBaseResource> list() {
        return tbFrameworkBaseResourceService.list();
    }

    /**
     * 根据主键获取基础资源。
     *
     * @param id 基础资源主键
     * @return 基础资源详情
     */
    @GetMapping("getInfo/{id}")
    public TbFrameworkBaseResource getInfo(@PathVariable String id) {
        return tbFrameworkBaseResourceService.getById(id);
    }

    /**
     * 分页查询基础资源。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbFrameworkBaseResource> page(Page<TbFrameworkBaseResource> page) {
        return tbFrameworkBaseResourceService.page(page);
    }

}
