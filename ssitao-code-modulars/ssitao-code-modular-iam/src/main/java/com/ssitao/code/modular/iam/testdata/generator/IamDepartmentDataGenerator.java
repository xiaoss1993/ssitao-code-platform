package com.ssitao.code.modular.iam.testdata.generator;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.IdUtil;
import com.ssitao.code.modular.iam.organization.dal.dataobject.IamDepartmentDO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 部门测试数据生成器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Component
public class IamDepartmentDataGenerator {

    private static final String[] DEPT_NAMES = {"研发部", "产品部", "设计部", "市场部", "销售部", "财务部", "人力资源部", "行政部", "技术部", "运营部"};

    /**
     * 生成部门树形结构数据
     *
     * @param companyIds 公司ID列表
     * @ 租户IDparam tenantId
     * @param count      生成数量
     * @return 部门列表
     */
    public List<IamDepartmentDO> generateTree(List<String> companyIds, String tenantId, int count) {
        List<IamDepartmentDO> departments = new ArrayList<>();

        // 生成顶级部门
        int topCount = Math.max(count / 3, 1);
        for (int i = 0; i < topCount; i++) {
            String deptId = generateId();
            IamDepartmentDO dept = IamDepartmentDO.builder()
                    .deptId(deptId)
                    .deptCode("DEPT" + IdUtil.getSnowflakeNextIdStr())
                    .deptName(RandomUtil.randomEle(DEPT_NAMES) + (i + 1))
                    .deptType("DEPARTMENT")
                    .deptParentId("0")
                    .deptCompanyId(RandomUtil.randomEle(companyIds))
                    .deptPhone("400-" + RandomUtil.randomNumbers(7))
                    .deptStatus(1)
                    .deptSort(i * 10)
                    .deptTreePath("0," + deptId)
                    .deptTreeLevel(1)
                    .tenantId(tenantId)
                    .createTime(LocalDateTime.now())
                    .isDeleted(0)
                    .version(0)
                    .build();
            departments.add(dept);

            // 生成二级部门
            int childCount = RandomUtil.randomInt(2, 5);
            for (int j = 0; j < childCount; j++) {
                String childId = generateId();
                IamDepartmentDO child = IamDepartmentDO.builder()
                        .deptId(childId)
                        .deptCode("DEPT" + IdUtil.getSnowflakeNextIdStr())
                        .deptName(RandomUtil.randomEle(DEPT_NAMES) + (j + 1))
                        .deptType("DEPARTMENT")
                        .deptParentId(dept.getDeptId())
                        .deptCompanyId(dept.getDeptCompanyId())
                        .deptPhone("400-" + RandomUtil.randomNumbers(7))
                        .deptStatus(1)
                        .deptSort(j * 10)
                        .deptTreePath(dept.getDeptTreePath() + "," + childId)
                        .deptTreeLevel(2)
                        .tenantId(tenantId)
                        .createTime(LocalDateTime.now())
                        .isDeleted(0)
                        .version(0)
                        .build();
                departments.add(child);
            }
        }

        return departments;
    }

    private String generateId() {
        return "DEPT" + cn.hutool.core.lang.UUID.fastUUID().toString(true).substring(0, 8);
    }
}
