package com.ssitao.code.modular.dashboard.controller;

import com.alibaba.fastjson.JSON;
import com.ssitao.code.modular.dashboard.controller.model.UserDashBoardResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.ssitao.code.frame.authorization.Authentication;
import com.ssitao.code.frame.authorization.AuthenticationPredicate;
import com.ssitao.code.frame.authorization.annotation.Authorize;
import com.ssitao.code.commons.entity.DataStatus;
import com.ssitao.code.commons.controller.message.ResponseMessage;
import com.ssitao.code.modular.dashboard.service.DashBoardConfigEntity;
import com.ssitao.code.modular.dashboard.service.DashBoardService;
import com.ssitao.code.modular.iam.authorization.entity.UserSettingEntity;
import com.ssitao.code.modular.iam.authorization.service.UserSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 * @since 3.0
 */
@RestController
@RequestMapping("/dashboard-user")
@Api(tags = "仪表盘-用户配置", value = "仪表盘用户配置")
@Authorize
public class DashBoardUserConfigController {

    @Autowired
    private UserSettingService userSettingService;

    @Autowired
    private DashBoardService dashBoardService;

    @GetMapping("/all")
    @ApiOperation("获取用户可选择的仪表盘配置")
    public ResponseMessage<List<UserDashBoardResponse>> getUserAllDashBoardConfig(Authentication authentication) {
        List<UserDashBoardResponse> configList = dashBoardService.select()
                .stream()
                .filter(Objects::nonNull)
                .filter(config-> DataStatus.STATUS_ENABLED.equals(config.getStatus()))
                //过滤权限
                .filter(config -> StringUtils.isEmpty(config) ||
                        AuthenticationPredicate.has(config.getPermission()).test(authentication))
                .map(config -> config.copyTo(new UserDashBoardResponse()))
                .collect(Collectors.toList());
        return ResponseMessage.ok(configList);
    }

    @GetMapping
    @ApiOperation("获取用户自定义的仪表盘配置")
    public ResponseMessage<List<UserDashBoardResponse>> getUserConfigDashBoardConfig(Authentication authentication) {
        UserSettingEntity settingEntity = userSettingService.selectByUser(authentication.getUser().getId(), "dashboard-config", "current");
        List<DashBoardConfigEntity> configs;

        if (settingEntity == null) {
            configs = dashBoardService.selectAllDefaults();
            Collections.sort(configs);
        } else {
            List<String> ids = JSON.parseArray(settingEntity.getSetting(), String.class);
            configs = dashBoardService.selectByPk(ids);
            configs.sort(Comparator.comparing(conf -> ids.indexOf(conf.getId())));
        }
        List<UserDashBoardResponse> configList = configs.stream()
                //过滤权限
                .filter(config -> DataStatus.STATUS_ENABLED.equals(config.getStatus())&&(!StringUtils.hasText(config.getPermission()) ||
                        AuthenticationPredicate.has(config.getPermission()).test(authentication)))
                .map(config -> config.copyTo(new UserDashBoardResponse()))
                .collect(Collectors.toList());

        return ResponseMessage.ok(configList);
    }

    @PutMapping
    @ApiOperation("保存用户自定义的仪表盘配置")
    public ResponseMessage<Void> saveUserDashBoardConfig(@RequestBody List<String> configIdList, Authentication authentication) {
        UserSettingEntity settingEntity = userSettingService.selectByUser(authentication.getUser().getId(), "dashboard-config", "current");
        if (settingEntity == null) {
            settingEntity = userSettingService.createEntity();
            settingEntity.setUserId(authentication.getUser().getId());
            settingEntity.setKey("dashboard-config");
            settingEntity.setSettingId("current");
        }
        settingEntity.setSetting(JSON.toJSONString(configIdList));
        userSettingService.saveOrUpdate(settingEntity);

        return ResponseMessage.ok();
    }
}
