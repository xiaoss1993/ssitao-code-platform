
package com.ssitao.code.frame.mybatisflex.core.mybatis.executor;

import com.ssitao.code.frame.mybatisflex.core.FlexConsts;
import org.apache.ibatis.cache.CacheKey;

import java.util.Arrays;
import java.util.Map;

public interface CacheKeyBuilder {

    default CacheKey buildCacheKey(CacheKey cacheKey, Object parameterObject) {
        if (parameterObject instanceof Map && ((Map) parameterObject).containsKey(FlexConsts.SQL_ARGS)) {
            cacheKey.update(Arrays.toString((Object[]) ((Map<?, ?>) parameterObject).get(FlexConsts.SQL_ARGS)));
        }
        return cacheKey;
    }

}
