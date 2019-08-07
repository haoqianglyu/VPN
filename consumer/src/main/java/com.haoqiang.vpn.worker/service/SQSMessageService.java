package com.haoqiang.vpn.worker.service;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;

import java.util.List;
import java.util.Map;

/**
 * @author Haoqiang Lyu
 * @date 2019-07-30 11:41
 */

public class SQSMessageService {
    private AmazonSQS sqsClient = AmazonSQSClientBuilder.standard().withCredentials(new DefaultAWSCredentialsProviderChain()).build();

    private String queueUrl = "https://sqs.us-east-1.amazonaws.com/546257999623/vpn-dev";

    public void receiveMessage(){
        System.out.println("receiving messgae from queue");
        List<Message> messages = sqsClient.receiveMessage(queueUrl).getMessages();
        for (final Message message : messages){
            System.out.println("Message");
            System.out.println("  MessageId:     " + message.getMessageId());
            System.out.println("  ReceiptHandle: " + message.getReceiptHandle());
            System.out.println("  MD5OfBody:     " + message.getMD5OfBody());
            System.out.println("  Body:          " + message.getBody());
            Map<String,String> messageAttributes = message.getAttributes();
            for (final Map.Entry<String, String> entry : messageAttributes.entrySet()) {
                System.out.println("Attribute");
                System.out.println("  Name:  " + entry.getKey());
                System.out.println("  Value: " + entry.getValue());
            }
            sqsClient.deleteMessage(queueUrl,message.getReceiptHandle());
        }
    }
}
