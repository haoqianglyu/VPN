package com.haoqiang.vpn.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author Haoqiang Lyu
 * @date 2019-07-24 11:03
 */

@Service
public class MessageSQSService {

    private AmazonSQS sqs;

    //@Value("${jms.queue.name}")
    private String sqsName = System.getProperty("jms.queue.name");

    private String queueUrl;

    public MessageSQSService(@Autowired AmazonSQS sqs){
        this.sqs = sqs;
        this.queueUrl = getQueueUrl(sqsName);
    }

    public void sendMessage(String s,int delay){
        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(s)
                .withDelaySeconds(delay);
        sqs.sendMessage(send_msg_request);
    }

    public String getQueueUrl(String queueName){
        String queueUrl = sqs.getQueueUrl(queueName).getQueueUrl();
        return queueUrl;
    }

}
