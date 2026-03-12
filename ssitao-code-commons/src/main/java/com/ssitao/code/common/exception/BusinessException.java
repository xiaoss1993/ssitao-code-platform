package com.ssitao.code.common.exception;

import lombok.Getter;

/**
 * 业务异常
 *
 * @author ssitao
 * @since 1.0.0
 */
@Getter
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private final Integer code;

    /**
     * 错误消息
     */
    private final String message;

    /**
     * 错误详情
     */
    private final String detail;

    public BusinessException(String message) {
        super(message);
        this.code = 500;
        this.message = message;
        this.detail = null;
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
        this.detail = null;
    }

    public BusinessException(Integer code, String message, String detail) {
        super(message);
        this.code = code;
        this.message = message;
        this.detail = detail;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.code = 500;
        this.message = message;
        this.detail = cause != null ? cause.getMessage() : null;
    }

    public BusinessException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
        this.detail = cause != null ? cause.getMessage() : null;
    }

    @Override
    public String getMessage() {
        return message;
    }

    /**
     * 创建业务异常
     */
    public static BusinessException of(String message) {
        return new BusinessException(message);
    }

    /**
     * 创建业务异常（带错误码）
     */
    public static BusinessException of(Integer code, String message) {
        return new BusinessException(code, message);
    }

    /**
     * 创建业务异常（带详情）
     */
    public static BusinessException of(String message, String detail) {
        return new BusinessException(500, message, detail);
    }
}
