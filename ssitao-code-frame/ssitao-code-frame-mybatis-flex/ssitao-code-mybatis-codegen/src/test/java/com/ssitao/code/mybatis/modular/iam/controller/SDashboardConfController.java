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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SDashboardConf;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SDashboardConfService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 仪盘配置 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/sDashboardConf")
public class SDashboardConfController {

    @Autowired
    private SDashboardConfService sDashboardConfService;

    /**
     * 保存仪盘配置。
     *
     * @param sDashboardConf 仪盘配置
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody SDashboardConf sDashboardConf) {
        return sDashboardConfService.save(sDashboardConf);
    }

    /**
     * 根据主键删除仪盘配置。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return sDashboardConfService.removeById(id);
    }

    /**
     * 根据主键更新仪盘配置。
     *
     * @param sDashboardConf 仪盘配置
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody SDashboardConf sDashboardConf) {
        return sDashboardConfService.updateById(sDashboardConf);
    }

    /**
     * 查询所有仪盘配置。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<SDashboardConf> list() {
        return sDashboardConfService.list();
    }

    /**
     * 根据主键获取仪盘配置。
     *
     * @param id 仪盘配置主键
     * @return 仪盘配置详情
     */
    @GetMapping("getInfo/{id}")
    public SDashboardConf getInfo(@PathVariable String id) {
        return sDashboardConfService.getById(id);
    }

    /**
     * 分页查询仪盘配置。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<SDashboardConf> page(Page<SDashboardConf> page) {
        return sDashboardConfService.page(page);
    }

}
