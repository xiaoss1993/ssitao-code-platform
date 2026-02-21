
package com.ssitao.code.frame.mybatisflex.spring;

import com.ssitao.code.frame.mybatisflex.core.transaction.TransactionContext;
import com.ssitao.code.frame.mybatisflex.core.transaction.TransactionalManager;
import com.ssitao.code.frame.mybatisflex.core.util.StringUtil;
import org.springframework.jdbc.datasource.JdbcTransactionObjectSupport;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.transaction.support.DefaultTransactionStatus;

/**
 * MyBatis-Flex 事务管理器
 * <p>
 * 扩展 Spring 的 AbstractPlatformTransactionManager，实现 MyBatis-Flex 的事务管理。
 * 支持事务的挂起、恢复、提交、回滚等操作。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class FlexTransactionManager extends AbstractPlatformTransactionManager {

    /**
     * 获取事务对象
     *
     * @param transaction 事务对象
     * @return 事务对象
     * @throws TransactionException 事务异常
     */
    @Override
    protected Object doGetTransaction() throws TransactionException {
        return new TransactionObject(TransactionContext.getXID());
    }

    /**
     * 判断是否已存在事务
     *
     * @param transaction 事务对象
     * @return 如果已存在事务返回true
     * @throws TransactionException 事务异常
     */
    @Override
    protected boolean isExistingTransaction(Object transaction) throws TransactionException {
        TransactionObject transactionObject = (TransactionObject) transaction;
        return StringUtil.hasText(transactionObject.prevXid);
    }

    /**
     * 挂起事务
     *
     * @param transaction 事务对象
     * @return 挂起的事务资源
     * @throws TransactionException 事务异常
     */
    @Override
    protected Object doSuspend(Object transaction) throws TransactionException {
        TransactionContext.release();
        TransactionObject transactionObject = (TransactionObject) transaction;
        return transactionObject.prevXid;
    }

    /**
     * 恢复事务
     *
     * @param transaction 事务对象
     * @param suspendedResources 挂起的事务资源
     * @throws TransactionException 事务异常
     */
    @Override
    protected void doResume(Object transaction, Object suspendedResources) throws TransactionException {
        String xid = (String) suspendedResources;
        TransactionContext.holdXID(xid);
    }

    /**
     * 开始事务
     *
     * @param transaction 事务对象
     * @param definition 事务定义
     * @throws TransactionException 事务异常
     */
    @Override
    protected void doBegin(Object transaction, TransactionDefinition definition) throws TransactionException {
        TransactionObject transactionObject = (TransactionObject) transaction;
        transactionObject.currentXid = TransactionalManager.startTransactional();

        TimeoutHolder.hold(definition);
    }

    /**
     * 提交事务
     *
     * @param status 事务状态
     * @throws TransactionException 事务异常
     */
    @Override
    protected void doCommit(DefaultTransactionStatus status) throws TransactionException {
        TransactionObject transactionObject = (TransactionObject) status.getTransaction();
        TransactionalManager.commit(transactionObject.currentXid);
        transactionObject.clear();
    }

    /**
     * 回滚事务
     *
     * @param status 事务状态
     * @throws TransactionException 事务异常
     */
    @Override
    protected void doRollback(DefaultTransactionStatus status) throws TransactionException {
        TransactionObject transactionObject = (TransactionObject) status.getTransaction();
        TransactionalManager.rollback(transactionObject.currentXid);
        transactionObject.clear();
    }

    /**
     * 设置事务仅回滚标记
     * <p>
     * 在多个事务嵌套时，子事务的传递方式为 REQUIRED（加入当前事务）。
     * 当子事务抛出异常时，会调用当前方法，而不是直接调用 doRollback。
     * 此时，需要标识 prevXid 进行回滚。
     *
     * @param status 事务状态
     * @throws TransactionException 事务异常
     */
    @Override
    protected void doSetRollbackOnly(DefaultTransactionStatus status) throws TransactionException {
        TransactionObject transactionObject = (TransactionObject) status.getTransaction();
        transactionObject.setRollbackOnly();
    }

    /**
     * 事务完成后的清理工作
     *
     * @param transaction 事务对象
     */
    @Override
    protected void doCleanupAfterCompletion(Object transaction) {
        TimeoutHolder.clear();
    }

    /**
     * 事务对象
     * <p>
     * 用于保存事务的上下文信息
     */
    public static class TransactionObject extends JdbcTransactionObjectSupport {

        /**
         * 仅回滚的事务ID集合
         */
        private static final ThreadLocal<String> ROLLBACK_ONLY_XIDS = new ThreadLocal<>();

        /**
         * 前一个事务ID
         */
        private final String prevXid;

        /**
         * 当前事务ID
         */
        private String currentXid;

        /**
         * 构造函数
         *
         * @param prevXid 前一个事务ID
         */
        public TransactionObject(String prevXid) {
            this.prevXid = prevXid;
        }

        /**
         * 设置仅回滚标记
         */
        public void setRollbackOnly() {
            ROLLBACK_ONLY_XIDS.set(prevXid);
        }

        /**
         * 清理事务状态
         */
        public void clear() {
            ROLLBACK_ONLY_XIDS.remove();
        }

        /**
         * 判断是否仅回滚
         *
         * @return 如果是仅回滚事务返回true
         */
        @Override
        public boolean isRollbackOnly() {
            return currentXid != null && currentXid.equals(ROLLBACK_ONLY_XIDS.get());
        }
    }

}
