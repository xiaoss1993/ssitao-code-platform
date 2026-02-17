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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbSysCalendarPush;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbSysCalendarPushService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 工作日历消息推送 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbSysCalendarPush")
public class TbSysCalendarPushController {

    @Autowired
    private TbSysCalendarPushService tbSysCalendarPushService;

    /**
     * 保存工作日历消息推送。
     *
     * @param tbSysCalendarPush 工作日历消息推送
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbSysCalendarPush tbSysCalendarPush) {
        return tbSysCalendarPushService.save(tbSysCalendarPush);
    }

    /**
     * 根据主键删除工作日历消息推送。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbSysCalendarPushService.removeById(id);
    }

    /**
     * 根据主键更新工作日历消息推送。
     *
     * @param tbSysCalendarPush 工作日历消息推送
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbSysCalendarPush tbSysCalendarPush) {
        return tbSysCalendarPushService.updateById(tbSysCalendarPush);
    }

    /**
     * 查询所有工作日历消息推送。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbSysCalendarPush> list() {
        return tbSysCalendarPushService.list();
    }

    /**
     * 根据主键获取工作日历消息推送。
     *
     * @param id 工作日历消息推送主键
     * @return 工作日历消息推送详情
     */
    @GetMapping("getInfo/{id}")
    public TbSysCalendarPush getInfo(@PathVariable String id) {
        return tbSysCalendarPushService.getById(id);
    }

    /**
     * 分页查询工作日历消息推送。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbSysCalendarPush> page(Page<TbSysCalendarPush> page) {
        return tbSysCalendarPushService.page(page);
    }

}
