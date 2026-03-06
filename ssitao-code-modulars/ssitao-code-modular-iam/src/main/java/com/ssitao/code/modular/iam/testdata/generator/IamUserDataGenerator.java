package com.ssitao.code.modular.iam.testdata.generator;

import cn.hutool.core.util.RandomUtil;
import com.ssitao.code.modular.iam.userprofile.dal.dataobject.IamUserDO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 用户测试数据生成器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Component
public class IamUserDataGenerator {

    private static final List<String> SEX_LIST = Arrays.asList("MALE", "FEMALE", "UNKNOWN");
    private static final List<String> STATUS_LIST = Arrays.asList("ON_JOB", "RESIGN", "probation");
    private static final List<String> EMPLOYMENT_TYPE_LIST = Arrays.asList("FULL_TIME", "PART_TIME", "INTERNSHIP", "OUTSOURCING");
    private static final List<String> EDUCATION_LIST = Arrays.asList("博士", "硕士", "本科", "大专", "高中", "初中");

    private static final String[] SURNAMES = {"王", "李", "张", "刘", "陈", "杨", "黄", "赵", "周", "吴", "徐", "孙", "马", "朱", "胡", "郭", "何", "高", "林", "罗"};
    private static final String[] NAMES = {"伟", "芳", "娜", "秀英", "敏", "静", "丽", "强", "磊", "军", "洋", "勇", "艳", "杰", "涛", "明", "超", "秀兰", "霞", "平"};

    /**
     * 生成随机用户数据
     *
     * @param deptIds  部门ID列表（用于关联）
     * @param postIds  岗位ID列表（用于关联）
     * @param tenantId 租户ID
     * @return 用户数据对象
     */
    public IamUserDO generate(List<String> deptIds, List<String> postIds, String tenantId) {
        IamUserDO user = IamUserDO.builder()
                .userCode("U" + RandomUtil.randomNumbers(8))
                .userName(generateRandomName())
                .userSex(RandomUtil.randomEle(SEX_LIST))
                .userBirthday(generateRandomBirthday())
                .userPhone(generateRandomPhone())
                .userMail(RandomUtil.randomString(6).toLowerCase() + "@test.com")
                .userNation("汉族")
                .userMaritalStatus(RandomUtil.randomBoolean() ? "MARRIED" : "SINGLE")
                .userPoliticalStatus(RandomUtil.randomEle(Arrays.asList("群众", "党员", "团员")))
                .userWorkNumber("W" + RandomUtil.randomNumbers(6))
                .userEntryDate(generateRandomEntryDate())
                .userEmploymentType(RandomUtil.randomEle(EMPLOYMENT_TYPE_LIST))
                .userEducation(RandomUtil.randomEle(EDUCATION_LIST))
                .userStatus(RandomUtil.randomEle(STATUS_LIST))
                .tenantId(tenantId)
                .createTime(LocalDateTime.now())
                .isDeleted(0)
                .version(0)
                .build();

        // 关联部门
        if (deptIds != null && !deptIds.isEmpty()) {
            user.setCreateOrgId(RandomUtil.randomEle(deptIds));
        }

        return user;
    }

    private String generateRandomName() {
        return RandomUtil.randomEle(SURNAMES) + RandomUtil.randomEle(NAMES);
    }

    private LocalDate generateRandomBirthday() {
        int age = RandomUtil.randomInt(18, 60);
        return LocalDate.now().minusYears(age).minusMonths(RandomUtil.randomInt(0, 12)).minusDays(RandomUtil.randomInt(0, 28));
    }

    private String generateRandomPhone() {
        String[] prefixes = {"138", "139", "150", "151", "152", "188", "189"};
        return RandomUtil.randomEle(prefixes) + RandomUtil.randomNumbers(8);
    }

    private LocalDate generateRandomEntryDate() {
        return LocalDate.now().minusMonths(RandomUtil.randomInt(0, 36));
    }
}
