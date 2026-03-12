package com.ssitao.code.modular.iam.testdata.generator;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.ssitao.code.modular.iam.identity.dal.dataobject.IamAccountDO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 账户测试数据生成器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Component
public class IamAccountDataGenerator {

    /**
     * 生成账户数据
     *
     * @param tenantId 租户ID
     * @param count 数量
     * @return 账户列表
     */
    public List<IamAccountDO> generate(String tenantId, int count) {
        List<IamAccountDO> accounts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            IamAccountDO account = IamAccountDO.builder()
                    .accountId(generateId())
                    .accountCode("ACC" + IdUtil.getSnowflakeNextIdStr())
                    .accountName("测试用户" + (i + 1))
                    .accountPassword("$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Z5EH") // 密码: 123456
                    .accountPhone(generatePhone())
                    .accountMail("test" + (i + 1) + "@test.com")
                    .accountType("SYSTEM")
                    .accountSource("LOCAL")
                    .accountStatus(1)
                    .accountIsAdmin(0)
                    .accountInitPassword(1)
                    .tenantId(tenantId)
                    .createTime(LocalDateTime.now())
                    .isDeleted(0)
                    .version(0)
                    .build();
            accounts.add(account);
        }
        return accounts;
    }

    private String generateId() {
        return "ACC" + IdUtil.getSnowflakeNextIdStr();
    }

    private String generatePhone() {
        String[] prefixes = {"138", "139", "150", "151", "152", "188", "189"};
        return RandomUtil.randomEle(prefixes) + RandomUtil.randomNumbers(8);
    }
}
