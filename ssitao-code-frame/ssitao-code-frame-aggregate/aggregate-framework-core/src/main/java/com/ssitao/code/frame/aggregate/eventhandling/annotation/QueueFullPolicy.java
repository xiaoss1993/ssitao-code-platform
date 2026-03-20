package com.ssitao.code.frame.aggregate.eventhandling.annotation;

public enum QueueFullPolicy {
    SYNCHRONOUS,
    ENQUEUE,
    DISCARD;
}
