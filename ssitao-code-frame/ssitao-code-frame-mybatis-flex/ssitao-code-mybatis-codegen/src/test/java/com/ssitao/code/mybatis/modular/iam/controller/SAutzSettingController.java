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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SAutzSetting;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SAutzSettingService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 权限设置 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/sAutzSetting")
public class SAutzSettingController {

    @Autowired
    private SAutzSettingService sAutzSettingService;

    /**
     * 保存权限设置。
     *
     * @param sAutzSetting 权限设置
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody SAutzSetting sAutzSetting) {
        return sAutzSettingService.save(sAutzSetting);
    }

    /**
     * 根据主键删除权限设置。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return sAutzSettingService.removeById(id);
    }

    /**
     * 根据主键更新权限设置。
     *
     * @param sAutzSetting 权限设置
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody SAutzSetting sAutzSetting) {
        return sAutzSettingService.updateById(sAutzSetting);
    }

    /**
     * 查询所有权限设置。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<SAutzSetting> list() {
        return sAutzSettingService.list();
    }

    /**
     * 根据主键获取权限设置。
     *
     * @param id 权限设置主键
     * @return 权限设置详情
     */
    @GetMapping("getInfo/{id}")
    public SAutzSetting getInfo(@PathVariable String id) {
        return sAutzSettingService.getById(id);
    }

    /**
     * 分页查询权限设置。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<SAutzSetting> page(Page<SAutzSetting> page) {
        return sAutzSettingService.page(page);
    }

}
