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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SScript;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SScriptService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 动态脚本 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/sScript")
public class SScriptController {

    @Autowired
    private SScriptService sScriptService;

    /**
     * 保存动态脚本。
     *
     * @param sScript 动态脚本
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody SScript sScript) {
        return sScriptService.save(sScript);
    }

    /**
     * 根据主键删除动态脚本。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return sScriptService.removeById(id);
    }

    /**
     * 根据主键更新动态脚本。
     *
     * @param sScript 动态脚本
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody SScript sScript) {
        return sScriptService.updateById(sScript);
    }

    /**
     * 查询所有动态脚本。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<SScript> list() {
        return sScriptService.list();
    }

    /**
     * 根据主键获取动态脚本。
     *
     * @param id 动态脚本主键
     * @return 动态脚本详情
     */
    @GetMapping("getInfo/{id}")
    public SScript getInfo(@PathVariable String id) {
        return sScriptService.getById(id);
    }

    /**
     * 分页查询动态脚本。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<SScript> page(Page<SScript> page) {
        return sScriptService.page(page);
    }

}
