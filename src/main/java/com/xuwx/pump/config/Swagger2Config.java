package com.xuwx.pump.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *@ClassName Swagger2Config
 *@Description Swagger2API文档的配置
 *@Author wx.Xu
 *Date 2020/7/17 15:39
 *Version 1.0
 */
@Configuration
@EnableSwagger2
@EnableKnife4j //knife4j 增强功能开启
public class Swagger2Config {

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("PUMP - SwaggerUI演示").description("PUMP").contact("xuwx").version("1.0.0").build();
    }

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xuwx.pump.controller"))
                //为有@Api注解的Controller生成API文档
                //.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                //为有@ApiOperation注解的方法生成API文档
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }


}
