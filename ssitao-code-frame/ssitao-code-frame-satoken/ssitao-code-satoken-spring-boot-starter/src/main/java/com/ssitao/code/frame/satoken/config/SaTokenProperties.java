package com.ssitao.code.frame.satoken.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Sa-Token 配置属性
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Data
@ConfigurationProperties(prefix = "sa-token")
public class SaTokenProperties {

    /**
     * token 名称（同时也是 cookie 名称）
     */
    private String tokenName = "Authorization";

    /**
     * token 有效期（单位：秒），-1 代表永久有效
     */
    private Long timeout = 2592000L;

    /**
     * token 临时有效期（指定时间内无操作就视为 token 过期）单位：秒
     */
    private Long activityTimeout = -1L;

    /**
     * 是否允许同一账号多地同时登录
     */
    private Boolean isConcurrent = true;

    /**
     * 在多人登录同一账号时，是否共用一个 token
     */
    private Boolean isShare = false;

    /**
     * token 风格
     */
    private String tokenStyle = "uuid";

    /**
     * 是否输出操作日志
     */
    private Boolean isLog = false;

    /**
     * 是否从 cookie 中读取 token
     */
    private Boolean isReadCookie = false;

    /**
     * 是否从 header 中读取 token
     */
    private Boolean isReadHeader = true;

    /**
     * token 前缀
     */
    private String tokenPrefix = "Bearer";

    /**
     * 是否在初始化配置时打印版本字符画
     */
    private Boolean isPrint = false;

    /**
     * jwt 秘钥
     */
    private String jwtSecretKey = "abcdefghijklmnopqrstuvwxyz";

    /**
     * 是否启用 JWT 模式
     */
    private Boolean jwtEnabled = true;

    /**
     * 存储方式：redis-Redis存储
     */
    private String daoType = "redis";

    /**
     * Redis 数据库索引
     */
    private Integer database = 0;

    /**
     * Redis 键前缀
     */
    private String keyPrefix = "satoken:";

}
