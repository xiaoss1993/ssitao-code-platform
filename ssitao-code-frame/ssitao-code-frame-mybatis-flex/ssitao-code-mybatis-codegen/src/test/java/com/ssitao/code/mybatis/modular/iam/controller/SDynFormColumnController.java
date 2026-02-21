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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SDynFormColumn;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SDynFormColumnService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 动态单列 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/sDynFormColumn")
public class SDynFormColumnController {

    @Autowired
    private SDynFormColumnService sDynFormColumnService;

    /**
     * 保存动态单列。
     *
     * @param sDynFormColumn 动态单列
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody SDynFormColumn sDynFormColumn) {
        return sDynFormColumnService.save(sDynFormColumn);
    }

    /**
     * 根据主键删除动态单列。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return sDynFormColumnService.removeById(id);
    }

    /**
     * 根据主键更新动态单列。
     *
     * @param sDynFormColumn 动态单列
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody SDynFormColumn sDynFormColumn) {
        return sDynFormColumnService.updateById(sDynFormColumn);
    }

    /**
     * 查询所有动态单列。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<SDynFormColumn> list() {
        return sDynFormColumnService.list();
    }

    /**
     * 根据主键获取动态单列。
     *
     * @param id 动态单列主键
     * @return 动态单列详情
     */
    @GetMapping("getInfo/{id}")
    public SDynFormColumn getInfo(@PathVariable String id) {
        return sDynFormColumnService.getById(id);
    }

    /**
     * 分页查询动态单列。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<SDynFormColumn> page(Page<SDynFormColumn> page) {
        return sDynFormColumnService.page(page);
    }

}
