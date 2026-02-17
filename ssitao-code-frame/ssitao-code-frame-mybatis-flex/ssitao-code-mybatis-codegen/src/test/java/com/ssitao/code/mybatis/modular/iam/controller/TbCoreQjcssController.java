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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreQjcss;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreQjcssService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 全局样式库 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbCoreQjcss")
public class TbCoreQjcssController {

    @Autowired
    private TbCoreQjcssService tbCoreQjcssService;

    /**
     * 保存全局样式库。
     *
     * @param tbCoreQjcss 全局样式库
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreQjcss tbCoreQjcss) {
        return tbCoreQjcssService.save(tbCoreQjcss);
    }

    /**
     * 根据主键删除全局样式库。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreQjcssService.removeById(id);
    }

    /**
     * 根据主键更新全局样式库。
     *
     * @param tbCoreQjcss 全局样式库
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreQjcss tbCoreQjcss) {
        return tbCoreQjcssService.updateById(tbCoreQjcss);
    }

    /**
     * 查询所有全局样式库。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreQjcss> list() {
        return tbCoreQjcssService.list();
    }

    /**
     * 根据主键获取全局样式库。
     *
     * @param id 全局样式库主键
     * @return 全局样式库详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreQjcss getInfo(@PathVariable String id) {
        return tbCoreQjcssService.getById(id);
    }

    /**
     * 分页查询全局样式库。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreQjcss> page(Page<TbCoreQjcss> page) {
        return tbCoreQjcssService.page(page);
    }

}
