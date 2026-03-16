package com.ssitao.code.modular.dashboard.service.strategy;

import com.ssitao.code.modular.dashboard.service.DashBoardConfigEntity;
import com.ssitao.code.modular.dashboard.service.DashBoardExecutorStrategy;
import lombok.SneakyThrows;
import com.ssitao.code.expands.script.engine.DynamicScriptEngine;
import com.ssitao.code.expands.script.engine.DynamicScriptEngineFactory;
import com.ssitao.code.frame.ezorm.rdb.executor.SqlExecutor;
import com.ssitao.code.frame.authorization.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ScriptExecutorStrategy implements DashBoardExecutorStrategy {

    @Autowired
    private SqlExecutor sqlExecutor;

    static List<String> supportLang = Arrays.asList("js", "javascript", "groovy", "sql");

    @Override
    public boolean support(DashBoardConfigEntity entity) {
        return StringUtils.hasText(entity.getScriptLanguage()) && StringUtils.hasText(entity.getScript())
                && supportLang.contains(entity.getScriptLanguage());
    }

    @Override
    @SneakyThrows
    public Object execute(DashBoardConfigEntity entity, Authentication authentication) {
        Map<String, Object> scriptContext = new HashMap<>();

        scriptContext.put("autz", authentication);

        if ("sql".equals(entity.getScriptLanguage())) {
            return sqlExecutor.list(entity.getScript(), scriptContext);
        }

        DynamicScriptEngine engine = DynamicScriptEngineFactory.getEngine(entity.getScriptLanguage());
        if (engine != null) {
            String id = DigestUtils.md5DigestAsHex(entity.getScript().getBytes());

            if (!engine.compiled(id)) {
                engine.compile(id, entity.getScript());
            }

            return engine.execute(id, scriptContext).getIfSuccess();
        }

        return null;
    }
}
