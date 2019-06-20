package com.haoqiang.vpn.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("com.haoqiang.vpn.api")
//@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MvcConfig {
}
