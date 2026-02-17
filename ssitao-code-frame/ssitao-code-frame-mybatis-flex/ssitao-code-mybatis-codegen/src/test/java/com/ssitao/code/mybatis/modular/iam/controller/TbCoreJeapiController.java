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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreJeapi;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreJeapiService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 平台前台api 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbCoreJeapi")
public class TbCoreJeapiController {

    @Autowired
    private TbCoreJeapiService tbCoreJeapiService;

    /**
     * 保存平台前台api。
     *
     * @param tbCoreJeapi 平台前台api
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreJeapi tbCoreJeapi) {
        return tbCoreJeapiService.save(tbCoreJeapi);
    }

    /**
     * 根据主键删除平台前台api。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreJeapiService.removeById(id);
    }

    /**
     * 根据主键更新平台前台api。
     *
     * @param tbCoreJeapi 平台前台api
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreJeapi tbCoreJeapi) {
        return tbCoreJeapiService.updateById(tbCoreJeapi);
    }

    /**
     * 查询所有平台前台api。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreJeapi> list() {
        return tbCoreJeapiService.list();
    }

    /**
     * 根据主键获取平台前台api。
     *
     * @param id 平台前台api主键
     * @return 平台前台api详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreJeapi getInfo(@PathVariable String id) {
        return tbCoreJeapiService.getById(id);
    }

    /**
     * 分页查询平台前台api。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreJeapi> page(Page<TbCoreJeapi> page) {
        return tbCoreJeapiService.page(page);
    }

}
