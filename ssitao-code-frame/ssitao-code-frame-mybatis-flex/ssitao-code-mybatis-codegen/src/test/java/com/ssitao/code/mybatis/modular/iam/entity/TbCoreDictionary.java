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
 * 数据字典 实体类。
 *
 * @author ssitao
 * @since 1.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("tb_core_dictionary")
public class TbCoreDictionary extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String tbCoreDictionaryId;

    /**
     * 字典编码
     */
    private String dictionaryDdcode;

    /**
     * 字典名称
     */
    private String dictionaryDdname;

    /**
     * 字典类型
     */
    private String dictionaryDdtype;

    /**
     * 外部自定实体名称
     */
    private String dictionaryClassname;

    /**
     * 所属子系统
     */
    private String dictionaryBelongsto;

    /**
     * 所属子系统
     */
    private String dictionaryBelongstoname;

    /**
     * 类型
     */
    private String dictionaryDictype;

    /**
     * sql配置信息
     */
    private String dictionarySqlconfig;

    /**
     * sql
     */
    private String dictionarySql;

    /**
     * sql列表说明
     */
    private String dictionarySqllbsm;

    /**
     * sql树形说明
     */
    private String dictionarySqlsxsm;

    /**
     * 树形扩展字段
     */
    private String dictionaryFieldconfigs;

    /**
     * sql配置信息列表
     */
    private String dictionarySqlpzxxlb;

    /**
     * 查询条件
     */
    private String dictionaryWheresql;

    /**
     * 排序条件
     */
    private String dictionaryOrdersql;

    /**
     * 类名
     */
    private String dictionaryClass;

    /**
     * 方法名
     */
    private String dictionaryMethod;

    /**
     * 字典项根节点id
     */
    private String dictionaryItemrootId;

    /**
     * 字典项概要
     */
    private String dictionaryZdxgy;

    /**
     * 表、视图
     */
    private String dictionaryVtableCode;

    /**
     * 表、视图名称
     */
    private String dictionaryVtableName;

    /**
     * 过滤条件描述
     */
    private String dictionaryGltjms;

    /**
     * 排序条件描述
     */
    private String dictionaryPxtjms;

    /**
     * 方法参数
     */
    private String dictionaryFfcs;

    /**
     * 方法参数描述
     */
    private String dictionaryFfcsms;

    /**
     * sql描述
     */
    private String dictionarySqlms;

    /**
     * 数据结构_name
     */
    private String dictionarySjjgName;

    /**
     * 数据结构_code
     */
    private String dictionarySjjgCode;

    /**
     * 分隔符
     */
    private String dictionaryFgf;

    /**
     * 主键
     */
    private String dictionaryZj;

    /**
     * 编码
     */
    private String dictionaryBm;

    /**
     * 名称
     */
    private String dictionaryMc;

    /**
     * 图标样式
     */
    private String dictionaryTbys;

    /**
     * 字体颜色
     */
    private String dictionaryZtys;

    /**
     * 字体背景颜色
     */
    private String dictionaryZtbjys;

    /**
     * 树形路径
     */
    private String dictionarySxlj;

    /**
     * 节点类型
     */
    private String dictionaryJdlx;

    /**
     * 树形排序字段
     */
    private String dictionarySxpxzd;

    /**
     * 父节点
     */
    private String dictionaryFjd;

    /**
     * 父节点路径
     */
    private String dictionaryFjdlj;

    /**
     * 是否启用
     */
    private String dictionarySfqy;

    /**
     * 部门树形
     */
    private String dictionaryDepartmentWwName;

    /**
     * 部门树形_code
     */
    private String dictionaryDepartmentWwCode;

    /**
     * 所属模块路径
     */
    private String dictionarySsmklj;

    /**
     * 删除人
     */
    private String dictionaryDeleteusername;

    /**
     * 删除时间
     */
    private String dictionaryDeletetime;

    /**
     * 删除人id
     */
    private String dictionaryDeleteuserid;

    /**
     * 查询条件_sql
     */
    private String dictionaryWheresqlSql;

    /**
     * 排序条件_sql
     */
    private String dictionaryOrdersqlSql;

    /**
     * 常用
     */
    private String dictionarySfcyCode;

    /**
     * 业务字段
     */
    private String dictionaryYwzdName;

    /**
     * 业务字段编码
     */
    private String dictionaryYwzdCode;

    /**
     * je核心
     */
    private String syJecore;

    /**
     * je系统
     */
    private String syJesys;

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

}
