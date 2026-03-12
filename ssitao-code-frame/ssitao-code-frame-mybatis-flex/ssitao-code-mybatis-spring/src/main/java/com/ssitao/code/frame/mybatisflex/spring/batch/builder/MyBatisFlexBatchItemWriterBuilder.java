package com.ssitao.code.frame.mybatisflex.spring.batch.builder;

import com.ssitao.code.frame.mybatisflex.spring.batch.MybatisFlexBatchItemWriter;
import com.ssitao.code.frame.mybatisflex.core.BaseMapper;

import java.util.Optional;

/**
 * MyBatis-Flex 批量写入器构建器
 * <p>
 * 用于构建 MybatisFlexBatchItemWriter 实例的建造者类，
 * 支持链式调用设置批量写入器的各项参数。
 *
 * @author ssitao
 * @since 1.0.0
 * @param <T> 实体类型
 * @see MybatisFlexBatchItemWriter
 */
public class MyBatisFlexBatchItemWriterBuilder<T> {

  /**
   * BaseMapper 实例
   */
  private BaseMapper<T> mapper;

  /**
   * 是否验证更新数量
   */
  private Boolean assertUpdates;

  /**
   * 设置 BaseMapper
   *
   * @param mapper BaseMapper实例
   * @return this
   */
  public MyBatisFlexBatchItemWriterBuilder<T> mapper(BaseMapper<T> mapper) {
    this.mapper = mapper;
    return this;
  }

  /**
   * 设置是否验证更新数量
   *
   * @param assertUpdates 是否验证更新数量
   * @return this
   */
  public MyBatisFlexBatchItemWriterBuilder<T> assertUpdates(boolean assertUpdates) {
    this.assertUpdates = assertUpdates;
    return this;
  }

  /**
   * 构建批量写入器实例
   *
   * @return MybatisFlexBatchItemWriter 实例
   */
  public MybatisFlexBatchItemWriter<T> build() {
    MybatisFlexBatchItemWriter<T> writer = new MybatisFlexBatchItemWriter<>();
    writer.setMapper(this.mapper);
    Optional.ofNullable(this.assertUpdates).ifPresent(writer::setAssertUpdates);
    return writer;
  }

}
