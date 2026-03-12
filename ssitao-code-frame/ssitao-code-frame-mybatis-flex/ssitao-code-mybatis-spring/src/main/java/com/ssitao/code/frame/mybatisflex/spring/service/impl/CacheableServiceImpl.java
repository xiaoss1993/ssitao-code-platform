

package com.ssitao.code.frame.mybatisflex.spring.service.impl;

import com.ssitao.code.frame.mybatisflex.core.BaseMapper;
import com.ssitao.code.frame.mybatisflex.core.query.QueryTable;
import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;
import com.ssitao.code.frame.mybatisflex.core.service.IService;
import com.ssitao.code.frame.mybatisflex.core.table.TableInfo;
import com.ssitao.code.frame.mybatisflex.core.table.TableInfoFactory;
import com.ssitao.code.frame.mybatisflex.core.util.ClassUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 可缓存数据的 Service 实现类
 * <p>
 * 该实现类对缓存做了以下优化处理：
 * <ul>
 *     <li>重写 {@link #saveOrUpdate(Object)} 方法，分别调用 {@link #save(Object)} 和 {@link #updateById(Object)}
 *     方法，避免缓存无法更新造成数据不一致。</li>
 *     <li>重写 {@link #query()} 方法，解决使用 {@link QueryWrapper#toSQL()} 作为缓存
 *     的主键时，"SELECT * FROM" 后面没有表名的问题。</li>
 * </ul>
 *
 * @author ssitao
 * @since 1.0.0
 * @param <M> Mapper 类型
 * @param <T> 实体类型
 */
public class CacheableServiceImpl<M extends BaseMapper<T>, T> implements IService<T> {

    /**
     * BaseMapper 实例，由 Spring 自动注入
     */
    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    protected M mapper;

    /**
     * 获取 BaseMapper 实例
     *
     * @return BaseMapper 实例
     */
    @Override
    public BaseMapper<T> getMapper() {
        return mapper;
    }

    /**
     * 保存或更新实体
     * <p>
     * 根据主键值判断是执行插入还是更新操作，
     * 主键为空时执行插入，主键有值时执行更新
     *
     * @param entity 实体对象
     * @return 操作是否成功
     */
    @Override
    public boolean saveOrUpdate(T entity) {
        TableInfo tableInfo = TableInfoFactory.ofEntityClass(entity.getClass());
        Object[] pkArgs = tableInfo.buildPkSqlArgs(entity);
        if (pkArgs.length == 0 || pkArgs[0] == null) {
            return save(entity);
        } else {
            return updateById(entity);
        }
    }

    /**
     * 获取默认的查询条件包装器
     * <p>
     * 使用 {@link QueryWrapper#create()} 构建默认查询条件时，
     * 需要使用 {@link QueryWrapper#from(String...)} 方法指定从哪个表查询数据，
     * 否则生成的 SQL 语句只是 "SELECT * FROM"，缺少表名信息。
     * <p>
     * 默认通过反射获取表名，建议子类重写此方法以提升性能。
     * <p>
     * 例如：
     * <pre>{@code
     * @Override
     * public QueryWrapper query() {
     *     return QueryWrapper.create().from(ACCOUNT);
     * }
     * }</pre>
     *
     * @return 默认的 {@link QueryWrapper}
     */
    @Override
    public QueryWrapper query() {
        Class<?> mapperClass = ClassUtil.getUsefulClass(getMapper().getClass());
        TableInfo tableInfo = TableInfoFactory.ofMapperClass(mapperClass);
        return QueryWrapper.create().from(new QueryTable(tableInfo.getSchema(), tableInfo.getTableName()));
    }

}
