


package com.ssitao.code.modular.oauth2.server.dao;

import com.ssitao.code.modular.oauth2.server.entity.AuthorizationCodeEntity;
import com.tweb.commons.dao.InsertDao;
import com.tweb.commons.dao.dynamic.DeleteByEntityDao;
import com.tweb.commons.dao.dynamic.QueryByEntityDao;

/**
 *
 */
public interface AuthorizationCodeDao extends
        InsertDao<AuthorizationCodeEntity>,
        DeleteByEntityDao,
        QueryByEntityDao<AuthorizationCodeEntity> {

}
