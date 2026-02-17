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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamAccount;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamAccountService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 账号管理 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbIamAccount")
public class TbIamAccountController {

    @Autowired
    private TbIamAccountService tbIamAccountService;

    /**
     * 保存账号管理。
     *
     * @param tbIamAccount 账号管理
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbIamAccount tbIamAccount) {
        return tbIamAccountService.save(tbIamAccount);
    }

    /**
     * 根据主键删除账号管理。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbIamAccountService.removeById(id);
    }

    /**
     * 根据主键更新账号管理。
     *
     * @param tbIamAccount 账号管理
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbIamAccount tbIamAccount) {
        return tbIamAccountService.updateById(tbIamAccount);
    }

    /**
     * 查询所有账号管理。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbIamAccount> list() {
        return tbIamAccountService.list();
    }

    /**
     * 根据主键获取账号管理。
     *
     * @param id 账号管理主键
     * @return 账号管理详情
     */
    @GetMapping("getInfo/{id}")
    public TbIamAccount getInfo(@PathVariable String id) {
        return tbIamAccountService.getById(id);
    }

    /**
     * 分页查询账号管理。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbIamAccount> page(Page<TbIamAccount> page) {
        return tbIamAccountService.page(page);
    }

}
