package com.ssitao.code.modular.codegen.controller;

import com.ssitao.code.common.core.domain.AjaxResult;
import com.ssitao.code.starter.web.controller.ServiceQueryController;
import com.ssitao.code.starter.web.controller.ServiceSaveController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/codegen/template-group")
@Slf4j
public class CodeGenTemplateGroupController implements ServiceQueryController, ServiceSaveController {

    private String prefix = "/codegen/templategroup/";

    @Override
    public String getPrefix() {
        return this.prefix;
    }

    @RequestMapping("/page")
    @ResponseBody
    public AjaxResult   queryPage(){

        return AjaxResult.success();
    }


    @RequestMapping("/getById")
    @ResponseBody
    public AjaxResult   getById(Long id){
        return AjaxResult.success();
    }


    @RequestMapping("/save")
    @ResponseBody
    public AjaxResult   save(Long id){
        return AjaxResult.success();
    }

    @RequestMapping("/edit")
    @ResponseBody
    public AjaxResult   edit(Long id){
        return AjaxResult.success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public AjaxResult   delete(Long id){
        return AjaxResult.success();
    }
}
