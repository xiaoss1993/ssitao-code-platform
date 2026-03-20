package com.ssitao.code.frame.aggregate.spring.xid;

import com.xfvape.uid.UidGenerator;
import com.ssitao.code.frame.aggregate.xid.UUIDGenerator;

public class DefaultUUIDGenerator implements UUIDGenerator {
    private UidGenerator uidGenerator;

    public DefaultUUIDGenerator(UidGenerator uidGenerator) {
        this.uidGenerator = uidGenerator;
    }

    @Override
    public String generate() {
        long value = this.uidGenerator.getUID();
        return Long.toHexString(value);
    }
}
