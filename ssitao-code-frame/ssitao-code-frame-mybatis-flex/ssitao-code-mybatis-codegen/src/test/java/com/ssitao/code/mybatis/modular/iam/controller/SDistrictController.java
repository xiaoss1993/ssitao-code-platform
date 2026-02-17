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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SDistrict;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SDistrictService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 行政区域 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/sDistrict")
public class SDistrictController {

    @Autowired
    private SDistrictService sDistrictService;

    /**
     * 保存行政区域。
     *
     * @param sDistrict 行政区域
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody SDistrict sDistrict) {
        return sDistrictService.save(sDistrict);
    }

    /**
     * 根据主键删除行政区域。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return sDistrictService.removeById(id);
    }

    /**
     * 根据主键更新行政区域。
     *
     * @param sDistrict 行政区域
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody SDistrict sDistrict) {
        return sDistrictService.updateById(sDistrict);
    }

    /**
     * 查询所有行政区域。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<SDistrict> list() {
        return sDistrictService.list();
    }

    /**
     * 根据主键获取行政区域。
     *
     * @param id 行政区域主键
     * @return 行政区域详情
     */
    @GetMapping("getInfo/{id}")
    public SDistrict getInfo(@PathVariable String id) {
        return sDistrictService.getById(id);
    }

    /**
     * 分页查询行政区域。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<SDistrict> page(Page<SDistrict> page) {
        return sDistrictService.page(page);
    }

}
