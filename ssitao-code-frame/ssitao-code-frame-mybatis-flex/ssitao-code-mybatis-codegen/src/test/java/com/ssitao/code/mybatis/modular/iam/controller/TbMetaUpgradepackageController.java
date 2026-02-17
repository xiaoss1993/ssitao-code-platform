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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbMetaUpgradepackage;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbMetaUpgradepackageService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 产品升级包管理 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbMetaUpgradepackage")
public class TbMetaUpgradepackageController {

    @Autowired
    private TbMetaUpgradepackageService tbMetaUpgradepackageService;

    /**
     * 保存产品升级包管理。
     *
     * @param tbMetaUpgradepackage 产品升级包管理
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbMetaUpgradepackage tbMetaUpgradepackage) {
        return tbMetaUpgradepackageService.save(tbMetaUpgradepackage);
    }

    /**
     * 根据主键删除产品升级包管理。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbMetaUpgradepackageService.removeById(id);
    }

    /**
     * 根据主键更新产品升级包管理。
     *
     * @param tbMetaUpgradepackage 产品升级包管理
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbMetaUpgradepackage tbMetaUpgradepackage) {
        return tbMetaUpgradepackageService.updateById(tbMetaUpgradepackage);
    }

    /**
     * 查询所有产品升级包管理。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbMetaUpgradepackage> list() {
        return tbMetaUpgradepackageService.list();
    }

    /**
     * 根据主键获取产品升级包管理。
     *
     * @param id 产品升级包管理主键
     * @return 产品升级包管理详情
     */
    @GetMapping("getInfo/{id}")
    public TbMetaUpgradepackage getInfo(@PathVariable String id) {
        return tbMetaUpgradepackageService.getById(id);
    }

    /**
     * 分页查询产品升级包管理。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbMetaUpgradepackage> page(Page<TbMetaUpgradepackage> page) {
        return tbMetaUpgradepackageService.page(page);
    }

}
