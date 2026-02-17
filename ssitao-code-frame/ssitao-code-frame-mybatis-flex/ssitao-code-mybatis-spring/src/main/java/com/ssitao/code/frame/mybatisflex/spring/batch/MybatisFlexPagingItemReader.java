package com.ssitao.code.frame.mybatisflex.spring.batch;

import com.ssitao.code.frame.mybatisflex.core.BaseMapper;
import com.ssitao.code.frame.mybatisflex.core.paginate.Page;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import org.mybatis.spring.batch.MyBatisPagingItemReader;
import org.springframework.batch.item.database.AbstractPagingItemReader;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.springframework.util.Assert.notNull;
import static org.springframework.util.ClassUtils.getShortName;

/**
 * MyBatis-Flex 分页读取器
 * <p>
 * 实现 Spring Batch 的 AbstractPagingItemReader 接口，
 * 使用 MyBatis-Flex 的分页功能进行分页数据读取。
 *
 * @author ssitao
 * @since 1.0.0
 * @param <T> 实体类型
 */
public class MybatisFlexPagingItemReader<T> extends AbstractPagingItemReader<T>  {

    /**
     * BaseMapper 实例
     */
    private BaseMapper<T> mapper;

    /**
     * 查询条件包装器
     */
    private QueryWrapper queryWrapper;

    /**
     * 构造函数
     */
    public MybatisFlexPagingItemReader() {
        setName(getShortName(MyBatisPagingItemReader.class));
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
     * 初始化回调
     * <p>
     * 验证必要属性是否已设置
     *
     * @throws Exception 当必要属性未设置时抛出异常
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        notNull(mapper, "mapper is required.");
        notNull(queryWrapper, "querywrapper is required.");
    }

    /**
     * 读取当前页数据
     */
    @Override
    protected void doReadPage() {
        if (results == null) {
            results = new CopyOnWriteArrayList<>();
        } else {
            results.clear();
        }
        Page<T> paginate = mapper.paginate(getPage() + 1, getPageSize(), queryWrapper);
        results.addAll(paginate.getRecords());
    }

    /**
     * 跳转到指定页
     *
     * @param itemIndex 目标页索引
     */
    @Override
    protected void doJumpToPage(int itemIndex) {
        if (results == null) {
            results = new CopyOnWriteArrayList<>();
        } else {
            results.clear();
        }
        Page<T> paginate = mapper.paginate(itemIndex + 1, getPageSize(), queryWrapper);
        results.addAll(paginate.getRecords());
    }
}
