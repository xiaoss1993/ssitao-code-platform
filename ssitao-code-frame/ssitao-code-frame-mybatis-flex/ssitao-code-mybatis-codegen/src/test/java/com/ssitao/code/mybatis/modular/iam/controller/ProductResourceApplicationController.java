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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.ProductResourceApplication;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.ProductResourceApplicationService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 产品资源申请 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/productResourceApplication")
public class ProductResourceApplicationController {

    @Autowired
    private ProductResourceApplicationService productResourceApplicationService;

    /**
     * 保存产品资源申请。
     *
     * @param productResourceApplication 产品资源申请
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody ProductResourceApplication productResourceApplication) {
        return productResourceApplicationService.save(productResourceApplication);
    }

    /**
     * 根据主键删除产品资源申请。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return productResourceApplicationService.removeById(id);
    }

    /**
     * 根据主键更新产品资源申请。
     *
     * @param productResourceApplication 产品资源申请
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody ProductResourceApplication productResourceApplication) {
        return productResourceApplicationService.updateById(productResourceApplication);
    }

    /**
     * 查询所有产品资源申请。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<ProductResourceApplication> list() {
        return productResourceApplicationService.list();
    }

    /**
     * 根据主键获取产品资源申请。
     *
     * @param id 产品资源申请主键
     * @return 产品资源申请详情
     */
    @GetMapping("getInfo/{id}")
    public ProductResourceApplication getInfo(@PathVariable String id) {
        return productResourceApplicationService.getById(id);
    }

    /**
     * 分页查询产品资源申请。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<ProductResourceApplication> page(Page<ProductResourceApplication> page) {
        return productResourceApplicationService.page(page);
    }

}
