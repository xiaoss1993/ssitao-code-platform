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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.TbFrameworkServer;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.TbFrameworkServerService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 服务器维护 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/tbFrameworkServer")
public class TbFrameworkServerController {

    @Autowired
    private TbFrameworkServerService tbFrameworkServerService;

    /**
     * 保存服务器维护。
     *
     * @param tbFrameworkServer 服务器维护
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody TbFrameworkServer tbFrameworkServer) {
        return tbFrameworkServerService.save(tbFrameworkServer);
    }

    /**
     * 根据主键删除服务器维护。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return tbFrameworkServerService.removeById(id);
    }

    /**
     * 根据主键更新服务器维护。
     *
     * @param tbFrameworkServer 服务器维护
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody TbFrameworkServer tbFrameworkServer) {
        return tbFrameworkServerService.updateById(tbFrameworkServer);
    }

    /**
     * 查询所有服务器维护。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<TbFrameworkServer> list() {
        return tbFrameworkServerService.list();
    }

    /**
     * 根据主键获取服务器维护。
     *
     * @param id 服务器维护主键
     * @return 服务器维护详情
     */
    @GetMapping("getInfo/{id}")
    public TbFrameworkServer getInfo(@PathVariable String id) {
        return tbFrameworkServerService.getById(id);
    }

    /**
     * 分页查询服务器维护。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<TbFrameworkServer> page(Page<TbFrameworkServer> page) {
        return tbFrameworkServerService.page(page);
    }

}
