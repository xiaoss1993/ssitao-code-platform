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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SRelationDef;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SRelationDefService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 关系定义 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/sRelationDef")
public class SRelationDefController {

    @Autowired
    private SRelationDefService sRelationDefService;

    /**
     * 保存关系定义。
     *
     * @param sRelationDef 关系定义
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody SRelationDef sRelationDef) {
        return sRelationDefService.save(sRelationDef);
    }

    /**
     * 根据主键删除关系定义。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return sRelationDefService.removeById(id);
    }

    /**
     * 根据主键更新关系定义。
     *
     * @param sRelationDef 关系定义
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody SRelationDef sRelationDef) {
        return sRelationDefService.updateById(sRelationDef);
    }

    /**
     * 查询所有关系定义。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<SRelationDef> list() {
        return sRelationDefService.list();
    }

    /**
     * 根据主键获取关系定义。
     *
     * @param id 关系定义主键
     * @return 关系定义详情
     */
    @GetMapping("getInfo/{id}")
    public SRelationDef getInfo(@PathVariable String id) {
        return sRelationDefService.getById(id);
    }

    /**
     * 分页查询关系定义。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<SRelationDef> page(Page<SRelationDef> page) {
        return sRelationDefService.page(page);
    }

}
