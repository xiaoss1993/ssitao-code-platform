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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreFuncedit;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreFunceditService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 阅读标记 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbCoreFuncedit")
public class TbCoreFunceditController {

    @Autowired
    private TbCoreFunceditService tbCoreFunceditService;

    /**
     * 保存阅读标记。
     *
     * @param tbCoreFuncedit 阅读标记
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreFuncedit tbCoreFuncedit) {
        return tbCoreFunceditService.save(tbCoreFuncedit);
    }

    /**
     * 根据主键删除阅读标记。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreFunceditService.removeById(id);
    }

    /**
     * 根据主键更新阅读标记。
     *
     * @param tbCoreFuncedit 阅读标记
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreFuncedit tbCoreFuncedit) {
        return tbCoreFunceditService.updateById(tbCoreFuncedit);
    }

    /**
     * 查询所有阅读标记。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreFuncedit> list() {
        return tbCoreFunceditService.list();
    }

    /**
     * 根据主键获取阅读标记。
     *
     * @param id 阅读标记主键
     * @return 阅读标记详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreFuncedit getInfo(@PathVariable String id) {
        return tbCoreFunceditService.getById(id);
    }

    /**
     * 分页查询阅读标记。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreFuncedit> page(Page<TbCoreFuncedit> page) {
        return tbCoreFunceditService.page(page);
    }

}
