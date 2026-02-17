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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbMetaNofinddic;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbMetaNofinddicService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 丢失字典信息 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbMetaNofinddic")
public class TbMetaNofinddicController {

    @Autowired
    private TbMetaNofinddicService tbMetaNofinddicService;

    /**
     * 保存丢失字典信息。
     *
     * @param tbMetaNofinddic 丢失字典信息
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbMetaNofinddic tbMetaNofinddic) {
        return tbMetaNofinddicService.save(tbMetaNofinddic);
    }

    /**
     * 根据主键删除丢失字典信息。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbMetaNofinddicService.removeById(id);
    }

    /**
     * 根据主键更新丢失字典信息。
     *
     * @param tbMetaNofinddic 丢失字典信息
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbMetaNofinddic tbMetaNofinddic) {
        return tbMetaNofinddicService.updateById(tbMetaNofinddic);
    }

    /**
     * 查询所有丢失字典信息。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbMetaNofinddic> list() {
        return tbMetaNofinddicService.list();
    }

    /**
     * 根据主键获取丢失字典信息。
     *
     * @param id 丢失字典信息主键
     * @return 丢失字典信息详情
     */
    @GetMapping("getInfo/{id}")
    public TbMetaNofinddic getInfo(@PathVariable String id) {
        return tbMetaNofinddicService.getById(id);
    }

    /**
     * 分页查询丢失字典信息。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbMetaNofinddic> page(Page<TbMetaNofinddic> page) {
        return tbMetaNofinddicService.page(page);
    }

}
