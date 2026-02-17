/*
 *    Copyright 2015-2022 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
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
