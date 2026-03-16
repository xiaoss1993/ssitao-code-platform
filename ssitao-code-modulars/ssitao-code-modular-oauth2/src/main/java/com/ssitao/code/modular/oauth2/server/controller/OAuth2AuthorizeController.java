

package com.ssitao.code.modular.oauth2.server.controller;

import com.tweb.frame.authorization.Authentication;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import com.tweb.frame.authorization.annotation.Authorize;
import com.tweb.frame.authorization.exception.UnAuthorizedException;
import com.tweb.frame.authorization.oauth2.server.OAuth2AccessToken;
import com.tweb.frame.authorization.oauth2.server.event.OAuth2GrantEvent;
import com.tweb.frame.authorization.oauth2.server.support.OAuth2Granter;
import com.tweb.frame.authorization.oauth2.server.support.code.AuthorizationCodeRequest;
import com.tweb.frame.authorization.oauth2.server.support.code.AuthorizationCodeService;
import com.tweb.frame.authorization.oauth2.server.support.code.HttpAuthorizationCodeRequest;
import com.tweb.frame.authorization.oauth2.server.support.implicit.HttpImplicitRequest;
import com.tweb.frame.authorization.oauth2.server.support.implicit.ImplicitRequest;
import com.tweb.frame.authorization.oauth2.core.GrantType;
import com.tweb.frame.authorization.oauth2.core.OAuth2Constants;
import com.ssitao.code.modular.oauth2.model.AuthorizationCodeModel;
import com.ssitao.code.modular.oauth2.model.ImplicitAccessTokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 *
 */
@RestController
@Api(tags = "OAuth2.0-服务-授权", value = "OAuth2.0-服务-授权")
@RequestMapping("${tweb.web.mappings.authorize-oauth2:oauth2/authorize}")
public class OAuth2AuthorizeController {

    @Resource
    private AuthorizationCodeService authorizationCodeService;

    @Resource
    private OAuth2Granter oAuth2Granter;

    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping(params = "response_type=code")
    @ApiOperation("获取当前登录用户OAuth2.0授权码")
    @Authorize
    @ApiImplicitParam(paramType = "query",name =  OAuth2Constants.client_id,required = true)
    public AuthorizationCodeModel requestCode(
            @RequestParam("redirect_uri") String redirectUri,
            @RequestParam(value = "state", required = false) String state,
            HttpServletRequest request) {
        Authentication authentication = Authentication.current().orElseThrow(UnAuthorizedException::new);

        AuthorizationCodeRequest codeRequest = new HttpAuthorizationCodeRequest(authentication.getUser().getId(), request);

        String code = authorizationCodeService.createAuthorizationCode(codeRequest);

        AuthorizationCodeModel model = new AuthorizationCodeModel();
        model.setCode(code);
        model.setRedirectUri(redirectUri);
        model.setState(state);
        return model;
    }


    @GetMapping(params = "response_type=token")
    @ApiOperation(value = "implicit方式申请token", tags = "OAuth2.0-服务-申请token")
    @ApiImplicitParam(paramType = "query",name =  OAuth2Constants.client_id,required = true)
    public ImplicitAccessTokenModel authorizeByImplicit(
            @RequestParam(value = "redirect_uri") String redirect_uri,
            @RequestParam(value = "state") String state,
            HttpServletRequest request) {

        ImplicitRequest implicitRequest = new HttpImplicitRequest(request);
        OAuth2AccessToken accessToken = oAuth2Granter.grant(GrantType.implicit, implicitRequest);
        publisher.publishEvent(new OAuth2GrantEvent(accessToken));

        ImplicitAccessTokenModel model = new ImplicitAccessTokenModel();
        model.setState(state);
        model.setToken_type("example");
        model.setAccess_token(accessToken.getAccessToken());
        model.setExpires_in(accessToken.getExpiresIn());
        model.setRedirect_uri(redirect_uri);
        return model;
    }

}
