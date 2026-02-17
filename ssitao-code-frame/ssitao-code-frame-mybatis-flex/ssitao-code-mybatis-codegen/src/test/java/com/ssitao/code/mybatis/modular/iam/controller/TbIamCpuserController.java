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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamCpuser;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamCpuserService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 三方人员管理 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbIamCpuser")
public class TbIamCpuserController {

    @Autowired
    private TbIamCpuserService tbIamCpuserService;

    /**
     * 保存三方人员管理。
     *
     * @param tbIamCpuser 三方人员管理
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbIamCpuser tbIamCpuser) {
        return tbIamCpuserService.save(tbIamCpuser);
    }

    /**
     * 根据主键删除三方人员管理。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbIamCpuserService.removeById(id);
    }

    /**
     * 根据主键更新三方人员管理。
     *
     * @param tbIamCpuser 三方人员管理
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbIamCpuser tbIamCpuser) {
        return tbIamCpuserService.updateById(tbIamCpuser);
    }

    /**
     * 查询所有三方人员管理。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbIamCpuser> list() {
        return tbIamCpuserService.list();
    }

    /**
     * 根据主键获取三方人员管理。
     *
     * @param id 三方人员管理主键
     * @return 三方人员管理详情
     */
    @GetMapping("getInfo/{id}")
    public TbIamCpuser getInfo(@PathVariable String id) {
        return tbIamCpuserService.getById(id);
    }

    /**
     * 分页查询三方人员管理。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbIamCpuser> page(Page<TbIamCpuser> page) {
        return tbIamCpuserService.page(page);
    }

}
