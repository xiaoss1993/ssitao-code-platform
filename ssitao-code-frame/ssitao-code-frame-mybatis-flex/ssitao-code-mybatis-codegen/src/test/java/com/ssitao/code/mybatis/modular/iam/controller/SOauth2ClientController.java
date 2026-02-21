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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SOauth2Client;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SOauth2ClientService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * OAuth2客户端 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/sOauth2Client")
public class SOauth2ClientController {

    @Autowired
    private SOauth2ClientService sOauth2ClientService;

    /**
     * 保存OAuth2客户端。
     *
     * @param sOauth2Client OAuth2客户端
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody SOauth2Client sOauth2Client) {
        return sOauth2ClientService.save(sOauth2Client);
    }

    /**
     * 根据主键删除OAuth2客户端。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return sOauth2ClientService.removeById(id);
    }

    /**
     * 根据主键更新OAuth2客户端。
     *
     * @param sOauth2Client OAuth2客户端
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody SOauth2Client sOauth2Client) {
        return sOauth2ClientService.updateById(sOauth2Client);
    }

    /**
     * 查询所有OAuth2客户端。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<SOauth2Client> list() {
        return sOauth2ClientService.list();
    }

    /**
     * 根据主键获取OAuth2客户端。
     *
     * @param id OAuth2客户端主键
     * @return OAuth2客户端详情
     */
    @GetMapping("getInfo/{id}")
    public SOauth2Client getInfo(@PathVariable String id) {
        return sOauth2ClientService.getById(id);
    }

    /**
     * 分页查询OAuth2客户端。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<SOauth2Client> page(Page<SOauth2Client> page) {
        return sOauth2ClientService.page(page);
    }

}
