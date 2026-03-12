
package com.ssitao.code.frame.mybatisflex.core.keygen.impl;

import com.ssitao.code.frame.mybatisflex.core.keygen.IKeyGenerator;

import java.util.UUID;

public class UUIDKeyGenerator implements IKeyGenerator {

    @Override
    public Object generate(Object entity, String keyColumn) {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
