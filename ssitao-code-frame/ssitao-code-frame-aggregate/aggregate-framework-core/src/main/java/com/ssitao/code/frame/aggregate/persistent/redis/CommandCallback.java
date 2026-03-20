package com.ssitao.code.frame.aggregate.persistent.redis;

public interface CommandCallback<T> {
    T execute(RedisCommands commands);
}
