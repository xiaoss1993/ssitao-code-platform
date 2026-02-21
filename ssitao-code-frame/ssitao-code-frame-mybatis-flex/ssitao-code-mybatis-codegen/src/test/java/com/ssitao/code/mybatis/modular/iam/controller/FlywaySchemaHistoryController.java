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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.FlywaySchemaHistory;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.FlywaySchemaHistoryService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 *  控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/flywaySchemaHistory")
public class FlywaySchemaHistoryController {

    @Autowired
    private FlywaySchemaHistoryService flywaySchemaHistoryService;

    /**
     * 保存。
     *
     * @param flywaySchemaHistory 
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody FlywaySchemaHistory flywaySchemaHistory) {
        return flywaySchemaHistoryService.save(flywaySchemaHistory);
    }

    /**
     * 根据主键删除。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable Integer id) {
        return flywaySchemaHistoryService.removeById(id);
    }

    /**
     * 根据主键更新。
     *
     * @param flywaySchemaHistory 
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody FlywaySchemaHistory flywaySchemaHistory) {
        return flywaySchemaHistoryService.updateById(flywaySchemaHistory);
    }

    /**
     * 查询所有。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<FlywaySchemaHistory> list() {
        return flywaySchemaHistoryService.list();
    }

    /**
     * 根据主键获取。
     *
     * @param id 主键
     * @return 详情
     */
    @GetMapping("getInfo/{id}")
    public FlywaySchemaHistory getInfo(@PathVariable Integer id) {
        return flywaySchemaHistoryService.getById(id);
    }

    /**
     * 分页查询。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<FlywaySchemaHistory> page(Page<FlywaySchemaHistory> page) {
        return flywaySchemaHistoryService.page(page);
    }

}
