package com.ssitao.code.modular.template.starter;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @since 3.0
 */
@Configuration
@ComponentScan({"com.ssitao.code.modular.template"
        , "com.ssitao.code.modular.template.controller"})
public class TemplateAutoConfiguration {
}
