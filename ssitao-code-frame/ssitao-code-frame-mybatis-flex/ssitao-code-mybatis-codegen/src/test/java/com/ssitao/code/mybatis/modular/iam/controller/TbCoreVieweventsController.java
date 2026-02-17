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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreViewevents;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreVieweventsService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 平台组件事件定义 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbCoreViewevents")
public class TbCoreVieweventsController {

    @Autowired
    private TbCoreVieweventsService tbCoreVieweventsService;

    /**
     * 保存平台组件事件定义。
     *
     * @param tbCoreViewevents 平台组件事件定义
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreViewevents tbCoreViewevents) {
        return tbCoreVieweventsService.save(tbCoreViewevents);
    }

    /**
     * 根据主键删除平台组件事件定义。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreVieweventsService.removeById(id);
    }

    /**
     * 根据主键更新平台组件事件定义。
     *
     * @param tbCoreViewevents 平台组件事件定义
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreViewevents tbCoreViewevents) {
        return tbCoreVieweventsService.updateById(tbCoreViewevents);
    }

    /**
     * 查询所有平台组件事件定义。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreViewevents> list() {
        return tbCoreVieweventsService.list();
    }

    /**
     * 根据主键获取平台组件事件定义。
     *
     * @param id 平台组件事件定义主键
     * @return 平台组件事件定义详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreViewevents getInfo(@PathVariable String id) {
        return tbCoreVieweventsService.getById(id);
    }

    /**
     * 分页查询平台组件事件定义。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreViewevents> page(Page<TbCoreViewevents> page) {
        return tbCoreVieweventsService.page(page);
    }

}
