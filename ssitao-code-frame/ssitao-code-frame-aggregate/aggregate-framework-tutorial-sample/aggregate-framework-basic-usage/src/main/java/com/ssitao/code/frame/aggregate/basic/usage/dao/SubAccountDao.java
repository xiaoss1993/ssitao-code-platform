package com.ssitao.code.frame.aggregate.basic.usage.dao;


import com.ssitao.code.frame.aggregate.basic.usage.entity.SubAccount;
import com.ssitao.code.frame.aggregate.dao.DomainObjectDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Nervose.Wu
 * @date 2023/6/26 14:33
 */
public interface SubAccountDao extends DomainObjectDao<SubAccount,Long> {

    List<SubAccount> findByParentId(@Param("parentId") Long parentId);
}
