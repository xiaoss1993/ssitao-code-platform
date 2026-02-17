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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreUserinfo;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreUserinfoService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 用户个性化（用户态） 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbCoreUserinfo")
public class TbCoreUserinfoController {

    @Autowired
    private TbCoreUserinfoService tbCoreUserinfoService;

    /**
     * 保存用户个性化（用户态）。
     *
     * @param tbCoreUserinfo 用户个性化（用户态）
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreUserinfo tbCoreUserinfo) {
        return tbCoreUserinfoService.save(tbCoreUserinfo);
    }

    /**
     * 根据主键删除用户个性化（用户态）。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreUserinfoService.removeById(id);
    }

    /**
     * 根据主键更新用户个性化（用户态）。
     *
     * @param tbCoreUserinfo 用户个性化（用户态）
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreUserinfo tbCoreUserinfo) {
        return tbCoreUserinfoService.updateById(tbCoreUserinfo);
    }

    /**
     * 查询所有用户个性化（用户态）。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreUserinfo> list() {
        return tbCoreUserinfoService.list();
    }

    /**
     * 根据主键获取用户个性化（用户态）。
     *
     * @param id 用户个性化（用户态）主键
     * @return 用户个性化（用户态）详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreUserinfo getInfo(@PathVariable String id) {
        return tbCoreUserinfoService.getById(id);
    }

    /**
     * 分页查询用户个性化（用户态）。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreUserinfo> page(Page<TbCoreUserinfo> page) {
        return tbCoreUserinfoService.page(page);
    }

}
