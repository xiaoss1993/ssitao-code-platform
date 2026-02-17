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
 * 应用资源 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_framework_application_resource")
public class TbFrameworkApplicationResource extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String id;

    /**
     * 数据库实例id
     */
    private String resourceDatabaseInstanceId;

    /**
     * 产品部署目录
     */
    private String productDeployDirectory;

    /**
     * 应用端口号
     */
    private String productPort;

    /**
     * 应用日志目录
     */
    private String productLog;

    /**
     * 创建类型
     */
    private String buildType;

    /**
     * 代码仓库地址
     */
    private String codeWarehouseAddress;

    /**
     * 仓库用户名
     */
    private String warehouseUsername;

    /**
     * 仓库密码
     */
    private String warehousePassword;

    /**
     * 仓库分支
     */
    private String warehouseBranch;

    /**
     * 产品id
     */
    private String productId;

    /**
     * 产品编码
     */
    private String productCode;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * jar构建路径
     */
    private String jarBuildPath;

    /**
     * 上架状态
     */
    private String resourceUsable;

    /**
     * 备注
     */
    private String resourceBz;

    /**
     * 使用堆内存
     */
    private String resourceUseMemory;

    /**
     * 数据库
     */
    private String resourceDatabaseInstanceName;

    /**
     * license持久化
     */
    private String resourceLicensePvc;

    /**
     * 持久化pvc
     */
    private String resourcePvc;

    /**
     * 服务运行状态
     */
    private String serviceOperatorStatus;

    /**
     * license镜像
     */
    private String resourceLicenseImage;

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
     * 审核标记
     */
    private String syAudflag;

    /**
     * 登记部门主键
     */
    private String syCreateorgid;

    /**
     * 登记部门编码
     */
    private String syCreateorg;

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
     * 登记人编码
     */
    private String syCreateuser;

    /**
     * 登记人
     */
    private String syCreateusername;

    /**
     * 数据状态
     */
    private String syStatus;

    /**
     * 排序字段
     */
    private Integer syOrderindex;

    /**
     * 流程实例id
     */
    private String syPiid;

    /**
     * 流程定义id
     */
    private String syPdid;

    /**
     * 集团公司名称
     */
    private String syJtgsmc;

    /**
     * 集团公司id
     */
    private String syJtgsid;

}
