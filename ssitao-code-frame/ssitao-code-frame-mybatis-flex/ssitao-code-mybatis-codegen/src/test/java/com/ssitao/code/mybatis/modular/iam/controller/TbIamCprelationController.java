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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamCprelation;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamCprelationService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 三方组织人员关系 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbIamCprelation")
public class TbIamCprelationController {

    @Autowired
    private TbIamCprelationService tbIamCprelationService;

    /**
     * 保存三方组织人员关系。
     *
     * @param tbIamCprelation 三方组织人员关系
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbIamCprelation tbIamCprelation) {
        return tbIamCprelationService.save(tbIamCprelation);
    }

    /**
     * 根据主键删除三方组织人员关系。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbIamCprelationService.removeById(id);
    }

    /**
     * 根据主键更新三方组织人员关系。
     *
     * @param tbIamCprelation 三方组织人员关系
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbIamCprelation tbIamCprelation) {
        return tbIamCprelationService.updateById(tbIamCprelation);
    }

    /**
     * 查询所有三方组织人员关系。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbIamCprelation> list() {
        return tbIamCprelationService.list();
    }

    /**
     * 根据主键获取三方组织人员关系。
     *
     * @param id 三方组织人员关系主键
     * @return 三方组织人员关系详情
     */
    @GetMapping("getInfo/{id}")
    public TbIamCprelation getInfo(@PathVariable String id) {
        return tbIamCprelationService.getById(id);
    }

    /**
     * 分页查询三方组织人员关系。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbIamCprelation> page(Page<TbIamCprelation> page) {
        return tbIamCprelationService.page(page);
    }

}
