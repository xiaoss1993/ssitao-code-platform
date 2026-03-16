package com.ssitao.code.modular.dynamic.form.service;

import com.ssitao.code.commons.entity.PagerResult;
import com.ssitao.code.commons.entity.param.DeleteParamEntity;
import com.ssitao.code.commons.entity.param.QueryParamEntity;
import com.ssitao.code.commons.entity.param.UpdateParamEntity;

import java.util.List;

/**
 * 动态表单操作接口,用于对动态表单进行增删改查操作
 *
 *
 * @since 3.0
 */
public interface DynamicFormOperationService {
    <T> PagerResult<T> selectPager(String formId, QueryParamEntity paramEntity);

    <T> T selectSingle(String formId, QueryParamEntity paramEntity);

    <T> List<T> select(String formId, QueryParamEntity paramEntity);

    int count(String formId, QueryParamEntity paramEntity);

    <T> int update(String formId, UpdateParamEntity<T> paramEntity);

    <T> T updateById(String formId, Object id, T data);

    <T> T insert(String formId, T entity);

    int delete(String formId, DeleteParamEntity paramEntity);

    int deleteById(String formId, Object id);

    <T> T saveOrUpdate(String formId, T data);

    <T> T selectById(String formId, Object id);

}
