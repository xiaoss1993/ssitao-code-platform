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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreTablecolumn;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreTablecolumnService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 资源_列管理 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbCoreTablecolumn")
public class TbCoreTablecolumnController {

    @Autowired
    private TbCoreTablecolumnService tbCoreTablecolumnService;

    /**
     * 保存资源_列管理。
     *
     * @param tbCoreTablecolumn 资源_列管理
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreTablecolumn tbCoreTablecolumn) {
        return tbCoreTablecolumnService.save(tbCoreTablecolumn);
    }

    /**
     * 根据主键删除资源_列管理。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreTablecolumnService.removeById(id);
    }

    /**
     * 根据主键更新资源_列管理。
     *
     * @param tbCoreTablecolumn 资源_列管理
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreTablecolumn tbCoreTablecolumn) {
        return tbCoreTablecolumnService.updateById(tbCoreTablecolumn);
    }

    /**
     * 查询所有资源_列管理。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreTablecolumn> list() {
        return tbCoreTablecolumnService.list();
    }

    /**
     * 根据主键获取资源_列管理。
     *
     * @param id 资源_列管理主键
     * @return 资源_列管理详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreTablecolumn getInfo(@PathVariable String id) {
        return tbCoreTablecolumnService.getById(id);
    }

    /**
     * 分页查询资源_列管理。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreTablecolumn> page(Page<TbCoreTablecolumn> page) {
        return tbCoreTablecolumnService.page(page);
    }

}
