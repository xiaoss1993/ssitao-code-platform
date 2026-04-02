package com.ssitao.code.common.utils;

import java.util.List;
import java.util.Map;

/**
 * 验证结果接口
 *
 * @since 3.0
 */
public interface ValidateResults {

    /**
     * 添加验证结果
     *
     * @param field   字段名
     * @param message 错误消息
     */
    void addResult(String field, String message);

    /**
     * 获取所有错误消息
     *
     * @return 错误消息列表
     */
    List<String> getAllMessages();

    /**
     * 获取字段错误消息
     *
     * @param field 字段名
     * @return 错误消息
     */
    List<String> getMessages(String field);

    /**
     * 获取所有错误
     *
     * @return 字段到错误消息的映射
     */
    Map<String, List<String>> getErrors();

    /**
     * 是否有错误
     *
     * @return 是否有错误
     */
    boolean hasErrors();

    /**
     * 获取错误数量
     *
     * @return 错误数量
     */
    int getErrorCount();
}
