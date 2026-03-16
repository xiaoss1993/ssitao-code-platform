package com.ssitao.code.modular.script.service;

import java.util.Map;

/**
 *
 *
 */
public interface ScriptExecutorService {
    Object execute(String id, Map<String, Object> parameters) throws Exception;
}
