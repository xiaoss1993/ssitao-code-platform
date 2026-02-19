package com.ssitao.code.modular.iam.service.impl;

import com.ssitao.code.common.exception.BusinessException;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.frame.satoken.api.LoginUser;
import com.ssitao.code.frame.satoken.core.SecurityUtil;
import com.ssitao.code.modular.iam.dal.dataobject.OAuth2AccessTokenDO;
import com.ssitao.code.modular.iam.dal.mapper.OAuth2AccessTokenMapper;
import com.ssitao.code.modular.iam.service.OAuth2TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * OAuth2令牌服务实现
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2TokenServiceImpl implements OAuth2TokenService {

    private final OAuth2AccessTokenMapper oauth2AccessTokenMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createAccessToken(String accessToken, String refreshToken, Long userId, String clientId, String scopes) {
        OAuth2AccessTokenDO token = new OAuth2AccessTokenDO();
        token.setAccessToken(accessToken);
        token.setRefreshToken(refreshToken);
        token.setUserId(userId);
        token.setClientId(clientId);
        token.setScopes(scopes);

        // 设置过期时间（默认30天）
        token.setExpiresTime(LocalDateTime.now().plusDays(30));

        LoginUser loginUser = SecurityUtil.getLoginUser();
        if (loginUser != null) {
            token.setTenantId(loginUser.getTenantId());
        }

        token.setCreateTime(LocalDateTime.now());

        oauth2AccessTokenMapper.insert(token);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String refreshAccessToken(String refreshToken) {
        OAuth2AccessTokenDO tokenInfo = oauth2AccessTokenMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("refresh_token", refreshToken)
        );

        if (tokenInfo == null) {
            throw new BusinessException("刷新令牌无效");
        }

        // 检查是否过期
        if (tokenInfo.getExpiresTime().isBefore(LocalDateTime.now())) {
            throw new BusinessException("刷新令牌已过期");
        }

        // TODO: 生成新的访问令牌
        String newAccessToken = generateToken();

        // 更新访问令牌
        tokenInfo.setAccessToken(newAccessToken);
        tokenInfo.setExpiresTime(LocalDateTime.now().plusDays(30));
        oauth2AccessTokenMapper.update(tokenInfo);

        return newAccessToken;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeAccessToken(String accessToken) {
        OAuth2AccessTokenDO token = oauth2AccessTokenMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("access_token", accessToken)
        );

        if (token != null) {
            oauth2AccessTokenMapper.deleteById(token.getId());
        }
    }

    @Override
    public OAuth2AccessTokenDO getAccessToken(String accessToken) {
        return oauth2AccessTokenMapper.selectOneByQuery(
                QueryWrapper.create()
                        .eq("access_token", accessToken)
        );
    }

    /**
     * 生成随机令牌
     */
    private String generateToken() {
        return java.util.UUID.randomUUID().toString().replace("-", "");
    }

}
