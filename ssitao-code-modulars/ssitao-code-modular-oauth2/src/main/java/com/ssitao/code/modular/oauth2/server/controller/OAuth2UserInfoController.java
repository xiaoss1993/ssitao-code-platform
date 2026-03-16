

package com.ssitao.code.modular.oauth2.server.controller;

import com.tweb.frame.authorization.Authentication;
import com.tweb.frame.authorization.AuthenticationHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.tweb.commons.utils.NotFoundException;
import com.tweb.frame.authorization.oauth2.server.OAuth2AccessToken;
import com.tweb.frame.authorization.oauth2.server.exception.GrantTokenException;
import com.tweb.frame.authorization.oauth2.server.token.AccessTokenService;
import com.tweb.commons.controller.message.ResponseMessage;
import com.tweb.frame.authorization.oauth2.core.ErrorType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 *
 */
@RestController
@Api(tags = "OAuth2.0-服务-获取用户信息", value = "OAuth2.0-服务-获取用户信息")
@RequestMapping("${tweb.web.mappings.oauth2-auth-info:oauth2/user-auth-info}")
public class OAuth2UserInfoController {


    @Resource
    private AccessTokenService accessTokenService;

    @GetMapping
    @ApiOperation("根据accessToken获取对应用户信息")
    public ResponseMessage<Authentication> getLoginUser(@RequestParam("access_token") String access_token) {
        OAuth2AccessToken auth2AccessEntity = accessTokenService.getTokenByAccessToken(access_token);
        if (null == auth2AccessEntity) {
            throw new GrantTokenException(ErrorType.EXPIRED_TOKEN);
        }
        return ResponseMessage.ok(AuthenticationHolder.get(auth2AccessEntity.getOwnerId()));
    }

    @GetMapping("/{userId}")
    @ApiOperation("根据accessToken获取特定的用户信息")
    public ResponseMessage<Authentication> getUserById(
            @PathVariable("userId") String userId,
            @RequestParam("access_token") String access_token) {
        OAuth2AccessToken auth2AccessEntity = accessTokenService.getTokenByAccessToken(access_token);
        if (null == auth2AccessEntity) {
            throw new GrantTokenException(ErrorType.EXPIRED_TOKEN);
        }
        if (auth2AccessEntity.getScope() == null ||(!auth2AccessEntity.getScope().contains("*")&&!auth2AccessEntity.getScope().contains("user:get"))) {
            throw new GrantTokenException(ErrorType.UNAUTHORIZED_CLIENT);
        }
        Authentication info=  AuthenticationHolder.get(userId);
        if(info==null){
            throw new NotFoundException("user:"+userId+" not found");
        }
        return ResponseMessage.ok(info);
    }

}
