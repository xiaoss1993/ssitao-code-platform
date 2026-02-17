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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.ProductResourceApplicationDetails;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.ProductResourceApplicationDetailsService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 产品资源申请明细 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/productResourceApplicationDetails")
public class ProductResourceApplicationDetailsController {

    @Autowired
    private ProductResourceApplicationDetailsService productResourceApplicationDetailsService;

    /**
     * 保存产品资源申请明细。
     *
     * @param productResourceApplicationDetails 产品资源申请明细
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody ProductResourceApplicationDetails productResourceApplicationDetails) {
        return productResourceApplicationDetailsService.save(productResourceApplicationDetails);
    }

    /**
     * 根据主键删除产品资源申请明细。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return productResourceApplicationDetailsService.removeById(id);
    }

    /**
     * 根据主键更新产品资源申请明细。
     *
     * @param productResourceApplicationDetails 产品资源申请明细
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody ProductResourceApplicationDetails productResourceApplicationDetails) {
        return productResourceApplicationDetailsService.updateById(productResourceApplicationDetails);
    }

    /**
     * 查询所有产品资源申请明细。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<ProductResourceApplicationDetails> list() {
        return productResourceApplicationDetailsService.list();
    }

    /**
     * 根据主键获取产品资源申请明细。
     *
     * @param id 产品资源申请明细主键
     * @return 产品资源申请明细详情
     */
    @GetMapping("getInfo/{id}")
    public ProductResourceApplicationDetails getInfo(@PathVariable String id) {
        return productResourceApplicationDetailsService.getById(id);
    }

    /**
     * 分页查询产品资源申请明细。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<ProductResourceApplicationDetails> page(Page<ProductResourceApplicationDetails> page) {
        return productResourceApplicationDetailsService.page(page);
    }

}
