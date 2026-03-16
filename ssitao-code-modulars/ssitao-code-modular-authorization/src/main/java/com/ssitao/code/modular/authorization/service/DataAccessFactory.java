package com.ssitao.code.modular.authorization.service;

import com.ssitao.code.frame.authorization.access.DataAccessConfig;
import com.ssitao.code.frame.authorization.builder.DataAccessConfigBuilderFactory;
import com.ssitao.code.modular.authorization.entity.DataAccessEntity;

/**
 * 数据权限配置工厂,用户将动态数据权限配置转为权限框架需要的配置,便于实现自定义数据权限
 *
 *
 * @since 3.0
 * @see DataAccessConfig
 * @see DataAccessConfigBuilderFactory
 */
public interface DataAccessFactory {
    DataAccessConfig create(DataAccessEntity entity);
}
