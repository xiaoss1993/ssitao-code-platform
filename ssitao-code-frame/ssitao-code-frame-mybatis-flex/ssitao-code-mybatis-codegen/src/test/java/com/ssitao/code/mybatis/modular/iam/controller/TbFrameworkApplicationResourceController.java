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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbFrameworkApplicationResource;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbFrameworkApplicationResourceService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 应用资源 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbFrameworkApplicationResource")
public class TbFrameworkApplicationResourceController {

    @Autowired
    private TbFrameworkApplicationResourceService tbFrameworkApplicationResourceService;

    /**
     * 保存应用资源。
     *
     * @param tbFrameworkApplicationResource 应用资源
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbFrameworkApplicationResource tbFrameworkApplicationResource) {
        return tbFrameworkApplicationResourceService.save(tbFrameworkApplicationResource);
    }

    /**
     * 根据主键删除应用资源。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbFrameworkApplicationResourceService.removeById(id);
    }

    /**
     * 根据主键更新应用资源。
     *
     * @param tbFrameworkApplicationResource 应用资源
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbFrameworkApplicationResource tbFrameworkApplicationResource) {
        return tbFrameworkApplicationResourceService.updateById(tbFrameworkApplicationResource);
    }

    /**
     * 查询所有应用资源。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbFrameworkApplicationResource> list() {
        return tbFrameworkApplicationResourceService.list();
    }

    /**
     * 根据主键获取应用资源。
     *
     * @param id 应用资源主键
     * @return 应用资源详情
     */
    @GetMapping("getInfo/{id}")
    public TbFrameworkApplicationResource getInfo(@PathVariable String id) {
        return tbFrameworkApplicationResourceService.getById(id);
    }

    /**
     * 分页查询应用资源。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbFrameworkApplicationResource> page(Page<TbFrameworkApplicationResource> page) {
        return tbFrameworkApplicationResourceService.page(page);
    }

}
