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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreExecutionlog;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreExecutionlogService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 执行日志 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbCoreExecutionlog")
public class TbCoreExecutionlogController {

    @Autowired
    private TbCoreExecutionlogService tbCoreExecutionlogService;

    /**
     * 保存执行日志。
     *
     * @param tbCoreExecutionlog 执行日志
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreExecutionlog tbCoreExecutionlog) {
        return tbCoreExecutionlogService.save(tbCoreExecutionlog);
    }

    /**
     * 根据主键删除执行日志。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreExecutionlogService.removeById(id);
    }

    /**
     * 根据主键更新执行日志。
     *
     * @param tbCoreExecutionlog 执行日志
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreExecutionlog tbCoreExecutionlog) {
        return tbCoreExecutionlogService.updateById(tbCoreExecutionlog);
    }

    /**
     * 查询所有执行日志。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreExecutionlog> list() {
        return tbCoreExecutionlogService.list();
    }

    /**
     * 根据主键获取执行日志。
     *
     * @param id 执行日志主键
     * @return 执行日志详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreExecutionlog getInfo(@PathVariable String id) {
        return tbCoreExecutionlogService.getById(id);
    }

    /**
     * 分页查询执行日志。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreExecutionlog> page(Page<TbCoreExecutionlog> page) {
        return tbCoreExecutionlogService.page(page);
    }

}
