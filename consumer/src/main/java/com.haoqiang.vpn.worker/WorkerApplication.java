package com.haoqiang.vpn.worker;

import com.haoqiang.vpn.worker.service.SQSMessageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

/**
 * @author Haoqiang Lyu
 * @date 2019-07-30 11:32
 */

@SpringBootApplication
public class WorkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkerApplication.class,args);


        final Scanner input = new Scanner(System.in);

        System.out.print("Enter the queue name: ");
        final String queueName = input.nextLine();

        System.out.print("Enter the ReceiveMessage wait time (1-20 seconds): ");
        final String receiveMessageWaitTime = input.nextLine();


        SQSMessageService messageService = new SQSMessageService();
        messageService.receiveMessage();
    }

}
