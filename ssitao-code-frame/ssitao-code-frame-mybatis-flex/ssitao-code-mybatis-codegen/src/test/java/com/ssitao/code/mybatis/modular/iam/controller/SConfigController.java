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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SConfig;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SConfigService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 系统配置文件 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/sConfig")
public class SConfigController {

    @Autowired
    private SConfigService sConfigService;

    /**
     * 保存系统配置文件。
     *
     * @param sConfig 系统配置文件
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody SConfig sConfig) {
        return sConfigService.save(sConfig);
    }

    /**
     * 根据主键删除系统配置文件。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return sConfigService.removeById(id);
    }

    /**
     * 根据主键更新系统配置文件。
     *
     * @param sConfig 系统配置文件
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody SConfig sConfig) {
        return sConfigService.updateById(sConfig);
    }

    /**
     * 查询所有系统配置文件。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<SConfig> list() {
        return sConfigService.list();
    }

    /**
     * 根据主键获取系统配置文件。
     *
     * @param id 系统配置文件主键
     * @return 系统配置文件详情
     */
    @GetMapping("getInfo/{id}")
    public SConfig getInfo(@PathVariable String id) {
        return sConfigService.getById(id);
    }

    /**
     * 分页查询系统配置文件。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<SConfig> page(Page<SConfig> page) {
        return sConfigService.page(page);
    }

}
