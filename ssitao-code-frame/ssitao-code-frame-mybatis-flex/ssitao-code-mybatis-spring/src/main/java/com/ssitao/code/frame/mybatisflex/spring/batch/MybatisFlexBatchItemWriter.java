package com.ssitao.code.frame.mybatisflex.spring.batch;

import com.ssitao.code.frame.mybatisflex.core.BaseMapper;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Objects;

import static org.springframework.util.Assert.notNull;

/**
 * MyBatis-Flex 批量数据写入工具
 * <p>
 * 实现 Spring Batch 的 ItemWriter 接口，用于批量写入数据到数据库。
 * 使用 MyBatis-Flex 的 BaseMapper 进行批量插入操作。
 *
 * @author ssitao
 * @since 1.0.0
 * @param <T> 实体类型
 */
public class MybatisFlexBatchItemWriter<T> implements ItemWriter<T>, InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(MybatisFlexBatchItemWriter.class);

    /**
     * BaseMapper 实例
     */
    private BaseMapper<T> mapper;

    /**
     * 是否验证更新数量
     */
    private boolean assertUpdates = true;

    /**
     * 设置是否验证更新数量
     * <p>
     * 当设置为true时，如果批量操作影响的行数与传入的数据量不一致，
     * 将会抛出 EmptyResultDataAccessException 异常
     *
     * @param assertUpdates 是否验证更新数量，默认为true
     */
    public void setAssertUpdates(boolean assertUpdates) {
        this.assertUpdates = assertUpdates;
    }

    /**
     * 设置 BaseMapper
     *
     * @param mapper BaseMapper实例
     * @throws RuntimeException 当mapper为null时抛出异常
     */
    public void setMapper(BaseMapper<T> mapper) {
        if (Objects.isNull(mapper))
            throw new RuntimeException("MybatisFlex Mapper can't be null!");

        this.mapper = mapper;
    }

    /**
     * 初始化回调
     * <p>
     * 验证必要属性是否已设置
     *
     * @throws Exception 当必要属性未设置时抛出异常
     */
    @Override
    public void afterPropertiesSet() {
        notNull(mapper, "A Mapper is required.");
    }

    /**
     * 批量写入数据
     *
     * @param items 要写入的数据列表
     * @throws Exception 写入过程中的异常
     */
    @Override
    public void write(final List<? extends T> items) {

        if (!items.isEmpty()) {
            LOGGER.debug(() -> "Executing batch with " + items.size() + " items.");
            int results = this.mapper.insertBatch((List<T>) items);

            if (assertUpdates) {
                if (results != items.size()) {
                    throw new EmptyResultDataAccessException(
                            "Items.size + " + items.size() + " doesn't match the number of updated rows: " + results, 1);

                }
            }
        }
    }
}
