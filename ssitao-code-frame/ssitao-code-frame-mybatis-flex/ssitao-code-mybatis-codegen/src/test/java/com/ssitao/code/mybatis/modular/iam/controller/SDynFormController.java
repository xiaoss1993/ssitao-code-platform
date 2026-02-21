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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SDynForm;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SDynFormService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 动态单 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/sDynForm")
public class SDynFormController {

    @Autowired
    private SDynFormService sDynFormService;

    /**
     * 保存动态单。
     *
     * @param sDynForm 动态单
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody SDynForm sDynForm) {
        return sDynFormService.save(sDynForm);
    }

    /**
     * 根据主键删除动态单。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return sDynFormService.removeById(id);
    }

    /**
     * 根据主键更新动态单。
     *
     * @param sDynForm 动态单
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody SDynForm sDynForm) {
        return sDynFormService.updateById(sDynForm);
    }

    /**
     * 查询所有动态单。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<SDynForm> list() {
        return sDynFormService.list();
    }

    /**
     * 根据主键获取动态单。
     *
     * @param id 动态单主键
     * @return 动态单详情
     */
    @GetMapping("getInfo/{id}")
    public SDynForm getInfo(@PathVariable String id) {
        return sDynFormService.getById(id);
    }

    /**
     * 分页查询动态单。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<SDynForm> page(Page<SDynForm> page) {
        return sDynFormService.page(page);
    }

}
