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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreTablekey;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreTablekeyService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 资源_键管理 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbCoreTablekey")
public class TbCoreTablekeyController {

    @Autowired
    private TbCoreTablekeyService tbCoreTablekeyService;

    /**
     * 保存资源_键管理。
     *
     * @param tbCoreTablekey 资源_键管理
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreTablekey tbCoreTablekey) {
        return tbCoreTablekeyService.save(tbCoreTablekey);
    }

    /**
     * 根据主键删除资源_键管理。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreTablekeyService.removeById(id);
    }

    /**
     * 根据主键更新资源_键管理。
     *
     * @param tbCoreTablekey 资源_键管理
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreTablekey tbCoreTablekey) {
        return tbCoreTablekeyService.updateById(tbCoreTablekey);
    }

    /**
     * 查询所有资源_键管理。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreTablekey> list() {
        return tbCoreTablekeyService.list();
    }

    /**
     * 根据主键获取资源_键管理。
     *
     * @param id 资源_键管理主键
     * @return 资源_键管理详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreTablekey getInfo(@PathVariable String id) {
        return tbCoreTablekeyService.getById(id);
    }

    /**
     * 分页查询资源_键管理。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreTablekey> page(Page<TbCoreTablekey> page) {
        return tbCoreTablekeyService.page(page);
    }

}
