package com.haoqiang.vpn.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "com.haoqiang.vpn",
        excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,pattern = "com.haoqiang.vpn.api.*"))
public class AppConfig {

}
