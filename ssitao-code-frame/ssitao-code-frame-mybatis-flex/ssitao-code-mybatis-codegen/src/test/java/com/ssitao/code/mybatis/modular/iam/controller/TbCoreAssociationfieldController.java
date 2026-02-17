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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreAssociationfield;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreAssociationfieldService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 主子功能关联字段 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbCoreAssociationfield")
public class TbCoreAssociationfieldController {

    @Autowired
    private TbCoreAssociationfieldService tbCoreAssociationfieldService;

    /**
     * 保存主子功能关联字段。
     *
     * @param tbCoreAssociationfield 主子功能关联字段
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreAssociationfield tbCoreAssociationfield) {
        return tbCoreAssociationfieldService.save(tbCoreAssociationfield);
    }

    /**
     * 根据主键删除主子功能关联字段。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreAssociationfieldService.removeById(id);
    }

    /**
     * 根据主键更新主子功能关联字段。
     *
     * @param tbCoreAssociationfield 主子功能关联字段
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreAssociationfield tbCoreAssociationfield) {
        return tbCoreAssociationfieldService.updateById(tbCoreAssociationfield);
    }

    /**
     * 查询所有主子功能关联字段。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreAssociationfield> list() {
        return tbCoreAssociationfieldService.list();
    }

    /**
     * 根据主键获取主子功能关联字段。
     *
     * @param id 主子功能关联字段主键
     * @return 主子功能关联字段详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreAssociationfield getInfo(@PathVariable String id) {
        return tbCoreAssociationfieldService.getById(id);
    }

    /**
     * 分页查询主子功能关联字段。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreAssociationfield> page(Page<TbCoreAssociationfield> page) {
        return tbCoreAssociationfieldService.page(page);
    }

}
