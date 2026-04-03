package com.ssitao.code.starter.web.controller;

import org.springframework.web.bind.annotation.GetMapping;

/**
 *  通用CRUD查询控制器接口
 * <p>基于{@link CrudService}提供了标准化的数据查询REST API接口。</p>
 * <p>支持多种查询方式：分页查询、不分页查询、统计查询、根据ID查询等。</p>
 * <p>主要功能：</p>
 * <ul>
 *     <li>GET/POST方式的分页动态查询</li>
 *     <li>GET/POST方式的不分页动态查询</li>
 *     <li>GET/POST方式的统计查询</li>
 *     <li>根据ID精确查询单个实体</li>
 *     <li>支持复杂的动态查询条件</li>
 *     <li>支持排序、分页、条件过滤</li>
 * </ul>
 * <p>查询条件支持：</p>
 * <ul>
 *     <li>简单where条件：<code>where=name is 张三</code></li>
 *     <li>复杂terms条件：支持like、eq、gt、lt等多种条件类型</li>
 *     <li>排序：<code>orderBy=id desc,name asc</code></li>
 *     <li>分页：<code>pageIndex=0&pageSize=20</code></li>
 * </ul>
 *
 *
 */
public interface ServiceQueryController{
    String prefix = "/";

    default String getPrefix(){
        return prefix;
    }
    @GetMapping("/index")
    default String  indexView(){
        return getPrefix()+"index";
    }



}
