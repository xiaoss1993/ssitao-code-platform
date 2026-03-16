package com.ssitao.code.modular.script.service;

import com.ssitao.code.modular.script.service.simple.DefaultScriptExecutorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @since 3.0
 */
@Configuration
@ComponentScan({
        "com.ssitao.code.modular.script.service",
        "com.ssitao.code.modular.script.controller"
})
public class DynamicScriptAutoConfiguration {

    @Bean
    public DefaultScriptExecutorService defaultScriptExecutorService() {
        return new DefaultScriptExecutorService();
    }
}
