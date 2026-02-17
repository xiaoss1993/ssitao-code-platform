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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamWechatConfig;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamWechatConfigService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 企微设置管理 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbIamWechatConfig")
public class TbIamWechatConfigController {

    @Autowired
    private TbIamWechatConfigService tbIamWechatConfigService;

    /**
     * 保存企微设置管理。
     *
     * @param tbIamWechatConfig 企微设置管理
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbIamWechatConfig tbIamWechatConfig) {
        return tbIamWechatConfigService.save(tbIamWechatConfig);
    }

    /**
     * 根据主键删除企微设置管理。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbIamWechatConfigService.removeById(id);
    }

    /**
     * 根据主键更新企微设置管理。
     *
     * @param tbIamWechatConfig 企微设置管理
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbIamWechatConfig tbIamWechatConfig) {
        return tbIamWechatConfigService.updateById(tbIamWechatConfig);
    }

    /**
     * 查询所有企微设置管理。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbIamWechatConfig> list() {
        return tbIamWechatConfigService.list();
    }

    /**
     * 根据主键获取企微设置管理。
     *
     * @param id 企微设置管理主键
     * @return 企微设置管理详情
     */
    @GetMapping("getInfo/{id}")
    public TbIamWechatConfig getInfo(@PathVariable String id) {
        return tbIamWechatConfigService.getById(id);
    }

    /**
     * 分页查询企微设置管理。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbIamWechatConfig> page(Page<TbIamWechatConfig> page) {
        return tbIamWechatConfigService.page(page);
    }

}
