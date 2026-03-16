package com.ssitao.code.modular.devtools;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;

import java.lang.annotation.*;

/**
 *
 * @since 3.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ImportAutoConfiguration(DevToolsAutoConfiguration.class)
public @interface EnableDevTools {
}
