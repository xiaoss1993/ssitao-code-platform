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
 * 产品资源申请 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("product_resource_application")
public class ProductResourceApplication extends BaseEntity implements Serializable {

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
     * 预估用户数
     */
    private String estimatedNumberUsers;

    /**
     * 预估同时在线数
     */
    private String estimatedNumberSimultaneousOnline;

    /**
     * 数据库类型
     */
    private String databaseType;

    /**
     * 用途
     */
    private String purpose;

    /**
     * 使用时间
     */
    private String usageTime;

    /**
     * 紧急程度
     */
    private String degreeUrgency;

    /**
     * 项目仓库地址
     */
    private String projectWarehouseAddress;

    /**
     * 申请人
     */
    private String applicant;

    /**
     * 申请时间
     */
    private String applicationTime;

    /**
     * 申请人电话
     */
    private String applicantTelephone;

    /**
     * 申请人邮箱
     */
    private String applicantEmail;

    /**
     * 是否邮件通知
     */
    private String emailNotification;

    /**
     * 处理状态
     */
    private String processingStatus;

    /**
     * 备注信息
     */
    private String applicationBzxx;

    /**
     * 产品类型
     */
    private String applicationType;

    /**
     * jar构建路径
     */
    private String applicationJargjlj;

    /**
     * 堆内存
     */
    private String applicationDnc;

    /**
     * 代码分支
     */
    private String applicationDmfz;

    /**
     * 产品端口号
     */
    private String applicationCpdkh;

    /**
     * 配置中心应用名
     */
    private String applicationPzzxyym;

    /**
     * 反馈信息
     */
    private String applicationFkxx;

    /**
     * 修改人
     */
    private String modifier;

    /**
     * 修改时间
     */
    private String modificationTime;

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
     * 集团公司名称
     */
    private String syJtgsmc;

    /**
     * 集团公司id
     */
    private String syJtgsid;

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
