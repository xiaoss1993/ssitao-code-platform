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

import org.apache.ibatis.scripting.LanguageDriver;
import org.mybatis.scripting.freemarker.FreeMarkerLanguageDriver;
import org.mybatis.scripting.freemarker.FreeMarkerLanguageDriverConfig;
import org.mybatis.scripting.thymeleaf.ThymeleafLanguageDriver;
import org.mybatis.scripting.thymeleaf.ThymeleafLanguageDriverConfig;
import org.mybatis.scripting.velocity.VelocityLanguageDriver;
import org.mybatis.scripting.velocity.VelocityLanguageDriverConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis 脚本语言驱动自动配置
 * <p>
 * 为 MyBatis-Flex 配置脚本语言驱动，支持 FreeMarker、Velocity 和 Thymeleaf。
 * 用于在 SQL 映射文件中使用模板语言生成动态 SQL。
 * <p>
 * 注：常规项目一般不需要使用，只是为了与 MyBatis 自带配置保持同步。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(LanguageDriver.class)
public class MybatisLanguageDriverAutoConfiguration {

    /**
     * 配置属性前缀
     */
    private static final String CONFIGURATION_PROPERTY_PREFIX = "mybatis-flex.scripting-language-driver";

    /**
     * FreeMarker 1.1.x 及以下版本的配置类
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(FreeMarkerLanguageDriver.class)
    @ConditionalOnMissingClass("org.mybatis.scripting.freemarker.FreeMarkerLanguageDriverConfig")
    public static class LegacyFreeMarkerConfiguration {

        /**
         * 创建 FreeMarker 语言驱动
         *
         * @return FreeMarkerLanguageDriver 实例
         */
        @Bean
        @ConditionalOnMissingBean
        FreeMarkerLanguageDriver freeMarkerLanguageDriver() {
            return new FreeMarkerLanguageDriver();
        }

    }

    /**
     * FreeMarker 1.2.x 及以上版本的配置类
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({FreeMarkerLanguageDriver.class, FreeMarkerLanguageDriverConfig.class})
    public static class FreeMarkerConfiguration {

        /**
         * 创建 FreeMarker 语言驱动
         *
         * @param config FreeMarker 配置
         * @return FreeMarkerLanguageDriver 实例
         */
        @Bean
        @ConditionalOnMissingBean
        FreeMarkerLanguageDriver freeMarkerLanguageDriver(FreeMarkerLanguageDriverConfig config) {
            return new FreeMarkerLanguageDriver(config);
        }

        /**
         * 创建 FreeMarker 配置
         *
         * @return FreeMarkerLanguageDriverConfig 实例
         */
        @Bean
        @ConditionalOnMissingBean
        @ConfigurationProperties(CONFIGURATION_PROPERTY_PREFIX + ".freemarker")
        public FreeMarkerLanguageDriverConfig freeMarkerLanguageDriverConfig() {
            return FreeMarkerLanguageDriverConfig.newInstance();
        }

    }

    /**
     * Velocity 2.0 及以下版本的配置类
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(org.mybatis.scripting.velocity.Driver.class)
    @ConditionalOnMissingClass("org.mybatis.scripting.velocity.VelocityLanguageDriverConfig")
    @SuppressWarnings("deprecation")
    public static class LegacyVelocityConfiguration {

        /**
         * 创建 Velocity 语言驱动
         *
         * @return Velocity Driver 实例
         */
        @Bean
        @ConditionalOnMissingBean
        org.mybatis.scripting.velocity.Driver velocityLanguageDriver() {
            return new org.mybatis.scripting.velocity.Driver();
        }

    }

    /**
     * Velocity 2.1.x 及以上版本的配置类
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass({VelocityLanguageDriver.class, VelocityLanguageDriverConfig.class})
    public static class VelocityConfiguration {

        /**
         * 创建 Velocity 语言驱动
         *
         * @param config Velocity 配置
         * @return VelocityLanguageDriver 实例
         */
        @Bean
        @ConditionalOnMissingBean
        VelocityLanguageDriver velocityLanguageDriver(VelocityLanguageDriverConfig config) {
            return new VelocityLanguageDriver(config);
        }

        /**
         * 创建 Velocity 配置
         *
         * @return VelocityLanguageDriverConfig 实例
         */
        @Bean
        @ConditionalOnMissingBean
        @ConfigurationProperties(CONFIGURATION_PROPERTY_PREFIX + ".velocity")
        public VelocityLanguageDriverConfig velocityLanguageDriverConfig() {
            return VelocityLanguageDriverConfig.newInstance();
        }

    }

    /**
     * Thymeleaf 语言驱动的配置类
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(ThymeleafLanguageDriver.class)
    public static class ThymeleafConfiguration {

        /**
         * 创建 Thymeleaf 语言驱动
         *
         * @param config Thymeleaf 配置
         * @return ThymeleafLanguageDriver 实例
         */
        @Bean
        @ConditionalOnMissingBean
        ThymeleafLanguageDriver thymeleafLanguageDriver(ThymeleafLanguageDriverConfig config) {
            return new ThymeleafLanguageDriver(config);
        }

        /**
         * 创建 Thymeleaf 配置
         *
         * @return ThymeleafLanguageDriverConfig 实例
         */
        @Bean
        @ConditionalOnMissingBean
        @ConfigurationProperties(CONFIGURATION_PROPERTY_PREFIX + ".thymeleaf")
        public ThymeleafLanguageDriverConfig thymeleafLanguageDriverConfig() {
            return ThymeleafLanguageDriverConfig.newInstance();
        }

        // This class provides to avoid the https://github.com/spring-projects/spring-boot/issues/21626 as workaround.
        @SuppressWarnings("unused")
        private static class MetadataThymeleafLanguageDriverConfig extends ThymeleafLanguageDriverConfig {

            @ConfigurationProperties(CONFIGURATION_PROPERTY_PREFIX + ".thymeleaf.dialect")
            @Override
            public DialectConfig getDialect() {
                return super.getDialect();
            }

            @ConfigurationProperties(CONFIGURATION_PROPERTY_PREFIX + ".thymeleaf.template-file")
            @Override
            public TemplateFileConfig getTemplateFile() {
                return super.getTemplateFile();
            }

        }

    }

}
