package com.ssitao.code.modular.iam.testdata.generator;

import cn.hutool.core.util.RandomUtil;
import com.ssitao.code.modular.iam.organization.dal.dataobject.IamCompanyDO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 公司测试数据生成器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Component
public class IamCompanyDataGenerator {

    private static final String[] COMPANY_NAMES = {"阿里巴巴", "腾讯科技", "百度在线", "京东集团", "美团点评", "字节跳动", "网易公司", "小米科技", "华为技术", "滴滴出行"};

    /**
     * 生成公司数据
     *
     * @param tenantId 租户ID
     * @param count    数量
     * @return 公司列表
     */
    public List<IamCompanyDO> generate(String tenantId, int count) {
        List<IamCompanyDO> companies = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            IamCompanyDO company = IamCompanyDO.builder()
                    .companyId(generateId())
                    .companyCode("CO" + RandomUtil.randomNumbers(6))
                    .companyName(RandomUtil.randomEle(COMPANY_NAMES) + (i + 1) + "分公司")
                    .companyType("COMPANY")
                    .companyParentId("0")
                    .companyPhone("400-" + RandomUtil.randomNumbers(7))
                    .companyAddress("测试地址" + (i + 1))
                    .companyStatus(1)
                    .companySort(i * 10)
                    .companyTreePath("0,")
                    .companyTreeLevel(1)
                    .tenantId(tenantId)
                    .createTime(LocalDateTime.now())
                    .isDeleted(0)
                    .version(0)
                    .build();
            companies.add(company);
        }
        return companies;
    }

    private String generateId() {
        return "CO" + cn.hutool.core.lang.UUID.fastUUID().toString(true).substring(0, 8);
    }
}
