

package com.ssitao.code.modular.authorization.controller;

import com.ssitao.code.frame.authorization.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.ssitao.code.frame.authorization.annotation.Authorize;
import com.ssitao.code.commons.entity.param.QueryParamEntity;
import com.ssitao.code.commons.controller.SimpleGenericEntityController;
import com.ssitao.code.commons.controller.message.ResponseMessage;
import com.ssitao.code.modular.authorization.entity.RoleEntity;
import com.ssitao.code.modular.authorization.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ssitao.code.commons.controller.message.ResponseMessage.ok;

/**
 * 角色控制器
 *
 *
 */
@RestController
@RequestMapping("${tweb.web.mappings.role:role}")
@Authorize(permission = "role", description = "角色管理")
@Api(value = "角色管理",tags = "权限-角色管理")
public class RoleController implements SimpleGenericEntityController<RoleEntity, String, QueryParamEntity> {

    @Autowired
    private RoleService roleService;

    @Override
    public RoleService getService() {
        return roleService;
    }

    @PutMapping("/disable/{id:.+}")
    @Authorize(action = Permission.ACTION_DISABLE)
    @ApiOperation("禁用角色")
    public ResponseMessage disable(@PathVariable String id) {
        roleService.disable(id);
        return ok();
    }

    @PutMapping("/enable/{id}")
    @Authorize(action = Permission.ACTION_ENABLE)
    @ApiOperation("启用角色")
    public ResponseMessage enable(@PathVariable String id) {
        roleService.enable(id);
        return ok();
    }
}
