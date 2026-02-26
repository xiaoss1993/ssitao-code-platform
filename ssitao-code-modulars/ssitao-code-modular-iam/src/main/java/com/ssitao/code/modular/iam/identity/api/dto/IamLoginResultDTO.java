package com.ssitao.code.modular.iam.identity.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * IAM登录结果DTO
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "IAM登录结果DTO")
public class IamLoginResultDTO {

    @Schema(description = "账号信息")
    private IamAccountDTO account;

    @Schema(description = "用户信息（前端兼容）")
    private UserInfo userInfo;

    @Schema(description = "访问令牌")
    private String accessToken;

    @Schema(description = "刷新令牌")
    private String refreshToken;

    @Schema(description = "令牌类型")
    private String tokenType = "Bearer";

    @Schema(description = "令牌过期时间(秒)")
    private Long expiresIn;

    @Schema(description = "租户ID")
    private String tenantId;

    /**
     * 用户信息（前端兼容格式）
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "用户信息")
    public static class UserInfo {
        @Schema(description = "用户ID")
        private String id;

        @Schema(description = "用户名")
        private String username;

        @Schema(description = "真实姓名")
        private String realName;

        @Schema(description = "昵称")
        private String nickname;

        @Schema(description = "头像")
        private String avatar;

        @Schema(description = "邮箱")
        private String email;

        @Schema(description = "手机号")
        private String phone;

        @Schema(description = "部门名称")
        private String deptName;

        @Schema(description = "岗位名称")
        private String postName;

        @Schema(description = "角色列表")
        private List<String> roles;

        @Schema(description = "权限列表")
        private List<String> permissions;

        @Schema(description = "仪表盘类型")
        private String dashboard;

        @Schema(description = "租户ID")
        private String tenantId;
    }
}
