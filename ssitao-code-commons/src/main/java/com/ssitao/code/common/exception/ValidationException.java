package com.ssitao.code.common.exception;

import com.ssitao.code.common.utils.ValidateResults;

/**
 * 验证异常
 *
 * @since 3.0
 */
public class ValidationException extends RuntimeException {

    private ValidateResults validateResults;

    public ValidationException() {
        super();
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(ValidateResults validateResults) {
        super("Validation failed");
        this.validateResults = validateResults;
    }

    public ValidateResults getValidateResults() {
        return validateResults;
    }

    public void setValidateResults(ValidateResults validateResults) {
        this.validateResults = validateResults;
    }
}
