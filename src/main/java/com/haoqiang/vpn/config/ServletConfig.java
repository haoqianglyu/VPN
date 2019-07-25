package com.haoqiang.vpn.config;

import com.haoqiang.vpn.config.viewresolver.JsonViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Haoqiang Lyu
 * @date 2019-06-12 12:08
 */

@Configuration
@EnableWebMvc
@ComponentScan("com.haoqiang.vpn.api")
public class ServletConfig  implements WebMvcConfigurer {

    @Override
    public  void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }

    @Bean
    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager){
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(manager);

        List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
        resolvers.add(new JsonViewResolver());
        //resolvers.add(new XmlViewResolver());

        resolver.setViewResolvers(resolvers);
        return resolver;

    }

    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getMultipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSize(10008439);
        return resolver;
    }
}
