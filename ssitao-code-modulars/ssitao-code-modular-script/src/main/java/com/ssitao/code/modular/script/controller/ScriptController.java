package com.ssitao.code.modular.script.controller;

import com.tweb.commons.controller.SimpleGenericEntityController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.tweb.frame.authorization.annotation.Authorize;
import com.tweb.commons.entity.param.QueryParamEntity;
import com.tweb.commons.controller.message.ResponseMessage;
import com.ssitao.code.modular.script.entity.ScriptEntity;
import com.ssitao.code.modular.script.service.ScriptExecutorService;
import com.ssitao.code.modular.script.service.ScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 动态脚本
 *
 *
 */
@RestController
@RequestMapping("${tweb.web.mappings.script:script}")
@Authorize(permission = "script", description = "动态脚本管理")
@Api(value = "动态脚本",tags = "动态脚本管理")
public class ScriptController implements SimpleGenericEntityController<ScriptEntity, String, QueryParamEntity> {

    private ScriptService scriptService;

    private ScriptExecutorService scriptExecutorService;

    @Autowired
    public void setScriptService(ScriptService scriptService) {
        this.scriptService = scriptService;
    }

    @Autowired
    public void setScriptExecutorService(ScriptExecutorService scriptExecutorService) {
        this.scriptExecutorService = scriptExecutorService;
    }

    @Override
    public ScriptService getService() {
        return scriptService;
    }


    @GetMapping("/{id}/execute")
    @ApiOperation("执行脚本")
    @Authorize(action = "execute", description = "执行脚本")
    public ResponseMessage<Object> executeForGet(@PathVariable String id, @RequestParam(required = false) Map<String, Object> parameters) throws Exception {
        if (parameters == null) {
            parameters = new HashMap<>();
        }
        Object result = scriptExecutorService.execute(id, parameters);
        return ResponseMessage.ok(result);
    }


    @RequestMapping(value = "/{id}/execute", method = {RequestMethod.POST, RequestMethod.PUT})
    @Authorize(action = "execute", description = "执行脚本")
    @ApiOperation("执行脚本")
    public ResponseMessage<Object> executeFotPostAndPut(@PathVariable String id,
                                                        @RequestBody(required = false) Map<String, Object> parameters) throws Exception {
        return ResponseMessage.ok(executeForGet(id, parameters));
    }
}
