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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SAutzDetail;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SAutzDetailService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 权限设置详情 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/sAutzDetail")
public class SAutzDetailController {

    @Autowired
    private SAutzDetailService sAutzDetailService;

    /**
     * 保存权限设置详情。
     *
     * @param sAutzDetail 权限设置详情
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody SAutzDetail sAutzDetail) {
        return sAutzDetailService.save(sAutzDetail);
    }

    /**
     * 根据主键删除权限设置详情。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return sAutzDetailService.removeById(id);
    }

    /**
     * 根据主键更新权限设置详情。
     *
     * @param sAutzDetail 权限设置详情
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody SAutzDetail sAutzDetail) {
        return sAutzDetailService.updateById(sAutzDetail);
    }

    /**
     * 查询所有权限设置详情。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<SAutzDetail> list() {
        return sAutzDetailService.list();
    }

    /**
     * 根据主键获取权限设置详情。
     *
     * @param id 权限设置详情主键
     * @return 权限设置详情详情
     */
    @GetMapping("getInfo/{id}")
    public SAutzDetail getInfo(@PathVariable String id) {
        return sAutzDetailService.getById(id);
    }

    /**
     * 分页查询权限设置详情。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<SAutzDetail> page(Page<SAutzDetail> page) {
        return sAutzDetailService.page(page);
    }

}
