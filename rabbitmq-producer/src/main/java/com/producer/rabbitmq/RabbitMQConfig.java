package com.producer.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMQConfig {
    
    //queue
    //exchange
    //their binding
    //messageconverter
    //rabbitmqtemplate

    @Bean
    Queue basicQueue(){
        return new Queue("basicQueue",false);
    }

    @Bean
    DirectExchange exchange(){
        return new DirectExchange("globalExchange");
    }

    @Bean
    Binding queueBinding(Queue basicQueue, DirectExchange exchange){
        return BindingBuilder.bind(basicQueue).to(exchange).with("basicRoutingKey");
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    public AmqpTemplate rabbiTemplate(ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
