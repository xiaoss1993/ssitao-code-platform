package com.ssitao.code.modular.devtools;

import com.ssitao.code.modular.devtools.web.FileManagerDevToolsController;
import com.ssitao.code.modular.devtools.writer.CodeWriter;
import com.ssitao.code.modular.devtools.writer.DefaultCodeWriter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @since 3.0
 */
@Configuration
public class DevToolsAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(CodeWriter.class)
    public DefaultCodeWriter defaultCodeWriter() {
        return new DefaultCodeWriter();
    }

    @Bean
    public FileManagerDevToolsController fileManagerDevToolsController() {
        return new FileManagerDevToolsController();
    }
}
