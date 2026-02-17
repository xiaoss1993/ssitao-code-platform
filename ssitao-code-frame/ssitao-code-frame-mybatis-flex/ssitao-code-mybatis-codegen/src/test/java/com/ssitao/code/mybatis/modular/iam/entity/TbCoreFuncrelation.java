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
 * 子功能 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_funcrelation")
public class TbCoreFuncrelation extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String tbCoreFuncrelationId;

    /**
     * 功能名称
     */
    private String funcrelationName;

    /**
     * 功能编码
     */
    private String funcrelationCode;

    /**
     * 实体全限定名
     */
    private String funcrelationEntityname;

    /**
     * 过滤条件
     */
    private String funcrelationFiltersql;

    /**
     * 依赖类型
     */
    private String funcrelationRelyontype;

    /**
     * 是否树形功能
     */
    private String funcrelationIstreefunc;

    /**
     * 表名
     */
    private String funcrelationTablename;

    /**
     * 系统模式
     */
    private String funcrelationSysmode;

    /**
     * 产品扩展功能id
     */
    private String funcrelationNewfuncid;

    /**
     * 控制显隐
     */
    private String funcrelationInterpreter;

    /**
     * 功能外键
     */
    private String funcrelationFuncinfoId;

    /**
     * 是否启用
     */
    private String funcrelationEnabled;

    /**
     * 个性化显示方式
     */
    private String funcrelationShowtype;

    /**
     * 功能主键
     */
    private String funcrelationFuncid;

    /**
     * 级联复制
     */
    private String funcrelationCopy;

    /**
     * 定位
     */
    private String funcrelationLocate;

    /**
     * 功能英文名
     */
    private String funcrelationNameEn;

    /**
     * 高度
     */
    private String funcrelationHeight;

    /**
     * 显示标题
     */
    private String funcrelationShowtitle;

    /**
     * 分组
     */
    private String funcrelationGroup;

    /**
     * 图标
     */
    private String funcrelationIcon;

    /**
     * saas产品
     */
    private String funcrelationSaasPid;

    /**
     * 主从关系
     */
    private String funcrelationZcgx;

    /**
     * 登记者所在部门id
     */
    private String syCreateorgid;

    /**
     * 登记人id
     */
    private String syCreateuserid;

    /**
     * 修改人部门id
     */
    private String syModifyorgid;

    /**
     * 修改人id
     */
    private String syModifyuserid;

    /**
     * 租户_id
     */
    private String syTenantId;

    /**
     * 租户名称
     */
    private String syTenantName;

    /**
     * 登记者所在部门
     */
    private String syCreateorgname;

    /**
     * 登记时间
     */
    private String syCreatetime;

    /**
     * 登记人
     */
    private String syCreateusername;

    /**
     * 是否启用本条数据
     */
    private String syFlag;

    /**
     * 修改人部门
     */
    private String syModifyorgname;

    /**
     * 修改时间
     */
    private String syModifytime;

    /**
     * 修改人
     */
    private String syModifyusername;

    /**
     * 数据状态
     */
    private String syStatus;

    /**
     * 排序字段
     */
    private Integer syOrderindex;

    /**
     * 拼音简写
     */
    private String syPyjz;

    /**
     * 拼音全称
     */
    private String syPyqc;

    /**
     * 表单上传虚字段
     */
    private String syFormuploadfiles;

}
