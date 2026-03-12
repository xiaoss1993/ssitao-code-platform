package com.ssitao.code.common.exception;

/**
 * 资源未找到异常
 *
 * @author ssitao
 * @since 1.0.0
 */
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotFoundException() {
        super("资源未找到");
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String resource, Object id) {
        super(String.format("%s [id=%s] 未找到", resource, id));
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
