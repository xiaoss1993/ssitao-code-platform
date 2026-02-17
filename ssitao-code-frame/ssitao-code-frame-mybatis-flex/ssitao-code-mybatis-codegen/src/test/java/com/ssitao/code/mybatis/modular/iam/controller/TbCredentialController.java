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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCredential;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCredentialService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 凭据 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbCredential")
public class TbCredentialController {

    @Autowired
    private TbCredentialService tbCredentialService;

    /**
     * 保存凭据。
     *
     * @param tbCredential 凭据
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCredential tbCredential) {
        return tbCredentialService.save(tbCredential);
    }

    /**
     * 根据主键删除凭据。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCredentialService.removeById(id);
    }

    /**
     * 根据主键更新凭据。
     *
     * @param tbCredential 凭据
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCredential tbCredential) {
        return tbCredentialService.updateById(tbCredential);
    }

    /**
     * 查询所有凭据。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCredential> list() {
        return tbCredentialService.list();
    }

    /**
     * 根据主键获取凭据。
     *
     * @param id 凭据主键
     * @return 凭据详情
     */
    @GetMapping("getInfo/{id}")
    public TbCredential getInfo(@PathVariable String id) {
        return tbCredentialService.getById(id);
    }

    /**
     * 分页查询凭据。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCredential> page(Page<TbCredential> page) {
        return tbCredentialService.page(page);
    }

}
