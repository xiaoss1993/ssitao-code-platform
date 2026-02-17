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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbMetaUpgraderesource;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbMetaUpgraderesourceService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 升级包资源管理 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbMetaUpgraderesource")
public class TbMetaUpgraderesourceController {

    @Autowired
    private TbMetaUpgraderesourceService tbMetaUpgraderesourceService;

    /**
     * 保存升级包资源管理。
     *
     * @param tbMetaUpgraderesource 升级包资源管理
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbMetaUpgraderesource tbMetaUpgraderesource) {
        return tbMetaUpgraderesourceService.save(tbMetaUpgraderesource);
    }

    /**
     * 根据主键删除升级包资源管理。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbMetaUpgraderesourceService.removeById(id);
    }

    /**
     * 根据主键更新升级包资源管理。
     *
     * @param tbMetaUpgraderesource 升级包资源管理
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbMetaUpgraderesource tbMetaUpgraderesource) {
        return tbMetaUpgraderesourceService.updateById(tbMetaUpgraderesource);
    }

    /**
     * 查询所有升级包资源管理。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbMetaUpgraderesource> list() {
        return tbMetaUpgraderesourceService.list();
    }

    /**
     * 根据主键获取升级包资源管理。
     *
     * @param id 升级包资源管理主键
     * @return 升级包资源管理详情
     */
    @GetMapping("getInfo/{id}")
    public TbMetaUpgraderesource getInfo(@PathVariable String id) {
        return tbMetaUpgraderesourceService.getById(id);
    }

    /**
     * 分页查询升级包资源管理。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbMetaUpgraderesource> page(Page<TbMetaUpgraderesource> page) {
        return tbMetaUpgraderesourceService.page(page);
    }

}
