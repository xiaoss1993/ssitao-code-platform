package com.ssitao.code.frame.log.operation.core;

import cn.hutool.extra.servlet.ServletUtil;
import com.alibaba.fastjson2.JSON;
import com.ssitao.code.frame.log.operation.annotation.AuditLog;
import com.ssitao.code.frame.satoken.api.LoginUser;
import com.ssitao.code.frame.satoken.core.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 操作日志切面
 *
 * @author ssitao-code
 * @since 1.1.1
 */
@Slf4j
@Aspect
@Component
public class AuditLogAspect {

    private static final String TRACE_ID = "traceId";

    @Around("@annotation(auditLog)")
    public Object around(ProceedingJoinPoint joinPoint, AuditLog auditLog) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = null;
        Throwable exception = null;

        try {
            // 执行方法
            result = joinPoint.proceed();
            return result;
        } catch (Throwable e) {
            exception = e;
            throw e;
        } finally {
            long duration = System.currentTimeMillis() - startTime;
            // 记录操作日志
            logAuditLog(joinPoint, auditLog, result, exception, duration);
        }
    }

    /**
     * 记录操作日志
     *
     * @param joinPoint 切点
     * @param auditLog  操作日志注解
     * @param result    返回结果
     * @param exception  异常
     * @param duration   执行时长
     */
    private void logAuditLog(ProceedingJoinPoint joinPoint, AuditLog auditLog, Object result, Throwable exception, long duration) {
        try {
            // 获取方法信息
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();

            // 获取请求信息
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes != null ? attributes.getRequest() : null;

            // 获取登录用户信息
            LoginUser loginUser = null;
            try {
                loginUser = SecurityUtil.getLoginUser();
            } catch (Exception ignored) {
                // 未登录或获取失败
            }

            // 构建日志信息
            Map<String, Object> logInfo = new HashMap<>();
            logInfo.put("module", auditLog.module());
            logInfo.put("operation", auditLog.name());
            logInfo.put("type", auditLog.type().name());
            logInfo.put("method", getMethodSignature(method));
            logInfo.put("duration", duration + "ms");
            logInfo.put("status", exception == null ? "SUCCESS" : "ERROR");

            // 用户信息
            if (loginUser != null) {
                logInfo.put("userId", loginUser.getId());
                logInfo.put("username", loginUser.getUsername());
                logInfo.put("tenantId", loginUser.getTenantId());
            }

            // 请求信息
            if (request != null) {
                logInfo.put("uri", request.getRequestURI());
                logInfo.put("method", request.getMethod());
                logInfo.put("ip", ServletUtil.getClientIP(request));
                logInfo.put("userAgent", request.getHeader("User-Agent"));
            }

            // 请求参数
            if (auditLog.logArgs()) {
                Object[] args = joinPoint.getArgs();
                if (args != null && args.length > 0) {
                    try {
                        logInfo.put("args", JSON.toJSONString(args));
                    } catch (Exception e) {
                        logInfo.put("args", args.toString());
                    }
                }
            }

            // 返回结果
            if (auditLog.logResult() && result != null) {
                try {
                    logInfo.put("result", JSON.toJSONString(result));
                } catch (Exception e) {
                    logInfo.put("result", result.toString());
                }
            }

            // 异常信息
            if (auditLog.logError() && exception != null) {
                logInfo.put("error", exception.getMessage());
                logInfo.put("errorType", exception.getClass().getSimpleName());
            }

            // 记录日志
            if (exception != null) {
                log.error("操作日志: {}", JSON.toJSONString(logInfo), exception);
            } else {
                log.info("操作日志: {}", JSON.toJSONString(logInfo));
            }

        } catch (Exception e) {
            log.error("记录操作日志失败", e);
        }
    }

    /**
     * 获取方法签名
     *
     * @param method 方法
     * @return 方法签名
     */
    private String getMethodSignature(Method method) {
        return method.getDeclaringClass().getSimpleName() + "." + method.getName();
    }

}
