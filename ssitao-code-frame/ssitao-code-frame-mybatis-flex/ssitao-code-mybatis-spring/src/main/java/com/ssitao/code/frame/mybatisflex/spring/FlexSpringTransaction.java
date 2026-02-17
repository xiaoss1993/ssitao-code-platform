
package com.ssitao.code.frame.mybatisflex.spring;

import com.ssitao.code.frame.mybatisflex.core.datasource.FlexDataSource;
import com.ssitao.code.frame.mybatisflex.core.transaction.TransactionContext;
import com.ssitao.code.frame.mybatisflex.core.util.StringUtil;
import org.apache.ibatis.transaction.Transaction;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Spring 事务支持
 * <p>
 * 实现 MyBatis Transaction 接口，用于在 Spring 环境下管理 MyBatis-Flex 的事务。
 * 解决在分布式事务和多数据源场景下的事务管理问题。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class FlexSpringTransaction implements Transaction {

    /**
     * 数据源
     */
    private final DataSource dataSource;

    /**
     * 连接是否在事务中
     */
    private Boolean isConnectionTransactional;

    /**
     * 自动提交状态
     */
    private Boolean autoCommit;

    /**
     * 数据库连接
     */
    private Connection connection;

    /**
     * 构造函数
     *
     * @param dataSource 数据源
     */
    public FlexSpringTransaction(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取数据库连接
     * <p>
     * 首次调用时获取连接并判断是否在事务环境中，
     * 后续调用根据事务状态返回相应的连接
     *
     * @return 数据库连接
     * @throws SQLException 数据库异常
     */
    @Override
    public Connection getConnection() throws SQLException {
        if (isConnectionTransactional == null) {
            connection = dataSource.getConnection();
            isConnectionTransactional = StringUtil.hasText(TransactionContext.getXID());
            autoCommit = connection.getAutoCommit();
            return connection;
        }
        // 在事务中，通过 FlexDataSource 去获取
        // FlexDataSource 内部会进行 connection 缓存以及多数据源下的 key 判断
        else if (isConnectionTransactional) {
            return dataSource.getConnection();
        }
        // 非事务，返回当前链接
        else {
            return connection;
        }
    }

    /**
     * 提交事务
     * <p>
     * 仅在非Spring管理的事务中执行提交
     *
     * @throws SQLException 数据库异常
     */
    @Override
    public void commit() throws SQLException {
        if (this.connection != null && !this.isConnectionTransactional && !this.autoCommit) {
            this.connection.commit();
        }
    }

    /**
     * 回滚事务
     * <p>
     * 仅在非Spring管理的事务中执行回滚
     *
     * @throws SQLException 数据库异常
     */
    @Override
    public void rollback() throws SQLException {
        if (this.connection != null && !this.isConnectionTransactional && !this.autoCommit) {
            this.connection.rollback();
        }
    }

    /**
     * 关闭连接
     * <p>
     * 仅在非Spring管理的事务中关闭连接
     *
     * @throws SQLException 数据库异常
     */
    @Override
    public void close() throws SQLException {
        if (this.connection != null && !this.isConnectionTransactional) {
            connection.close();
        }
    }

    /**
     * 获取事务超时时间
     *
     * @return 超时时间（秒）
     * @throws SQLException 数据库异常
     */
    @Override
    public Integer getTimeout() throws SQLException {
        return TimeoutHolder.getTimeToLiveInSeconds();
    }
}
