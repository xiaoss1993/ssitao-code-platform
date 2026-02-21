
package com.ssitao.code.frame.mybatisflex.spring.service.impl;

import com.ssitao.code.frame.mybatisflex.core.BaseMapper;
import com.ssitao.code.frame.mybatisflex.core.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Service 基础实现类
 * <p>
 * 由 MyBatis-Flex 提供的顶级增强 Service 接口的默认实现类。
 * 提供了基础的 Mapper 注入，子类可以直接使用 mapper 进行数据库操作。
 *
 * @author ssitao
 * @since 1.0.0
 * @param <T> 实体类（Entity）类型
 * @param <M> 映射类（Mapper）类型
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class ServiceImpl<M extends BaseMapper<T>, T> implements IService<T> {

    /**
     * BaseMapper 实例，由 Spring 自动注入
     */
    @Autowired
    protected M mapper;

    /**
     * 获取 BaseMapper 实例
     *
     * @return BaseMapper 实例
     */
    @Override
    public M getMapper() {
        return mapper;
    }

}
