package com.ssitao.code.modular.iam.testdata.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * 测试数据生成结果
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestDataGenerateResultDTO {

    /**
     * 总生成数量
     */
    private int totalCount;

    /**
     * 各类型数据生成数量
     */
    private Map<String, Integer> typeCountMap;

    /**
     * 租户ID
     */
    private String tenantId;

    /**
     * 耗时（毫秒）
     */
    private long elapsedTime;

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 错误信息
     */
    private String errorMessage;
}
