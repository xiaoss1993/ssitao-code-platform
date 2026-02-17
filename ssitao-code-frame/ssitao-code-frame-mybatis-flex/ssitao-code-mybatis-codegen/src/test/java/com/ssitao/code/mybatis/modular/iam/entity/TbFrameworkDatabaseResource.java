package com.ssitao.code.frame.mybatisflex.codegen.test.modular.iam.entity;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import com.ssitao.code.frame.mybatisflex.codegen.test.BaseEntity;
import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;

/**
 * 数据库资源 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_framework_database_resource")
public class TbFrameworkDatabaseResource extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String id;

    /**
     * 资源目录
     */
    private String resourceDirectory;

    /**
     * 资源日志目录
     */
    private String resourceLog;

    /**
     * 服务器id
     */
    private String serverId;

    /**
     * 数据库管理员用户名
     */
    private String databaseManageUsername;

    /**
     * 数据库管理员密码
     */
    private String databaseManagePassword;

    /**
     * 服务器名称
     */
    private String serverName;

    /**
     * 数据库类型
     */
    private String databaseType;

    /**
     * 创建类型
     */
    private String buildType;

    /**
     * 初始化状态
     */
    private String initializationStatus;

    /**
     * 初始化人
     */
    private String initializationUsername;

    /**
     * 初始化时间
     */
    private String initializationTime;

    /**
     * 数据库名称
     */
    private String databaseName;

    /**
     * 数据库端口
     */
    private String databasePort;

    /**
     * 是否可用
     */
    private String resourceUsable;

    /**
     * 备注
     */
    private String resourceBz;

    /**
     * 上架状态
     */
    private String resourceShelves;

    /**
     * 上架人_id
     */
    private String resourceOnlineuserId;

    /**
     * 上架人_name
     */
    private String resourceOnlineuserName;

    /**
     * 备注信息
     */
    private String resourceRemark;

    /**
     * 上架时间
     */
    private String resourceOnlineTime;

    /**
     * 登记部门主键
     */
    private String syCreateorgid;

    /**
     * 登记部门
     */
    private String syCreateorgname;

    /**
     * 登记时间
     */
    private String syCreatetime;

    /**
     * 登记人主键
     */
    private String syCreateuserid;

    /**
     * 登记人
     */
    private String syCreateusername;

    /**
     * 修改人部门主键
     */
    private String syModifyorgid;

    /**
     * 修改人部门
     */
    private String syModifyorgname;

    /**
     * 修改人主键
     */
    private String syModifyuserid;

    /**
     * 修改人
     */
    private String syModifyusername;

    /**
     * 修改时间
     */
    private String syModifytime;

    /**
     * 数据状态
     */
    private String syStatus;

}
