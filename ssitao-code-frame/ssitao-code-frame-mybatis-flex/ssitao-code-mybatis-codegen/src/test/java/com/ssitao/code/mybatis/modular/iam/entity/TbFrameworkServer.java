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
 * 服务器维护 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_framework_server")
public class TbFrameworkServer extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String id;

    /**
     * 服务器名称
     */
    private String serverName;

    /**
     * 服务器ip
     */
    private String serverIp;

    /**
     * 服务器端口
     */
    private String serverPort;

    /**
     * 剩余内存
     */
    private String serverSurplusMemory;

    /**
     * 服务器密码
     */
    private String serverPassword;

    /**
     * 安装服务标签
     */
    private String serverLabel;

    /**
     * 上架状态
     */
    private String serverShelves;

    /**
     * 服务器密钥
     */
    private String serverKey;

    /**
     * 创建类型
     */
    private String buildType;

    /**
     * 服务器用户名
     */
    private String serverUsername;

    /**
     * 连接状态
     */
    private String serverConnStatus;

    /**
     * 标准内存
     */
    private String memory;

    /**
     * 作用域
     */
    private String scope;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 状态
     */
    private String state;

    /**
     * 上架时间
     */
    private String onlineTime;

    /**
     * 服务器类型
     */
    private String serverType;

    /**
     * 上架人姓名
     */
    private String onlineUserName;

    /**
     * 上架人id
     */
    private String onlineUserId;

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
