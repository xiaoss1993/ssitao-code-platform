package com.ssitao.code.frame.mybatisflex.spring.batch.builder;

import com.ssitao.code.frame.mybatisflex.core.BaseMapper;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.frame.mybatisflex.spring.batch.MybatisFlexPagingItemReader;

import java.util.Optional;

/**
 * MyBatis-Flex 分页读取器构建器
 * <p>
 * 用于构建 MybatisFlexPagingItemReader 实例的建造者类，
 * 支持链式调用设置分页读取器的各项参数。
 *
 * @author ssitao
 * @since 1.0.0
 * @param <T> 实体类型
 */
public class MyBatisFlexPagingItemReaderBuilder<T> {

  /**
   * BaseMapper 实例
   */
  private BaseMapper<T> mapper;

  /**
   * 查询条件对象
   */
  private QueryWrapper queryWrapper;

  /**
   * 分页大小
   */
  private Integer pageSize;

  /**
   * 保存状态标志位
   */
  private Boolean saveState;

  /**
   * 数据最大读取数量
   */
  private Integer maxItemCount;

  /**
   * 设置 BaseMapper
   *
   * @param mapper BaseMapper实例
   * @return this
   */
  public MyBatisFlexPagingItemReaderBuilder<T> mapper(BaseMapper<T> mapper) {
    this.mapper = mapper;
    return this;
  }

  /**
   * 设置查询条件
   *
   * @param queryWrapper 查询条件包装器
   * @return this
   */
  public MyBatisFlexPagingItemReaderBuilder<T> queryWrapper(QueryWrapper queryWrapper) {
    this.queryWrapper = queryWrapper;
    return this;
  }

  /**
   * 设置分页大小
   *
   * @param pageSize 每页记录数
   * @return this
   */
  public MyBatisFlexPagingItemReaderBuilder<T> pageSize(int pageSize) {
    this.pageSize = pageSize;
    return this;
  }

  /**
   * 设置是否保存状态
   *
   * @param saveState 是否保存状态标志位
   * @return this
   */
  public MyBatisFlexPagingItemReaderBuilder<T> saveState(boolean saveState) {
    this.saveState = saveState;
    return this;
  }

  /**
   * 设置最大读取数量
   * <p>
   * 默认值为 Integer.MAX_VALUE
   *
   * @param maxItemCount 最大读取数量
   * @return this
   */
  public MyBatisFlexPagingItemReaderBuilder<T> maxItemCount(int maxItemCount) {
    this.maxItemCount = maxItemCount;
    return this;
  }

  /**
   * 构建分页读取器实例
   *
   * @return MybatisFlexPagingItemReader 实例
   */
  public MybatisFlexPagingItemReader<T> build() {
    MybatisFlexPagingItemReader<T> reader = new MybatisFlexPagingItemReader<>();
    reader.setMapper(this.mapper);
    reader.setQueryWrapper(this.queryWrapper);
    Optional.ofNullable(this.pageSize).ifPresent(reader::setPageSize);
    Optional.ofNullable(this.saveState).ifPresent(reader::setSaveState);
    Optional.ofNullable(this.maxItemCount).ifPresent(reader::setMaxItemCount);
    return reader;
  }

}
