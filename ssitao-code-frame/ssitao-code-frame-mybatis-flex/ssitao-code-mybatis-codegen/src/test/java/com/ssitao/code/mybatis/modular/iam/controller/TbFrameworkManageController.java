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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbFrameworkManage;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbFrameworkManageService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 架构管理（暂时没用） 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbFrameworkManage")
public class TbFrameworkManageController {

    @Autowired
    private TbFrameworkManageService tbFrameworkManageService;

    /**
     * 保存架构管理（暂时没用）。
     *
     * @param tbFrameworkManage 架构管理（暂时没用）
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbFrameworkManage tbFrameworkManage) {
        return tbFrameworkManageService.save(tbFrameworkManage);
    }

    /**
     * 根据主键删除架构管理（暂时没用）。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbFrameworkManageService.removeById(id);
    }

    /**
     * 根据主键更新架构管理（暂时没用）。
     *
     * @param tbFrameworkManage 架构管理（暂时没用）
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbFrameworkManage tbFrameworkManage) {
        return tbFrameworkManageService.updateById(tbFrameworkManage);
    }

    /**
     * 查询所有架构管理（暂时没用）。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbFrameworkManage> list() {
        return tbFrameworkManageService.list();
    }

    /**
     * 根据主键获取架构管理（暂时没用）。
     *
     * @param id 架构管理（暂时没用）主键
     * @return 架构管理（暂时没用）详情
     */
    @GetMapping("getInfo/{id}")
    public TbFrameworkManage getInfo(@PathVariable String id) {
        return tbFrameworkManageService.getById(id);
    }

    /**
     * 分页查询架构管理（暂时没用）。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbFrameworkManage> page(Page<TbFrameworkManage> page) {
        return tbFrameworkManageService.page(page);
    }

}
