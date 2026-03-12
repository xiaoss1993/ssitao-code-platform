
package com.ssitao.code.frame.mybatisflex.spring;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionTimedOutException;

import java.util.Date;

/**
 * 事务超时持有者
 * <p>
 * 用于更完整地实现 Spring 事务超时控制。
 * 仅支持传统 JDBC 事务，不支持 R2DBC 事务。
 *
 * @author ssitao
 * @since 1.0.0
 */
public final class TimeoutHolder {
    /**
     * 事务截止时间的 ThreadLocal 存储
     */
    private static final ThreadLocal<Long> TRANSACTION_DEADLINE = new ThreadLocal<>();

    /**
     * 保存事务定义信息
     *
     * @param definition 事务定义
     */
    public static void hold(TransactionDefinition definition) {
        if (definition == null) {
            return;
        }
        int timeout = definition.getTimeout();
        if (timeout != TransactionDefinition.TIMEOUT_DEFAULT) {
            Long deadline = System.currentTimeMillis() + timeout * 1000L;
            TRANSACTION_DEADLINE.set(deadline);
        }
    }

    /**
     * 清除事务上下文
     */
    public static void clear() {
        TRANSACTION_DEADLINE.remove();
    }

    /**
     * 获取当前事务可用 TTL（秒）
     *
     * @return 剩余时间（秒），如果未设置超时则返回null
     * @throws TransactionTimedOutException 事务超时异常
     */
    public static Integer getTimeToLiveInSeconds() {
        Long deadline = TRANSACTION_DEADLINE.get();
        if (deadline == null) {
            return null;
        }
        double diff = ((double) getTimeToLiveInMillis(deadline)) / 1000;
        int secs = (int) Math.ceil(diff);
        checkTransactionTimeout(secs <= 0, deadline);
        return secs;
    }

    /**
     * 检查事务是否超时
     *
     * @param deadlineReached 是否已到达截止时间
     * @param deadline 截止时间
     * @throws TransactionTimedOutException 事务超时异常
     */
    private static void checkTransactionTimeout(boolean deadlineReached, Long deadline) throws TransactionTimedOutException {
        if (deadlineReached) {
            throw new TransactionTimedOutException("Transaction timed out: deadline was " + new Date(deadline));
        }
    }

    /**
     * 获取剩余时间（毫秒）
     *
     * @param deadline 截止时间
     * @return 剩余毫秒数
     * @throws TransactionTimedOutException 事务超时异常
     */
    private static long getTimeToLiveInMillis(Long deadline) throws TransactionTimedOutException {
        if (deadline == null) {
            throw new IllegalStateException("No timeout specified for this resource holder");
        }
        long timeToLive = deadline - System.currentTimeMillis();
        checkTransactionTimeout(timeToLive <= 0, deadline);
        return timeToLive;
    }

}
