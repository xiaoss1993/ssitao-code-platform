package com.ssitao.code.gateway.filter;

import com.alibaba.fastjson2.JSON;
import com.ssitao.code.auth.model.LoginUser;
import com.ssitao.code.auth.service.AuthService;
import com.ssitao.code.common.constant.ConstantVars;
import com.ssitao.code.common.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 网关认证过滤器
 * 验证用户Token，将用户信息传递给下游服务
 *
 * @author ssitao
 * @since 1.0.0
 */
@Slf4j
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    /**
     * 不需要认证的路径
     */
    private static final String[] WHITE_LIST = {
            "/auth/login",
            "/auth/register",
            "/auth/captcha",
            "/health",
            "/actuator"
    };

    @Resource
    private AuthService authService;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();

        // 检查白名单
        if (isWhiteList(path)) {
            return chain.filter(exchange);
        }

        // 提取Token
        String token = extractToken(request);
        if (StringUtil.isEmpty(token)) {
            return unauthorized(exchange, "未登录或Token已过期");
        }

        // 验证Token
        if (!authService.validateToken(token)) {
            return unauthorized(exchange, "Token已失效");
        }

        // 获取用户信息
        try {
            LoginUser loginUser = authService.getCurrentUser(token);

            // 将用户信息添加到请求头，传递给下游服务
            ServerHttpRequest mutatedRequest = request.mutate()
                    .header("X-User-Id", loginUser.getUserId())
                    .header("X-User-Code", loginUser.getUserCode() != null ? loginUser.getUserCode() : "")
                    .header("X-User-Name", loginUser.getUserName() != null ? loginUser.getUserName() : "")
                    .header("X-Tenant-Id", loginUser.getTenantId() != null ? loginUser.getTenantId() : "")
                    .build();

            exchange = exchange.mutate().request(mutatedRequest).build();
            return chain.filter(exchange);

        } catch (Exception e) {
            log.error("获取用户信息失败", e);
            return unauthorized(exchange, "获取用户信息失败");
        }
    }

    @Override
    public int getOrder() {
        return -100;
    }

    /**
     * 提取Token
     */
    private String extractToken(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        String bearerToken = headers.getFirst(ConstantVars.TOKEN_HEADER);
        if (StringUtil.isNotEmpty(bearerToken) && bearerToken.startsWith(ConstantVars.TOKEN_PREFIX)) {
            return bearerToken.substring(ConstantVars.TOKEN_PREFIX.length());
        }
        return bearerToken;
    }

    /**
     * 检查是否在白名单中
     */
    private boolean isWhiteList(String path) {
        for (String whitePath : WHITE_LIST) {
            if (path.startsWith(whitePath)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 返回未授权响应
     */
    private Mono<Void> unauthorized(ServerWebExchange exchange, String message) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> result = new HashMap<>();
        result.put("code", 401);
        result.put("message", message);
        result.put("timestamp", System.currentTimeMillis());

        DataBuffer buffer = response.bufferFactory()
                .wrap(JSON.toJSONString(result).getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }
}
