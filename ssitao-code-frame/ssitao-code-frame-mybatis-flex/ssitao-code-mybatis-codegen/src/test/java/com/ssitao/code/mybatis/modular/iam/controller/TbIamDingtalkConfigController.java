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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamDingtalkConfig;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamDingtalkConfigService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 钉钉设置管理 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbIamDingtalkConfig")
public class TbIamDingtalkConfigController {

    @Autowired
    private TbIamDingtalkConfigService tbIamDingtalkConfigService;

    /**
     * 保存钉钉设置管理。
     *
     * @param tbIamDingtalkConfig 钉钉设置管理
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbIamDingtalkConfig tbIamDingtalkConfig) {
        return tbIamDingtalkConfigService.save(tbIamDingtalkConfig);
    }

    /**
     * 根据主键删除钉钉设置管理。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbIamDingtalkConfigService.removeById(id);
    }

    /**
     * 根据主键更新钉钉设置管理。
     *
     * @param tbIamDingtalkConfig 钉钉设置管理
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbIamDingtalkConfig tbIamDingtalkConfig) {
        return tbIamDingtalkConfigService.updateById(tbIamDingtalkConfig);
    }

    /**
     * 查询所有钉钉设置管理。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbIamDingtalkConfig> list() {
        return tbIamDingtalkConfigService.list();
    }

    /**
     * 根据主键获取钉钉设置管理。
     *
     * @param id 钉钉设置管理主键
     * @return 钉钉设置管理详情
     */
    @GetMapping("getInfo/{id}")
    public TbIamDingtalkConfig getInfo(@PathVariable String id) {
        return tbIamDingtalkConfigService.getById(id);
    }

    /**
     * 分页查询钉钉设置管理。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbIamDingtalkConfig> page(Page<TbIamDingtalkConfig> page) {
        return tbIamDingtalkConfigService.page(page);
    }

}
