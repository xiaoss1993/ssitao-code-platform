package com.ssitao.code.modular.iam.service;

import com.ssitao.code.frame.mybatisflex.core.query.QueryWrapper;

import java.util.List;

/**
 * 数据权限服务接口
 *
 * @author ssitao-code
 * @since 1.1.1
 */
public interface DataScopeService {

    /**
     * 为查询条件添加数据权限过滤
     *
     * @param queryWrapper 查询条件
     * @param deptIdColumn 部门ID字段名（默认为 dept_id）
     * @param userIdColumn 用户ID字段名（默认为 user_id 或 creator）
     */
    void filterDataScope(QueryWrapper queryWrapper, String deptIdColumn, String userIdColumn);

    /**
     * 获取用户的数据权限部门ID列表
     *
     * @param userId 用户ID
     * @return 部门ID列表
     */
    List<Long> getDataScopeDeptIds(Long userId);

    /**
     * 获取用户的数据权限范围
     *
     * @param userId 用户ID
     * @return 数据权限范围
     */
    DataScope getUserDataScope(Long userId);

    /**
     * 为查询条件添加数据权限过滤（使用默认字段名）
     *
     * @param queryWrapper 查询条件
     */
    void filterDataScope(QueryWrapper queryWrapper);

    /**
     * 数据权限范围枚举
     */
    enum DataScope {
        ALL(1, "全部数据"),
        DEPT_AND_CHILD(2, "本部门及子部门"),
        DEPT(3, "本部门"),
        SELF(4, "仅本人"),
        CUSTOM(5, "自定义部门");

        private final Integer code;
        private final String name;

        DataScope(Integer code, String name) {
            this.code = code;
            this.name = name;
        }

        public static DataScope fromCode(Integer code) {
            for (DataScope scope : values()) {
                if (scope.code.equals(code)) {
                    return scope;
                }
            }
            return null;
        }

        public Integer getCode() {
            return code;
        }

        public String getName() {
            return name;
        }
    }

}
