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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreTableview;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreTableviewService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 资源-->视图管理 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbCoreTableview")
public class TbCoreTableviewController {

    @Autowired
    private TbCoreTableviewService tbCoreTableviewService;

    /**
     * 保存资源-->视图管理。
     *
     * @param tbCoreTableview 资源-->视图管理
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreTableview tbCoreTableview) {
        return tbCoreTableviewService.save(tbCoreTableview);
    }

    /**
     * 根据主键删除资源-->视图管理。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreTableviewService.removeById(id);
    }

    /**
     * 根据主键更新资源-->视图管理。
     *
     * @param tbCoreTableview 资源-->视图管理
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreTableview tbCoreTableview) {
        return tbCoreTableviewService.updateById(tbCoreTableview);
    }

    /**
     * 查询所有资源-->视图管理。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreTableview> list() {
        return tbCoreTableviewService.list();
    }

    /**
     * 根据主键获取资源-->视图管理。
     *
     * @param id 资源-->视图管理主键
     * @return 资源-->视图管理详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreTableview getInfo(@PathVariable String id) {
        return tbCoreTableviewService.getById(id);
    }

    /**
     * 分页查询资源-->视图管理。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreTableview> page(Page<TbCoreTableview> page) {
        return tbCoreTableviewService.page(page);
    }

}
