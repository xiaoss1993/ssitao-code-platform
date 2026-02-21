
package com.ssitao.code.frame.mybatisflex.core.keygen;

import com.ssitao.code.frame.mybatisflex.core.FlexConsts;
import com.ssitao.code.frame.mybatisflex.core.util.CollectionUtil;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;

import java.sql.Statement;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 多实体主键生成器，用于批量插入的场景
 */
public class MultiEntityKeyGenerator implements KeyGenerator {

    private final KeyGenerator keyGenerator;

    public MultiEntityKeyGenerator(KeyGenerator keyGenerator) {
        this.keyGenerator = keyGenerator;
    }

    @Override
    public void processBefore(Executor executor, MappedStatement ms, Statement stmt, Object parameter) {
        Collection<Object> entities = (Collection<Object>) ((Map) parameter).get(FlexConsts.ENTITIES);
        if (CollectionUtil.isNotEmpty(entities)) {
            for (Object entity : entities) {
                ((Map) parameter).put(FlexConsts.ENTITY, entity);
                keyGenerator.processBefore(executor, ms, stmt, parameter);
            }
        }
    }


    @Override
    public void processAfter(Executor executor, MappedStatement ms, Statement stmt, Object parameter) {
        // do nothing
        // 多条数据批量插入的场景下，不支持后设置主键
        // 比如 INSERT INTO `tb_account`(uuid,name,sex) VALUES (?, ?, ?), (?, ?, ?)
    }

}
