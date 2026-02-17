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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreFuncinfo;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreFuncinfoService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 功能 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbCoreFuncinfo")
public class TbCoreFuncinfoController {

    @Autowired
    private TbCoreFuncinfoService tbCoreFuncinfoService;

    /**
     * 保存功能。
     *
     * @param tbCoreFuncinfo 功能
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreFuncinfo tbCoreFuncinfo) {
        return tbCoreFuncinfoService.save(tbCoreFuncinfo);
    }

    /**
     * 根据主键删除功能。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreFuncinfoService.removeById(id);
    }

    /**
     * 根据主键更新功能。
     *
     * @param tbCoreFuncinfo 功能
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreFuncinfo tbCoreFuncinfo) {
        return tbCoreFuncinfoService.updateById(tbCoreFuncinfo);
    }

    /**
     * 查询所有功能。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreFuncinfo> list() {
        return tbCoreFuncinfoService.list();
    }

    /**
     * 根据主键获取功能。
     *
     * @param id 功能主键
     * @return 功能详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreFuncinfo getInfo(@PathVariable String id) {
        return tbCoreFuncinfoService.getById(id);
    }

    /**
     * 分页查询功能。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreFuncinfo> page(Page<TbCoreFuncinfo> page) {
        return tbCoreFuncinfoService.page(page);
    }

}
