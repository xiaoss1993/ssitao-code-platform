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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreSlowsqllog;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreSlowsqllogService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 慢sql日志 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbCoreSlowsqllog")
public class TbCoreSlowsqllogController {

    @Autowired
    private TbCoreSlowsqllogService tbCoreSlowsqllogService;

    /**
     * 保存慢sql日志。
     *
     * @param tbCoreSlowsqllog 慢sql日志
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreSlowsqllog tbCoreSlowsqllog) {
        return tbCoreSlowsqllogService.save(tbCoreSlowsqllog);
    }

    /**
     * 根据主键删除慢sql日志。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreSlowsqllogService.removeById(id);
    }

    /**
     * 根据主键更新慢sql日志。
     *
     * @param tbCoreSlowsqllog 慢sql日志
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreSlowsqllog tbCoreSlowsqllog) {
        return tbCoreSlowsqllogService.updateById(tbCoreSlowsqllog);
    }

    /**
     * 查询所有慢sql日志。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreSlowsqllog> list() {
        return tbCoreSlowsqllogService.list();
    }

    /**
     * 根据主键获取慢sql日志。
     *
     * @param id 慢sql日志主键
     * @return 慢sql日志详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreSlowsqllog getInfo(@PathVariable String id) {
        return tbCoreSlowsqllogService.getById(id);
    }

    /**
     * 分页查询慢sql日志。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreSlowsqllog> page(Page<TbCoreSlowsqllog> page) {
        return tbCoreSlowsqllogService.page(page);
    }

}
