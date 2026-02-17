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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamCpdept;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamCpdeptService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 三方组织管理 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbIamCpdept")
public class TbIamCpdeptController {

    @Autowired
    private TbIamCpdeptService tbIamCpdeptService;

    /**
     * 保存三方组织管理。
     *
     * @param tbIamCpdept 三方组织管理
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbIamCpdept tbIamCpdept) {
        return tbIamCpdeptService.save(tbIamCpdept);
    }

    /**
     * 根据主键删除三方组织管理。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbIamCpdeptService.removeById(id);
    }

    /**
     * 根据主键更新三方组织管理。
     *
     * @param tbIamCpdept 三方组织管理
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbIamCpdept tbIamCpdept) {
        return tbIamCpdeptService.updateById(tbIamCpdept);
    }

    /**
     * 查询所有三方组织管理。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbIamCpdept> list() {
        return tbIamCpdeptService.list();
    }

    /**
     * 根据主键获取三方组织管理。
     *
     * @param id 三方组织管理主键
     * @return 三方组织管理详情
     */
    @GetMapping("getInfo/{id}")
    public TbIamCpdept getInfo(@PathVariable String id) {
        return tbIamCpdeptService.getById(id);
    }

    /**
     * 分页查询三方组织管理。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbIamCpdept> page(Page<TbIamCpdept> page) {
        return tbIamCpdeptService.page(page);
    }

}
