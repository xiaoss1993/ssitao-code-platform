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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreExceptionlog;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreExceptionlogService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 异常日志 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbCoreExceptionlog")
public class TbCoreExceptionlogController {

    @Autowired
    private TbCoreExceptionlogService tbCoreExceptionlogService;

    /**
     * 保存异常日志。
     *
     * @param tbCoreExceptionlog 异常日志
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreExceptionlog tbCoreExceptionlog) {
        return tbCoreExceptionlogService.save(tbCoreExceptionlog);
    }

    /**
     * 根据主键删除异常日志。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreExceptionlogService.removeById(id);
    }

    /**
     * 根据主键更新异常日志。
     *
     * @param tbCoreExceptionlog 异常日志
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreExceptionlog tbCoreExceptionlog) {
        return tbCoreExceptionlogService.updateById(tbCoreExceptionlog);
    }

    /**
     * 查询所有异常日志。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreExceptionlog> list() {
        return tbCoreExceptionlogService.list();
    }

    /**
     * 根据主键获取异常日志。
     *
     * @param id 异常日志主键
     * @return 异常日志详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreExceptionlog getInfo(@PathVariable String id) {
        return tbCoreExceptionlogService.getById(id);
    }

    /**
     * 分页查询异常日志。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreExceptionlog> page(Page<TbCoreExceptionlog> page) {
        return tbCoreExceptionlogService.page(page);
    }

}
