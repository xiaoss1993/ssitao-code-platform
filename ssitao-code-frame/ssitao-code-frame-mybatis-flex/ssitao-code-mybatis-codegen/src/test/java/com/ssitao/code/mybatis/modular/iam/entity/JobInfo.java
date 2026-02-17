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
 * 作业信息 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("job_info")
public class JobInfo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String id;

    /**
     * 作业名称
     */
    private String jobName;

    /**
     * 代码类型
     */
    private String codeType;

    /**
     * 代码地址
     */
    private String codeAddress;

    /**
     * 代码分支
     */
    private String codeBranch;

    /**
     * 打包命令
     */
    private String packagingCommand;

    /**
     * 最后一次构建状态
     */
    private String lastBuildStatus;

    /**
     * 最后一次成功时间
     */
    private String lastSuccessfulTime;

    /**
     * 最后一次失败时间
     */
    private String lastFailureTime;

    /**
     * 最后一次持续时间
     */
    private String lastDuration;

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
     * 凭证id
     */
    private String credentialId;

    /**
     * 备注信息
     */
    private String remarkInformation;

    /**
     * 仓库token
     */
    private String warehouseWebhookToken;

    /**
     * 应用_id
     */
    private String infoApplicationId;

    /**
     * jar上传路径
     */
    private String infoJarsclj;

    /**
     * 容器
     */
    private String container;

    /**
     * 仓库地址
     */
    private String warehouseHost;

    /**
     * 仓库命名空间
     */
    private String warehouseNamespace;

    /**
     * 仓库凭证id
     */
    private String warehouseCredentialId;

    /**
     * 仓库名字
     */
    private String warehouseName;

    /**
     * 镜像版本
     */
    private String imageVersion;

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
     * 数据状态
     */
    private String syStatus;

    /**
     * 排序字段
     */
    private Integer syOrderindex;

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

}
