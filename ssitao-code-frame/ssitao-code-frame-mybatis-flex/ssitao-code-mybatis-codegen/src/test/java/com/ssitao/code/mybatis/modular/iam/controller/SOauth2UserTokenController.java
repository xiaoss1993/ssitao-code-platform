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
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity.SOauth2UserToken;
import com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.service.SOauth2UserTokenService;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * OAuth2用户授权信息 控制层。
 *
 * @author ssitao
 * @since 1.0.0
 */
@RestController
@RequestMapping("/sOauth2UserToken")
public class SOauth2UserTokenController {

    @Autowired
    private SOauth2UserTokenService sOauth2UserTokenService;

    /**
     * 保存OAuth2用户授权信息。
     *
     * @param sOauth2UserToken OAuth2用户授权信息
     * @return {@code true} 保存成功，{@code false} 保存失败
     */
    @PostMapping("save")
    public boolean save(@RequestBody SOauth2UserToken sOauth2UserToken) {
        return sOauth2UserTokenService.save(sOauth2UserToken);
    }

    /**
     * 根据主键删除OAuth2用户授权信息。
     *
     * @param id 主键
     * @return {@code true} 删除成功，{@code false} 删除失败
     */
    @DeleteMapping("remove/{id}")
    public boolean remove(@PathVariable String id) {
        return sOauth2UserTokenService.removeById(id);
    }

    /**
     * 根据主键更新OAuth2用户授权信息。
     *
     * @param sOauth2UserToken OAuth2用户授权信息
     * @return {@code true} 更新成功，{@code false} 更新失败
     */
    @PutMapping("update")
    public boolean update(@RequestBody SOauth2UserToken sOauth2UserToken) {
        return sOauth2UserTokenService.updateById(sOauth2UserToken);
    }

    /**
     * 查询所有OAuth2用户授权信息。
     *
     * @return 所有数据
     */
    @GetMapping("list")
    public List<SOauth2UserToken> list() {
        return sOauth2UserTokenService.list();
    }

    /**
     * 根据主键获取OAuth2用户授权信息。
     *
     * @param id OAuth2用户授权信息主键
     * @return OAuth2用户授权信息详情
     */
    @GetMapping("getInfo/{id}")
    public SOauth2UserToken getInfo(@PathVariable String id) {
        return sOauth2UserTokenService.getById(id);
    }

    /**
     * 分页查询OAuth2用户授权信息。
     *
     * @param page 分页对象
     * @return 分页对象
     */
    @GetMapping("page")
    public Page<SOauth2UserToken> page(Page<SOauth2UserToken> page) {
        return sOauth2UserTokenService.page(page);
    }

}
