
package com.ssitao.code.modular.organization.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import com.ssitao.code.modular.common.entity.SimpleGenericEntity;

/**
 * 人员
 *
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimplePersonEntity extends SimpleGenericEntity<String> implements PersonEntity {
    private static final long serialVersionUID = -4232153898188508965L;
    //姓名
    @NotBlank
    private String name;
    //性别
    private Byte sex;
    //电子邮箱
    @Email
    private String email;
    //联系电话
    private String phone;
    //照片
    private String photo;
    //关联用户id
    private String userId;
    //状态
    private Byte status;
    //备注
    private String remark;


}
