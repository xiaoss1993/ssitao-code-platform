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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreDeveloplog;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreDeveloplogService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 开发日志 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbCoreDeveloplog")
public class TbCoreDeveloplogController {

    @Autowired
    private TbCoreDeveloplogService tbCoreDeveloplogService;

    /**
     * 保存开发日志。
     *
     * @param tbCoreDeveloplog 开发日志
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreDeveloplog tbCoreDeveloplog) {
        return tbCoreDeveloplogService.save(tbCoreDeveloplog);
    }

    /**
     * 根据主键删除开发日志。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreDeveloplogService.removeById(id);
    }

    /**
     * 根据主键更新开发日志。
     *
     * @param tbCoreDeveloplog 开发日志
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreDeveloplog tbCoreDeveloplog) {
        return tbCoreDeveloplogService.updateById(tbCoreDeveloplog);
    }

    /**
     * 查询所有开发日志。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreDeveloplog> list() {
        return tbCoreDeveloplogService.list();
    }

    /**
     * 根据主键获取开发日志。
     *
     * @param id 开发日志主键
     * @return 开发日志详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreDeveloplog getInfo(@PathVariable String id) {
        return tbCoreDeveloplogService.getById(id);
    }

    /**
     * 分页查询开发日志。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreDeveloplog> page(Page<TbCoreDeveloplog> page) {
        return tbCoreDeveloplogService.page(page);
    }

}
