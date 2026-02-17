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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamLoginlog;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamLoginlogService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 新登录日志 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbIamLoginlog")
public class TbIamLoginlogController {

    @Autowired
    private TbIamLoginlogService tbIamLoginlogService;

    /**
     * 保存新登录日志。
     *
     * @param tbIamLoginlog 新登录日志
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbIamLoginlog tbIamLoginlog) {
        return tbIamLoginlogService.save(tbIamLoginlog);
    }

    /**
     * 根据主键删除新登录日志。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbIamLoginlogService.removeById(id);
    }

    /**
     * 根据主键更新新登录日志。
     *
     * @param tbIamLoginlog 新登录日志
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbIamLoginlog tbIamLoginlog) {
        return tbIamLoginlogService.updateById(tbIamLoginlog);
    }

    /**
     * 查询所有新登录日志。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbIamLoginlog> list() {
        return tbIamLoginlogService.list();
    }

    /**
     * 根据主键获取新登录日志。
     *
     * @param id 新登录日志主键
     * @return 新登录日志详情
     */
    @GetMapping("getInfo/{id}")
    public TbIamLoginlog getInfo(@PathVariable String id) {
        return tbIamLoginlogService.getById(id);
    }

    /**
     * 分页查询新登录日志。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbIamLoginlog> page(Page<TbIamLoginlog> page) {
        return tbIamLoginlogService.page(page);
    }

}
