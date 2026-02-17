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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SDictParser;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SDictParserService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 数据字典解析配置 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/sDictParser")
public class SDictParserController {

    @Autowired
    private SDictParserService sDictParserService;

    /**
     * 保存数据字典解析配置。
     *
     * @param sDictParser 数据字典解析配置
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody SDictParser sDictParser) {
        return sDictParserService.save(sDictParser);
    }

    /**
     * 根据主键删除数据字典解析配置。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return sDictParserService.removeById(id);
    }

    /**
     * 根据主键更新数据字典解析配置。
     *
     * @param sDictParser 数据字典解析配置
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody SDictParser sDictParser) {
        return sDictParserService.updateById(sDictParser);
    }

    /**
     * 查询所有数据字典解析配置。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<SDictParser> list() {
        return sDictParserService.list();
    }

    /**
     * 根据主键获取数据字典解析配置。
     *
     * @param id 数据字典解析配置主键
     * @return 数据字典解析配置详情
     */
    @GetMapping("getInfo/{id}")
    public SDictParser getInfo(@PathVariable String id) {
        return sDictParserService.getById(id);
    }

    /**
     * 分页查询数据字典解析配置。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<SDictParser> page(Page<SDictParser> page) {
        return sDictParserService.page(page);
    }

}
