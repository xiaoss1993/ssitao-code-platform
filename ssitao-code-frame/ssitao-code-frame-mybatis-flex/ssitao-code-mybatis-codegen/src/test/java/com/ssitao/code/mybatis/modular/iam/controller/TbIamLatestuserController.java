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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamLatestuser;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamLatestuserService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 常用人员 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbIamLatestuser")
public class TbIamLatestuserController {

    @Autowired
    private TbIamLatestuserService tbIamLatestuserService;

    /**
     * 保存常用人员。
     *
     * @param tbIamLatestuser 常用人员
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbIamLatestuser tbIamLatestuser) {
        return tbIamLatestuserService.save(tbIamLatestuser);
    }

    /**
     * 根据主键删除常用人员。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbIamLatestuserService.removeById(id);
    }

    /**
     * 根据主键更新常用人员。
     *
     * @param tbIamLatestuser 常用人员
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbIamLatestuser tbIamLatestuser) {
        return tbIamLatestuserService.updateById(tbIamLatestuser);
    }

    /**
     * 查询所有常用人员。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbIamLatestuser> list() {
        return tbIamLatestuserService.list();
    }

    /**
     * 根据主键获取常用人员。
     *
     * @param id 常用人员主键
     * @return 常用人员详情
     */
    @GetMapping("getInfo/{id}")
    public TbIamLatestuser getInfo(@PathVariable String id) {
        return tbIamLatestuserService.getById(id);
    }

    /**
     * 分页查询常用人员。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbIamLatestuser> page(Page<TbIamLatestuser> page) {
        return tbIamLatestuserService.page(page);
    }

}
