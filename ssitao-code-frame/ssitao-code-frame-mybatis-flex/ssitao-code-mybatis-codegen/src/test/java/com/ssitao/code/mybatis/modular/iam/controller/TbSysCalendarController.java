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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbSysCalendar;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbSysCalendarService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 工作日历 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/tbSysCalendar")
public class TbSysCalendarController {

    @Autowired
    private TbSysCalendarService tbSysCalendarService;

    /**
     * 保存工作日历。
     *
     * @param tbSysCalendar 工作日历
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbSysCalendar tbSysCalendar) {
        return tbSysCalendarService.save(tbSysCalendar);
    }

    /**
     * 根据主键删除工作日历。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbSysCalendarService.removeById(id);
    }

    /**
     * 根据主键更新工作日历。
     *
     * @param tbSysCalendar 工作日历
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbSysCalendar tbSysCalendar) {
        return tbSysCalendarService.updateById(tbSysCalendar);
    }

    /**
     * 查询所有工作日历。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbSysCalendar> list() {
        return tbSysCalendarService.list();
    }

    /**
     * 根据主键获取工作日历。
     *
     * @param id 工作日历主键
     * @return 工作日历详情
     */
    @GetMapping("getInfo/{id}")
    public TbSysCalendar getInfo(@PathVariable String id) {
        return tbSysCalendarService.getById(id);
    }

    /**
     * 分页查询工作日历。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbSysCalendar> page(Page<TbSysCalendar> page) {
        return tbSysCalendarService.page(page);
    }

}
