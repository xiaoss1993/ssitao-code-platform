package com.ssitao.code.frame.aggregate.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 聚合框架配置类
 * <p>
 * 自动配置聚合框架的核心组件，包括事件存储、事件处理器等
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Configuration
@ComponentScan(basePackages = {
        "com.ssitao.code.frame.aggregate",
        "com.ssitao.code.modular.**.infrastructure.event"
})
public class AggregateFrameworkConfiguration {

    // 配置通过 @ComponentScan 自动发现和管理以下组件：
    // 1. EventStore 事件存储实现
    // 2. EventHandler 事件处理器
    // 3. Repository 仓储实现
}
