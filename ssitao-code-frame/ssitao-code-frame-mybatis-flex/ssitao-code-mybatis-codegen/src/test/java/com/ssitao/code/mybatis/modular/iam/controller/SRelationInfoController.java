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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SRelationInfo;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SRelationInfoService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 关系信息 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/sRelationInfo")
public class SRelationInfoController {

    @Autowired
    private SRelationInfoService sRelationInfoService;

    /**
     * 保存关系信息。
     *
     * @param sRelationInfo 关系信息
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody SRelationInfo sRelationInfo) {
        return sRelationInfoService.save(sRelationInfo);
    }

    /**
     * 根据主键删除关系信息。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return sRelationInfoService.removeById(id);
    }

    /**
     * 根据主键更新关系信息。
     *
     * @param sRelationInfo 关系信息
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody SRelationInfo sRelationInfo) {
        return sRelationInfoService.updateById(sRelationInfo);
    }

    /**
     * 查询所有关系信息。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<SRelationInfo> list() {
        return sRelationInfoService.list();
    }

    /**
     * 根据主键获取关系信息。
     *
     * @param id 关系信息主键
     * @return 关系信息详情
     */
    @GetMapping("getInfo/{id}")
    public SRelationInfo getInfo(@PathVariable String id) {
        return sRelationInfoService.getById(id);
    }

    /**
     * 分页查询关系信息。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<SRelationInfo> page(Page<SRelationInfo> page) {
        return sRelationInfoService.page(page);
    }

}
