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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbIamSyncLog;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbIamSyncLogService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 同步日志 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbIamSyncLog")
public class TbIamSyncLogController {

    @Autowired
    private TbIamSyncLogService tbIamSyncLogService;

    /**
     * 保存同步日志。
     *
     * @param tbIamSyncLog 同步日志
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbIamSyncLog tbIamSyncLog) {
        return tbIamSyncLogService.save(tbIamSyncLog);
    }

    /**
     * 根据主键删除同步日志。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbIamSyncLogService.removeById(id);
    }

    /**
     * 根据主键更新同步日志。
     *
     * @param tbIamSyncLog 同步日志
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbIamSyncLog tbIamSyncLog) {
        return tbIamSyncLogService.updateById(tbIamSyncLog);
    }

    /**
     * 查询所有同步日志。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbIamSyncLog> list() {
        return tbIamSyncLogService.list();
    }

    /**
     * 根据主键获取同步日志。
     *
     * @param id 同步日志主键
     * @return 同步日志详情
     */
    @GetMapping("getInfo/{id}")
    public TbIamSyncLog getInfo(@PathVariable String id) {
        return tbIamSyncLogService.getById(id);
    }

    /**
     * 分页查询同步日志。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbIamSyncLog> page(Page<TbIamSyncLog> page) {
        return tbIamSyncLogService.page(page);
    }

}
