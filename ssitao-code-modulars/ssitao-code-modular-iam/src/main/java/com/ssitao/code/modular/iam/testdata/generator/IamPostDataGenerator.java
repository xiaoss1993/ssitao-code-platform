package com.ssitao.code.modular.iam.testdata.generator;

import cn.hutool.core.util.RandomUtil;
import com.ssitao.code.modular.iam.organization.dal.dataobject.IamPostDO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 岗位测试数据生成器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Component
public class IamPostDataGenerator {

    private static final String[] POST_NAMES = {"Java开发工程师", "Python开发工程师", "前端开发工程师", "产品经理", "UI设计师", "测试工程师", "运维工程师", "数据分析师", "算法工程师", "项目经理"};

    /**
     * 生成岗位数据
     *
     * @param tenantId 租户ID
     * @param count    数量
     * @return 岗位列表
     */
    public List<IamPostDO> generate(String tenantId, int count) {
        List<IamPostDO> posts = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            IamPostDO post = IamPostDO.builder()
                    .postId(generateId())
                    .postCode("POST" + RandomUtil.randomNumbers(5))
                    .postName(RandomUtil.randomEle(POST_NAMES) + (i + 1))
                    .postType("TECHNICAL")
                    .postStatus(1)
                    .postSort(i * 10)
                    .postDesc("测试岗位描述" + (i + 1))
                    .tenantId(tenantId)
                    .createTime(LocalDateTime.now())
                    .isDeleted(0)
                    .version(0)
                    .build();
            posts.add(post);
        }
        return posts;
    }

    private String generateId() {
        return "POST" + cn.hutool.core.lang.UUID.fastUUID().toString(true).substring(0, 8);
    }
}
