
package com.ssitao.code.frame.mybatisflex.core.audit;

/**
 * 对于性要求特别高的场景，用户可以定义自己的时钟，用来代替 {@link System#currentTimeMillis()}
 */
public interface Clock {

    long getTick();

}
