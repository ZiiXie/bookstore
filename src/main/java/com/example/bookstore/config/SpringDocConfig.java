package com.example.bookstore.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                // 配置接口文档基本信息
                .info(this.getApiInfo())
                ;
    }

    private Info getApiInfo() {
        return new Info()
                // 配置文档标题
                .title("bookstore")
                // 配置文档描述
                .description("bookstore sample")
                // 配置作者信息
                .contact(new Contact().name("xxx").url("https://www.xxx.cn").email("xxx@163.com"))
                // 配置License许可证信息
                .license(new License().name("Apache 2.0").url("https://www.xxx.cn"))
                // 概述信息
                .summary("bookstore sample")
                .termsOfService("https://www.xxx.cn")
                // 配置版本号
                .version("1.0");
    }
}
