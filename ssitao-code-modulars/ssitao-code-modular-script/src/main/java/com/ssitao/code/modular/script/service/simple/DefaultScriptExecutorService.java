package com.ssitao.code.modular.script.service.simple;

import com.ssitao.code.modular.script.service.ScriptExecutorService;
import org.apache.commons.codec.digest.DigestUtils;
import com.tweb.expands.script.engine.DynamicScriptEngine;
import com.tweb.expands.script.engine.DynamicScriptEngineFactory;
import com.tweb.expands.script.engine.ScriptContext;
import com.ssitao.code.modular.script.entity.ScriptEntity;
import com.ssitao.code.modular.script.service.ScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 *
 */
public class DefaultScriptExecutorService implements ScriptExecutorService {

    @Autowired
    private ScriptService scriptService;

    public void setScriptService(ScriptService scriptService) {
        this.scriptService = scriptService;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Object execute(String id, Map<String, Object> parameters) throws Exception {
        ScriptEntity scriptEntity = scriptService.selectByPk(id);
        if (scriptEntity==null){
            return null;
        }
        DynamicScriptEngine engine = DynamicScriptEngineFactory.getEngine(scriptEntity.getLanguage());

        String scriptId = "dynamicScript-" + id;
        String scriptMd5 = DigestUtils.md5Hex(scriptEntity.getScript());

        ScriptContext context = engine.getContext(scriptId);

        if (context == null || !context.getMd5().equals(scriptMd5)) {
            engine.compile(scriptId, scriptEntity.getScript());
        }

        return engine.execute(scriptId, parameters).getIfSuccess();
    }
}
