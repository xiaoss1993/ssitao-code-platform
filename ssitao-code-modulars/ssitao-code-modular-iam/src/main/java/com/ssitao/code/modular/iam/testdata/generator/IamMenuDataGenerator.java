package com.ssitao.code.modular.iam.testdata.generator;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.IdUtil;
import com.ssitao.code.modular.iam.menu.dal.dataobject.IamMenuDO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 菜单测试数据生成器
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Component
public class IamMenuDataGenerator {

    private static final String[] MENU_NAMES = {"用户管理", "角色管理", "部门管理", "菜单管理", "权限管理", "公司管理", "岗位管理", "字典管理", "系统配置", "日志管理"};
    private static final String[] COMPONENTS = {"system/user/index", "system/role/index", "system/dept/index", "system/menu/index", "system/permission/index"};

    /**
     * 生成菜单树形结构数据
     *
     * @param tenantId 租户ID
     * @param count    数量
     * @return 菜单列表
     */
    public List<IamMenuDO> generateTree(String tenantId, int count) {
        List<IamMenuDO> menus = new ArrayList<>();

        // 生成顶级菜单（目录）
        int dirCount = Math.min(count / 3, 10);
        for (int i = 0; i < dirCount; i++) {
            String menuId = generateId();
            IamMenuDO menu = IamMenuDO.builder()
                    .menuId(menuId)
                    .menuCode("MENU" + IdUtil.getSnowflakeNextIdStr())
                    .menuName(RandomUtil.randomEle(MENU_NAMES))
                    .menuType("DIRECTORY")
                    .menuParentId("0")
                    .menuPath("/" + RandomUtil.randomString(6).toLowerCase())
                    .menuIcon(RandomUtil.randomEle(Arrays.asList("user", "role", "dept", "menu", "setting")))
                    .menuSort(i * 100)
                    .menuIsVisible(1)
                    .menuIsCached(0)
                    .menuIsAffix(0)
                    .menuStatus(1)
                    .menuIsBuiltin(0)
                    .menuTreePath("0," + menuId)
                    .menuTreeLevel(1)
                    .tenantId(tenantId)
                    .createTime(LocalDateTime.now())
                    .isDeleted(0)
                    .version(0)
                    .build();
            menus.add(menu);

            // 为每个顶级菜单生成子菜单
            int childCount = RandomUtil.randomInt(2, 5);
            for (int j = 0; j < childCount; j++) {
                String childId = generateId();
                IamMenuDO child = IamMenuDO.builder()
                        .menuId(childId)
                        .menuCode("MENU" + IdUtil.getSnowflakeNextIdStr())
                        .menuName(RandomUtil.randomEle(MENU_NAMES))
                        .menuType("MENU")
                        .menuParentId(menuId)
                        .menuPath("/" + RandomUtil.randomString(6).toLowerCase())
                        .menuComponent(RandomUtil.randomEle(COMPONENTS))
                        .menuIcon(RandomUtil.randomEle(Arrays.asList("user", "role", "dept", "menu", "setting")))
                        .menuSort(j * 10)
                        .menuIsVisible(1)
                        .menuIsCached(0)
                        .menuIsAffix(0)
                        .menuStatus(1)
                        .menuIsBuiltin(0)
                        .menuTreePath("0," + menuId + "," + childId)
                        .menuTreeLevel(2)
                        .tenantId(tenantId)
                        .createTime(LocalDateTime.now())
                        .isDeleted(0)
                        .version(0)
                        .build();
                menus.add(child);

                // 生成按钮
                if (RandomUtil.randomBoolean()) {
                    String btnId = generateId();
                    IamMenuDO btn = IamMenuDO.builder()
                            .menuId(btnId)
                            .menuCode("BTN" + IdUtil.getSnowflakeNextIdStr())
                            .menuName(RandomUtil.randomEle(Arrays.asList("新增", "编辑", "删除", "导出", "导入")))
                            .menuType("BUTTON")
                            .menuParentId(childId)
                            .menuPermission(RandomUtil.randomString(6).toLowerCase() + ":" + RandomUtil.randomEle(Arrays.asList("add", "edit", "delete", "export", "import")))
                            .menuSort(0)
                            .menuStatus(1)
                            .menuIsBuiltin(0)
                            .menuTreePath("0," + menuId + "," + childId + "," + btnId)
                            .menuTreeLevel(3)
                            .tenantId(tenantId)
                            .createTime(LocalDateTime.now())
                            .isDeleted(0)
                            .version(0)
                            .build();
                    menus.add(btn);
                }
            }
        }

        return menus;
    }

    private String generateId() {
        return "MENU" + cn.hutool.core.lang.UUID.fastUUID().toString(true).substring(0, 8);
    }
}
