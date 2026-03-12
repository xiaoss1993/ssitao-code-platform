
package com.ssitao.code.frame.mybatisflex.core.exception;

import com.ssitao.code.frame.mybatisflex.core.exception.locale.Localizable;

/**
 * MybatisFlexException 异常封装类
 */
public final class FlexExceptions {

    private FlexExceptions() {
    }


    /**
     * 封装 MybatisFlexException 异常
     *
     * @param throwable 异常
     * @return MybatisFlexException
     */
    public static MybatisFlexException wrap(Throwable throwable) {
        if (throwable instanceof MybatisFlexException) {
            return (MybatisFlexException) throwable;
        }
        return new MybatisFlexException(throwable);
    }


    /**
     * 封装 MybatisFlexException 异常
     *
     * @param throwable 异常
     * @param msg       消息
     * @param params    消息参数
     * @return MybatisFlexException
     */
    public static MybatisFlexException wrap(Throwable throwable, String msg, Object... params) {
        return new MybatisFlexException(String.format(msg, params), throwable);
    }

    /**
     * 封装 MybatisFlexException 异常
     *
     * @param msg    消息
     * @param params 消息参数
     * @return MybatisFlexException
     */
    public static MybatisFlexException wrap(String msg, Object... params) {
        return new MybatisFlexException(String.format(msg, params));
    }

    public static MybatisFlexException wrap(Throwable cause, Localizable pattern, Object... args) {
        return new MybatisFlexException(cause, pattern, args);
    }

    public static MybatisFlexException wrap(Localizable pattern, Object... args) {
        return new MybatisFlexException(pattern, args);
    }

}
