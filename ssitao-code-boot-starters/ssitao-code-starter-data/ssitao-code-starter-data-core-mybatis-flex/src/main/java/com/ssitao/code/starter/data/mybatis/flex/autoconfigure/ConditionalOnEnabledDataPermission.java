package com.ssitao.code.starter.data.mybatis.flex.autoconfigure;

import com.ssitao.code.common.constant.PropertiesConstants;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@ConditionalOnProperty(prefix = "mybatis-flex.extension.data-permission", name = PropertiesConstants.ENABLED, havingValue = "true")
public @interface ConditionalOnEnabledDataPermission {
}