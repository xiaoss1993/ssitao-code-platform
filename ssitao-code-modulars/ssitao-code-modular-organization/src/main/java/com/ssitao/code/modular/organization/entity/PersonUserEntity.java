
package com.ssitao.code.modular.organization.entity;

import com.ssitao.code.modular.common.entity.Entity;

import java.util.Set;

/**
 *
 */
public class PersonUserEntity implements Entity {
    private static final long serialVersionUID = -2619415787107625818L;
    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
