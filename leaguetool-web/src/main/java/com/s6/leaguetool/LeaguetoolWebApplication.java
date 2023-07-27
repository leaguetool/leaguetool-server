package com.s6.leaguetool;

import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableNacosConfig
@EnableAsync
@MapperScan(basePackages = "com.s6.leaguetool.mapper")
public class LeaguetoolWebApplication {

    public static void main(String[] args) {
        //启动http server
        SpringApplication.run(LeaguetoolWebApplication.class, args);
    }
}


