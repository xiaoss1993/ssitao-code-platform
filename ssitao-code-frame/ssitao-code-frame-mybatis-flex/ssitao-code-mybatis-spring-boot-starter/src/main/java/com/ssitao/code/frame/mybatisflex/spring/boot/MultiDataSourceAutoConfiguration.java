
package com.ssitao.code.frame.mybatisflex.spring.boot;

import com.ssitao.code.frame.mybatisflex.core.datasource.DataSourceBuilder;
import com.ssitao.code.frame.mybatisflex.core.datasource.DataSourceDecipher;
import com.ssitao.code.frame.mybatisflex.core.datasource.DataSourceManager;
import com.ssitao.code.frame.mybatisflex.core.datasource.FlexDataSource;
import com.ssitao.code.frame.mybatisflex.core.dialect.DbType;
import com.ssitao.code.frame.mybatisflex.core.dialect.DbTypeUtil;
import com.ssitao.code.frame.mybatisflex.core.exception.FlexExceptions;
import com.ssitao.code.frame.mybatisflex.core.util.MapUtil;
import com.ssitao.code.frame.mybatisflex.spring.boot.MybatisFlexProperties.SeataConfig;
import com.ssitao.code.frame.mybatisflex.spring.datasource.DataSourceAdvice;
import io.seata.rm.datasource.DataSourceProxy;
import io.seata.rm.datasource.xa.DataSourceProxyXA;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Optional;

/**
 * MyBatis-Flex 多数据源自动配置
 * <p>
 * 在 Spring Boot 环境下自动配置多数据源支持，通过配置文件动态创建和管理多个数据源。
 * 支持 Seata 分布式事务集成。
 *
 * @author ssitao
 * @since 1.0.0
 */
@ConditionalOnMybatisFlexDatasource()
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(MybatisFlexProperties.class)
@ConditionalOnClass(
    value = {SqlSessionFactory.class, SqlSessionFactoryBean.class},
    name = {
        "org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration",
        "org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration",
    }
)
@AutoConfigureBefore(value = {DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class}
    , name = {"com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure",
    "com.alibaba.druid.spring.boot3.autoconfigure.DruidDataSourceAutoConfigure"})
public class MultiDataSourceAutoConfiguration {

    /**
     * 默认数据源key
     */
    private final String master;

    /**
     * 数据源配置映射
     */
    private final Map<String, Map<String, String>> dataSourceProperties;

    /**
     * Seata 分布式事务配置
     */
    private final SeataConfig seataConfig;

    /**
     * 数据源解密器
     */
    protected final DataSourceDecipher dataSourceDecipher;


    /**
     * 构造函数
     *
     * @param properties MyBatis-Flex 配置属性
     * @param dataSourceDecipherProvider 数据源解密器提供者
     */
    public MultiDataSourceAutoConfiguration(MybatisFlexProperties properties
        , ObjectProvider<DataSourceDecipher> dataSourceDecipherProvider
    ) {
        dataSourceProperties = properties.getDatasource();
        dataSourceDecipher = dataSourceDecipherProvider.getIfAvailable();
        seataConfig = properties.getSeataConfig();
        master = properties.getDefaultDatasourceKey();
    }

    /**
     * 创建 FlexDataSource Bean
     *
     * @return FlexDataSource 实例
     */
    @Bean
    @ConditionalOnMissingBean
    public DataSource dataSource() {

        FlexDataSource flexDataSource = null;

        if (dataSourceProperties != null && !dataSourceProperties.isEmpty()) {

            if (dataSourceDecipher != null) {
                DataSourceManager.setDecipher(dataSourceDecipher);
            }

            if (master != null) {
                Map<String, String> map = dataSourceProperties.remove(master);
                if (map != null) {
                    // 这里创建master时，flexDataSource一定是null
                    flexDataSource = addDataSource(MapUtil.entry(master, map), null);
                } else {
                    throw FlexExceptions.wrap("没有找到默认数据源 \"%s\" 对应的配置，请检查您的多数据源配置。", master);
                }
            }

            for (Map.Entry<String, Map<String, String>> entry : dataSourceProperties.entrySet()) {
                flexDataSource = addDataSource(entry, flexDataSource);
            }
        }

        return flexDataSource;
    }

    /**
     * 添加数据源到 FlexDataSource
     *
     * @param entry 数据源配置条目
     * @param flexDataSource FlexDataSource 实例
     * @return 更新后的 FlexDataSource
     */
    private FlexDataSource addDataSource(Map.Entry<String, Map<String, String>> entry, FlexDataSource flexDataSource) {
        DataSource dataSource = new DataSourceBuilder(entry.getValue()).build();
        DataSourceManager.decryptDataSource(dataSource);

        // 数据库类型
        DbType dbType = null;
        if (seataConfig != null && seataConfig.isEnable()) {
            if (seataConfig.getSeataMode() == MybatisFlexProperties.SeataMode.XA) {
                DataSourceProxyXA sourceProxyXa = new DataSourceProxyXA(dataSource);
                dbType = DbType.findByName(sourceProxyXa.getDbType());
                dataSource = sourceProxyXa;
            } else {
                DataSourceProxy dataSourceProxy = new DataSourceProxy(dataSource);
                dbType = DbType.findByName(dataSourceProxy.getDbType());
                dataSource = dataSourceProxy;
            }
        }

        // 如果没有构建成功dbType，需要自解析
        final DataSource lambdaInnerDataSource = dataSource;
        dbType = Optional.ofNullable(dbType).orElseGet(() -> DbTypeUtil.getDbType(lambdaInnerDataSource));
        if (flexDataSource == null) {
            flexDataSource = new FlexDataSource(entry.getKey(), dataSource, dbType, false);
        } else {
            flexDataSource.addDataSource(entry.getKey(), dataSource, dbType, false);
        }
        return flexDataSource;
    }


    /**
     * 创建数据源切换切面
     * <p>
     * 用于拦截 @UseDataSource 注解，实现动态数据源切换
     *
     * @return DataSourceAdvice 切面实例
     */
    @Bean
    @ConditionalOnMissingBean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public DataSourceAdvice dataSourceAdvice() {
        return new DataSourceAdvice();
    }

}
