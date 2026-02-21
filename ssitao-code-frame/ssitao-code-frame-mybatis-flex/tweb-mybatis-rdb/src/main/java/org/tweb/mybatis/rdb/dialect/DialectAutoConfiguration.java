package org.tweb.mybatis.rdb.dialect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

/**
 * 数据库方言自动配置
 * <p>
 * 自动检测数据库类型并配置相应的方言
 *
 * @author ssitao
 * @since 1.0.0
 */
@Configuration
public class DialectAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(DialectAutoConfiguration.class);

    /**
     * 手动指定的方言类型
     */
    @Value("${tweb.mybatis.dialect:auto}")
    private String dialectType;

    /**
     * 配置默认方言
     *
     * @param dataSource 数据源
     * @return 方言实例
     */
    @Bean
    @ConditionalOnMissingBean
    public Dialect dialect(DataSource dataSource) {
        Dialect dialect;

        if ("auto".equalsIgnoreCase(dialectType)) {
            // 自动检测数据库类型
            dialect = detectDialect(dataSource);
        } else {
            // 使用指定的方言
            dialect = DialectFactory.getDialect(dialectType);
        }

        DialectFactory.setDefaultDialect(dialect);
        log.info("配置数据库方言: {}", dialect.getName());

        return dialect;
    }

    /**
     * 自动检测数据库方言
     *
     * @param dataSource 数据源
     * @return 方言实例
     */
    private Dialect detectDialect(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            String productName = metaData.getDatabaseProductName();
            String productVersion = metaData.getDatabaseProductVersion();

            log.info("检测到数据库: {} {}", productName, productVersion);

            Dialect dialect = DialectFactory.getDialectByProductName(productName);
            log.info("自动选择方言: {}", dialect.getName());

            return dialect;

        } catch (Exception e) {
            log.warn("无法检测数据库类型，使用默认方言", e);
            return DialectFactory.getDefaultDialect();
        }
    }
}
