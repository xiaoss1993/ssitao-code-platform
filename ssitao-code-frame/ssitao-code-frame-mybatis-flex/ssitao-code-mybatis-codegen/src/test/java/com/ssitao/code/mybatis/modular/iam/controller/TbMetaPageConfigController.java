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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbMetaPageConfig;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbMetaPageConfigService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 页面配置 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbMetaPageConfig")
public class TbMetaPageConfigController {

    @Autowired
    private TbMetaPageConfigService tbMetaPageConfigService;

    /**
     * 保存页面配置。
     *
     * @param tbMetaPageConfig 页面配置
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbMetaPageConfig tbMetaPageConfig) {
        return tbMetaPageConfigService.save(tbMetaPageConfig);
    }

    /**
     * 根据主键删除页面配置。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbMetaPageConfigService.removeById(id);
    }

    /**
     * 根据主键更新页面配置。
     *
     * @param tbMetaPageConfig 页面配置
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbMetaPageConfig tbMetaPageConfig) {
        return tbMetaPageConfigService.updateById(tbMetaPageConfig);
    }

    /**
     * 查询所有页面配置。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbMetaPageConfig> list() {
        return tbMetaPageConfigService.list();
    }

    /**
     * 根据主键获取页面配置。
     *
     * @param id 页面配置主键
     * @return 页面配置详情
     */
    @GetMapping("getInfo/{id}")
    public TbMetaPageConfig getInfo(@PathVariable String id) {
        return tbMetaPageConfigService.getById(id);
    }

    /**
     * 分页查询页面配置。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbMetaPageConfig> page(Page<TbMetaPageConfig> page) {
        return tbMetaPageConfigService.page(page);
    }

}
