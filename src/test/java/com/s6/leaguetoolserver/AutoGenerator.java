package com.s6.leaguetoolserver;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class AutoGenerator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://49.232.68.210:3307/leaguetool?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8&autoReconnect=true&useSSL=true&allowMultiQueries=true", "root", "cailong520")
                .globalConfig(builder -> {
                    builder.author("cailong") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D:\\code\\leaguetool-server\\src\\main\\java\\"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.s6.leaguetoolserver.server") // 设置父包名
                            .moduleName("userinfo"); // 设置父包模块名
//                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\code\\leaguetool-server\\src\\main\\java\\com\\s6\\leaguetoolserver\\server\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("league_user_info"); // 设置需要生成的表名
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
