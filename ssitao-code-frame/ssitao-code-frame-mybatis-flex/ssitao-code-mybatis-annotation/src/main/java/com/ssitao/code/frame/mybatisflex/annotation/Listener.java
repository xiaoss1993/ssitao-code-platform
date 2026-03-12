

package com.ssitao.code.frame.mybatisflex.annotation;

/**
 * 监听器接口。
 *
 * @author ssitao
 * @since 1.0.0
 */
public interface Listener extends Comparable<Listener> {

    /**
     * <p>多个监听器时的执行顺序。
     *
     * <p>值越小越早触发执行。
     *
     * @return order
     */
    default int order() {
        return Integer.MAX_VALUE;
    }

    @Override
    default int compareTo(Listener other) {
        return order() - other.order();
    }

}
