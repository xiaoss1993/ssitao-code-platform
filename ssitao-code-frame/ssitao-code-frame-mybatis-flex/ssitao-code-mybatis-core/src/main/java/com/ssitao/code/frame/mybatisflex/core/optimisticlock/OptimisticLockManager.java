
package com.ssitao.code.frame.mybatisflex.core.optimisticlock;

import java.util.function.Supplier;

/**
 * 乐观锁管理器。
 */
public class OptimisticLockManager {

    private OptimisticLockManager() {
    }

    private static final ThreadLocal<Boolean> skipFlags = new ThreadLocal<>();

    /**
     * 跳过乐观锁字段处理，直接进行数据库物理操作。
     */
    public static <T> T execWithoutOptimisticLock(Supplier<T> supplier) {
        try {
            skipOptimisticLock();
            return supplier.get();
        } finally {
            restoreOptimisticLock();
        }
    }

    /**
     * 跳过乐观锁字段处理，直接进行数据库物理操作。
     */
    public static void execWithoutOptimisticLock(Runnable runnable) {
        try {
            skipOptimisticLock();
            runnable.run();
        } finally {
            restoreOptimisticLock();
        }
    }

    /**
     * 跳过乐观锁字段处理。
     */
    public static void skipOptimisticLock() {
        skipFlags.set(Boolean.TRUE);
    }

    /**
     * 恢复乐观锁字段处理。
     */
    public static void restoreOptimisticLock() {
        skipFlags.remove();
    }

    /**
     * 获取乐观锁列，返回 {@code null} 表示跳过乐观锁。
     *
     * @param optimisticLockColumn 乐观锁列
     * @return 乐观锁列
     */
    public static String getOptimisticLockColumn(String optimisticLockColumn) {
        if (optimisticLockColumn == null) {
            return null;
        }
        Boolean skipFlag = skipFlags.get();
        if (skipFlag == null) {
            return optimisticLockColumn;
        }
        return skipFlag ? null : optimisticLockColumn;
    }

}
