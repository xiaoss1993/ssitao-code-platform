
package com.ssitao.code.frame.mybatisflex.core.keygen;

import com.ssitao.code.frame.mybatisflex.core.exception.FlexExceptions;
import com.ssitao.code.frame.mybatisflex.core.exception.locale.LocalizedFormats;
import com.ssitao.code.frame.mybatisflex.core.keygen.impl.FlexIDKeyGenerator;
import com.ssitao.code.frame.mybatisflex.core.keygen.impl.SnowFlakeIDKeyGenerator;
import com.ssitao.code.frame.mybatisflex.core.keygen.impl.ULIDKeyGenerator;
import com.ssitao.code.frame.mybatisflex.core.keygen.impl.UUIDKeyGenerator;
import com.ssitao.code.frame.mybatisflex.core.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

public class KeyGeneratorFactory {

    private KeyGeneratorFactory() {
    }

    private static final Map<String, IKeyGenerator> KEY_GENERATOR_MAP = new HashMap<>();

    static {
        /** 内置了 uuid 的生成器，因此主键配置的时候可以直接配置为 @Id(keyType = KeyType.Generator, value = "uuid")
         * {@link com.ssitao.code.frame.mybatisflex.annotation.Id}
         */
        register(KeyGenerators.uuid, new UUIDKeyGenerator());
        register(KeyGenerators.flexId, new FlexIDKeyGenerator());
        register(KeyGenerators.snowFlakeId, new SnowFlakeIDKeyGenerator());
        register(KeyGenerators.ulid, new ULIDKeyGenerator());

    }


    /**
     * 获取 主键生成器
     *
     * @param name
     * @return 主键生成器
     */
    public static IKeyGenerator getKeyGenerator(String name) {
        if (StringUtil.noText(name)) {
            throw FlexExceptions.wrap(LocalizedFormats.KEY_GENERATOR_BLANK);
        }
        return KEY_GENERATOR_MAP.get(name.trim());
    }


    /**
     * 注册一个主键生成器
     *
     * @param key
     * @param keyGenerator
     */
    public static void register(String key, IKeyGenerator keyGenerator) {
        KEY_GENERATOR_MAP.put(key.trim(), keyGenerator);
    }

}
