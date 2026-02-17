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
 * 应用安装信息 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_framework_application_install")
public class TbFrameworkApplicationInstall extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String id;

    /**
     * 服务器名称
     */
    private String installServerName;

    /**
     * 安装实例名称
     */
    private String installInstanceName;

    /**
     * 使用内存
     */
    private String installUseMemory;

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
     * 安装目录
     */
    private String installDeployDirectory;

    /**
     * 日志目录
     */
    private String installLog;

    /**
     * 创建类型
     */
    private String installBuildType;

    /**
     * 应用id
     */
    private String installApplicationId;

    /**
     * 服务器id
     */
    private String installServerId;

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

    /**
     * 排序字段
     */
    private Integer syOrderindex;

}
