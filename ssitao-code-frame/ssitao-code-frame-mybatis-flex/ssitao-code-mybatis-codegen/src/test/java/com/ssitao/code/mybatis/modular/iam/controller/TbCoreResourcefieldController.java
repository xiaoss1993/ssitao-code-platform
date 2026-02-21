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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreResourcefield;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreResourcefieldService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 单字段 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbCoreResourcefield")
public class TbCoreResourcefieldController {

    @Autowired
    private TbCoreResourcefieldService tbCoreResourcefieldService;

    /**
     * 保存单字段。
     *
     * @param tbCoreResourcefield 单字段
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreResourcefield tbCoreResourcefield) {
        return tbCoreResourcefieldService.save(tbCoreResourcefield);
    }

    /**
     * 根据主键删除单字段。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreResourcefieldService.removeById(id);
    }

    /**
     * 根据主键更新单字段。
     *
     * @param tbCoreResourcefield 单字段
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreResourcefield tbCoreResourcefield) {
        return tbCoreResourcefieldService.updateById(tbCoreResourcefield);
    }

    /**
     * 查询所有单字段。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreResourcefield> list() {
        return tbCoreResourcefieldService.list();
    }

    /**
     * 根据主键获取单字段。
     *
     * @param id 单字段主键
     * @return 单字段详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreResourcefield getInfo(@PathVariable String id) {
        return tbCoreResourcefieldService.getById(id);
    }

    /**
     * 分页查询单字段。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreResourcefield> page(Page<TbCoreResourcefield> page) {
        return tbCoreResourcefieldService.page(page);
    }

}
