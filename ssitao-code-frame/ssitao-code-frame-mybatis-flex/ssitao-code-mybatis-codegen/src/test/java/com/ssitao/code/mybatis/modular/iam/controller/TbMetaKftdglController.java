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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbMetaKftdgl;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbMetaKftdglService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 开发团队管理 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbMetaKftdgl")
public class TbMetaKftdglController {

    @Autowired
    private TbMetaKftdglService tbMetaKftdglService;

    /**
     * 保存开发团队管理。
     *
     * @param tbMetaKftdgl 开发团队管理
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbMetaKftdgl tbMetaKftdgl) {
        return tbMetaKftdglService.save(tbMetaKftdgl);
    }

    /**
     * 根据主键删除开发团队管理。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbMetaKftdglService.removeById(id);
    }

    /**
     * 根据主键更新开发团队管理。
     *
     * @param tbMetaKftdgl 开发团队管理
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbMetaKftdgl tbMetaKftdgl) {
        return tbMetaKftdglService.updateById(tbMetaKftdgl);
    }

    /**
     * 查询所有开发团队管理。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbMetaKftdgl> list() {
        return tbMetaKftdglService.list();
    }

    /**
     * 根据主键获取开发团队管理。
     *
     * @param id 开发团队管理主键
     * @return 开发团队管理详情
     */
    @GetMapping("getInfo/{id}")
    public TbMetaKftdgl getInfo(@PathVariable String id) {
        return tbMetaKftdglService.getById(id);
    }

    /**
     * 分页查询开发团队管理。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbMetaKftdgl> page(Page<TbMetaKftdgl> page) {
        return tbMetaKftdglService.page(page);
    }

}
