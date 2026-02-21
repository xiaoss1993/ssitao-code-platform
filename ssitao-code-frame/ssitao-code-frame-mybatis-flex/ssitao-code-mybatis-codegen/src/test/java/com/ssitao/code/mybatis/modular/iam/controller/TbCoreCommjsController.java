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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreCommjs;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreCommjsService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 全局脚本库 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbCoreCommjs")
public class TbCoreCommjsController {

    @Autowired
    private TbCoreCommjsService tbCoreCommjsService;

    /**
     * 保存全局脚本库。
     *
     * @param tbCoreCommjs 全局脚本库
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreCommjs tbCoreCommjs) {
        return tbCoreCommjsService.save(tbCoreCommjs);
    }

    /**
     * 根据主键删除全局脚本库。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreCommjsService.removeById(id);
    }

    /**
     * 根据主键更新全局脚本库。
     *
     * @param tbCoreCommjs 全局脚本库
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreCommjs tbCoreCommjs) {
        return tbCoreCommjsService.updateById(tbCoreCommjs);
    }

    /**
     * 查询所有全局脚本库。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreCommjs> list() {
        return tbCoreCommjsService.list();
    }

    /**
     * 根据主键获取全局脚本库。
     *
     * @param id 全局脚本库主键
     * @return 全局脚本库详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreCommjs getInfo(@PathVariable String id) {
        return tbCoreCommjsService.getById(id);
    }

    /**
     * 分页查询全局脚本库。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreCommjs> page(Page<TbCoreCommjs> page) {
        return tbCoreCommjsService.page(page);
    }

}
