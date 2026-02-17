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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreSetting;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreSettingService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 系统设置 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbCoreSetting")
public class TbCoreSettingController {

    @Autowired
    private TbCoreSettingService tbCoreSettingService;

    /**
     * 保存系统设置。
     *
     * @param tbCoreSetting 系统设置
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreSetting tbCoreSetting) {
        return tbCoreSettingService.save(tbCoreSetting);
    }

    /**
     * 根据主键删除系统设置。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreSettingService.removeById(id);
    }

    /**
     * 根据主键更新系统设置。
     *
     * @param tbCoreSetting 系统设置
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreSetting tbCoreSetting) {
        return tbCoreSettingService.updateById(tbCoreSetting);
    }

    /**
     * 查询所有系统设置。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreSetting> list() {
        return tbCoreSettingService.list();
    }

    /**
     * 根据主键获取系统设置。
     *
     * @param id 系统设置主键
     * @return 系统设置详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreSetting getInfo(@PathVariable String id) {
        return tbCoreSettingService.getById(id);
    }

    /**
     * 分页查询系统设置。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreSetting> page(Page<TbCoreSetting> page) {
        return tbCoreSettingService.page(page);
    }

}
