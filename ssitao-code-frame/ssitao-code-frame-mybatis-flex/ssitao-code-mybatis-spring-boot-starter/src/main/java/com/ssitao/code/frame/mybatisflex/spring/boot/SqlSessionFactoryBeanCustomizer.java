
package com.ssitao.code.frame.mybatisflex.spring.boot;

import org.mybatis.spring.SqlSessionFactoryBean;

/**
 * SqlSessionFactoryBean 自定义器接口
 * <p>
 * 用于在 Spring Boot 环境下对 FlexSqlSessionFactoryBean 进行自定义配置。
 * 可以通过实现此接口并在 Spring 容器中注册，来定制 SqlSessionFactoryBean 的行为。
 *
 * @author ssitao
 * @since 1.0.0
 * @see com.ssitao.code.frame.mybatisflex.spring.FlexSqlSessionFactoryBean
 */
@FunctionalInterface
public interface SqlSessionFactoryBeanCustomizer {

    /**
     * 自定义 SqlSessionFactoryBean 配置
     *
     * @param factoryBean SqlSessionFactoryBean 实例
     */
    void customize(SqlSessionFactoryBean factoryBean);

}
