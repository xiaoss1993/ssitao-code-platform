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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreFuncrelation;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreFuncrelationService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 子功能 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbCoreFuncrelation")
public class TbCoreFuncrelationController {

    @Autowired
    private TbCoreFuncrelationService tbCoreFuncrelationService;

    /**
     * 保存子功能。
     *
     * @param tbCoreFuncrelation 子功能
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreFuncrelation tbCoreFuncrelation) {
        return tbCoreFuncrelationService.save(tbCoreFuncrelation);
    }

    /**
     * 根据主键删除子功能。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreFuncrelationService.removeById(id);
    }

    /**
     * 根据主键更新子功能。
     *
     * @param tbCoreFuncrelation 子功能
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreFuncrelation tbCoreFuncrelation) {
        return tbCoreFuncrelationService.updateById(tbCoreFuncrelation);
    }

    /**
     * 查询所有子功能。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreFuncrelation> list() {
        return tbCoreFuncrelationService.list();
    }

    /**
     * 根据主键获取子功能。
     *
     * @param id 子功能主键
     * @return 子功能详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreFuncrelation getInfo(@PathVariable String id) {
        return tbCoreFuncrelationService.getById(id);
    }

    /**
     * 分页查询子功能。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreFuncrelation> page(Page<TbCoreFuncrelation> page) {
        return tbCoreFuncrelationService.page(page);
    }

}
