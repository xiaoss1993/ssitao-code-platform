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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreQjsql;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreQjsqlService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * sql模板库 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbCoreQjsql")
public class TbCoreQjsqlController {

    @Autowired
    private TbCoreQjsqlService tbCoreQjsqlService;

    /**
     * 保存sql模板库。
     *
     * @param tbCoreQjsql sql模板库
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreQjsql tbCoreQjsql) {
        return tbCoreQjsqlService.save(tbCoreQjsql);
    }

    /**
     * 根据主键删除sql模板库。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreQjsqlService.removeById(id);
    }

    /**
     * 根据主键更新sql模板库。
     *
     * @param tbCoreQjsql sql模板库
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreQjsql tbCoreQjsql) {
        return tbCoreQjsqlService.updateById(tbCoreQjsql);
    }

    /**
     * 查询所有sql模板库。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreQjsql> list() {
        return tbCoreQjsqlService.list();
    }

    /**
     * 根据主键获取sql模板库。
     *
     * @param id sql模板库主键
     * @return sql模板库详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreQjsql getInfo(@PathVariable String id) {
        return tbCoreQjsqlService.getById(id);
    }

    /**
     * 分页查询sql模板库。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreQjsql> page(Page<TbCoreQjsql> page) {
        return tbCoreQjsqlService.page(page);
    }

}
