package com.producer.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {
    
    private final AmqpTemplate amqpTemplate;
    public RabbitMQSender(
        AmqpTemplate amqpTemplate
    ){
        this.amqpTemplate = amqpTemplate;
    }
    public boolean sendMessage(String message){
        try {
            this.amqpTemplate.convertAndSend("globalExchange","basicRoutingKey",message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
