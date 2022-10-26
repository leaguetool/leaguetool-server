package com.s6.leaguetoolserver;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;

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
                            .moduleName("user"); // 设置父包模块名
//                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\code\\leaguetool-server\\src\\main\\java\\com\\s6\\leaguetoolserver\\server\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder()
                            //开启 lombok 模型
                            .enableLombok()
                            //开启生成实体时生成字段注解
                            .enableTableFieldAnnotation()
                            //逻辑删除字段名(数据库)
                            .logicDeleteColumnName("delete")
                            //逻辑删除属性名(实体)
                            .logicDeletePropertyName("delete")
                            //全局主键类型
                            .idType(IdType.ASSIGN_UUID)
                            //添加表字段填充
                            .addTableFills(new Column("create_time", FieldFill.INSERT))
                            .addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE))
                            .formatFileName("%sEntity");
                    builder.addInclude("league_user"); // 设置需要生成的表名
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板

                .execute();
    }
}
