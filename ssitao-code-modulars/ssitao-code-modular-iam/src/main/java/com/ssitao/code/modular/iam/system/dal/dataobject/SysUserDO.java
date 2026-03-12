package com.ssitao.code.modular.iam.system.dal.dataobject;

import com.ssitao.code.frame.mybatisflex.annotation.Id;
import com.ssitao.code.frame.mybatisflex.annotation.KeyType;
import com.ssitao.code.frame.mybatisflex.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 系统用户数据对象
 * 对应表：sys_user
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table("sys_user")
public class SysUserDO {

    /**
     * 用户ID
     */
    @Id(keyType = KeyType.Auto)
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String picUrl;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 用户性别(0=女,1=男)
     */
    private Integer gender;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 职位ID
     */
    private Long positionId;

    /**
     * 职位名称
     */
    private String positionName;

    /**
     * 角色ID(多个角色用逗号分隔)
     */
    private String roleIds;

    /**
     * 皮肤主题
     */
    private String skin;

    /**
     * 主题模式
     */
    private String theme;

    /**
     * 用户状态(0=禁用,1=正常)
     */
    private Integer status;

    /**
     * 删除标志(0=正常,1=删除)
     */
    private Integer deleted;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 最后登录IP
     */
    private String lastLoginIp;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建者ID
     */
    private Long createId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新者ID
     */
    private Long updateId;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
