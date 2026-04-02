
package com.ssitao.code.starter.data.mybatis.flex.autoconfigure;

import com.ssitao.code.common.constant.PropertiesConstants;
import com.ssitao.code.common.utils.GeneralPropertySourceFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * MyBatis Flex 自动配置
 *
 * @author hellokaton
 * @since 2.0.2
 */
@AutoConfiguration
@MapperScan("${mybatis-flex.extension.mapper-package}")
@EnableTransactionManagement(proxyTargetClass = true)
@EnableConfigurationProperties(MyBatisFlexExtensionProperties.class)
@ConditionalOnProperty(prefix = "mybatis-flex.extension", name = PropertiesConstants.ENABLED, havingValue = "true")
@PropertySource(value = "classpath:default-data-mybatis-flex.yml", factory = GeneralPropertySourceFactory.class)
public class MybatisFlexAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(MybatisFlexAutoConfiguration.class);

//    @Resource
//    private DataPermissionFilter dataPermissionFilter;

    @PostConstruct
    public void postConstruct() {
        log.debug("[ContiNew Starter] - Auto Configuration 'MyBatis Flex' completed initialization.");
        //DialectFactory.registerDialect(DbType.MYSQL, new DataPermissionDialect(dataPermissionFilter));
    }

}
