package com.ssitao.code.modular.database.starter;

import com.ssitao.code.modular.database.service.DatabaseManagerService;
import com.ssitao.code.modular.database.service.simple.SimpleDatabaseManagerService;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.ssitao.code.modular.database.controller"
        , "com.ssitao.code.modular.database.service"})
@ImportAutoConfiguration(TableMetaDataParserAutoConfiguration.class)
@AutoConfigureBefore(TableMetaDataParserAutoConfiguration.class)
public class DataBaseManagerAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(DatabaseManagerService.class)
    public SimpleDatabaseManagerService simpleDatabaseManagerService() {
        return new SimpleDatabaseManagerService();
    }


}
