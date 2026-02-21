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
 * 产品部署管理 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("product_deployment")
public class ProductDeployment extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Id
    private String id;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品编码
     */
    private String productCode;

    /**
     * 产品id
     */
    private String productId;

    /**
     * 紧急程度
     */
    private String degreeUrgency;

    /**
     * 要求开始时间
     */
    private String requiredStartTime;

    /**
     * 要求结束时间
     */
    private String requiredEndTime;

    /**
     * 申请人
     */
    private String applicant;

    /**
     * 申请时间
     */
    private String applicationTime;

    /**
     * 申请人邮箱
     */
    private String applicantEmail;

    /**
     * 测试负责人
     */
    private String testLeader;

    /**
     * 测试负责人邮箱
     */
    private String emailAddressTestPrincipal;

    /**
     * 功能负责人
     */
    private String functionalDirector;

    /**
     * 功能负责人邮箱
     */
    private String emailAddressFunctionLeader;

    /**
     * 处理状态
     */
    private String processingStatus;

    /**
     * 是否异常
     */
    private String abnormal;

    /**
     * 升级概要
     */
    private String upgradeSummary;

    /**
     * 升级信息
     */
    private String upgradeInformation;

    /**
     * 影响范围
     */
    private String scopeInfluence;

    /**
     * 异常信息
     */
    private String abnormalInformation;

    /**
     * 备注信息
     */
    private String remarkInformation;

    /**
     * 申请人电话
     */
    private String applicantPhone;

    /**
     * 产品类型
     */
    private String deploymentType;

    /**
     * 反馈信息
     */
    private String deploymentFkxx;

    /**
     * 配置中心应用名
     */
    private String deploymentPzzxyym;

    /**
     * 产品端口号
     */
    private String deploymentCpdkh;

    /**
     * 堆内存
     */
    private String deploymentDnc;

    /**
     * 代码分支
     */
    private String deploymentDmfz;

    /**
     * jar构建路径
     */
    private String deploymentJargjlj;

    /**
     * 审核标记
     */
    private String syAudflag;

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
     * 流程实例id
     */
    private String syPiid;

    /**
     * 流程定义id
     */
    private String syPdid;

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
