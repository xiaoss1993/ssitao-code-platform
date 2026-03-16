

package com.ssitao.code.modular.authorization.controller;

import io.swagger.annotations.Api;
import com.ssitao.code.frame.authorization.annotation.Authorize;
import com.ssitao.code.commons.entity.param.QueryParamEntity;
import com.ssitao.code.commons.controller.SimpleGenericEntityController;
import com.ssitao.code.modular.authorization.entity.PermissionEntity;
import com.ssitao.code.modular.authorization.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 权限管理
 *
 *
 */
@RestController
@RequestMapping("${tweb.web.mappings.permission:permission}")
@Authorize(permission = "permission", description = "权限管理")
@Api(value = "权限管理",tags = "权限-权限管理")
public class PermissionController implements SimpleGenericEntityController<PermissionEntity, String, QueryParamEntity> {

    private PermissionService permissionService;

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Override
    public PermissionService getService() {
        return permissionService;
    }
}
