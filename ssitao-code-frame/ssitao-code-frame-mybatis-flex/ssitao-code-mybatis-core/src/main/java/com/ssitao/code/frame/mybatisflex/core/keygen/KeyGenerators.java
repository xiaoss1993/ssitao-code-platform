
package com.ssitao.code.frame.mybatisflex.core.keygen;

public class KeyGenerators {

    private KeyGenerators() {
    }

    /**
     * uuid 主键生成器
     * {@link com.ssitao.code.frame.mybatisflex.core.keygen.impl.UUIDKeyGenerator}
     */
    public static final String uuid = "uuid";

    /**
     * flexId 主键生成器
     * {@link com.ssitao.code.frame.mybatisflex.core.keygen.impl.FlexIDKeyGenerator}
     */
    public static final String flexId = "flexId";

    /**
     * 雪花算法主键生成器
     * {@link com.ssitao.code.frame.mybatisflex.core.keygen.impl.SnowFlakeIDKeyGenerator}
     */
    public static final String snowFlakeId = "snowFlakeId";

    /**
     * ulid 主键生成器
     * {@link com.ssitao.code.frame.mybatisflex.core.keygen.impl.ULIDKeyGenerator}
     */
    public static final String ulid = "ulid";
}
