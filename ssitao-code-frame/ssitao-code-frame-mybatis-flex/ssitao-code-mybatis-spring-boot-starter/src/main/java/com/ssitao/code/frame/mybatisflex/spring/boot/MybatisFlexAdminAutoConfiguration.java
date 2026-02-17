

package com.ssitao.code.frame.mybatisflex.spring.boot;

import com.ssitao.code.frame.mybatisflex.core.audit.AuditManager;
import com.ssitao.code.frame.mybatisflex.core.audit.MessageFactory;
import com.ssitao.code.frame.mybatisflex.core.audit.MessageReporter;
import com.ssitao.code.frame.mybatisflex.core.audit.http.HttpMessageReporter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis-Flex Admin 自动配置
 * <p>
 * 用于配置 MyBatis-Flex 的 SQL 审计功能，
 * 支持通过 HTTP 接口将 SQL 执行信息上报到指定端点。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureAfter(MybatisFlexAutoConfiguration.class)
@EnableConfigurationProperties(MybatisFlexProperties.class)
@ConditionalOnProperty(prefix = "mybatis-flex.admin-config", name = "enable", havingValue = "true")
public class MybatisFlexAdminAutoConfiguration implements InitializingBean {

    /**
     * 消息工厂
     */
    private final MessageFactory messageFactory;

    /**
     * 配置属性
     */
    private final MybatisFlexProperties properties;

    /**
     * JSON 格式化器
     */
    private final HttpMessageReporter.JSONFormatter jsonFormatter;

    /**
     * 构造函数
     *
     * @param messageFactory 消息工厂提供者
     * @param jsonFormatter JSON 格式化器提供者
     * @param properties 配置属性
     */
    public MybatisFlexAdminAutoConfiguration(ObjectProvider<MessageFactory> messageFactory,
                                             ObjectProvider<HttpMessageReporter.JSONFormatter> jsonFormatter,
                                             MybatisFlexProperties properties) {
        this.properties = properties;
        this.jsonFormatter = jsonFormatter.getIfAvailable();
        this.messageFactory = messageFactory.getIfAvailable();
    }

    /**
     * 初始化配置
     * <p>
     * 设置审计管理器的消息工厂和消息上报器
     */
    @Override
    public void afterPropertiesSet() {
        AuditManager.setAuditEnable(true);
        if (messageFactory != null) {
            AuditManager.setMessageFactory(messageFactory);
        }
        MybatisFlexProperties.AdminConfig adminConfig = properties.getAdminConfig();
        MessageReporter messageReporter = new HttpMessageReporter(
            adminConfig.getEndpoint(),
            adminConfig.getSecretKey(),
            jsonFormatter
        );
        AuditManager.setMessageReporter(messageReporter);
    }

}
