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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamHeadmenuRelation;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamHeadmenuRelationService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 顶部菜单关联 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbIamHeadmenuRelation")
public class TbIamHeadmenuRelationController {

    @Autowired
    private TbIamHeadmenuRelationService tbIamHeadmenuRelationService;

    /**
     * 保存顶部菜单关联。
     *
     * @param tbIamHeadmenuRelation 顶部菜单关联
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbIamHeadmenuRelation tbIamHeadmenuRelation) {
        return tbIamHeadmenuRelationService.save(tbIamHeadmenuRelation);
    }

    /**
     * 根据主键删除顶部菜单关联。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbIamHeadmenuRelationService.removeById(id);
    }

    /**
     * 根据主键更新顶部菜单关联。
     *
     * @param tbIamHeadmenuRelation 顶部菜单关联
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbIamHeadmenuRelation tbIamHeadmenuRelation) {
        return tbIamHeadmenuRelationService.updateById(tbIamHeadmenuRelation);
    }

    /**
     * 查询所有顶部菜单关联。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbIamHeadmenuRelation> list() {
        return tbIamHeadmenuRelationService.list();
    }

    /**
     * 根据主键获取顶部菜单关联。
     *
     * @param id 顶部菜单关联主键
     * @return 顶部菜单关联详情
     */
    @GetMapping("getInfo/{id}")
    public TbIamHeadmenuRelation getInfo(@PathVariable String id) {
        return tbIamHeadmenuRelationService.getById(id);
    }

    /**
     * 分页查询顶部菜单关联。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbIamHeadmenuRelation> page(Page<TbIamHeadmenuRelation> page) {
        return tbIamHeadmenuRelationService.page(page);
    }

}
