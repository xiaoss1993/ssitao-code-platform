
package com.ssitao.code.frame.mybatisflex.spring.boot;

import com.ssitao.code.frame.mybatisflex.core.mybatis.FlexConfiguration;

/**
 * FlexConfiguration 配置自定义器接口
 * <p>
 * 用于在 Spring Boot 环境下对 MyBatis-Flex 的 Configuration 进行自定义配置。
 * 可以通过实现此接口并在 Spring 容器中注册，来定制 Configuration 的行为。
 *
 * @author ssitao
 * @since 1.0.0
 */
@FunctionalInterface
public interface ConfigurationCustomizer {

    /**
     * 自定义 FlexConfiguration 配置
     *
     * @param configuration MyBatis Flex Configuration 实例
     */
    void customize(FlexConfiguration configuration);

}
