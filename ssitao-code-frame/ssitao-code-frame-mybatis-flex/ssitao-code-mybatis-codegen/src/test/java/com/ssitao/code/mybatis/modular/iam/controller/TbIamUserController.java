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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamUser;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamUserService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 部门人员 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbIamUser")
public class TbIamUserController {

    @Autowired
    private TbIamUserService tbIamUserService;

    /**
     * 保存部门人员。
     *
     * @param tbIamUser 部门人员
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbIamUser tbIamUser) {
        return tbIamUserService.save(tbIamUser);
    }

    /**
     * 根据主键删除部门人员。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbIamUserService.removeById(id);
    }

    /**
     * 根据主键更新部门人员。
     *
     * @param tbIamUser 部门人员
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbIamUser tbIamUser) {
        return tbIamUserService.updateById(tbIamUser);
    }

    /**
     * 查询所有部门人员。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbIamUser> list() {
        return tbIamUserService.list();
    }

    /**
     * 根据主键获取部门人员。
     *
     * @param id 部门人员主键
     * @return 部门人员详情
     */
    @GetMapping("getInfo/{id}")
    public TbIamUser getInfo(@PathVariable String id) {
        return tbIamUserService.getById(id);
    }

    /**
     * 分页查询部门人员。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbIamUser> page(Page<TbIamUser> page) {
        return tbIamUserService.page(page);
    }

}
