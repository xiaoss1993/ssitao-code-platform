


package com.ssitao.code.modular.oauth2.server.service.simple;

import com.tweb.frame.authorization.Authentication;
import com.tweb.frame.authorization.AuthenticationManager;
import com.tweb.frame.authorization.oauth2.server.support.password.PasswordService;
import com.tweb.frame.authorization.simple.PlainTextUsernamePasswordAuthenticationRequest;
import com.tweb.commons.utils.validate.ValidationException;

/**
 *
 */
public class SimplePasswordService implements PasswordService {
    private AuthenticationManager authenticationManager;

    public SimplePasswordService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String getUserIdByUsernameAndPassword(String username, String password) {
        try {
            Authentication authenticate = authenticationManager.authenticate(new PlainTextUsernamePasswordAuthenticationRequest(username, password));
            if (null != authenticate) {
                return authenticate.getUser().getId();
            }
        } catch (ValidationException | UnsupportedOperationException | IllegalArgumentException e) {
            return null;
        }
        return null;
    }
}
