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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreUserinfoColumn;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreUserinfoColumnService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 用户列个性化 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbCoreUserinfoColumn")
public class TbCoreUserinfoColumnController {

    @Autowired
    private TbCoreUserinfoColumnService tbCoreUserinfoColumnService;

    /**
     * 保存用户列个性化。
     *
     * @param tbCoreUserinfoColumn 用户列个性化
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreUserinfoColumn tbCoreUserinfoColumn) {
        return tbCoreUserinfoColumnService.save(tbCoreUserinfoColumn);
    }

    /**
     * 根据主键删除用户列个性化。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreUserinfoColumnService.removeById(id);
    }

    /**
     * 根据主键更新用户列个性化。
     *
     * @param tbCoreUserinfoColumn 用户列个性化
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreUserinfoColumn tbCoreUserinfoColumn) {
        return tbCoreUserinfoColumnService.updateById(tbCoreUserinfoColumn);
    }

    /**
     * 查询所有用户列个性化。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreUserinfoColumn> list() {
        return tbCoreUserinfoColumnService.list();
    }

    /**
     * 根据主键获取用户列个性化。
     *
     * @param id 用户列个性化主键
     * @return 用户列个性化详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreUserinfoColumn getInfo(@PathVariable String id) {
        return tbCoreUserinfoColumnService.getById(id);
    }

    /**
     * 分页查询用户列个性化。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreUserinfoColumn> page(Page<TbCoreUserinfoColumn> page) {
        return tbCoreUserinfoColumnService.page(page);
    }

}
