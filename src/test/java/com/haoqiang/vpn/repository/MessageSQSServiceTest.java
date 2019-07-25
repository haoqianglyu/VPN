package com.haoqiang.vpn.repository;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.haoqiang.vpn.config.AppConfig;
import com.haoqiang.vpn.service.MessageSQSService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Haoqiang Lyu
 * @date 2019-07-24 11:33
 */
@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
@WebAppConfiguration
public class MessageSQSServiceTest {
    @Autowired
    private MessageSQSService messageSQSService;

    @Test
    public void sendMessageTest(){
        //AmazonSQS client = AmazonSQSClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain()).build();
        //messageSQSService = new MessageSQSService(client);
        messageSQSService.sendMessage("test",5);
    }

}
