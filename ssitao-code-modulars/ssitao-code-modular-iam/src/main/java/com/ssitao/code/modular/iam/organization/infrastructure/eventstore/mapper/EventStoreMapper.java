package com.ssitao.code.modular.iam.organization.infrastructure.eventstore.mapper;

import com.ssitao.code.frame.mybatisflex.core.BaseMapper;
import com.ssitao.code.modular.iam.organization.infrastructure.eventstore.dataobject.EventStoreDO;

/**
 * 事件存储Mapper
 * <p>
 * 使用 MyBatis-Flex 提供的基础 CRUD 功能
 * 自定义查询使用 QueryWrapper 构建
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface EventStoreMapper extends BaseMapper<EventStoreDO> {
    // MyBatis-Flex 会自动提供基础 CRUD 方法
    // insertOne, insertBatch, update, delete, selectOneById, selectListByQuery 等
}
