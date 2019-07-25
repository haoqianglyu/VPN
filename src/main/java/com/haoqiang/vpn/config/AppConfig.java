package com.haoqiang.vpn.config;


import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.haoqiang.vpn.service.StorageService;
import org.springframework.context.annotation.*;


@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = "com.haoqiang.vpn",
        excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,pattern = "com.haoqiang.vpn.api.*"))
public class AppConfig {

    @Bean
    public StorageService getStorageServiceBean(){
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain()).build();
        StorageService ss = new StorageService(s3Client);
        ss.setBucket("vpnlyu-dev");
        return ss;
    }

    @Bean
    public AmazonSQS getAmazonSQS(){
        AmazonSQS client = AmazonSQSClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain()).build();
        return client;
    }

}
