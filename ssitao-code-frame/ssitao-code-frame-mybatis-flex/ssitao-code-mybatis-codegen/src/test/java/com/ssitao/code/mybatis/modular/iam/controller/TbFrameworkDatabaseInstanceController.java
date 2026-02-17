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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbFrameworkDatabaseInstance;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbFrameworkDatabaseInstanceService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 数据库实例 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbFrameworkDatabaseInstance")
public class TbFrameworkDatabaseInstanceController {

    @Autowired
    private TbFrameworkDatabaseInstanceService tbFrameworkDatabaseInstanceService;

    /**
     * 保存数据库实例。
     *
     * @param tbFrameworkDatabaseInstance 数据库实例
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbFrameworkDatabaseInstance tbFrameworkDatabaseInstance) {
        return tbFrameworkDatabaseInstanceService.save(tbFrameworkDatabaseInstance);
    }

    /**
     * 根据主键删除数据库实例。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbFrameworkDatabaseInstanceService.removeById(id);
    }

    /**
     * 根据主键更新数据库实例。
     *
     * @param tbFrameworkDatabaseInstance 数据库实例
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbFrameworkDatabaseInstance tbFrameworkDatabaseInstance) {
        return tbFrameworkDatabaseInstanceService.updateById(tbFrameworkDatabaseInstance);
    }

    /**
     * 查询所有数据库实例。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbFrameworkDatabaseInstance> list() {
        return tbFrameworkDatabaseInstanceService.list();
    }

    /**
     * 根据主键获取数据库实例。
     *
     * @param id 数据库实例主键
     * @return 数据库实例详情
     */
    @GetMapping("getInfo/{id}")
    public TbFrameworkDatabaseInstance getInfo(@PathVariable String id) {
        return tbFrameworkDatabaseInstanceService.getById(id);
    }

    /**
     * 分页查询数据库实例。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbFrameworkDatabaseInstance> page(Page<TbFrameworkDatabaseInstance> page) {
        return tbFrameworkDatabaseInstanceService.page(page);
    }

}
