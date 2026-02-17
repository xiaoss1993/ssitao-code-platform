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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamAccountbind;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamAccountbindService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 三方人员绑定 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbIamAccountbind")
public class TbIamAccountbindController {

    @Autowired
    private TbIamAccountbindService tbIamAccountbindService;

    /**
     * 保存三方人员绑定。
     *
     * @param tbIamAccountbind 三方人员绑定
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbIamAccountbind tbIamAccountbind) {
        return tbIamAccountbindService.save(tbIamAccountbind);
    }

    /**
     * 根据主键删除三方人员绑定。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbIamAccountbindService.removeById(id);
    }

    /**
     * 根据主键更新三方人员绑定。
     *
     * @param tbIamAccountbind 三方人员绑定
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbIamAccountbind tbIamAccountbind) {
        return tbIamAccountbindService.updateById(tbIamAccountbind);
    }

    /**
     * 查询所有三方人员绑定。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbIamAccountbind> list() {
        return tbIamAccountbindService.list();
    }

    /**
     * 根据主键获取三方人员绑定。
     *
     * @param id 三方人员绑定主键
     * @return 三方人员绑定详情
     */
    @GetMapping("getInfo/{id}")
    public TbIamAccountbind getInfo(@PathVariable String id) {
        return tbIamAccountbindService.getById(id);
    }

    /**
     * 分页查询三方人员绑定。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbIamAccountbind> page(Page<TbIamAccountbind> page) {
        return tbIamAccountbindService.page(page);
    }

}
