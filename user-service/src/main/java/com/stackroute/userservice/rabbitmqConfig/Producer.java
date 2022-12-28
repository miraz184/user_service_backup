package com.stackroute.userservice.rabbitmqConfig;

import com.stackroute.userservice.entity.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private RabbitTemplate rabbitTemplate;
    private MessageConfig messageConfig;

    @Autowired
    public Producer(RabbitTemplate rabbitTemplate, MessageConfig messageConfig){
        super();
        this.rabbitTemplate = rabbitTemplate;
        this.messageConfig = messageConfig;
    }

    public void sendMessageToRabbitmq(User user){
        rabbitTemplate.convertAndSend(messageConfig.EXCHANGE, messageConfig.ROUTING_KEY, user);
    }

}
