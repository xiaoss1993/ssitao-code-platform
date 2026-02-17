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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbProductFlowlog;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbProductFlowlogService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 产品申请部署流转日志 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbProductFlowlog")
public class TbProductFlowlogController {

    @Autowired
    private TbProductFlowlogService tbProductFlowlogService;

    /**
     * 保存产品申请部署流转日志。
     *
     * @param tbProductFlowlog 产品申请部署流转日志
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbProductFlowlog tbProductFlowlog) {
        return tbProductFlowlogService.save(tbProductFlowlog);
    }

    /**
     * 根据主键删除产品申请部署流转日志。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbProductFlowlogService.removeById(id);
    }

    /**
     * 根据主键更新产品申请部署流转日志。
     *
     * @param tbProductFlowlog 产品申请部署流转日志
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbProductFlowlog tbProductFlowlog) {
        return tbProductFlowlogService.updateById(tbProductFlowlog);
    }

    /**
     * 查询所有产品申请部署流转日志。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbProductFlowlog> list() {
        return tbProductFlowlogService.list();
    }

    /**
     * 根据主键获取产品申请部署流转日志。
     *
     * @param id 产品申请部署流转日志主键
     * @return 产品申请部署流转日志详情
     */
    @GetMapping("getInfo/{id}")
    public TbProductFlowlog getInfo(@PathVariable String id) {
        return tbProductFlowlogService.getById(id);
    }

    /**
     * 分页查询产品申请部署流转日志。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbProductFlowlog> page(Page<TbProductFlowlog> page) {
        return tbProductFlowlogService.page(page);
    }

}
