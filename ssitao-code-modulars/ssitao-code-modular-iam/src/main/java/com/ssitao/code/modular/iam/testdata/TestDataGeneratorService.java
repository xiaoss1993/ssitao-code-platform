package com.ssitao.code.modular.iam.testdata;

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
     * @return 生成数量
     */
    int generateIamData(IamDataTypeEnum type, int count);

    /**
     * 生成全部 IAM 模块测试数据
     *
     * @param count 每个实体生成的数量
     * @return 生成的总数量
     */
    int generateAllIamData(int count);

    /**
     * 清空测试数据
     *
     * @param type 数据类型
     * @return 清空数量
     */
    int clearTestData(IamDataTypeEnum type);
}
