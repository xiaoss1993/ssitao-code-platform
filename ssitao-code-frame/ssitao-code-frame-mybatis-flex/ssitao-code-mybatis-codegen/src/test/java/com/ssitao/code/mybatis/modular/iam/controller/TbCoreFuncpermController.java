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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreFuncperm;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreFuncpermService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 数据权限 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbCoreFuncperm")
public class TbCoreFuncpermController {

    @Autowired
    private TbCoreFuncpermService tbCoreFuncpermService;

    /**
     * 保存数据权限。
     *
     * @param tbCoreFuncperm 数据权限
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreFuncperm tbCoreFuncperm) {
        return tbCoreFuncpermService.save(tbCoreFuncperm);
    }

    /**
     * 根据主键删除数据权限。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreFuncpermService.removeById(id);
    }

    /**
     * 根据主键更新数据权限。
     *
     * @param tbCoreFuncperm 数据权限
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreFuncperm tbCoreFuncperm) {
        return tbCoreFuncpermService.updateById(tbCoreFuncperm);
    }

    /**
     * 查询所有数据权限。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreFuncperm> list() {
        return tbCoreFuncpermService.list();
    }

    /**
     * 根据主键获取数据权限。
     *
     * @param id 数据权限主键
     * @return 数据权限详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreFuncperm getInfo(@PathVariable String id) {
        return tbCoreFuncpermService.getById(id);
    }

    /**
     * 分页查询数据权限。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreFuncperm> page(Page<TbCoreFuncperm> page) {
        return tbCoreFuncpermService.page(page);
    }

}
