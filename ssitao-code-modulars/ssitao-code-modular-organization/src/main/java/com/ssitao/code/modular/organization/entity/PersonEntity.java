
package com.ssitao.code.modular.organization.entity;

import com.ssitao.code.modular.iam.authorization.access.UserAttachEntity;
import com.ssitao.code.modular.common.entity.GenericEntity;
import com.ssitao.code.modular.organization.authorization.access.PersonAttachEntity;

/**
 * 人员 实体
 *
 *
 */
public interface PersonEntity extends GenericEntity<String>, PersonAttachEntity, UserAttachEntity {
  /*------------------------------------------
    |               属性名常量               |
    =========================================*/
    /**
     * 姓名
     */
    String name   = "name";
    /**
     * 性别
     */
    String sex    = "sex";
    /**
     * 电子邮箱
     */
    String email  = "email";
    /**
     * 联系电话
     */
    String phone  = "phone";
    /**
     * 照片
     */
    String photo  = "photo";
    /**
     * 关联用户id
     */
    String userId = "userId";
    /**
     * 状态
     */
    String status = "status";
    /**
     * 备注
     */
    String remark = "remark";

    /**
     * @return 姓名
     */
    String getName();

    /**
     * 设置 姓名
     */
    void setName(String name);

    /**
     * @return 性别
     */
    Byte getSex();

    /**
     * 设置 性别
     */
    void setSex(Byte sex);

    /**
     * @return 电子邮箱
     */
    String getEmail();

    /**
     * 设置 电子邮箱
     */
    void setEmail(String email);

    /**
     * @return 联系电话
     */
    String getPhone();

    /**
     * 设置 联系电话
     */
    void setPhone(String phone);

    /**
     * @return 照片
     */
    String getPhoto();

    /**
     * 设置 照片
     */
    void setPhoto(String photo);

    /**
     * @return 关联用户id
     */
    String getUserId();

    /**
     * 设置 关联用户id
     */
    void setUserId(String userId);

    /**
     * @return 状态
     */
    Byte getStatus();

    /**
     * 设置 状态
     */
    void setStatus(Byte status);

    /**
     * @return 备注
     */
    String getRemark();

    /**
     * 设置 备注
     */
    void setRemark(String remark);

    @Override
    default String getPersonId() {
        return getId();
    }

    @Override
    default void setPersonId(String personId) {
        setId(personId);
    }

    @Override
    default String getPersonIdProperty() {
        return "id";
    }
}
