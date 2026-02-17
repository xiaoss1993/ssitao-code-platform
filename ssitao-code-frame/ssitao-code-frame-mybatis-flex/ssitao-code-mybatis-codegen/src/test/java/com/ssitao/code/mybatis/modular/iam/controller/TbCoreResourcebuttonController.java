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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreResourcebutton;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreResourcebuttonService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 功能按钮 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbCoreResourcebutton")
public class TbCoreResourcebuttonController {

    @Autowired
    private TbCoreResourcebuttonService tbCoreResourcebuttonService;

    /**
     * 保存功能按钮。
     *
     * @param tbCoreResourcebutton 功能按钮
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreResourcebutton tbCoreResourcebutton) {
        return tbCoreResourcebuttonService.save(tbCoreResourcebutton);
    }

    /**
     * 根据主键删除功能按钮。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreResourcebuttonService.removeById(id);
    }

    /**
     * 根据主键更新功能按钮。
     *
     * @param tbCoreResourcebutton 功能按钮
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreResourcebutton tbCoreResourcebutton) {
        return tbCoreResourcebuttonService.updateById(tbCoreResourcebutton);
    }

    /**
     * 查询所有功能按钮。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreResourcebutton> list() {
        return tbCoreResourcebuttonService.list();
    }

    /**
     * 根据主键获取功能按钮。
     *
     * @param id 功能按钮主键
     * @return 功能按钮详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreResourcebutton getInfo(@PathVariable String id) {
        return tbCoreResourcebuttonService.getById(id);
    }

    /**
     * 分页查询功能按钮。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreResourcebutton> page(Page<TbCoreResourcebutton> page) {
        return tbCoreResourcebuttonService.page(page);
    }

}
