package com.ssitao.code.modular.iam.testdata;

import com.ssitao.code.modular.iam.testdata.dto.TestDataGenerateResultDTO;
import com.ssitao.code.modular.iam.enums.IamDataTypeEnum;

/**
 * 测试数据生成服务接口
 *
 * @author ssitao-code
 * @since 2.0.0
 */
public interface TestDataGeneratorService {

    /**
     * 生成 IAM 模块测试数据
     *
     * @param type 数据类型
     * @param count 数量
     * @return 生成结果
     */
    TestDataGenerateResultDTO generateIamData(IamDataTypeEnum type, int count);

    /**
     * 生成 IAM 模块测试数据（指定租户）
     *
     * @param type 数据类型
     * @param count 数量
     * @param tenantId 租户ID
     * @return 生成结果
     */
    TestDataGenerateResultDTO generateIamData(IamDataTypeEnum type, int count, String tenantId);

    /**
     * 生成全部 IAM 模块测试数据
     *
     * @param count 每个实体生成的数量
     * @return 生成结果
     */
    TestDataGenerateResultDTO generateAllIamData(int count);

    /**
     * 生成全部 IAM 模块测试数据（指定租户）
     *
     * @param count 每个实体生成的数量
     * @param tenantId 租户ID
     * @return 生成结果
     */
    TestDataGenerateResultDTO generateAllIamData(int count, String tenantId);

    /**
     * 清空测试数据
     *
     * @param type 数据类型
     * @return 清空数量
     */
    int clearTestData(IamDataTypeEnum type);

    /**
     * 清空测试数据（指定租户）
     *
     * @param type 数据类型
     * @param tenantId 租户ID
     * @return 清空数量
     */
    int clearTestData(IamDataTypeEnum type, String tenantId);
}
