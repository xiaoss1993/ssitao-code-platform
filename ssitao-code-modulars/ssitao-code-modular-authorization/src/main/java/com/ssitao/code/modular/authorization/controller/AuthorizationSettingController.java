

package com.ssitao.code.modular.authorization.controller;

import com.ssitao.code.frame.authorization.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.ssitao.code.frame.authorization.annotation.Authorize;
import com.ssitao.code.commons.entity.param.QueryParamEntity;
import com.ssitao.code.commons.controller.SimpleGenericEntityController;
import com.ssitao.code.commons.controller.message.ResponseMessage;
import com.ssitao.code.modular.authorization.entity.AuthorizationSettingEntity;
import com.ssitao.code.modular.authorization.service.AuthorizationSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 权限设置
 *
 *
 */
@RestController
@RequestMapping("${tweb.web.mappings.autz-setting:autz-setting}")
@Authorize(permission = "autz-setting", description = "权限设置")
@Api(tags = "权限-权限设置", value = "权限设置")
public class AuthorizationSettingController implements SimpleGenericEntityController<AuthorizationSettingEntity, String, QueryParamEntity> {

    private AuthorizationSettingService authorizationSettingService;

    @Autowired
    public void setAuthorizationSettingService(AuthorizationSettingService authorizationSettingService) {
        this.authorizationSettingService = authorizationSettingService;
    }

    @Override
    public AuthorizationSettingService getService() {
        return authorizationSettingService;
    }

    @GetMapping("/{type}/{settingFor}")
    @Authorize(action = Permission.ACTION_GET)
    @ApiOperation("根据type和settingFor获取配置")
    public ResponseMessage<AuthorizationSettingEntity> select(@PathVariable String type, @PathVariable String settingFor) {
        return ResponseMessage.ok(authorizationSettingService.select(type, settingFor));
    }

    @GetMapping("/permission/{permissionId}")
    @Authorize(action = Permission.ACTION_GET)
    @ApiOperation("根据权限ID获取对应的权限配置信息")
    public ResponseMessage<List<AuthorizationSettingEntity>> selectByPermissionId(@PathVariable String permissionId) {
        return ResponseMessage.ok(authorizationSettingService.selectByPermissionId(permissionId));
    }

    @PutMapping("/merge")
    @Authorize(action = Permission.ACTION_UPDATE)
    @ApiOperation("合并权限信息")
    public ResponseMessage<Void> mergeSetting(@RequestBody List<AuthorizationSettingEntity> list) {
        authorizationSettingService.mergeSetting(list);
        return ResponseMessage.ok();
    }

    @DeleteMapping("/{settingId}/{permissionId}")
    @Authorize(action = Permission.ACTION_UPDATE)
    @ApiOperation("删除单个权限配置详情")
    public ResponseMessage<Void> deleteDetail(@PathVariable String settingId, @PathVariable String permissionId) {
        authorizationSettingService.deleteDetail(settingId, permissionId);
        return ResponseMessage.ok();
    }
}
