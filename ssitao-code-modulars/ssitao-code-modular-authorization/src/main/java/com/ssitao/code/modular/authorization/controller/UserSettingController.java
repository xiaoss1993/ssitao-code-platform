package com.ssitao.code.modular.authorization.controller;

import com.ssitao.code.frame.authorization.Authentication;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.ssitao.code.frame.authorization.annotation.Authorize;
import com.ssitao.code.frame.authorization.exception.AccessDenyException;
import com.ssitao.code.commons.controller.message.ResponseMessage;
import com.ssitao.code.modular.authorization.entity.UserSettingEntity;
import com.ssitao.code.modular.authorization.service.UserSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.ssitao.code.frame.authorization.setting.UserSettingPermission.*;

/**
 *
 * @since 3.0
 */
@RestController
@RequestMapping("/user-setting")
@Authorize//(permission = "user-setting", description = "用户配置管理")
@Api(value = "用户配置管理", tags = "用户-用户配置管理")
public class UserSettingController {

    @Autowired
    private UserSettingService userSettingService;

    @GetMapping("/me/{key}/{id}")
    @Authorize(merge = false)
    @ApiOperation("获取当前用户的配置")
    public ResponseMessage<UserSettingEntity> get(Authentication authentication,
                                                  @PathVariable String key,
                                                  @PathVariable String id) {
        UserSettingEntity entity = userSettingService.selectByUser(authentication.getUser().getId(), key, id);
        if (entity != null && entity.hasPermission(R, RW)) {
            return ResponseMessage.ok(entity);
        }
        return ResponseMessage.ok();
    }

    @GetMapping("/me/{key}")
    @Authorize(merge = false)
    @ApiOperation("获取当前用户的配置列表")
    public ResponseMessage<List<UserSettingEntity>> get(Authentication authentication,
                                                        @PathVariable String key) {

        return ResponseMessage.ok(userSettingService
                .selectByUser(authentication.getUser().getId(), key)
                .stream()
                .filter(setting -> setting.hasPermission(R, RW))
                .collect(Collectors.toList()));
    }

    @PatchMapping("/me/{key}")
    @Authorize(merge = false)
    @ApiOperation("保存当前用户配置")
    public ResponseMessage<String> save(Authentication authentication,
                                        @PathVariable String key,
                                        @Validated
                                        @RequestBody UserSettingEntity userSettingEntity) {
        userSettingEntity.setId(null);
        userSettingEntity.setUserId(authentication.getUser().getId());
        userSettingEntity.setKey(key);
        UserSettingEntity old = userSettingService.selectByUser(authentication.getUser().getId(), key, userSettingEntity.getSettingId());
        if (old != null) {
            userSettingEntity.setId(old.getId());
            if (!old.hasPermission(RW, R)) {
                throw new AccessDenyException("没有权限保存此配置");
            }
        }
        userSettingEntity.setPermission(RW);
        String id = userSettingService.saveOrUpdate(userSettingEntity);
        return ResponseMessage.ok(id);
    }
}
