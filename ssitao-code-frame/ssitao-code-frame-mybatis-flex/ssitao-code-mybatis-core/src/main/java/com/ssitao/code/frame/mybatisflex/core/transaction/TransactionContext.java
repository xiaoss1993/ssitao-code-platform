
package com.ssitao.code.frame.mybatisflex.core.transaction;


import org.apache.ibatis.cursor.Cursor;

import java.io.IOException;

/**
 * 事务上下文管理器
 * <p>
 * 用于管理当前线程的事务ID和游标对象。
 * 通过 ThreadLocal 存储事务上下文信息，支持事务的嵌套和传播。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class TransactionContext {

    /**
     * 事务ID持有器
     */
    private static final ThreadLocal<String> XID_HOLDER = new ThreadLocal<>();

    /**
     * 游标持有器
     */
    private static final ThreadLocal<Cursor<?>> CURSOR_HOLDER = new ThreadLocal<>();

    /**
     * 私有构造函数，防止实例化
     */
    private TransactionContext() {
    }

    /**
     * 获取当前事务ID
     *
     * @return 当前事务ID，如果不在事务中则返回null
     */
    public static String getXID() {
        return XID_HOLDER.get();
    }

    /**
     * 释放事务上下文
     * <p>
     * 清除事务ID并关闭游标
     */
    public static void release() {
        XID_HOLDER.remove();
        closeCursor();
    }

    /**
     * 关闭游标
     * <p>
     * 安全关闭当前线程关联的游标对象
     */
    private static void closeCursor() {
        Cursor<?> cursor = CURSOR_HOLDER.get();
        if (cursor != null) {
            try {
                cursor.close();
            } catch (IOException e) {
                // 忽略关闭异常
            } finally {
                CURSOR_HOLDER.remove();
            }
        }
    }

    /**
     * 设置事务ID
     *
     * @param xid 事务ID
     */
    public static void holdXID(String xid) {
        XID_HOLDER.set(xid);
    }

    /**
     * 设置游标对象
     *
     * @param cursor 游标对象
     */
    public static void holdCursor(Cursor<?> cursor) {
        CURSOR_HOLDER.set(cursor);
    }

}
