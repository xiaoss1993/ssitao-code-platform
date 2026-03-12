
package com.ssitao.code.frame.mybatisflex.spring.boot;

import com.ssitao.code.frame.mybatisflex.core.row.Db;
import com.ssitao.code.frame.mybatisflex.spring.FlexTransactionManager;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.core.Ordered;
import org.springframework.lang.NonNull;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

/**
 * MyBatis-Flex 事务自动配置
 * <p>
 * 在 Spring Boot 环境下自动配置 MyBatis-Flex 的事务管理器。
 * 该配置优先于 Spring 默认的事务管理器，确保使用 FlexTransactionManager。
 *
 * @author ssitao
 * @since 1.0.0
 */
@ConditionalOnClass(
    value = Db.class,
    name = {
        "org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration",
        "org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration",
    }
)
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
@ConditionalOnMissingBean(TransactionManager.class)
@Configuration(proxyBeanMethods = false)
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@AutoConfigureAfter({MybatisFlexAutoConfiguration.class})
@AutoConfigureBefore(value = {TransactionAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
public class FlexTransactionAutoConfiguration implements TransactionManagementConfigurer {

    /**
     * Flex 事务管理器实例
     * <p>
     * 使用 final 修饰是因为：
     * <ul>
     *     <li>调用 {@link #annotationDrivenTransactionManager} 方法会返回 TransactionManager 对象</li>
     *     <li>{@code @Bean} 注入又会返回 TransactionManager 对象</li>
     * </ul>
     * 需要保证两个对象的一致性。
     */
    private final FlexTransactionManager flexTransactionManager = new FlexTransactionManager();

    /**
     * 配置注解驱动的事务管理器
     *
     * @return PlatformTransactionManager 实例
     */
    @NonNull
    @Override
    @Bean(name = "transactionManager")
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return flexTransactionManager;
    }

}
