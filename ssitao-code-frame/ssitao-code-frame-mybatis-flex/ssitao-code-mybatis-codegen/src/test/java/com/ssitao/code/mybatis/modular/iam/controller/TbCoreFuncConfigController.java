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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreFuncConfig;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreFuncConfigService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 功能业务配置 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbCoreFuncConfig")
public class TbCoreFuncConfigController {

    @Autowired
    private TbCoreFuncConfigService tbCoreFuncConfigService;

    /**
     * 保存功能业务配置。
     *
     * @param tbCoreFuncConfig 功能业务配置
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreFuncConfig tbCoreFuncConfig) {
        return tbCoreFuncConfigService.save(tbCoreFuncConfig);
    }

    /**
     * 根据主键删除功能业务配置。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreFuncConfigService.removeById(id);
    }

    /**
     * 根据主键更新功能业务配置。
     *
     * @param tbCoreFuncConfig 功能业务配置
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreFuncConfig tbCoreFuncConfig) {
        return tbCoreFuncConfigService.updateById(tbCoreFuncConfig);
    }

    /**
     * 查询所有功能业务配置。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreFuncConfig> list() {
        return tbCoreFuncConfigService.list();
    }

    /**
     * 根据主键获取功能业务配置。
     *
     * @param id 功能业务配置主键
     * @return 功能业务配置详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreFuncConfig getInfo(@PathVariable String id) {
        return tbCoreFuncConfigService.getById(id);
    }

    /**
     * 分页查询功能业务配置。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreFuncConfig> page(Page<TbCoreFuncConfig> page) {
        return tbCoreFuncConfigService.page(page);
    }

}
