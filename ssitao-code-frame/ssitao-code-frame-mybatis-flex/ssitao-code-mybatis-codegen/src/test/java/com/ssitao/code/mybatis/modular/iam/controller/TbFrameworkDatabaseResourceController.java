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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbFrameworkDatabaseResource;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbFrameworkDatabaseResourceService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 数据库资源 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbFrameworkDatabaseResource")
public class TbFrameworkDatabaseResourceController {

    @Autowired
    private TbFrameworkDatabaseResourceService tbFrameworkDatabaseResourceService;

    /**
     * 保存数据库资源。
     *
     * @param tbFrameworkDatabaseResource 数据库资源
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbFrameworkDatabaseResource tbFrameworkDatabaseResource) {
        return tbFrameworkDatabaseResourceService.save(tbFrameworkDatabaseResource);
    }

    /**
     * 根据主键删除数据库资源。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbFrameworkDatabaseResourceService.removeById(id);
    }

    /**
     * 根据主键更新数据库资源。
     *
     * @param tbFrameworkDatabaseResource 数据库资源
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbFrameworkDatabaseResource tbFrameworkDatabaseResource) {
        return tbFrameworkDatabaseResourceService.updateById(tbFrameworkDatabaseResource);
    }

    /**
     * 查询所有数据库资源。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbFrameworkDatabaseResource> list() {
        return tbFrameworkDatabaseResourceService.list();
    }

    /**
     * 根据主键获取数据库资源。
     *
     * @param id 数据库资源主键
     * @return 数据库资源详情
     */
    @GetMapping("getInfo/{id}")
    public TbFrameworkDatabaseResource getInfo(@PathVariable String id) {
        return tbFrameworkDatabaseResourceService.getById(id);
    }

    /**
     * 分页查询数据库资源。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbFrameworkDatabaseResource> page(Page<TbFrameworkDatabaseResource> page) {
        return tbFrameworkDatabaseResourceService.page(page);
    }

}
