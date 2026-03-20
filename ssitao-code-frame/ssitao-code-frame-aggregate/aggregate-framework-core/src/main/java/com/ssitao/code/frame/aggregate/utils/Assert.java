package com.ssitao.code.frame.aggregate.utils;

import com.ssitao.code.frame.aggregate.exception.SystemException;

/**
 * @author changming.xie
 */
public class Assert {
    public static void notNull(Object object, String message) {

        if (object == null) {
            throw new SystemException(message);
        }
    }
}
