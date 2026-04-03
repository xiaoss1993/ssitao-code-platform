package com.ssitao.code.starter.web.controller;

import org.springframework.web.bind.annotation.GetMapping;

public interface ServiceSaveController {
    String prefix = "/";

    @GetMapping("/add")
    default String  addView(){
        return prefix+"add";
    }

    @GetMapping("/edit")
    default String  editView(){
        return prefix+"edit";
    }

}
