package com.ssitao.code.frame.mybatisflex.spring.batch.builder;

import com.ssitao.code.frame.mybatisflex.spring.batch.MyBatisFlexCursorItemReader;
import com.ssitao.code.frame.mybatisflex.core.BaseMapper;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import org.mybatis.spring.batch.MyBatisCursorItemReader;

import java.util.Optional;

/**
 * MyBatis-Flex 游标读取器构建器
 * <p>
 * 用于构建 MyBatisFlexCursorItemReader 实例的建造者类，
 * 支持链式调用设置游标读取器的各项参数。
 *
 * @author ssitao
 * @since 1.0.0
 * @param <T> 实体类型
 */
public class MyBatisFlexCursorItemReaderBuilder<T> {

  /**
   * BaseMapper 实例
   */
  private BaseMapper<T> mapper;

  /**
   * 查询条件对象
   */
  private QueryWrapper queryWrapper;

  /**
   * 是否保存状态
   */
  private Boolean saveState;

  /**
   * 最大读取行数
   */
  private Integer maxItemCount;

  /**
   * 设置 BaseMapper
   *
   * @param mapper BaseMapper实例
   * @return this
   */
  public MyBatisFlexCursorItemReaderBuilder<T> mapper(BaseMapper<T> mapper) {
    this.mapper = mapper;
    return this;
  }

  /**
   * 设置查询条件
   *
   * @param queryWrapper 查询条件包装器
   * @return this
   */
  public MyBatisFlexCursorItemReaderBuilder<T> queryWrapper(QueryWrapper queryWrapper) {
    this.queryWrapper = queryWrapper;
    return this;
  }

  /**
   * 设置是否保存状态
   *
   * @param saveState 是否保存状态标志位
   * @return this
   */
  public MyBatisFlexCursorItemReaderBuilder<T> saveState(boolean saveState) {
    this.saveState = saveState;
    return this;
  }

  /**
   * 设置最大读取行数
   *
   * @param maxItemCount 最大读取行数
   * @return this
   */
  public MyBatisFlexCursorItemReaderBuilder<T> maxItemCount(int maxItemCount) {
    this.maxItemCount = maxItemCount;
    return this;
  }

  /**
   * 构建游标读取器实例
   *
   * @return MyBatisFlexCursorItemReader 实例
   */
  public MyBatisFlexCursorItemReader<T> build() {
    MyBatisFlexCursorItemReader<T> reader = new MyBatisFlexCursorItemReader<>();
    reader.setMapper(this.mapper);
    reader.setQueryWrapper(this.queryWrapper);
    Optional.ofNullable(this.saveState).ifPresent(reader::setSaveState);
    Optional.ofNullable(this.maxItemCount).ifPresent(reader::setMaxItemCount);
    return reader;
  }

}
