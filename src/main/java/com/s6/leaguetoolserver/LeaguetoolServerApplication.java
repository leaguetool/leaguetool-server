package com.s6.leaguetoolserver;

import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableNacosConfig
@MapperScan(basePackages = "com.s6.leaguetoolserver.server")
public class LeaguetoolServerApplication {

    public static void main(String[] args) {
        //启动http server
        SpringApplication.run(LeaguetoolServerApplication.class, args);
    }

}
