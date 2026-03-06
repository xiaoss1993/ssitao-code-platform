package com.ssitao.code.modular.iam.testdata;

import com.ssitao.code.modular.iam.enums.IamDataTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 测试数据生成控制器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Slf4j
@RestController
@RequestMapping("/api/test-data")
@RequiredArgsConstructor
public class TestDataController {

    private final TestDataGeneratorService testDataGeneratorService;

    /**
     * 生成 IAM 模块测试数据
     *
     * @param type  数据类型
     * @param count 数量
     * @return 生成数量
     */
    @PostMapping("/iam/{type}")
    public int generateIamData(@PathVariable String type, @RequestParam(defaultValue = "1000") int count) {
        log.info("开始生成 IAM 测试数据, type: {}, count: {}", type, count);
        IamDataTypeEnum dataType = IamDataTypeEnum.getByCode(type);
        int result = testDataGeneratorService.generateIamData(dataType, count);
        log.info("IAM 测试数据生成完成, 数量: {}", result);
        return result;
    }

    /**
     * 生成全部测试数据
     *
     * @param count 数量
     * @return 生成数量
     */
    @PostMapping("/all")
    public int generateAllData(@RequestParam(defaultValue = "1000") int count) {
        log.info("开始生成全部测试数据, count: {}", count);
        int result = testDataGeneratorService.generateAllIamData(count);
        log.info("全部测试数据生成完成, 数量: {}", result);
        return result;
    }

    /**
     * 清空测试数据
     *
     * @param type 数据类型
     * @return 清空数量
     */
    @DeleteMapping("/clear")
    public int clearTestData(@RequestParam(required = false) String type) {
        log.info("开始清空测试数据, type: {}", type);
        IamDataTypeEnum dataType = IamDataTypeEnum.getByCode(type);
        int result = testDataGeneratorService.clearTestData(dataType);
        log.info("测试数据清空完成, 数量: {}", result);
        return result;
    }
}
