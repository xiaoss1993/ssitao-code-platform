


package com.ssitao.code.modular.oauth2.server.dao;

import com.ssitao.code.modular.oauth2.server.entity.OAuth2AccessEntity;
import com.tweb.commons.dao.InsertDao;
import com.tweb.commons.dao.dynamic.DeleteByEntityDao;
import com.tweb.commons.dao.dynamic.QueryByEntityDao;
import com.tweb.commons.dao.dynamic.UpdateByEntityDao;

/**
 *
 */
public interface OAuth2AccessDao extends
        InsertDao<OAuth2AccessEntity>,
        DeleteByEntityDao,
        UpdateByEntityDao,
        QueryByEntityDao<OAuth2AccessEntity> {
}
