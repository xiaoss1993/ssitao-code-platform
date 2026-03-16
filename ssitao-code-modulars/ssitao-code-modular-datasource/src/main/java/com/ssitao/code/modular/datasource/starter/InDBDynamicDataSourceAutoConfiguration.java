package com.ssitao.code.modular.datasource.starter;

import com.tweb.frame.datasource.DynamicDataSourceAutoConfiguration;
import com.tweb.frame.datasource.DynamicDataSourceProxy;
import com.tweb.frame.datasource.DynamicDataSourceService;
import com.tweb.frame.datasource.config.DynamicDataSourceConfigRepository;
import com.tweb.frame.datasource.jta.JtaDynamicDataSourceService;
import com.ssitao.code.modular.datasource.service.DataSourceConfigService;
import com.ssitao.code.modular.datasource.service.simple.InDBDataSourceRepository;
import com.ssitao.code.modular.datasource.service.simple.InDBDynamicDataSourceService;
import com.ssitao.code.modular.datasource.service.simple.InDBJtaDynamicDataSourceService;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 *
 */
@Configuration
@ComponentScan({"com.ssitao.code.modular.datasource.service"
        , "com.ssitao.code.modular.datasource.controller"})
@AutoConfigureBefore(value = DynamicDataSourceAutoConfiguration.class, name = "com.tweb.frame.datasource.jta.AtomikosDataSourceAutoConfiguration")
@SuppressWarnings("all")
public class InDBDynamicDataSourceAutoConfiguration {

    @Bean
    @Primary
    public DynamicDataSourceConfigRepository inDBDataSourceRepository(DataSourceConfigService dataSourceConfigService) {
        return new InDBDataSourceRepository(dataSourceConfigService);
    }

    @Bean
    @ConditionalOnMissingClass("com.tweb.frame.datasource.jta.JtaDynamicDataSourceService")
    public DynamicDataSourceService inDBDynamicDataSourceService(DynamicDataSourceConfigRepository repository,
                                                                 DataSource dataSource) {
        return new InDBDynamicDataSourceService(repository, new DynamicDataSourceProxy("dataSource", dataSource));
    }

    @Configuration
    @ConditionalOnClass(JtaDynamicDataSourceService.class)
    public static class InDBJtaDynamicDataSourceServiceAutoConfiguration {
        @Bean
        public DynamicDataSourceService inDBJtaDynamicDataSourceService(DynamicDataSourceConfigRepository repository,
                                                                        DataSource dataSource) {
            return new InDBJtaDynamicDataSourceService(repository, new DynamicDataSourceProxy("dataSource", dataSource));
        }
    }

}
