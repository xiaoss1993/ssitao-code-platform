package com.ssitao.code.common.exception;

/**
 * 未授权异常
 *
 * @author ssitao
 * @since 1.0.0
 */
public class UnauthorizedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnauthorizedException() {
        super("未授权访问");
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}
