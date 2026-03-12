package com.ssitao.code.frame.mybatisflex.spring.batch;

import com.ssitao.code.frame.mybatisflex.core.BaseMapper;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import org.apache.ibatis.cursor.Cursor;
import org.springframework.batch.item.support.AbstractItemCountingItemStreamItemReader;
import org.springframework.beans.factory.InitializingBean;

import java.util.*;

import static org.springframework.util.Assert.notNull;
import static org.springframework.util.ClassUtils.getShortName;

/**
 * MyBatis-Flex 游标模式数据读取器
 * <p>
 * 实现 Spring Batch 的 ItemReader 接口，使用 MyBatis 游标方式读取数据。
 * 适用于读取大量数据的场景，不会一次性将所有数据加载到内存中。
 *
 * @author ssitao
 * @since 1.0.0
 * @param <T> 实体类型
 */
public class MyBatisFlexCursorItemReader<T> extends AbstractItemCountingItemStreamItemReader<T>
    implements InitializingBean {

  /**
   * BaseMapper 实例
   */
  private BaseMapper<T> mapper;

  /**
   * 查询条件包装器
   */
  private QueryWrapper queryWrapper;

  /**
   * 数据库游标
   */
  private Cursor<T> cursor;

  /**
   * 游标迭代器
   */
  private Iterator<T> cursorIterator;

  /**
   * 构造函数
   */
  public MyBatisFlexCursorItemReader() {
    setName(getShortName(MyBatisFlexCursorItemReader.class));
  }

  /**
   * 设置 BaseMapper
   *
   * @param mapper BaseMapper实例
   */
  public void setMapper(BaseMapper<T> mapper) {
    this.mapper = mapper;
  }

  /**
   * 设置查询条件
   *
   * @param queryWrapper 查询条件包装器
   */
  public void setQueryWrapper(QueryWrapper queryWrapper) {
    this.queryWrapper = queryWrapper;
  }

  /**
   * 读取下一条数据
   *
   * @return 下一条数据，如果没有更多数据则返回null
   * @throws Exception 读取过程中的异常
   */
  @Override
  protected T doRead() throws Exception {
    T next = null;
    if (cursorIterator.hasNext()) {
      next = cursorIterator.next();
    }
    return next;
  }

  /**
   * 打开游标
   * <p>
   * 执行查询并获取数据库游标
   *
   * @throws Exception 当mapper或queryWrapper未设置时抛出异常
   */
  @Override
  protected void doOpen() {
    if (Objects.isNull(this.mapper) || Objects.isNull(this.queryWrapper)) {
      throw new IllegalArgumentException("mapper or queryWrapper is required.");
    }
    this.cursor = this.mapper.selectCursorByQuery(queryWrapper);
    cursorIterator = cursor.iterator();
  }

  /**
   * 关闭游标
   * <p>
   * 释放数据库资源
   *
   * @throws Exception 关闭过程中的异常
   */
  @Override
  protected void doClose() throws Exception {
    if (cursor != null) {
      cursor.close();
    }
    cursorIterator = null;
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
    notNull(mapper, "A BaseMapper is required.");
    notNull(queryWrapper, "A queryWrapper is required.");
  }

}
