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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreTableindex;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreTableindexService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 资源_索引管理 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbCoreTableindex")
public class TbCoreTableindexController {

    @Autowired
    private TbCoreTableindexService tbCoreTableindexService;

    /**
     * 保存资源_索引管理。
     *
     * @param tbCoreTableindex 资源_索引管理
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreTableindex tbCoreTableindex) {
        return tbCoreTableindexService.save(tbCoreTableindex);
    }

    /**
     * 根据主键删除资源_索引管理。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreTableindexService.removeById(id);
    }

    /**
     * 根据主键更新资源_索引管理。
     *
     * @param tbCoreTableindex 资源_索引管理
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreTableindex tbCoreTableindex) {
        return tbCoreTableindexService.updateById(tbCoreTableindex);
    }

    /**
     * 查询所有资源_索引管理。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreTableindex> list() {
        return tbCoreTableindexService.list();
    }

    /**
     * 根据主键获取资源_索引管理。
     *
     * @param id 资源_索引管理主键
     * @return 资源_索引管理详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreTableindex getInfo(@PathVariable String id) {
        return tbCoreTableindexService.getById(id);
    }

    /**
     * 分页查询资源_索引管理。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreTableindex> page(Page<TbCoreTableindex> page) {
        return tbCoreTableindexService.page(page);
    }

}
