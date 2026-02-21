
package com.ssitao.code.frame.mybatisflex.spring;

import com.ssitao.code.frame.mybatisflex.core.datasource.FlexDataSource;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;

/**
 * MyBatis-Flex 事务工厂
 * <p>
 * 扩展 SpringManagedTransactionFactory，创建 FlexSpringTransaction 实例。
 * 用于在 Spring 环境下管理 MyBatis-Flex 的事务。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class FlexTransactionFactory extends SpringManagedTransactionFactory {

    /**
     * 创建新事务
     *
     * @param dataSource 数据源
     * @param level 事务隔离级别
     * @param autoCommit 是否自动提交
     * @return FlexSpringTransaction 实例
     */
    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        return new FlexSpringTransaction(dataSource);
    }

    /**
     * 创建新事务（基于连接）
     * <p>
     * Flex 事务要求数据源，不支持基于连接的事务创建
     *
     * @param conn 数据库连接
     * @return 事务实例
     * @throws UnsupportedOperationException 不支持此操作
     */
    @Override
    public Transaction newTransaction(Connection conn) {
        throw new UnsupportedOperationException("New Flex transactions require a DataSource");
    }
}
