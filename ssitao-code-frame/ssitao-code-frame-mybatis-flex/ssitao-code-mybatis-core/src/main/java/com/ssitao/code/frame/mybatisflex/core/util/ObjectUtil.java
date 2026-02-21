
package com.ssitao.code.frame.mybatisflex.core.util;

import com.ssitao.code.frame.mybatisflex.core.query.CloneSupport;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

public class ObjectUtil {

    private ObjectUtil() {
    }

    public static @Nullable Object cloneObject(@Nullable Object value) {
        // ROLE.ROLE_ID.ge(USER.USER_ID)
        if (value instanceof CloneSupport) {
            return ((CloneSupport<?>) value).clone();
        }
        return value;
    }

    public static <T extends CloneSupport<T>> @Nullable T clone(@Nullable T value) {
        if (value != null) {
            return value.clone();
        }
        return null;
    }

    public static <T> @Nullable T requireNonNullElse(@Nullable T t1, @Nullable T t2) {
        return t1 == null ? t2 : t1;
    }

    public static boolean areNotNull(Object... objs) {
        for (Object obj : objs) {
            if (obj == null) {
                return false;
            }
        }
        return true;
    }

    public static boolean areNull(Object... objs) {
        for (Object obj : objs) {
            if (obj != null) {
                return false;
            }
        }
        return true;
    }

    public static boolean equalsAny(Object a, @NonNull Object... others) {
        if (others == null || others.length == 0) {
            throw new IllegalArgumentException("others must not be null or empty.");
        }
        for (Object other : others) {
            if (Objects.equals(a, other)) {
                return true;
            }
        }
        return false;
    }

}
