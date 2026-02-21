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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamPermgroupperm;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamPermgrouppermService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 权限组权限关联 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbIamPermgroupperm")
public class TbIamPermgrouppermController {

    @Autowired
    private TbIamPermgrouppermService tbIamPermgrouppermService;

    /**
     * 保存权限组权限关联。
     *
     * @param tbIamPermgroupperm 权限组权限关联
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbIamPermgroupperm tbIamPermgroupperm) {
        return tbIamPermgrouppermService.save(tbIamPermgroupperm);
    }

    /**
     * 根据主键删除权限组权限关联。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbIamPermgrouppermService.removeById(id);
    }

    /**
     * 根据主键更新权限组权限关联。
     *
     * @param tbIamPermgroupperm 权限组权限关联
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbIamPermgroupperm tbIamPermgroupperm) {
        return tbIamPermgrouppermService.updateById(tbIamPermgroupperm);
    }

    /**
     * 查询所有权限组权限关联。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbIamPermgroupperm> list() {
        return tbIamPermgrouppermService.list();
    }

    /**
     * 根据主键获取权限组权限关联。
     *
     * @param id 权限组权限关联主键
     * @return 权限组权限关联详情
     */
    @GetMapping("getInfo/{id}")
    public TbIamPermgroupperm getInfo(@PathVariable String id) {
        return tbIamPermgrouppermService.getById(id);
    }

    /**
     * 分页查询权限组权限关联。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbIamPermgroupperm> page(Page<TbIamPermgroupperm> page) {
        return tbIamPermgrouppermService.page(page);
    }

}
