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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreDictionaryLog;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreDictionaryLogService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 数据字典删除日志 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbCoreDictionaryLog")
public class TbCoreDictionaryLogController {

    @Autowired
    private TbCoreDictionaryLogService tbCoreDictionaryLogService;

    /**
     * 保存数据字典删除日志。
     *
     * @param tbCoreDictionaryLog 数据字典删除日志
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreDictionaryLog tbCoreDictionaryLog) {
        return tbCoreDictionaryLogService.save(tbCoreDictionaryLog);
    }

    /**
     * 根据主键删除数据字典删除日志。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreDictionaryLogService.removeById(id);
    }

    /**
     * 根据主键更新数据字典删除日志。
     *
     * @param tbCoreDictionaryLog 数据字典删除日志
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreDictionaryLog tbCoreDictionaryLog) {
        return tbCoreDictionaryLogService.updateById(tbCoreDictionaryLog);
    }

    /**
     * 查询所有数据字典删除日志。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreDictionaryLog> list() {
        return tbCoreDictionaryLogService.list();
    }

    /**
     * 根据主键获取数据字典删除日志。
     *
     * @param id 数据字典删除日志主键
     * @return 数据字典删除日志详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreDictionaryLog getInfo(@PathVariable String id) {
        return tbCoreDictionaryLogService.getById(id);
    }

    /**
     * 分页查询数据字典删除日志。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreDictionaryLog> page(Page<TbCoreDictionaryLog> page) {
        return tbCoreDictionaryLogService.page(page);
    }

}
