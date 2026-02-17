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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamWechatApp;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamWechatAppService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 企微应用管理 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbIamWechatApp")
public class TbIamWechatAppController {

    @Autowired
    private TbIamWechatAppService tbIamWechatAppService;

    /**
     * 保存企微应用管理。
     *
     * @param tbIamWechatApp 企微应用管理
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbIamWechatApp tbIamWechatApp) {
        return tbIamWechatAppService.save(tbIamWechatApp);
    }

    /**
     * 根据主键删除企微应用管理。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbIamWechatAppService.removeById(id);
    }

    /**
     * 根据主键更新企微应用管理。
     *
     * @param tbIamWechatApp 企微应用管理
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbIamWechatApp tbIamWechatApp) {
        return tbIamWechatAppService.updateById(tbIamWechatApp);
    }

    /**
     * 查询所有企微应用管理。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbIamWechatApp> list() {
        return tbIamWechatAppService.list();
    }

    /**
     * 根据主键获取企微应用管理。
     *
     * @param id 企微应用管理主键
     * @return 企微应用管理详情
     */
    @GetMapping("getInfo/{id}")
    public TbIamWechatApp getInfo(@PathVariable String id) {
        return tbIamWechatAppService.getById(id);
    }

    /**
     * 分页查询企微应用管理。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbIamWechatApp> page(Page<TbIamWechatApp> page) {
        return tbIamWechatAppService.page(page);
    }

}
