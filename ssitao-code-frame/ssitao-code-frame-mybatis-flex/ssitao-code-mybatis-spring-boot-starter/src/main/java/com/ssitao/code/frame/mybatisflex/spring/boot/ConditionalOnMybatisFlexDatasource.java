
package com.ssitao.code.frame.mybatisflex.spring.boot;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Iterator;

/**
 * MyBatis-Flex 多数据源条件注解
 * <p>
 * 用于判断配置文件中是否存在 MyBatis-Flex 的多数据源配置。
 * 如果存在 mybatis-flex.datasource.* 配置，则加载多数据源自动配置类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Conditional(ConditionalOnMybatisFlexDatasource.OnMybatisFlexDataSourceCondition.class)
public @interface ConditionalOnMybatisFlexDatasource {

    /**
     * 多数据源条件判断类
     */
    @Order(Ordered.HIGHEST_PRECEDENCE)
    class OnMybatisFlexDataSourceCondition extends SpringBootCondition {

        /**
         * 判断条件是否匹配
         *
         * @param context 条件上下文
         * @param metadata 注解元数据
         * @return 条件匹配结果
         */
        @Override
        public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
            Environment env = context.getEnvironment();
            if (env instanceof AbstractEnvironment) {
                MutablePropertySources propertySources = ((AbstractEnvironment) env).getPropertySources();
                Iterator<PropertySource<?>> it = propertySources.stream().iterator();
                while (it.hasNext()) {
                    PropertySource<?> ps = it.next();
                    if (ps instanceof EnumerablePropertySource) {
                        for (String propertyName : ((EnumerablePropertySource<?>) ps).getPropertyNames()) {
                            if (propertyName.startsWith("mybatis-flex.datasource.")) {
                                return ConditionOutcome.match();
                            }
                        }
                    }
                }
            }
            return ConditionOutcome.noMatch("'mybatis-flex.datasource' is necessary.");
        }

    }

}
