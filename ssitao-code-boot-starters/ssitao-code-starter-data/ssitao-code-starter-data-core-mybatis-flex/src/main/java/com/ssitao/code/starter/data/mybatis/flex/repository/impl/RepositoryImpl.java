package com.ssitao.code.starter.data.mybatis.flex.repository.impl;

import com.ssitao.code.common.utils.ClassUtils;
import com.ssitao.code.frame.mybatisflex.core.service.IService;
import com.ssitao.code.frame.mybatisflex.spring.service.impl.ServiceImpl;
import com.ssitao.code.starter.data.mybatis.flex.base.BaseCrudMapper;

public class RepositoryImpl<M extends BaseCrudMapper<T> ,T>
    extends ServiceImpl<M,T> implements IService<T> {
    protected final Class<?>[] typeArguments = ClassUtils.getTypeArguments(this.getClass());
    protected final Class<T> entityClass = currentModelClass();

    protected Class<T> currentModelClass() {
        return (Class<T>)this.typeArguments[1];
    }
}
