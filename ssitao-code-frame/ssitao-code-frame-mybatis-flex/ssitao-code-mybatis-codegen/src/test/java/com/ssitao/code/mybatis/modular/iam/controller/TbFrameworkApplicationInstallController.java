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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbFrameworkApplicationInstall;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbFrameworkApplicationInstallService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 应用安装信息 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbFrameworkApplicationInstall")
public class TbFrameworkApplicationInstallController {

    @Autowired
    private TbFrameworkApplicationInstallService tbFrameworkApplicationInstallService;

    /**
     * 保存应用安装信息。
     *
     * @param tbFrameworkApplicationInstall 应用安装信息
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbFrameworkApplicationInstall tbFrameworkApplicationInstall) {
        return tbFrameworkApplicationInstallService.save(tbFrameworkApplicationInstall);
    }

    /**
     * 根据主键删除应用安装信息。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbFrameworkApplicationInstallService.removeById(id);
    }

    /**
     * 根据主键更新应用安装信息。
     *
     * @param tbFrameworkApplicationInstall 应用安装信息
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbFrameworkApplicationInstall tbFrameworkApplicationInstall) {
        return tbFrameworkApplicationInstallService.updateById(tbFrameworkApplicationInstall);
    }

    /**
     * 查询所有应用安装信息。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbFrameworkApplicationInstall> list() {
        return tbFrameworkApplicationInstallService.list();
    }

    /**
     * 根据主键获取应用安装信息。
     *
     * @param id 应用安装信息主键
     * @return 应用安装信息详情
     */
    @GetMapping("getInfo/{id}")
    public TbFrameworkApplicationInstall getInfo(@PathVariable String id) {
        return tbFrameworkApplicationInstallService.getById(id);
    }

    /**
     * 分页查询应用安装信息。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbFrameworkApplicationInstall> page(Page<TbFrameworkApplicationInstall> page) {
        return tbFrameworkApplicationInstallService.page(page);
    }

}
