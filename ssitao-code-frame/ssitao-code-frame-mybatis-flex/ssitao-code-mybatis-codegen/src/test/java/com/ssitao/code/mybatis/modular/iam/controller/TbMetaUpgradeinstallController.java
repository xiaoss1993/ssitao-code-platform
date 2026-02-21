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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbMetaUpgradeinstall;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbMetaUpgradeinstallService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 升级包安装管理 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbMetaUpgradeinstall")
public class TbMetaUpgradeinstallController {

    @Autowired
    private TbMetaUpgradeinstallService tbMetaUpgradeinstallService;

    /**
     * 保存升级包安装管理。
     *
     * @param tbMetaUpgradeinstall 升级包安装管理
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbMetaUpgradeinstall tbMetaUpgradeinstall) {
        return tbMetaUpgradeinstallService.save(tbMetaUpgradeinstall);
    }

    /**
     * 根据主键删除升级包安装管理。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbMetaUpgradeinstallService.removeById(id);
    }

    /**
     * 根据主键更新升级包安装管理。
     *
     * @param tbMetaUpgradeinstall 升级包安装管理
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbMetaUpgradeinstall tbMetaUpgradeinstall) {
        return tbMetaUpgradeinstallService.updateById(tbMetaUpgradeinstall);
    }

    /**
     * 查询所有升级包安装管理。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbMetaUpgradeinstall> list() {
        return tbMetaUpgradeinstallService.list();
    }

    /**
     * 根据主键获取升级包安装管理。
     *
     * @param id 升级包安装管理主键
     * @return 升级包安装管理详情
     */
    @GetMapping("getInfo/{id}")
    public TbMetaUpgradeinstall getInfo(@PathVariable String id) {
        return tbMetaUpgradeinstallService.getById(id);
    }

    /**
     * 分页查询升级包安装管理。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbMetaUpgradeinstall> page(Page<TbMetaUpgradeinstall> page) {
        return tbMetaUpgradeinstallService.page(page);
    }

}
