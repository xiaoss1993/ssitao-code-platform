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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreQuerystrategy;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreQuerystrategyService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 查询策略 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbCoreQuerystrategy")
public class TbCoreQuerystrategyController {

    @Autowired
    private TbCoreQuerystrategyService tbCoreQuerystrategyService;

    /**
     * 保存查询策略。
     *
     * @param tbCoreQuerystrategy 查询策略
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreQuerystrategy tbCoreQuerystrategy) {
        return tbCoreQuerystrategyService.save(tbCoreQuerystrategy);
    }

    /**
     * 根据主键删除查询策略。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreQuerystrategyService.removeById(id);
    }

    /**
     * 根据主键更新查询策略。
     *
     * @param tbCoreQuerystrategy 查询策略
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreQuerystrategy tbCoreQuerystrategy) {
        return tbCoreQuerystrategyService.updateById(tbCoreQuerystrategy);
    }

    /**
     * 查询所有查询策略。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreQuerystrategy> list() {
        return tbCoreQuerystrategyService.list();
    }

    /**
     * 根据主键获取查询策略。
     *
     * @param id 查询策略主键
     * @return 查询策略详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreQuerystrategy getInfo(@PathVariable String id) {
        return tbCoreQuerystrategyService.getById(id);
    }

    /**
     * 分页查询查询策略。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreQuerystrategy> page(Page<TbCoreQuerystrategy> page) {
        return tbCoreQuerystrategyService.page(page);
    }

}
