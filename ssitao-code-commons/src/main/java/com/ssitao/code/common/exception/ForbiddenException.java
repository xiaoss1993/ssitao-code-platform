package com.ssitao.code.common.exception;

/**
 * 禁止访问异常
 *
 * @author ssitao
 * @since 1.0.0
 */
public class ForbiddenException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ForbiddenException() {
        super("禁止访问");
    }

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }
}
