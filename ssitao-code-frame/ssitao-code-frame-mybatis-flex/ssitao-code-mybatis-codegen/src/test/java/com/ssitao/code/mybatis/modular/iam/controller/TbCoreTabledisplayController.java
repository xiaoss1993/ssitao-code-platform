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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbCoreTabledisplay;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbCoreTabledisplayService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 资源展示信息 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbCoreTabledisplay")
public class TbCoreTabledisplayController {

    @Autowired
    private TbCoreTabledisplayService tbCoreTabledisplayService;

    /**
     * 保存资源展示信息。
     *
     * @param tbCoreTabledisplay 资源展示信息
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbCoreTabledisplay tbCoreTabledisplay) {
        return tbCoreTabledisplayService.save(tbCoreTabledisplay);
    }

    /**
     * 根据主键删除资源展示信息。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbCoreTabledisplayService.removeById(id);
    }

    /**
     * 根据主键更新资源展示信息。
     *
     * @param tbCoreTabledisplay 资源展示信息
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbCoreTabledisplay tbCoreTabledisplay) {
        return tbCoreTabledisplayService.updateById(tbCoreTabledisplay);
    }

    /**
     * 查询所有资源展示信息。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbCoreTabledisplay> list() {
        return tbCoreTabledisplayService.list();
    }

    /**
     * 根据主键获取资源展示信息。
     *
     * @param id 资源展示信息主键
     * @return 资源展示信息详情
     */
    @GetMapping("getInfo/{id}")
    public TbCoreTabledisplay getInfo(@PathVariable String id) {
        return tbCoreTabledisplayService.getById(id);
    }

    /**
     * 分页查询资源展示信息。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbCoreTabledisplay> page(Page<TbCoreTabledisplay> page) {
        return tbCoreTabledisplayService.page(page);
    }

}
