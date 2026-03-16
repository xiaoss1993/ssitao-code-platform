

package com.ssitao.code.modular.authorization.controller;

import com.ssitao.code.frame.authorization.Authentication;
import com.ssitao.code.frame.authorization.AuthenticationManager;
import com.ssitao.code.frame.authorization.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.ssitao.code.frame.authorization.annotation.Authorize;
import com.ssitao.code.frame.authorization.exception.UnAuthorizedException;
import com.ssitao.code.commons.entity.PagerResult;
import com.ssitao.code.commons.entity.param.QueryParamEntity;
import com.ssitao.code.commons.controller.CreateController;
import com.ssitao.code.commons.controller.QueryController;
import com.ssitao.code.commons.controller.message.ResponseMessage;
import com.ssitao.code.modular.authorization.entity.UserEntity;
import com.ssitao.code.modular.authorization.entity.bind.BindRoleUserEntity;
import com.ssitao.code.modular.authorization.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.ssitao.code.commons.controller.message.ResponseMessage.ok;

/**
 * 用户管理控制器
 *
 *
 */
@RestController
@RequestMapping("${tweb.web.mappings.user:user}")
@Authorize(permission = "user", description = "用户管理")
@Api(value = "用户管理", tags = "权限-用户管理")
public class UserController implements
        QueryController<UserEntity, String, QueryParamEntity>,
        CreateController<BindRoleUserEntity, String, BindRoleUserEntity> {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    @SuppressWarnings("unchecked")
    public UserService getService() {
        return userService;
    }

    @Override
    @SuppressWarnings("all")
    public ResponseMessage<PagerResult<UserEntity>> list(QueryParamEntity param) {
        param.excludes("password", "salt");
        return QueryController.super.list(param)
                .exclude(UserEntity.class, "password", "salt");
    }

    @Override
    @SuppressWarnings("all")
    public ResponseMessage<UserEntity> getByPrimaryKey(@PathVariable String id) {
        return QueryController.super.getByPrimaryKey(id)
                .exclude(UserEntity.class, "password", "salt");
    }

    @Authorize(action = Permission.ACTION_UPDATE)
    @PutMapping(path = "/{id:.+}")
    @ApiOperation("根据ID修改用户信息")
    public ResponseMessage<Void> updateByPrimaryKey(@PathVariable String id,
                                                    @RequestBody BindRoleUserEntity userModel) {
        getService().update(id, userModel);
        return ok();
    }

    @Authorize(action = Permission.ACTION_GET)
    @GetMapping(path = "/{id:.+}/authentication")
    @ApiOperation("获取用户的权限信息")
    public ResponseMessage<Authentication> getUserAuthentication(@PathVariable String id) {
        return ok(authenticationManager.getByUserId(id));
    }

    @Authorize(merge = false)
    @PutMapping(path = "/password")
    @ApiOperation("修改当前登录用户的密码")
    public ResponseMessage<Void> updateLoginUserPassword(@RequestParam String password,
                                                         @RequestParam String oldPassword) {

        Authentication authentication = Authentication.current().orElseThrow(UnAuthorizedException::new);
        getService().updatePassword(authentication.getUser().getId(), oldPassword, password);
        return ok();
    }

    @Authorize(action = Permission.ACTION_UPDATE)
    @PutMapping(path = "/password/{id:.+}")
    @ApiOperation("修改指定用户的密码")
    public ResponseMessage<Void> updateByPasswordPrimaryKey(@PathVariable String id,
                                                            @RequestParam String password,
                                                            @RequestParam String oldPassword) {
        getService().updatePassword(id, oldPassword, password);
        return ok();
    }

    @Override
    public ResponseMessage<String> add(@RequestBody BindRoleUserEntity data) {
        Authentication authentication = Authentication.current().orElse(null);
        if (null != authentication) {
            data.setCreatorId(authentication.getUser().getId());
        }
        return CreateController.super.add(data);
    }

    @Authorize(action = Permission.ACTION_ENABLE)
    @PutMapping(path = "/{id}/enable")
    @ApiOperation("启用用户")
    public ResponseMessage<Boolean> enable(@PathVariable String id) {
        return ok(getService().enable(id));
    }

    @Authorize(action = Permission.ACTION_DISABLE)
    @PutMapping(path = "/{id}/disable")
    @ApiOperation("禁用用户")
    public ResponseMessage<Boolean> disable(@PathVariable String id) {
        return ok(getService().disable(id));
    }

    @Override
    public BindRoleUserEntity modelToEntity(BindRoleUserEntity model, BindRoleUserEntity entity) {
        return model;
    }
}
