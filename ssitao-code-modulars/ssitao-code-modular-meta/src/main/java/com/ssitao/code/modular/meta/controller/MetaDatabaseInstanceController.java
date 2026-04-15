package com.ssitao.code.modular.meta.controller;

import com.ssitao.code.modular.meta.application.MetaDatabaseInstanceApplicationService;
import com.ssitao.code.modular.meta.application.MetaDatabaseInstanceQueryService;
import com.ssitao.code.modular.meta.application.MetaDatabaseResourceApplicationService;
import com.ssitao.code.modular.meta.application.MetaDatabaseResourceQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据库实例管理
 */
@RestController
@RequestMapping("/meta/database-instance")
@Tag(name = "数据源实例管理")
@RequiredArgsConstructor
public class MetaDatabaseInstanceController {

    private final MetaDatabaseInstanceQueryService          metaDatabaseInstanceQueryService;

    private final MetaDatabaseInstanceApplicationService    metaDatabaseInstanceApplicationService;

    private final MetaDatabaseResourceApplicationService    metaDatabaseResourceApplicationService;

    private final MetaDatabaseResourceQueryService          metaDatabaseResourceQueryService;
}
