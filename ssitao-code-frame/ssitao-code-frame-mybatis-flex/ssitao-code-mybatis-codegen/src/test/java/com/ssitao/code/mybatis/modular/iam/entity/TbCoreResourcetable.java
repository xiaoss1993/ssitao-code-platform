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
 * 资源 实体类。
 *
 * @author SSiztao
 * @since 2026-02-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_resourcetable")
public class TbCoreResourcetable extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String tbCoreResourcetableId;

    /**
     * 节点信息
     */
    private String resourcetableNodeinfo;

    /**
     * 节点信息类型
     */
    private String resourcetableNodeinfotype;

    /**
     * 表名称
     */
    private String resourcetableTablename;

    /**
     * 表编码
     */
    private String resourcetableTablecode;

    /**
     * 表类型
     */
    private String resourcetableType;

    /**
     * 注解
     */
    private String resourcetableTablenote;

    /**
     * 是否已创建
     */
    private String resourcetableIscreate;

    /**
     * 是否存在外键
     */
    private String resourcetableIsuseforeignkey;

    /**
     * 使用初始化功能
     */
    private String resourcetableUsefunc;

    /**
     * 拥有子表
     */
    private String resourcetableChildtablecodes;

    /**
     * 备注
     */
    private String resourcetableRemark;

    /**
     * 模块名称
     */
    private String resourcetableModulename;

    /**
     * 模块编码
     */
    private String resourcetableModulecode;

    /**
     * 所属父表
     */
    private String resourcetableParenttablecodes;

    /**
     * 原表名
     */
    private String resourcetableOldtablecode;

    /**
     * 主键编码
     */
    private String resourcetablePkcode;

    /**
     * 初始化流程信息
     */
    private String resourcetableImplwf;

    /**
     * 视图sql
     */
    private String resourcetableSql;

    /**
     * 树形多根
     */
    private String resourcetableMoreroot;

    /**
     * 是否是导入的表
     */
    private String resourcetableImport;

    /**
     * 视图表信息
     */
    private String resourcetableTablesinfo;

    /**
     * 表坐标
     */
    private String resourcetableCoordinate;

    /**
     * 图标
     */
    private String resourcetableIcon;

    /**
     * 主键生成策略_name
     */
    private String resourcetableKeyGeneratorName;

    /**
     * 主键生成策略
     */
    private String resourcetableKeyGeneratorType;

    /**
     * 主键生成查询sql
     */
    private String resourcetableKeyGeneratorSql;

    /**
     * 序列名称
     */
    private String resourcetableIncrementerName;

    /**
     * 存储数据条数
     */
    private Integer resourcetableCcsjts;

    /**
     * 计算存储数据条数时间
     */
    private String resourcetableJsccsjtssj;

    /**
     * 视图关系说明
     */
    private String resourcetableRelationDesc;

    /**
     * 字段命名规则
     */
    private String resourcetableColumnNameRole;

    /**
     * 树形排序字段
     */
    private String syTreeorderindex;

    /**
     * 父节点路径
     */
    private String syParentpath;

    /**
     * 是否禁用
     */
    private String syDisabled;

    /**
     * je核心
     */
    private String syJecore;

    /**
     * je系统
     */
    private String syJesys;

    /**
     * 所属产品id
     */
    private String syProductId;

    /**
     * 所属产品名称
     */
    private String syProductName;

    /**
     * 所属产品code
     */
    private String syProductCode;

    /**
     * 审核标记
     */
    private String syAudflag;

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
     * 修改人部门编码
     */
    private String syModifyorg;

    /**
     * 修改人部门
     */
    private String syModifyorgname;

    /**
     * 修改时间
     */
    private String syModifytime;

    /**
     * 修改人编码
     */
    private String syModifyuser;

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
     * 流程实例id
     */
    private String syPiid;

    /**
     * 流程定义id
     */
    private String syPdid;

    /**
     * 登记者所在部门id
     */
    private String syCreateorgid;

    /**
     * 登记人主键
     */
    private String syCreateuserid;

    private String syModifyorgid;

    private String syModifyuserid;

    /**
     * 登记者所在部门编码
     */
    private String syCreateorg;

    /**
     * 登记人编码
     */
    private String syCreateuser;

    /**
     * 父节点id
     */
    private String syParent;

    /**
     * 节点类型
     */
    private String syNodetype;

    /**
     * 层次
     */
    private Integer syLayer;

    /**
     * 树形路径
     */
    private String syPath;

}
