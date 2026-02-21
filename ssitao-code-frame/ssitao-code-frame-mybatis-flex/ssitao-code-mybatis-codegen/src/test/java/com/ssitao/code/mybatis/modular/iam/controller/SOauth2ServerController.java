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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SOauth2Server;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SOauth2ServerService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * OAuth2 服务配置 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/sOauth2Server")
public class SOauth2ServerController {

    @Autowired
    private SOauth2ServerService sOauth2ServerService;

    /**
     * 保存OAuth2 服务配置。
     *
     * @param sOauth2Server OAuth2 服务配置
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody SOauth2Server sOauth2Server) {
        return sOauth2ServerService.save(sOauth2Server);
    }

    /**
     * 根据主键删除OAuth2 服务配置。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return sOauth2ServerService.removeById(id);
    }

    /**
     * 根据主键更新OAuth2 服务配置。
     *
     * @param sOauth2Server OAuth2 服务配置
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody SOauth2Server sOauth2Server) {
        return sOauth2ServerService.updateById(sOauth2Server);
    }

    /**
     * 查询所有OAuth2 服务配置。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<SOauth2Server> list() {
        return sOauth2ServerService.list();
    }

    /**
     * 根据主键获取OAuth2 服务配置。
     *
     * @param id OAuth2 服务配置主键
     * @return OAuth2 服务配置详情
     */
    @GetMapping("getInfo/{id}")
    public SOauth2Server getInfo(@PathVariable String id) {
        return sOauth2ServerService.getById(id);
    }

    /**
     * 分页查询OAuth2 服务配置。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<SOauth2Server> page(Page<SOauth2Server> page) {
        return sOauth2ServerService.page(page);
    }

}
