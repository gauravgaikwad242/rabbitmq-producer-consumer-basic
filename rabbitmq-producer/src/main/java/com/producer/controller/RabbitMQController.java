package com.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.producer.rabbitmq.RabbitMQSender;

@RestController
@RequestMapping("rabbitmq")
public class RabbitMQController {
    

    @Autowired
    private RabbitMQSender rabbitMQSender;

    @GetMapping("/send")
    public String sendMessage(
        @RequestParam(name = "message",required = true) String message
    ){
        System.out.println("new message received"+message);
        boolean status = this.rabbitMQSender.sendMessage(message);
        if(status){
            return "success";
        }else{
            return "failed";
        }
    }
}
