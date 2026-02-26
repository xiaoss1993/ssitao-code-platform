package com.ssitao.code.frame.security.tenant.web;

import com.ssitao.code.frame.security.tenant.annotation.CurrentTenantId;
import com.ssitao.code.frame.security.tenant.utils.TenantUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 租户ID方法参数解析器
 * <p>
 * 用于解析 {@link CurrentTenantId} 注解的参数，自动注入当前租户ID
 *
 * @author ssitao-code
 * @since 1.1.1
 */
public class TenantIdMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentTenantId.class)
                && parameter.getParameterType().equals(String.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) {
        CurrentTenantId annotation = parameter.getParameterAnnotation(CurrentTenantId.class);

        // 获取租户ID
        String tenantId = TenantUtils.getTenantId();

        // 检查是否必需
        if (annotation != null && annotation.required() && tenantId == null) {
            throw new IllegalStateException("租户功能已启用，但未能获取到租户ID");
        }

        return tenantId;
    }

}
