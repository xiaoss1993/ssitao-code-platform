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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamDeveloperOrg;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamDeveloperOrgService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 开发者机构人员 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbIamDeveloperOrg")
public class TbIamDeveloperOrgController {

    @Autowired
    private TbIamDeveloperOrgService tbIamDeveloperOrgService;

    /**
     * 保存开发者机构人员。
     *
     * @param tbIamDeveloperOrg 开发者机构人员
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbIamDeveloperOrg tbIamDeveloperOrg) {
        return tbIamDeveloperOrgService.save(tbIamDeveloperOrg);
    }

    /**
     * 根据主键删除开发者机构人员。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbIamDeveloperOrgService.removeById(id);
    }

    /**
     * 根据主键更新开发者机构人员。
     *
     * @param tbIamDeveloperOrg 开发者机构人员
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbIamDeveloperOrg tbIamDeveloperOrg) {
        return tbIamDeveloperOrgService.updateById(tbIamDeveloperOrg);
    }

    /**
     * 查询所有开发者机构人员。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbIamDeveloperOrg> list() {
        return tbIamDeveloperOrgService.list();
    }

    /**
     * 根据主键获取开发者机构人员。
     *
     * @param id 开发者机构人员主键
     * @return 开发者机构人员详情
     */
    @GetMapping("getInfo/{id}")
    public TbIamDeveloperOrg getInfo(@PathVariable String id) {
        return tbIamDeveloperOrgService.getById(id);
    }

    /**
     * 分页查询开发者机构人员。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbIamDeveloperOrg> page(Page<TbIamDeveloperOrg> page) {
        return tbIamDeveloperOrgService.page(page);
    }

}
