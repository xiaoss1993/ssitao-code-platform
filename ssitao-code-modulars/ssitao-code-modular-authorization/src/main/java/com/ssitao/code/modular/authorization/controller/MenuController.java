/*
 *  Copyright 2020 http://www.hswebframework.org
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 */

package com.ssitao.code.modular.authorization.controller;

import com.ssitao.code.frame.authorization.Authentication;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import com.ssitao.code.frame.authorization.annotation.Authorize;
import com.ssitao.code.commons.entity.param.QueryParamEntity;
import com.ssitao.code.commons.controller.SimpleGenericEntityController;
import com.ssitao.code.commons.controller.message.ResponseMessage;
import com.ssitao.code.modular.authorization.entity.MenuEntity;
import com.ssitao.code.modular.authorization.entity.UserMenuEntity;
import com.ssitao.code.modular.authorization.service.MenuService;
import com.ssitao.code.modular.authorization.service.UserMenuManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.ssitao.code.commons.controller.message.ResponseMessage.ok;

/**
 * 菜单分组
 *
 *
 */
@RestController
@RequestMapping("${tweb.web.mappings.menu:menu}")
@Authorize(permission = "menu",description = "菜单管理")
@Api(value = "系统菜单管理",tags = "权限-菜单管理")
public class MenuController implements SimpleGenericEntityController<MenuEntity, String, QueryParamEntity> {

    private MenuService menuService;

    private UserMenuManagerService userMenuManagerService;

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }

    @Autowired
    public void setUserMenuManagerService(UserMenuManagerService userMenuManagerService) {
        this.userMenuManagerService = userMenuManagerService;
    }

    @Override
    public MenuService getService() {
        return menuService;
    }

    @GetMapping("/user-own/list")
    @Authorize(merge = false)
    @ApiOperation("获取当前用户的菜单列表")
    public ResponseMessage<List<UserMenuEntity>> getUserMenuAsList(@ApiParam(hidden = true) Authentication authentication) {
        return ok(userMenuManagerService.getUserMenuAsList(authentication.getUser().getId()));
    }

    @GetMapping("/user-own/tree")
    @Authorize(merge = false)
    @ApiOperation("获取当前用户的菜单树")
    public ResponseMessage<List<UserMenuEntity>> getUserMenuAsTree(@ApiParam(hidden = true) Authentication authentication) {
        return ok(userMenuManagerService.getUserMenuAsTree(authentication.getUser().getId()));
    }
}
