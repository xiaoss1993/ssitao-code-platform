package com.ssitao.code.common.constant;

/**
 * 通用常量定义
 *
 * @author ssitao
 * @since 1.0.0
 */
public class ConstantVars {

    /**
     * 空字符串
     */
    public static final String BLANK_STR = "";

    /**
     * 默认结果名称
     */
    public static final String DEFAULT_RESULT_NAME = "rows";

    /**
     * 树形结果名称
     */
    public static final String TREE_RESULT_NAME = "children";

    /**
     * 根组织编码
     */
    public static final String TOP_ORG = "ROOT";

    /**
     * 树根编码
     */
    public static final String TREE_ROOT = "ROOT";

    /**
     * 缓存键前缀 - 数据字典名称
     */
    public static final String CACHE_DDNAME_PREFIX = "DD_NAME_";

    /**
     * 缓存键前缀 - 数据字典编码
     */
    public static final String CACHE_DDCODE_PREFIX = "DD_CODE_";

    /**
     * 缓存键前缀 - 数据字典编码树
     */
    public static final String CACHE_DD_CODE_TREE_PREFIX = "DD_CODE_TREE_";

    /**
     * 缓存键前缀 - 数据字典项
     */
    public static final String CACHE_DD_ITEM_PREFIX = "DDITEM_";

    // ==================== 数据库类型 ====================

    /**
     * Oracle数据库
     */
    public static final String STR_ORACLE = "ORACLE";

    /**
     * SQL Server数据库
     */
    public static final String STR_SQLSERVER = "SQLSERVER";

    /**
     * MySQL数据库
     */
    public static final String STR_MYSQL = "MYSQL";

    /**
     * 神通数据库
     */
    public static final String STR_SHENTONG = "OSCAR";

    /**
     * 人大金仓数据库
     */
    public static final String STR_KINGBASEES = "KINGBASE8";

    /**
     * 达梦数据库
     */
    public static final String STR_DM = "DM";

    /**
     * TiDB数据库
     */
    public static final String STR_TIDB = "TiDB";

    /**
     * PostgreSQL数据库
     */
    public static final String STR_POSTGRESQL = "POSTGRESQL";

    // ==================== 状态标记 ====================

    /**
     * 启用状态
     */
    public static final String FLAG_ENABLE = "1";

    /**
     * 禁用状态
     */
    public static final String FLAG_DISABLE = "0";

    /**
     * 正常状态
     */
    public static final String STATUS_NORMAL = "1";

    /**
     * 删除状态
     */
    public static final String STATUS_DELETED = "0";

    /**
     * 审核状态 - 未审核
     */
    public static final String AUD_STATUS_NOSTATUS = "0";

    /**
     * 审核状态 - 审核中
     */
    public static final String AUD_STATUS_AUDITING = "1";

    /**
     * 审核状态 - 已通过
     */
    public static final String AUD_STATUS_PASSED = "2";

    /**
     * 审核状态 - 已拒绝
     */
    public static final String AUD_STATUS_REJECTED = "3";

    // ==================== 用户相关 ====================

    /**
     * 超级管理员编码
     */
    public static final String SUPER_ADMIN_CODE = "admin";

    /**
     * 默认密码
     */
    public static final String DEFAULT_PASSWORD = "123456";

    // ==================== Token相关 ====================

    /**
     * Token前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * Token头
     */
    public static final String TOKEN_HEADER = "Authorization";

    /**
     * Token缓存键前缀
     */
    public static final String TOKEN_CACHE_PREFIX = "auth:token:";

    /**
     * 用户会话缓存键前缀
     */
    public static final String SESSION_CACHE_PREFIX = "auth:session:";

    // ==================== 权限相关 ====================

    /**
     * 权限缓存键前缀
     */
    public static final String PERMISSION_CACHE_PREFIX = "auth:permission:";

    /**
     * 角色缓存键前缀
     */
    public static final String ROLE_CACHE_PREFIX = "auth:role:";

    /**
     * 部门缓存键前缀
     */
    public static final String DEPT_CACHE_PREFIX = "auth:dept:";

    // ==================== 编码相关 ====================

    /**
     * UTF-8编码
     */
    public static final String UTF8 = "UTF-8";

    /**
     * GBK编码
     */
    public static final String GBK = "GBK";

    // ==================== 日期格式 ====================

    /**
     * 默认日期格式
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 默认日期时间格式
     */
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 默认时间格式
     */
    public static final String TIME_FORMAT = "HH:mm:ss";

    // ==================== HTTP相关 ====================

    /**
     * 请求头内容类型
     */
    public static final String CONTENT_TYPE = "Content-Type";

    /**
     * JSON内容类型
     */
    public static final String CONTENT_TYPE_JSON = "application/json";

    /**
     * 表单内容类型
     */
    public static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";

    /**
     * 用户代理头
     */
    public static final String USER_AGENT = "User-Agent";

    // ==================== 分隔符 ====================

    /**
     * 路径分隔符
     */
    public static final String PATH_SEPARATOR = "/";

    /**
     * 缓存分隔符
     */
    public static final String CACHE_SPLITTER = "/";

    /**
     * 逗号分隔符
     */
    public static final String COMMA = ",";

    /**
     * 冒号分隔符
     */
    public static final String COLON = ":";

    // 私有构造函数，防止实例化
    private ConstantVars() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
