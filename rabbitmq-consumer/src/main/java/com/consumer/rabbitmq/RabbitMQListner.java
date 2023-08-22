package com.consumer.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListner {
    
    @RabbitListener(queues = "basicQueue")
    public void listenMessage(String message){
        System.out.println("*********************************message received*********************");
        System.out.println(message);
        System.out.println("**********************************************************************");
    }
}
