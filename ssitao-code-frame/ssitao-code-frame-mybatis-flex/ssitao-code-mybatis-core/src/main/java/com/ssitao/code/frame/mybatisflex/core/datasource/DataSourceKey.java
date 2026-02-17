/*
 *  Copyright (c) 2022-2024, Mybatis-Flex (fuhai999@gmail.com).
 *  <p>
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 *  http://www.apache.org/licenses/LICENSE-2.0
 *  <p>
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.ssitao.code.frame.mybatisflex.core.datasource;

import com.ssitao.code.frame.mybatisflex.core.exception.FlexAssert;

import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.Supplier;

/**
 * 数据源Key管理器
 * <p>
 * 用于在多数据源环境中管理当前线程使用的数据源标识。
 * 通过 ThreadLocal 存储数据源key的栈结构，支持数据源的嵌套切换。
 *
 * @author ssitao
 * @since 1.0.0
 */
public class DataSourceKey {

    /**
     * 数据源key栈，用于支持嵌套切换
     */
    private static ThreadLocal<Deque<String>> lookup = ThreadLocal.withInitial(ArrayDeque::new);

    /**
     * 私有构造函数，防止实例化
     */
    private DataSourceKey() {
    }

    /**
     * 使用指定的数据源
     * <p>
     * 将数据源key压入栈顶，成为当前生效的数据源
     *
     * @param dataSourceKey 数据源key
     */
    public static void use(String dataSourceKey) {
        Deque<String> deque = lookup.get();
        if (deque == null) {
            deque = new ArrayDeque<>(1);
            lookup.set(deque);
        }
        deque.push(dataSourceKey);
    }

    /**
     * 获取当前使用的数据源key
     *
     * @return 当前数据源key，如果未设置则返回null
     */
    public static String get() {
        Deque<String> deque = lookup.get();
        return deque != null ? deque.peek() : null;
    }

    /**
     * 清除当前数据源key
     * <p>
     * 弹出栈顶元素，恢复到上一个数据源
     */
    public static void clear() {
        Deque<String> deque = lookup.get();
        if (deque != null) {
            deque.pop();
            if (deque.isEmpty()) {
                lookup.remove();
            }
        }
    }

    /**
     * 强制清除所有数据源key
     * <p>
     * 清空栈并移除 ThreadLocal
     */
    public static void forceClear() {
        lookup.remove();
    }

    /**
     * 在指定数据源上下文中执行任务
     *
     * @param dataSourceKey 数据源key
     * @param runnable 要执行的任务
     */
    public static void use(String dataSourceKey, Runnable runnable) {
        try {
            use(dataSourceKey);
            runnable.run();
        } finally {
            clear();
        }
    }

    /**
     * 在指定数据源上下文中执行任务并返回结果
     *
     * @param <T> 返回值类型
     * @param dataSourceKey 数据源key
     * @param supplier 要执行的任务
     * @return 任务执行结果
     */
    public static <T> T use(String dataSourceKey, Supplier<T> supplier) {
        try {
            use(dataSourceKey);
            return supplier.get();
        } finally {
            clear();
        }
    }

    /**
     * 设置自定义的 ThreadLocal
     * <p>
     * 用于支持特殊场景下的线程本地存储需求
     *
     * @param threadLocal 自定义的 ThreadLocal
     */
    public static void setThreadLocal(ThreadLocal<Deque<String>> threadLocal) {
        FlexAssert.notNull(threadLocal, "threadLocal");
        if (threadLocal.get() == null) {
            threadLocal.set(lookup.get());
        }
        lookup = threadLocal;
    }

    /**
     * 处理数据源key
     * <p>
     * 通过配置的处理器链对数据源key进行处理
     *
     * @param dataSourceKey 原始数据源key
     * @param targetOrProxy 目标对象或代理对象
     * @param method 调用的方法
     * @param arguments 方法参数
     * @return 处理后的数据源key
     */
    public static String processDataSourceKey(String dataSourceKey, Object targetOrProxy, Method method, Object[] arguments) {
        String dsKey = DataSourceManager.processDataSourceKey(dataSourceKey, targetOrProxy, method, arguments);
        return dsKey != null ? dsKey : dataSourceKey;
    }


    /**
     * 获取分片数据源key
     * <p>
     * 根据分片策略计算应该使用的数据源
     *
     * @param dataSource 原始数据源
     * @param mapper Mapper对象
     * @param method 调用的方法
     * @param args 方法参数
     * @return 分片后的数据源key
     */
    public static String getShardingDsKey(String dataSource, Object mapper, Method method, Object[] args) {
        String shardingDsKey = DataSourceManager.getShardingDsKey(dataSource, mapper, method, args);
        return shardingDsKey != null ? shardingDsKey : dataSource;
    }

}
