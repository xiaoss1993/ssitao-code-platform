


package com.ssitao.code.modular.oauth2.model;

import com.tweb.commons.model.Model;

/**
 *
 *
 */
public class AuthorizationCodeModel implements Model {
    private String code;
    private String state;
    private String redirectUri;

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
