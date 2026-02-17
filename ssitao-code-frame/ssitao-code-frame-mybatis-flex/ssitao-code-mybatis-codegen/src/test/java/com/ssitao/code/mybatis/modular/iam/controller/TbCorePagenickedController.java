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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCorePagenicked;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCorePagenickedService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 页面留痕 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbCorePagenicked")
public class TbCorePagenickedController {

    @Autowired
    private TbCorePagenickedService tbCorePagenickedService;

    /**
     * 保存页面留痕。
     *
     * @param tbCorePagenicked 页面留痕
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCorePagenicked tbCorePagenicked) {
        return tbCorePagenickedService.save(tbCorePagenicked);
    }

    /**
     * 根据主键删除页面留痕。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCorePagenickedService.removeById(id);
    }

    /**
     * 根据主键更新页面留痕。
     *
     * @param tbCorePagenicked 页面留痕
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCorePagenicked tbCorePagenicked) {
        return tbCorePagenickedService.updateById(tbCorePagenicked);
    }

    /**
     * 查询所有页面留痕。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCorePagenicked> list() {
        return tbCorePagenickedService.list();
    }

    /**
     * 根据主键获取页面留痕。
     *
     * @param id 页面留痕主键
     * @return 页面留痕详情
     */
    @GetMapping("getInfo/{id}")
    public TbCorePagenicked getInfo(@PathVariable String id) {
        return tbCorePagenickedService.getById(id);
    }

    /**
     * 分页查询页面留痕。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCorePagenicked> page(Page<TbCorePagenicked> page) {
        return tbCorePagenickedService.page(page);
    }

}
