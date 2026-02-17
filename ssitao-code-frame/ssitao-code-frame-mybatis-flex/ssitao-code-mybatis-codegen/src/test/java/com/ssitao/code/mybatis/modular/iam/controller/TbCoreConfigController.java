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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreConfig;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreConfigService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 系统变量 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbCoreConfig")
public class TbCoreConfigController {

    @Autowired
    private TbCoreConfigService tbCoreConfigService;

    /**
     * 保存系统变量。
     *
     * @param tbCoreConfig 系统变量
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreConfig tbCoreConfig) {
        return tbCoreConfigService.save(tbCoreConfig);
    }

    /**
     * 根据主键删除系统变量。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreConfigService.removeById(id);
    }

    /**
     * 根据主键更新系统变量。
     *
     * @param tbCoreConfig 系统变量
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreConfig tbCoreConfig) {
        return tbCoreConfigService.updateById(tbCoreConfig);
    }

    /**
     * 查询所有系统变量。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreConfig> list() {
        return tbCoreConfigService.list();
    }

    /**
     * 根据主键获取系统变量。
     *
     * @param id 系统变量主键
     * @return 系统变量详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreConfig getInfo(@PathVariable String id) {
        return tbCoreConfigService.getById(id);
    }

    /**
     * 分页查询系统变量。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreConfig> page(Page<TbCoreConfig> page) {
        return tbCoreConfigService.page(page);
    }

}
