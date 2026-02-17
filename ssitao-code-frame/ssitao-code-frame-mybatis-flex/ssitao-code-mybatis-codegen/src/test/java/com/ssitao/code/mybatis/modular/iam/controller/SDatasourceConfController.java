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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SDatasourceConf;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SDatasourceConfService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 数据源配置 控制层。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@RestController
@RequestMapping("/sDatasourceConf")
public class SDatasourceConfController {

    @Autowired
    private SDatasourceConfService sDatasourceConfService;

    /**
     * 保存数据源配置。
     *
     * @param sDatasourceConf 数据源配置
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody SDatasourceConf sDatasourceConf) {
        return sDatasourceConfService.save(sDatasourceConf);
    }

    /**
     * 根据主键删除数据源配置。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return sDatasourceConfService.removeById(id);
    }

    /**
     * 根据主键更新数据源配置。
     *
     * @param sDatasourceConf 数据源配置
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody SDatasourceConf sDatasourceConf) {
        return sDatasourceConfService.updateById(sDatasourceConf);
    }

    /**
     * 查询所有数据源配置。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<SDatasourceConf> list() {
        return sDatasourceConfService.list();
    }

    /**
     * 根据主键获取数据源配置。
     *
     * @param id 数据源配置主键
     * @return 数据源配置详情
     */
    @GetMapping("getInfo/{id}")
    public SDatasourceConf getInfo(@PathVariable String id) {
        return sDatasourceConfService.getById(id);
    }

    /**
     * 分页查询数据源配置。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<SDatasourceConf> page(Page<SDatasourceConf> page) {
        return sDatasourceConfService.page(page);
    }

}
