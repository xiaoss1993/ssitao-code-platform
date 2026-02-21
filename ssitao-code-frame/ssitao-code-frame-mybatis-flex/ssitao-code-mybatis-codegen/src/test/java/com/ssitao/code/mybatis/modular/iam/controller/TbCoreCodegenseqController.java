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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreCodegenseq;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreCodegenseqService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 业务编号 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbCoreCodegenseq")
public class TbCoreCodegenseqController {

    @Autowired
    private TbCoreCodegenseqService tbCoreCodegenseqService;

    /**
     * 保存业务编号。
     *
     * @param tbCoreCodegenseq 业务编号
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreCodegenseq tbCoreCodegenseq) {
        return tbCoreCodegenseqService.save(tbCoreCodegenseq);
    }

    /**
     * 根据主键删除业务编号。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreCodegenseqService.removeById(id);
    }

    /**
     * 根据主键更新业务编号。
     *
     * @param tbCoreCodegenseq 业务编号
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreCodegenseq tbCoreCodegenseq) {
        return tbCoreCodegenseqService.updateById(tbCoreCodegenseq);
    }

    /**
     * 查询所有业务编号。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreCodegenseq> list() {
        return tbCoreCodegenseqService.list();
    }

    /**
     * 根据主键获取业务编号。
     *
     * @param id 业务编号主键
     * @return 业务编号详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreCodegenseq getInfo(@PathVariable String id) {
        return tbCoreCodegenseqService.getById(id);
    }

    /**
     * 分页查询业务编号。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreCodegenseq> page(Page<TbCoreCodegenseq> page) {
        return tbCoreCodegenseqService.page(page);
    }

}
