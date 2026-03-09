package com.ssitao.code.modular.meta.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Column;
import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 字典类型数据对象
 * 对应表：core_dictionary
 *
 * @author ssitao-code
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("core_dictionary")
public class MetaDictionaryDO {

    /**
     * 字典ID
     */
    @Id(keyType = KeyType.None)
    @Column("dict_id")
    private String dictId;

    /**
     * 字典编码
     */
    @Column("dict_code")
    private String dictCode;

    /**
     * 字典名称
     */
    @Column("dict_name")
    private String dictName;

    /**
     * 字典类型: SYSTEM-系统, BUSINESS-业务
     */
    @Column("dict_type")
    private String dictType;

    /**
     * 字典来源: CUSTOM-自定义, SQL-SQL, API-接口
     */
    @Column("dict_source")
    private String dictSource;

    /**
     * 字典SQL
     */
    @Column("dict_sql")
    private String dictSql;

    /**
     * 字典API
     */
    @Column("dict_api")
    private String dictApi;

    /**
     * 字典配置(JSON)
     */
    @Column("dict_config")
    private String dictConfig;

    /**
     * 字典状态: 0-停用, 1-启用
     */
    @Column("dict_status")
    private Integer dictStatus;

    /**
     * 是否内置: 0-否, 1-是
     */
    @Column("dict_is_builtin")
    private Integer dictIsBuiltin;

    /**
     * 排序号
     */
    @Column("dict_sort")
    private Integer dictSort;

    /**
     * 字典描述
     */
    @Column("dict_desc")
    private String dictDesc;

    /**
     * 租户ID
     */
    @Column("tenant_id")
    private String tenantId;

    /**
     * 创建时间
     */
    @Column("create_time")
    private LocalDateTime createTime;

    /**
     * 创建人ID
     */
    @Column("create_user_id")
    private String createUserId;

    /**
     * 修改时间
     */
    @Column("modify_time")
    private LocalDateTime modifyTime;

    /**
     * 修改人ID
     */
    @Column("modify_user_id")
    private String modifyUserId;

    /**
     * 是否删除: 0-否, 1-是
     */
    @Column("is_deleted")
    private Integer isDeleted;

    /**
     * 版本号
     */
    private Integer version;
}
