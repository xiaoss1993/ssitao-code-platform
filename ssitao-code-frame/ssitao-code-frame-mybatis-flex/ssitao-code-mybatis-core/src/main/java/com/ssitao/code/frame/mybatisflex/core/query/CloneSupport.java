

package com.ssitao.code.frame.mybatisflex.core.query;

import java.io.Serializable;

/**
 * <p>克隆支持接口。
 *
 * <p>支持序列化克隆与 {@link Object#clone()} 方法。
 *
 * @param <T> 克隆对象类型
 * @author ssitao 
 * @since 1.0.0
 */
public interface CloneSupport<T> extends Serializable, Cloneable {

    /**
     * 改写 {@link Object#clone()} 方法。
     *
     * @return 克隆对象
     */
    T clone();

}
