package com.ssitao.code.modular.meta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 元数据/低代码管理模块启动类
 */
@SpringBootApplication(scanBasePackages = "com.ssitao.code.modular.meta")
public class MetaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetaApplication.class, args);
    }

}
