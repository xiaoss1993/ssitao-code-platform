package com.ssitao.code;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@MapperScan(basePackages = {
    "com.ssitao.code.modular.**.mapper",
    "com.ssitao.code.frame.mybatisflex.core.row"
})
public class SsitaoCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsitaoCodeApplication.class, args);
    }
}
