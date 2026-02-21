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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreJeappapi;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreJeappapiService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 移动端api 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbCoreJeappapi")
public class TbCoreJeappapiController {

    @Autowired
    private TbCoreJeappapiService tbCoreJeappapiService;

    /**
     * 保存移动端api。
     *
     * @param tbCoreJeappapi 移动端api
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreJeappapi tbCoreJeappapi) {
        return tbCoreJeappapiService.save(tbCoreJeappapi);
    }

    /**
     * 根据主键删除移动端api。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreJeappapiService.removeById(id);
    }

    /**
     * 根据主键更新移动端api。
     *
     * @param tbCoreJeappapi 移动端api
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreJeappapi tbCoreJeappapi) {
        return tbCoreJeappapiService.updateById(tbCoreJeappapi);
    }

    /**
     * 查询所有移动端api。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreJeappapi> list() {
        return tbCoreJeappapiService.list();
    }

    /**
     * 根据主键获取移动端api。
     *
     * @param id 移动端api主键
     * @return 移动端api详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreJeappapi getInfo(@PathVariable String id) {
        return tbCoreJeappapiService.getById(id);
    }

    /**
     * 分页查询移动端api。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreJeappapi> page(Page<TbCoreJeappapi> page) {
        return tbCoreJeappapiService.page(page);
    }

}
