
package com.ssitao.code.frame.mybatisflex.core.exception;

import com.ssitao.code.frame.mybatisflex.core.exception.locale.Localizable;

import java.text.MessageFormat;
import java.util.Locale;

/**
 * @author ssitao
 */
public class MybatisFlexException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Localizable pattern;
    private Object[] arguments;

    public MybatisFlexException(Throwable cause, Localizable pattern, Object[] arguments) {
        super(cause);
        this.pattern = pattern;
        this.arguments = arguments;
    }

    public MybatisFlexException(Localizable pattern, Object... arguments) {
        this.pattern = pattern;
        this.arguments = arguments;
    }

    public MybatisFlexException(String message) {
        super(message);
    }

    public MybatisFlexException(String message, Throwable cause) {
        super(message, cause);
    }

    public MybatisFlexException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return getMessage(Locale.CHINESE);
    }

    @Override
    public String getLocalizedMessage() {
        return getMessage(Locale.getDefault());
    }

    private String getMessage(Locale locale) {
        if (pattern == null) {
            return super.getMessage();
        }
        String localizedString = pattern.getLocalizedString(locale);
        return MessageFormat.format(localizedString, arguments);
    }

}
