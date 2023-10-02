package com.skinairvalve.sz;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

/**
 * @create on 2023/9/6-6:09 PM
 */
public class MyBatisPlusGenerator {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost/device_test", username = "zhangwei", password = "zhangwei";
        String author = "zw";
        String javaOutput = "/Users/zhangwei/github/device-test/src/main/java";
        String xmlOutput = "/Users/zhangwei/github/device-test/src/main/resources/mappers";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author(author) // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(javaOutput); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder.parent("com.skinairvalve.sz") // 设置父包名
//                            .moduleName("dao") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, xmlOutput)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("sz_data_record"); // 设置需要生成的表名
                    builder.controllerBuilder()
                            .enableFileOverride()
                            .mapperBuilder()
                            .enableFileOverride()
                            .enableBaseResultMap()
                            .serviceBuilder()
                            .entityBuilder()
                            .enableFileOverride();
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
