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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreException;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreExceptionService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 执行异常 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbCoreException")
public class TbCoreExceptionController {

    @Autowired
    private TbCoreExceptionService tbCoreExceptionService;

    /**
     * 保存执行异常。
     *
     * @param tbCoreException 执行异常
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreException tbCoreException) {
        return tbCoreExceptionService.save(tbCoreException);
    }

    /**
     * 根据主键删除执行异常。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreExceptionService.removeById(id);
    }

    /**
     * 根据主键更新执行异常。
     *
     * @param tbCoreException 执行异常
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreException tbCoreException) {
        return tbCoreExceptionService.updateById(tbCoreException);
    }

    /**
     * 查询所有执行异常。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreException> list() {
        return tbCoreExceptionService.list();
    }

    /**
     * 根据主键获取执行异常。
     *
     * @param id 执行异常主键
     * @return 执行异常详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreException getInfo(@PathVariable String id) {
        return tbCoreExceptionService.getById(id);
    }

    /**
     * 分页查询执行异常。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreException> page(Page<TbCoreException> page) {
        return tbCoreExceptionService.page(page);
    }

}
