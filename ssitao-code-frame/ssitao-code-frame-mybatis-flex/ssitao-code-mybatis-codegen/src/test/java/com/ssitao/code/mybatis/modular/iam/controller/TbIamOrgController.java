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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamOrg;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamOrgService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 机构管理 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbIamOrg")
public class TbIamOrgController {

    @Autowired
    private TbIamOrgService tbIamOrgService;

    /**
     * 保存机构管理。
     *
     * @param tbIamOrg 机构管理
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbIamOrg tbIamOrg) {
        return tbIamOrgService.save(tbIamOrg);
    }

    /**
     * 根据主键删除机构管理。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbIamOrgService.removeById(id);
    }

    /**
     * 根据主键更新机构管理。
     *
     * @param tbIamOrg 机构管理
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbIamOrg tbIamOrg) {
        return tbIamOrgService.updateById(tbIamOrg);
    }

    /**
     * 查询所有机构管理。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbIamOrg> list() {
        return tbIamOrgService.list();
    }

    /**
     * 根据主键获取机构管理。
     *
     * @param id 机构管理主键
     * @return 机构管理详情
     */
    @GetMapping("getInfo/{id}")
    public TbIamOrg getInfo(@PathVariable String id) {
        return tbIamOrgService.getById(id);
    }

    /**
     * 分页查询机构管理。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbIamOrg> page(Page<TbIamOrg> page) {
        return tbIamOrgService.page(page);
    }

}
